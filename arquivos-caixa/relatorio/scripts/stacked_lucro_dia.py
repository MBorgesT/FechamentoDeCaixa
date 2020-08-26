import matplotlib.pyplot as plt
import numpy as np
from datetime import datetime, timedelta
from common import imagens_path


def gerar_imagem(dias):
	inicio = dias[0].data
	fim = dias[-1].data

	dias_datetime = np.arange(inicio, fim+timedelta(days=1), timedelta(days=1)).astype(datetime)
	dias_str = [d.strftime('%d/%m/%Y') for d in dias_datetime]

	fechamentos_tardes = []
	fechamentos_manhas = []
	for d in dias:
		if (d.fechamentoManha is not None):
			fechamentos_manhas.append(d.fechamentoManha.get_valor_total())
		else:
			fechamentos_manhas.append(0)

		if (d.fechamentoTarde is not None):
			fechamentos_tardes.append(d.fechamentoTarde.get_valor_total())
		else:
			fechamentos_tardes.append(0)

	fig, ax = plt.subplots()

	ax.bar(dias_str, fechamentos_manhas, label='Manhã')
	ax.bar(dias_str, fechamentos_tardes, label='Tarde', bottom=fechamentos_manhas)

	ax.set_ylabel('Valor em R$')
	ax.set_xlabel('Lucro por dia')
	ax.legend()

	plt.savefig(imagens_path + 'lucro_dia.png', bbox_inches='tight')
	plt.clf()