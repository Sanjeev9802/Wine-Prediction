from flask import Flask,request,jsonify
import numpy as np
import pickle

app=Flask(__name__)

model=pickle.load(open('model.pkl','rb'))
@app.route('/')
def home():
    return "Hello"

@app.route('/predict',methods=['POST'])
def predict():
    fixed_acidity=request.form.get('fixed_acidity')
    volatile_acidity=request.form.get('volatile_acidity')
    citric_acid=request.form.get('citric_acid')
    residual_sugar=request.form.get('residual_sugar')
    chlorides=request.form.get('chlorides')
    free_So2=request.form.get('free_So2')
    total_So2=request.form.get('total_So2')
    density=request.form.get('density')
    ph=request.form.get('ph')
    sulphates=request.form.get('sulphates')
    alcohol=request.form.get('alcohol')

    input=np.array([[fixed_acidity,volatile_acidity,
                     citric_acid,residual_sugar,
                     chlorides,free_So2,
                     total_So2,
                     density,
                     ph,
                     sulphates,
                     alcohol]])
    result= model.predict(input)[0]
    return jsonify({'quality':str(result)})

if __name__=='__main__':
    app.run(debug=True)