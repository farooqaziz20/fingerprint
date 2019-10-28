package com.fgtit.genial365beta;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fgtit.utils.DBHelper;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DBHelper db=new DBHelper(getApplicationContext());
                String id=db.sup_id();
                if(id.equals("NR"))
                {
                    Intent intent = new Intent(start.this, login.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(start.this, dash.class);
                    startActivity(intent);
                }

            }
        }, 2000);
    }
}
