import sqlite3
conn = sqlite3.connect('/home/matheus/arquivos-padaria/arquivos-caixa/database/atual/caixa.db')
cursor = conn.cursor()


imagens_path = '/home/matheus/arquivos-padaria/arquivos-caixa/relatorio/imagens/'
config_path = '/home/matheus/arquivos-padaria/arquivos-caixa/relatorio/scripts/config.txt'