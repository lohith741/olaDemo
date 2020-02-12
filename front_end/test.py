import json
from flask import Flask,request,jsonify
# from flask_cors import CORS, cross_origin

app = Flask(__name__)

# cors = CORS(app)
# app.config['CORS_HEADERS'] = 'Content-Type'


@app.route('/get_req', methods=['GET','POST'])
def get_req():

    with open('./people.json') as f:
        data = json.load(f)

    return jsonify(data)
if __name__ == "__main__":
    app.run(debug=True)
