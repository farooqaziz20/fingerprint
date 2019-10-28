package com.fgtit.genial365beta;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fgtit.utils.DBHelper;

import org.json.JSONException;

import java.io.IOException;

public class set_time extends AppCompatActivity {
    private Button Login;
    private EditText timein;
    private EditText timeout;
    TextView txtmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        timein = (EditText) findViewById(R.id.txt_usrname);
        timeout = (EditText) findViewById(R.id.txt_password);
        Login = (Button) findViewById(R.id.btn_login);
        txtmsg = (TextView) findViewById(R.id.txtmsg);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                push push =new push();
                //push.push();
                txtmsg.setText("Please Wait ...");
                push ld=new push();
                DBHelper db=new DBHelper(set_time.this);
                Cursor cursor=db.getemployess();
                try {
                    String sup_id=db.sup_id();
                    String t_in= String.valueOf(timein.getText());
                    String t_out= String.valueOf(timeout.getText());

                    int var=ld.load(cursor,sup_id,t_in,t_out);
                        if(var==1)
                        {
                            Toast.makeText(getApplicationContext(),"Attendence Inserted to ONline Server", Toast.LENGTH_LONG).show();
                            txtmsg.setText("Inserted");
                        }
                        else if(var==0)
                        {
                            Toast.makeText(getApplicationContext(),"Error in Inserting the Data", Toast.LENGTH_LONG).show();
                            txtmsg.setText("Error");

                        }
                        else if(var==2)
                        {
                            Toast.makeText(getApplicationContext(),"Attendence for today is already inserted.", Toast.LENGTH_LONG).show();
                            txtmsg.setText("Already Inserted");
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Error in Inserting the Data", Toast.LENGTH_LONG).show();
                        }


                } catch (IOException e) {
                    txtmsg.setText("Login Fail, Try Again");
                    e.printStackTrace();
                } catch (JSONException e) {
                    txtmsg.setText("Login Faild, Try Again");
                    e.printStackTrace();
                }
            }
        });
    }
}
