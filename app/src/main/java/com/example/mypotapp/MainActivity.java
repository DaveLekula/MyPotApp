package com.example.mypotapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button loginBTN;
    private Button signUpBtn;
    private int counter = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBTN = (Button) findViewById(R.id.loginBTN);
        userName = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.passwordET);
        signUpBtn = (Button) findViewById(R.id.SignUpBTN);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(userName.getText().toString(),password.getText().toString());
                System.out.println(userName.getText().toString() + password.getText().toString());
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validate (String userNameP,String passwordP){

        if ((userNameP.equals("Client")) && (passwordP.equals("test"))){

            Intent intent = new Intent(MainActivity.this,ClientHomeActivity.class);
            startActivity(intent);

        }
        else if((userNameP.equals("Admin"))&&(passwordP.equals("1234"))){

            Intent intent = new Intent(MainActivity.this,AdminHomeActivity.class);
            startActivity(intent);
        }
        else if((userNameP.equals("Emp"))&&(passwordP.equals("1234"))){

            Intent intent = new Intent(MainActivity.this,EmpHomeActivity.class);
            startActivity(intent);

        }
        else{

            counter --;
            if(counter == 0){
                loginBTN.setEnabled(false);
            }
            Toast toast = Toast.makeText(getApplicationContext(),"Sorry incorrect password you have "+counter+" attemts remaining",Toast.LENGTH_LONG);
            toast.show();

        }


    }
}
