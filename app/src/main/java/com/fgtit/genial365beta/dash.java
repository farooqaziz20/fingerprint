package com.fgtit.genial365beta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fgtit.utils.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dash extends AppCompatActivity {
    insert_to_db load= new insert_to_db();
    DBHelper db;
    private ImageButton Login;
    public static TextView txt_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        txt_val = (TextView)findViewById(R.id.txt_val);
        insert_to_db load= new insert_to_db();
        DBHelper db= new DBHelper(this);
        String id=db.sup_id();
        load.execute(id);
        String txt=db.loadDatadb();
        txt_val.setText(txt);


        Login = (ImageButton) findViewById(R.id.btn_dayshift);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day_das();
            }
        });
        Login = (ImageButton) findViewById(R.id.btn_nightShift);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                night_das();
            }
        });
        Login = (ImageButton) findViewById(R.id.btn_registerUser);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        Login = (ImageButton) findViewById(R.id.btn_push);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push();
            }
        });
        Login = (ImageButton) findViewById(R.id.logout);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });


    }
    public void day_das() {

        SimpleDateFormat date = new SimpleDateFormat("dd");
        String day = date.format(new Date());
        SimpleDateFormat s_month = new SimpleDateFormat("MM");
        String month = s_month.format(new Date());
        SimpleDateFormat s_year = new SimpleDateFormat("yyyy");
        String year = s_year.format(new Date());
        String a=day+","+month+","+year;

        if(txt_val.getText().equals("Data Inserted")||txt_val.getText().equals(a))
        {

            Intent intent = new Intent(this, BluetoothReader.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Data is Loading Please Wait",Toast.LENGTH_SHORT).show();
        }
//        Intent intent = new Intent(this, BluetoothReader.class);
//        startActivity(intent);

    }
    public void night_das() {
        Intent intent = new Intent(this, search.class);
        startActivity(intent);

    }
    public void push()
    {
        Intent intent = new Intent(this, set_time.class);
        startActivity(intent);
    }
    public void register() {
        Intent intent = new Intent(this, view_att.class);
        startActivity(intent);

    }
    public void logout (){
        DBHelper db=new DBHelper(this);
        if(db.logout())
        {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        }
    }
    public  void execute()
    {

        if(load.ok())
        {
            DBHelper db= new DBHelper(this);
            db.loadDatadb();
        }
    }
}
