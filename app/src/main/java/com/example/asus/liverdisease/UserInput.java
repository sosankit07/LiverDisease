package com.example.asus.liverdisease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

import static java.util.jar.Pack200.Packer.PASS;

public class UserInput extends AppCompatActivity {
    Button submit;
    EditText age,gender,weight,height,bmi,obesity,waist,maxbp,minbp,goodcholestrol,badcholestrol,totalcholestrol,dyslipedemia,alcoholconsumption,hypertension;
    String Age,Gender,Weight,Height,Bmi,Obesity,Waist,Maxbp,Minbp,Goodcholestrol,Badcholestrol,Totalcholestrol,Dyslipedemia,Alcoholconsumption,Hypertension;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        submit = findViewById(R.id.submit);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        weight=findViewById(R.id.weight);
        height=findViewById(R.id.height);
        bmi=findViewById(R.id.bmi);
        obesity=findViewById(R.id.obesity);
        waist=findViewById(R.id.waist);
        maxbp=findViewById(R.id.maxbp);
        minbp = findViewById(R.id.minbp);
        goodcholestrol = findViewById(R.id.goodcholestrol);
        badcholestrol = findViewById(R.id.badcholestrol);
        totalcholestrol = findViewById(R.id.totalcholestrol);
        dyslipedemia = findViewById(R.id.dyslipedimia);
        alcoholconsumption = findViewById(R.id.alcohol);
        hypertension = findViewById(R.id.hypertension);
        result = findViewById(R.id.result);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                func();
            }
        });
    }
    public void func(){
        HashMap<String, String> getData = new HashMap<String, String>();
        Age = age.getText().toString();
        Weight = weight.getText().toString();
        Height = height.getText().toString();
        Bmi = bmi.getText().toString();
        Obesity = obesity.getText().toString();
        Waist = waist.getText().toString();
        Maxbp = maxbp.getText().toString();
        Minbp = minbp.getText().toString();
        Goodcholestrol = goodcholestrol.getText().toString();
        Badcholestrol = badcholestrol.getText().toString();
        Totalcholestrol = totalcholestrol.getText().toString();
        Dyslipedemia = dyslipedemia.getText().toString();
        Alcoholconsumption = alcoholconsumption.getText().toString();
        Hypertension = hypertension.getText().toString();
        Gender = gender.getText().toString();
        if(Gender == "M"){
            Gender = "1";
        }
        else if(Gender == "F"){
            Gender = "0";
        }
        getData.put("age", Age);
        getData.put("gender",Gender);
        getData.put("weight", Weight);
        getData.put("height", Height);
        getData.put("bmi", Bmi);
        getData.put("obesity", Obesity);
        getData.put("waist", Waist);
        getData.put("maxbp", Maxbp);
        getData.put("minbp",Minbp);
        getData.put("goodcholestrol",Goodcholestrol);
        getData.put("badcholestrol",Goodcholestrol);
        getData.put("totalcholestrol",Goodcholestrol);
        getData.put("dyslipedemia",Goodcholestrol);
        getData.put("alcohol",Alcoholconsumption);
        getData.put("hypertension",Hypertension);
        PostResponseAsyncTask task2 = new PostResponseAsyncTask(UserInput.this, getData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if (!(s.isEmpty())) {
                    //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    s = s.replace("[","");
                    s = s.replace("]","");
                    result.setText("Prediction "+s);

                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong! Try later!", Toast.LENGTH_SHORT).show();

                }

            }
        });
        task2.execute("http://192.168.43.170/News/liver.php");

        task2.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                Toast.makeText(getApplicationContext(), "Cannot connect to server!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                Toast.makeText(getApplicationContext(), "URL Error!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                Toast.makeText(getApplicationContext(), "Protocol Error!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                Toast.makeText(getApplicationContext(), "Encoding Error!", Toast.LENGTH_SHORT).show();

            }
        });



    }
}
