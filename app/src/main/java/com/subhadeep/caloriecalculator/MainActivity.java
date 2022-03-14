package com.subhadeep.caloriecalculator;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText age = findViewById(R.id.age_desc);
        EditText height = findViewById(R.id.height_desc);
        EditText weight = findViewById(R.id.weight_desc);
        EditText gender = findViewById(R.id.gender_desc);
        EditText waist = findViewById(R.id.waist_desc);
        EditText neck = findViewById(R.id.neck_desc);
        EditText act = findViewById(R.id.al_desc);

        height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display1).equals(height.getText().toString())){
                    height.setText("");
                }
            }
        });

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display1).equals(age.getText().toString())){
                    age.setText("");
                }
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display1).equals(weight.getText().toString())){
                    weight.setText("");
                }
            }
        });

        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display1).equals(gender.getText().toString())){
                    gender.setText("");
                }
            }
        });

        waist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display1).equals(waist.getText().toString())){
                    waist.setText("");
                }
            }
        });

        neck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display1).equals(neck.getText().toString())){
                    neck.setText("");
                }
            }
        });

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display1).equals(act.getText().toString())){
                    act.setText("");
                }
            }
        });



        Button but= (Button) findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                calculate(age.getText().toString(),height.getText().toString(),weight.getText().toString(),gender.getText().toString(),waist.getText().toString(),neck.getText().toString(),act.getText().toString());
            }
        });
    }
    public void calculate(String ages,String heights,String weights,String genders,String waists,String necks,String acts){
        double cal =0.0;
        int age=0;
        try {
            age = Integer.parseInt(ages);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),"Enter Age in numbers", Toast.LENGTH_SHORT).show();
        }
        double height=0.0;
        try {
            height = Double.parseDouble(heights);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),"Enter height in numbers", Toast.LENGTH_SHORT).show();
        }
        double weight=0.0;
        try {
            weight = Double.parseDouble(weights);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),"Enter weight in numbers", Toast.LENGTH_SHORT).show();
        }
        double waist=0.0;
        try {
            waist = Double.parseDouble(waists);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),"Enter waist in numbers", Toast.LENGTH_SHORT).show();
        }
        double neck=0.0;
        try {
            neck = Double.parseDouble(necks);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),"Enter neck in numbers", Toast.LENGTH_SHORT).show();
        }
        int act=1;
        try {
            act = Integer.parseInt(acts);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(),"Enter activity in numbers", Toast.LENGTH_SHORT).show();
        }

        char gender = genders.charAt(0);
        gender = Character.toUpperCase(gender);

        if(act > 5 || act < 1)
            Toast.makeText(getApplicationContext(),"Enter activity between 1 and 5, 5 being the highest", Toast.LENGTH_SHORT).show();

        if(!(gender=='m'||gender=='f'))
            Toast.makeText(getApplicationContext(),"Enter correct gender", Toast.LENGTH_SHORT).show();

        double bfp = bfccalc(gender,waist,neck,height);
        double lf=lfcalc(gender,bfp);
        double bmr=0.0;
        if(gender=='f')
            bmr=0.9*weight*24*lf;
        else
            bmr=weight*24*lf;
        cal=bmr*acalc(act);
        TextView display = findViewById(R.id.display);
        String s=String.valueOf(cal)+ " calories are necessary for daily intake";
        display.setText(s);

    }

    public double bfccalc(char g, double w, double n, double h){
        double bfc=0.0;

        if(g=='m')
            bfc=(495/(1.0324-0.19077*Math.log(w-n)+0.15456*Math.log(h))-450);
        else
            bfc=(495/(1.29579-0.35004*Math.log(w+w+10-n)+0.22100*Math.log(h))-450);
        return bfc;
    }

    public double lfcalc(char g, double bfc){
        if(g=='m'){
            if(bfc>28)
                return 0.85;
            else if(bfc>20)
                return 0.90;
            else if(bfc>14)
                return 0.95;
            else
                return 1;
        }
        else{
            if(bfc>38)
                return 0.85;
            else if(bfc>28)
                return 0.90;
            else if(bfc>18)
                return 0.95;
            else
                return 1;
        }
    }
    public double acalc(int a){
            switch(a){
                case 1:
                    return 1.3;
                case 2:
                    return 1.55;
                case 3:
                    return 1.65;
                case 4:
                    return 1.80;
                case 5:
                    return 2.0;
        }
        return 1;
    }
}