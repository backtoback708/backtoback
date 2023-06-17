import boto3
import logging
from keras import models
from flask import Flask, request
from flask_sqlalchemy import SQLAlchemy
from model.Reader import read_mp4_file
from io import BytesIO
from werkzeug.datastructures import FileStorage
from moviepy.editor import VideoFileClip
import tempfile
import os

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://btob:backback@k8a708.p.ssafy.io:3306/backtoback'
db = SQLAlchemy(app)

class PhotoCard(db.Model):
    __tablename__ = 'photocards'  # 여기에 실제 테이블 이름을 넣으세요.

    photo_card_seq = db.Column(db.BigInteger, primary_key=True, autoincrement=True)
    photo_card_url = db.Column(db.String(2500))
    quantity = db.Column(db.Integer)
    game_seq = db.Column(db.BigInteger)

logging.basicConfig(level=logging.INFO)

# 00. Static Variables
s3 = boto3.client('s3',
    aws_access_key_id = 'AKIATTODRATXXXYIQUSL',
    aws_secret_access_key = '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'
)
model = models.load_model("./model/front2back2/")
# 모델 구성

#################################################################
## 00. API
@app.route('/upload', methods=['POST'])
def upload_files():
    logging.info("!!! Start upload_file !!!")

    len_images = 50
    game_seq = request.form.get('gameSeq')
    files = request.files.getlist('files')

    len_data = len(files)

    # 파일 처리 로직
    tot_result = []
    saved_files = []
    for file in files:
        file_content = file.read()
        file_like_object = BytesIO(file_content)

        file = FileStorage(file_like_object, filename=file.filename, content_type=file.content_type)
        saved_files.append(file)
        file.seek(0)

    for file in saved_files:
        clip = read_mp4_file(len_images, file)
        tot_result.append(list(model.predict(clip)))
        file.seek(0)

    ## S3 에 저장
    for idx in range(len_data):
        # if tot_result[idx][1] >= 0.8: -> Labeling
        file_name = "HL_" + str(game_seq) + "_" +str(idx) + ".gif"

        # Get data from your BytesIO object
        saved_files[idx].seek(0)
        file_content = saved_files[idx].read()

        # Create a temporary file and write the content to it
        with tempfile.NamedTemporaryFile(suffix=".mp4", delete=False) as temp_file:
            temp_file.write(file_content)
            temp_file.close()

            # Now you can pass the file name to VideoFileClip
            clip = VideoFileClip(temp_file.name)
            clip.write_gif(file_name)
            clip.close()

            with open(file_name, 'rb') as data:
                s3.upload_fileobj(data, 'mybacktoback', file_name)
            os.unlink(temp_file.name)  # Delete the temporary file

        localDB_file_name = "https://mybacktoback.s3.amazonaws.com/" + file_name
        new_photo_card = PhotoCard(photo_card_url=localDB_file_name, quantity=10, game_seq=game_seq)
        db.session.add(new_photo_card)
        db.session.commit()
    return "Success"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, threaded=False)

# if __name__ == '__main__':
#     app.run(host='localhost', port=5000, threaded=False)
