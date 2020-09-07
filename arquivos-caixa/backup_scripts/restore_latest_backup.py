import pickle
import os.path
import io
import shutil
import os
from common import BASE_FOLDER
from googleapiclient.discovery import build
from googleapiclient.http import MediaIoBaseDownload
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request


PATH_TO_DB_FOLDER = BASE_FOLDER + 'database/atual/'


creds = None

if os.path.exists(BASE_FOLDER + 'backup-caderneta/token.pickle'):
	with open(BASE_FOLDER + 'backup-caderneta/token.pickle', 'rb') as token:
		creds = pickle.load(token)

if not creds or not creds.valid:
	if creds and creds.expired and creds.refresh_token:
		creds.refresh(Request())
	else:
		flow = InstalledAppFlow.from_client_secrets_file(
				BASE_FOLDER + 'backup-caderneta/credentials.json', SCOPES)
		creds = flow.run_local_server(port=0)

	with open(BASE_FOLDER + 'backup-caderneta/token.pickle', 'wb') as token:
		pickle.dump(creds, token)

drive_service = build('drive', 'v3', credentials=creds)

arq = open(BASE_FOLDER + 'backup-caderneta/latest_backup_id.txt', 'r')
latest_backup_id = arq.readline()
arq.close()

request = drive_service.files().get_media(fileId=latest_backup_id)
fh = io.BytesIO()
downloader = MediaIoBaseDownload(fh, request)
done = False
while done is False:
	status, done = downloader.next_chunk()
	print("Download %d%%." % int(status.progress() * 100))


this_dir = os.path.dirname(os.path.realpath(__file__))
file_path = os.path.join(this_dir, BASE_FOLDER + 'backup-caderneta/caderneta.db')

try:
    os.remove(BASE_FOLDER + 'backup-caderneta/caderneta.db')
except:
    None

fh.seek(0)
with open(file_path, 'wb') as f:
	shutil.copyfileobj(fh, f)

shutil.move(BASE_FOLDER + 'backup-caderneta/caderneta.db', PATH_TO_DB_FOLDER)