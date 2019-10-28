package com.fgtit.genial365beta;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import com.fgtit.utils.DBHelper;

public class login extends AppCompatActivity {
    private Button Login;
    private EditText Name;
    private EditText Password;
    TextView txtmsg;
    public static String id="";
    public static String name="";
    public static String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = (EditText) findViewById(R.id.txt_usrname);
        Password = (EditText) findViewById(R.id.txt_password);
        Login = (Button) findViewById(R.id.btn_login);
        txtmsg = (TextView) findViewById(R.id.txtmsg);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate(Name.getText().toString(), Password.getText().toString());
                login_api ld=new login_api();
                try {
                    if(ld.load(Name.getText().toString(), Password.getText().toString()))
                    {
                        DBHelper db=new DBHelper(getApplicationContext());
                        Boolean res=db.sup_insert(id,name,username);
                        if(res)
                        {
                            Intent intent = new Intent(login.this, dash.class);
                            startActivity(intent);
                        }
                        else
                        {
                            txtmsg.setText("Error in inserting data to database");
                        }


                    }
                    else
                    {
                       txtmsg.setText("Login Faild, Try Again");
                    }


                } catch (IOException e) {
                    txtmsg.setText("Login Faild, Try Again");
                    e.printStackTrace();
                } catch (JSONException e) {
                    txtmsg.setText("Login Faild, Try Again");
                    e.printStackTrace();
                }
//                //login log= new login();
//                log.parameters(Name.getText().toString(), Password.getText().toString());
//                log.execute();




            }
        });

    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please Click BACK Button Twice to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 500);
//        Intent intent = new Intent(login.this, login.class);
//        startActivity(intent);
    }
}
