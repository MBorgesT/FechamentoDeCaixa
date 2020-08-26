from models import *
from datetime import datetime, timedelta
from common import cursor, imagens_path, config_path

arq = open(config_path, 'r')
config = arq.read().splitlines()

inicio = datetime.strptime(config[0], '%d/%m/%Y')
fim = datetime.strptime(config[1], '%d/%m/%Y')

fim += timedelta(days=1) # para, na hora de filtrar, incluir o dia inteiro referente ao fim


dias = []
cursor.execute('SELECT * FROM dia')
for d in cursor.fetchall():
	idFechamentoManha = d[2]
	idFechamentoTarde = d[3]


	fechamentoManha = None
	if (idFechamentoManha != 0):
		ficaCaixasManha = []
		cursor.execute('SELECT * FROM ficaCaixa WHERE idFechamento = ' + str(idFechamentoManha))
		for fc in cursor.fetchall():
			ficaCaixasManha.append(FicaCaixa(*fc))

		saidasManha = []
		cursor.execute('SELECT * FROM saida WHERE idFechamento = ' + str(idFechamentoManha))
		for c in cursor.fetchall():
			saidasManha.append(Saida(*c))

		cursor.execute('SELECT * FROM fechamento WHERE idFechamento = ' + str(idFechamentoManha))
		sqlFechamentoManha = cursor.fetchone()
		fechamentoManha = Fechamento(*sqlFechamentoManha, saidasManha, ficaCaixasManha)


	fechamentoTarde = None
	if (idFechamentoTarde != 0):
		ficaCaixasTarde = []
		cursor.execute('SELECT * FROM ficaCaixa WHERE idFechamento = ' + str(idFechamentoTarde))
		for fc in cursor.fetchall():
			ficaCaixasTarde.append(FicaCaixa(*fc))

		saidasTarde = []
		cursor.execute('SELECT * FROM saida WHERE idFechamento = ' + str(idFechamentoTarde))
		for c in cursor.fetchall():
			saidasTarde.append(Saida(*c))

		cursor.execute('SELECT * FROM fechamento WHERE idFechamento = ' + str(idFechamentoTarde))
		sqlFechamentoTarde = cursor.fetchone()
		fechamentoTarde = Fechamento(*sqlFechamentoTarde, saidasTarde, ficaCaixasTarde)


	dias.append(Dia(
		d[0],
		datetime.strptime(d[1], '%d/%m/%Y'),
		fechamentoManha,
		fechamentoTarde
	))


sortedDias = sorted(
	dias,
	key=lambda x: x.data, 
	reverse=True
)


import stacked_lucro_dia, stacked_media_lucro_dia

#stacked_lucro_dia.gerar_imagem(dias)
stacked_media_lucro_dia.gerar_imagem(dias)

