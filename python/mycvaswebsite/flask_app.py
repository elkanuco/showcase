import requests
import json
from flask import Flask, render_template

#CV_JSON_GOOGLE_DRIVE_URL = "https://drive.google.com/file/d/1K5lIqFdRHy8VNfUFsvfrpoaMK5_x8o_d/view?usp=drive_link"

FILE_ID = '1K5lIqFdRHy8VNfUFsvfrpoaMK5_x8o_d'
URL = f'https://drive.google.com/uc?export=download&id={FILE_ID}'
DEFAULT_JSON = 'static/assets/json/cv_ats.json'

cv_dict: dict = None
cv_migth_be_outdated: bool = False

app = Flask(__name__)


def load_default():
    global cv_migth_be_outdated
    global cv_dict
    cv_migth_be_outdated = True
    print('There was an issue loading the file from google drive. A default will be used.')
    with open(DEFAULT_JSON, 'r', encoding='utf-8') as f:
        cv_dict = json.load(f)




@app.route("/")
def home():
    try:
        response = requests.get(URL, stream=True, timeout=10)
        if response.ok:
            global cv_dict
            cv_dict = json.load(response.raw)
        else:
            load_default()
    except Exception:
        load_default()
    return render_template('home.html', cv_dict=cv_dict)
    

if __name__ == "__main__":
    app.run(debug=True)






