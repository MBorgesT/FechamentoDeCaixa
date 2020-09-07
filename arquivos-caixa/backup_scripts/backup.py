from __future__ import print_function
import pickle
import os.path
from common import BASE_FOLDER
from googleapiclient.discovery import build
from googleapiclient.http import MediaFileUpload
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request

import sys
from datetime import datetime


# If modifying these scopes, delete the file token.pickle.
SCOPES = ['https://www.googleapis.com/auth/drive.file']
MEDIA_PATH = BASE_FOLDER + 'database/atual/caixa.db'

def main():

	creds = None

	if os.path.exists(BASE_FOLDER + 'backup_scripts/token.pickle'):
		with open(BASE_FOLDER + 'backup_scripts/token.pickle', 'rb') as token:
			creds = pickle.load(token)

	if not creds or not creds.valid:
		if creds and creds.expired and creds.refresh_token:
			creds.refresh(Request())
		else:
			flow = InstalledAppFlow.from_client_secrets_file(
				BASE_FOLDER + 'backup_scripts/credentials.json', SCOPES)
			creds = flow.run_local_server(port=0)

		with open(BASE_FOLDER + 'backup_scripts/token.pickle', 'wb') as token:
			pickle.dump(creds, token)

	drive_service = build('drive', 'v3', credentials=creds)



	# store backup

	now = datetime.now()
	year_folder_id = None
	month_folder_id = None
	latest_folder_id = None


	response = drive_service.files().list(q="mimeType = 'application/vnd.google-apps.folder'",
										  spaces='drive', 
										  fields='nextPageToken, files(id, name)').execute()

	flag = False
	flagLatest = False
	for file in response.get('files', []):
		print(file)
		if file.get('name') == str(now.year):
			year_folder_id = file.get('id')
			flag = True
		if file.get('name') == 'ultimo_backup':
			latest_folder_id = file.get('id')
			flagLatest = True


	if (flagLatest == False):
		file_metadata = {
			'name': 'ultimo_backup',
			'mimeType': 'application/vnd.google-apps.folder'
		}
		file = drive_service.files().create(body=file_metadata,
											fields='id').execute()

		latest_folder_id = file.get('id')	
			

	if (flag == False):
		file_metadata = {
			'name': str(now.year),
			'mimeType': 'application/vnd.google-apps.folder'
		}
		file = drive_service.files().create(body=file_metadata,
											fields='id').execute()

		year_folder_id = file.get('id')


	response = drive_service.files().list(q="mimeType = 'application/vnd.google-apps.folder' and '{}' in parents".format(year_folder_id),
										  spaces='drive', 
										  fields='nextPageToken, files(id, name)').execute()

	flag = False
	for file in response.get('files', []):
		if (file.get('name') == str(now.month)):
			month_folder_id = file.get('id')
			flag = True

	if (flag == False):
		file_metadata = {
			'name': str(now.month),
			'mimeType': 'application/vnd.google-apps.folder',
			'parents': [str(year_folder_id)]
		}
		file = drive_service.files().create(body=file_metadata,
											fields='id').execute()

		month_folder_id = file.get('id')


	file_name = str(now.year) + '-' + str(now.month) + '-' + str(now.day) + '.db'
	file_metadata = {
		'name': file_name,
		'parents': [month_folder_id]
	}

	media = MediaFileUpload(
		MEDIA_PATH,
		resumable=True
	)

	file = drive_service.files().create(body=file_metadata,
										media_body=media,
										fields='id').execute()

	print('File ID: %s' % file.get('id'))



	response = drive_service.files().list(q="mimeType != 'application/vnd.google-apps.folder' and '{}' in parents".format(latest_folder_id),
										  spaces='drive', 
										  fields='nextPageToken, files(id, name)').execute()

	if len(response.get('files', [])) > 0:
		for file in response.get('files', []):
			if (file.get('name') == 'caixa.db'):
				drive_service.files().delete(fileId=file.get('id')).execute()


	file_metadata = {
		'name': 'caixa.db',
		'parents': [latest_folder_id]
	}

	media = MediaFileUpload(
		MEDIA_PATH,
		resumable=True
	)

	file = drive_service.files().create(body=file_metadata,
										 media_body=media,
										 fields='id').execute()

	print('Latest file ID: %s' % file.get('id'))

	arq = open(BASE_FOLDER + 'backup_scripts/latest_backup_id.txt', 'w')
	arq.write(file.get('id'))
	arq.close()




if __name__ == '__main__':
    main()
