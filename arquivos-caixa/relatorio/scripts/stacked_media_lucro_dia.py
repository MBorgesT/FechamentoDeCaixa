import matplotlib.pyplot as plt
import numpy as np
from datetime import datetime, timedelta
from common import imagens_path


def zero_if_none(fechamento):
	if (fechamento is None):
		return 0
	else:
		return fechamento.get_valor_total()


def gerar_imagem(dias):
	morning_values = dict()
	afternoon_values = dict()

	for i in range(6):
		morning_values[i] = [0, 0]
		afternoon_values[i] = [0, 0]

	for d in dias:
		morning_values[d.data.weekday()][0] += 1
		morning_values[d.data.weekday()][1] += zero_if_none(d.fechamentoManha)

		afternoon_values[d.data.weekday()][0] += 1
		afternoon_values[d.data.weekday()][1] += zero_if_none(d.fechamentoTarde)
 
	fig, ax = plt.subplots()

	labels = ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado']

	morning_means = []
	for x in morning_values.items():
		if x[1][0] == 0:
			morning_means.append(0)
		else:
			morning_means.append(x[1][1]/x[1][0])

	afternoon_means = []
	for x in afternoon_values.items():
		if x[1][0] == 0:
			afternoon_means.append(0)
		else:
			afternoon_means.append(x[1][1]/x[1][0])

	ax.bar(labels, morning_means, label='Manhã')
	ax.bar(labels, afternoon_means, label='Tarde', bottom=morning_means)

	ax.set_ylabel('Valor em R$')
	ax.set_xlabel('Lucro médio por dia')
	ax.legend()

	plt.savefig(imagens_path + 'media_lucro_dia.png', bbox_inches='tight')
	plt.clf()
