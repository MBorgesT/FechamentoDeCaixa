class Saida:
	def __init__(self, idSaida, idFechamento, descricao, valor):
		self.idSaida = idSaida
		self.idFechamento = idFechamento
		self.descricao = descricao
		self.valor = valor


class FicaCaixa:
	def __init__(self, idFicaCaixa, idFechamento, descricao, valor):
		self.idFicaCaixa = idFicaCaixa
		self.idFechamento = idFechamento
		self.descricao = descricao
		self.valor = valor


class Fechamento:
	def __init__(self, idFechamento, idDia, turno, entrada, valorCaixa, valorDisplay, cartao, saidas, ficaCaixas):
		self.idFechamento = idFechamento
		self.idDia = idDia
		self.turno = turno
		self.entrada = entrada
		self.valorCaixa = valorCaixa
		self.valorDisplay = valorDisplay
		self.cartao = cartao
		self.saidas = saidas
		self.ficaCaixas = ficaCaixas

	def get_valor_total(self):
		total_saidas = 0
		for saida in self.saidas:
			total_saidas += saida.valor

		return (self.valorCaixa + self.cartao - (self.entrada + total_saidas))


class Dia:
	def __init__(self, idDia, data, fechamentoManha, fechamentoTarde):
		self.idDia = idDia
		self.data = data
		self.fechamentoManha = fechamentoManha
		self.fechamentoTarde = fechamentoTarde