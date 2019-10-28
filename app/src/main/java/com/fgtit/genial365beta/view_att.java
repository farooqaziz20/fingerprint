package com.fgtit.genial365beta;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.fgtit.utils.DBHelper;

public class view_att extends AppCompatActivity {

    ListView listView;
    private String[] name=new String[1000];
    private String[] timein=new String[100];
    private String[] timeout=new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_att);
        DBHelper db=new DBHelper(this);
        Cursor cursor=db.getemployess();
        int c=0;
        while (cursor.moveToNext())
        {
            cursor.moveToPosition(c);

            name[c]=cursor.getString(1);
            String a=cursor.getString(3);
            if(a.equals("")||cursor.getString(4).equals(""))
            {
                if(cursor.getString(3).equals("")&& cursor.getString(4).equals(""))
                {
                    timein[c]="--------";
                    timeout[c]="--------";
                }
                else if(cursor.getString(3).equals(""))
                {
                    timein[c]="--------";
                    timeout[c]=cursor.getString(4);
                }
                else if(cursor.getString(4).equals(""))
                {
                    timeout[c]="--------";
                    timein[c]=cursor.getString(3);
                }
            }
            else
            {
                timein[c]=cursor.getString(3);
                timeout[c]=cursor.getString(4);
            }


            c++;
        }


        String[] name2=new String[c];
        String[] timein2=new String[c];
        String[] timeout2=new String[c];
        for(int j=0;j<c;j++)
        {
            name2[j]=name[j];
            timein2[j]=timein[j];
            timeout2[j]=timeout[j];
        }
        listView=(ListView) findViewById(R.id.list);
        listitems list=new listitems(this,name2,timein2,timeout2);
        listView.setAdapter(list);


    }
}

