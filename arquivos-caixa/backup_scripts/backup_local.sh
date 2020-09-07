year=$(date +'%Y')
year_dir="/home/pi/arquivos-padaria/arquivos-caixa/database/backups/${year}"

if [ ! -d "$year_dir" ]; then
	mkdir ${year_dir}
fi

month=$(date +'%m')
month_dir="${year_dir}/${month}"

if [ ! -d "$month_dir" ]; then
	mkdir ${month_dir}
fi

day=$(date +'%d')

cp /home/pi/arquivos-padaria/arquivos-caixa/database/atual/caixa.db "${month_dir}/${year}-${month}-$day.db"
