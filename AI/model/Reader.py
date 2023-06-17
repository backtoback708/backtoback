import cv2
import numpy as np
import os
import tempfile
import logging

logging.basicConfig(level=logging.INFO)

def read_mp4_file(len_images, file):
    now = []
    filename = tempfile.mktemp(suffix=".mp4")
    file.save(filename)
    capture = cv2.VideoCapture(filename)

    while True:
        ## Ending point
        if capture.get(cv2.CAP_PROP_POS_FRAMES) == capture.get(cv2.CAP_PROP_FRAME_COUNT):
            logging.info(cv2.CAP_PROP_FRAME_COUNT)
            capture.set(cv2.CAP_PROP_POS_FRAMES, 0)
            break

        ## File Read
        ret, frame = capture.read()

        ## Normalization
        frame = np.array(frame / 255.0)
        now.append(frame)

    capture.release()
    os.remove(filename)
    return np.array(now[:len_images]).reshape((1, 50, 178, 320, 3))
