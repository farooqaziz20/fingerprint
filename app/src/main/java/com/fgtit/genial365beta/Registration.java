package com.fgtit.genial365beta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
    private final static byte CMD_ENROLID = 0x02;
    private static final String TAG = "BluetoothReader";
    public static TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

       // view = (TextView) findViewById(R.id.txt_view);
        Button mButton1 = (Button) findViewById(R.id.btn_enroll);
//        mButton1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                view.setText("Call Succesl");
//                BluetoothReader cls_blu= new BluetoothReader();
//                cls_blu.call();
//            }
//        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Locaggg");
//                int id = 2;
//                byte buf[] = new byte[2];
//                buf[0] = (byte) (id);
//                buf[1] = (byte) (id >> 8);

                BluetoothReader cls_blu= new BluetoothReader();
                 cls_blu.call();
            }

        });


    }

}
