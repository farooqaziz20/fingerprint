package com.fgtit.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_Name= "attendence23.db";
    private static final String DB_TABLE= "users";
    private static final String DB_TABLE_ATT= "attendence";
    private static final String DB_TABLE_SUP= "superviouser";

//User Table Variables
    private static final String E_ID= "enrol_ID";
    private static final String ID= "Id";
    private static final String U_Name= "user_name";
    private static final String U_ID= "user_ID";

    //Attendence Table Vribles
    private static final String A_ID= "id";
    private static final String A_NAME= "name";
    private static final String A_U_ID= "u_id";
    private static final String A_TIME_IN= "timein";
    private static final String A_TIME_OUT= "timeout";
    private static final String A_DATE= "date";
    private static final String A_MONTH= "month";
    private static final String A_YEAR= "year";
    private static final String A_SUP_ID= "sup_id";

    //Superviouser Table

    private static final String S_ID= "id";
    private static final String S_S_ID= "s_id";
    private static final String S_NAME= "s_name";
    private static final String S_USERNAME= "s_username";






    public static String[] users={"Select Name","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    public static String[] users2 = { "0 ","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","" ,"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    //Create Qeuries
    private static final String CREATE= "CREATE TABLE "+DB_TABLE+" ("+ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+E_ID+" TEXT, "+U_ID+" TEXT  , "+U_Name+" TEXT)";
    private static final String CREATE_ATT= "CREATE TABLE "+DB_TABLE_ATT+" ("+A_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+A_NAME+" TEXT, "+A_U_ID+" TEXT  , "+A_TIME_IN+" TEXT, "+A_TIME_OUT+" TEXT, "+A_DATE+" TEXT, "+A_MONTH+" TEXT, "+A_YEAR+" TEXT, "+A_SUP_ID +" TEXT)";
    private static final String CREATE_SUP= "CREATE TABLE "+DB_TABLE_SUP+" ("+S_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+S_S_ID+" TEXT, "+S_NAME+" TEXT  , "+S_USERNAME+" TEXT)";

    public DBHelper(Context context)
    {
        super(context,DB_Name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE);
        sqLiteDatabase.execSQL(CREATE_ATT);
        sqLiteDatabase.execSQL(CREATE_SUP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE_ATT);
    onCreate(sqLiteDatabase);
    }
    public boolean sup_insert(String id,String name,String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values= new ContentValues();
        values.put(S_S_ID,id);
        values.put(S_NAME,name);
        values.put(S_USERNAME,username);
        long result=db.insert(DB_TABLE_SUP,null,values);
        if(result !=-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean insert(String id, String name,String use_id)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values= new ContentValues();
        values.put(E_ID,id);
        values.put(U_ID,use_id);
        values.put(U_Name,name);
        long result=db.insert(DB_TABLE,null,values);
        return result !=-1;
    }
    public Cursor viewdata()
    {
        String[] params = new String[]{ users2[1] };
        SQLiteDatabase db= this.getReadableDatabase();
        String qeury="Select * FROM "+DB_TABLE_ATT +" where "+A_U_ID + "= ?";
        Cursor cursor=db.rawQuery(qeury,params);
        return cursor;
    }
    public String loadDatadb()
    {
        SimpleDateFormat date = new SimpleDateFormat("dd");
        String day = date.format(new Date());
        SimpleDateFormat s_month = new SimpleDateFormat("MM");
        String month = s_month.format(new Date());
        SimpleDateFormat s_year = new SimpleDateFormat("yyyy");
        String year = s_year.format(new Date());
        SQLiteDatabase db = this.getReadableDatabase();

            for(int i=0; i<users.length;i++)
            {
                if (users[i]!="") {
                    String[] params = new String[]{ users2[i],day,month,year };
                    String qeury="Select * FROM "+DB_TABLE_ATT +" where "+A_U_ID + "= ? and "+A_DATE+"= ? and "+A_MONTH+"= ? and "+A_YEAR+"= ?";
                    Cursor cursor=db.rawQuery(qeury,params);
                    int a=cursor.getCount();
                    if(a>0)
                    {
                        //Do Nothing
                    }
                    else
                    {
                        String sup_id=this.sup_id();
                        ContentValues values = new ContentValues();
                        values.put(A_NAME, users[i]);
                        values.put(A_U_ID, users2[i]);
                        values.put(A_TIME_IN, "");
                        values.put(A_TIME_OUT, "");
                        values.put(A_DATE, day);
                        values.put(A_MONTH, month);
                        values.put(A_YEAR, year);
                        values.put(A_SUP_ID, sup_id);

                        long result = db.insert(DB_TABLE_ATT, null, values);
                        if (result == -1) {
                            return "Error";
                        } else {

                        }
                    }


                }
            }


        String a=day+","+month+","+year;

        return a;
    }
    public int checkin(String id)
    {
        SimpleDateFormat date = new SimpleDateFormat("dd");
        String day = date.format(new Date());
        SimpleDateFormat s_month = new SimpleDateFormat("MM");
        String month = s_month.format(new Date());
        SimpleDateFormat s_year = new SimpleDateFormat("yyyy");
        String year = s_year.format(new Date());


        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{ id };
        String qeury="Select * FROM "+DB_TABLE +" where "+E_ID+ "= ?";
        Cursor cursor=db.rawQuery(qeury,params);
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            String user_id3=cursor.getString(2);
            String[] param = new String[]{ user_id3, day, month, year };
            String qeur="Select * FROM "+DB_TABLE_ATT +" where "+A_U_ID + "= ? and "+A_DATE+"= ? and "+A_MONTH+"= ? and "+A_YEAR+"= ?";
            Cursor curso=db.rawQuery(qeur,param);
            if(curso.getCount()>0)
            {
                curso.moveToFirst();
                String time_in=curso.getString(3);
                if(time_in.equals(""))
                {
                    SimpleDateFormat s_time = new SimpleDateFormat("HH:mm:ss");
                    String time = s_time.format(new Date());
                    cursor.moveToFirst();

                    String user_name=cursor.getString(3);
                    String[] valu = new String[]{ user_id3 };
                    ContentValues values = new ContentValues();
                    values.put(A_TIME_IN,time);
                    int result=db.update(DB_TABLE_ATT,values,A_U_ID+"=?",valu);
                    if(result!=-1)
                    {
                        int a=1;
                        return  a;
                    }

                }
                else
                {
                    int a=2;
                    return  a;
                }

            }
            else {
                int a=3;
                return  a;
            }
        }

        int a=4;
        return  a;
    }
    public int checkout(String id)
    {
        SimpleDateFormat date = new SimpleDateFormat("dd");
        String day = date.format(new Date());
        SimpleDateFormat s_month = new SimpleDateFormat("MM");
        String month = s_month.format(new Date());
        SimpleDateFormat s_year = new SimpleDateFormat("yyyy");
        String year = s_year.format(new Date());


        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{ id };
        String qeury="Select * FROM "+DB_TABLE +" where "+E_ID+ "= ?";
        Cursor cursor=db.rawQuery(qeury,params);
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            String user_id3=cursor.getString(2);
            String[] param = new String[]{ user_id3, day, month, year };
            String qeur="Select * FROM "+DB_TABLE_ATT +" where "+A_U_ID + "= ? and "+A_DATE+"= ? and "+A_MONTH+"= ? and "+A_YEAR+"= ?";
            Cursor curso=db.rawQuery(qeur,param);
            if(curso.getCount()>0)
            {
                curso.moveToFirst();
                String time_in=curso.getString(3);
                String time_out=curso.getString(4);
                if(!time_in.equals("")  )
                {
                    if(time_out.equals("")) {
                        SimpleDateFormat s_time = new SimpleDateFormat("HH:mm:ss");
                        String time = s_time.format(new Date());
                        cursor.moveToFirst();

                        String user_name = cursor.getString(3);
                        String[] valu = new String[]{user_id3};
                        ContentValues values = new ContentValues();
                        values.put(A_TIME_OUT, time);
                        int result = db.update(DB_TABLE_ATT, values, A_U_ID + "=?", valu);
                        if (result != -1) {
                            int a = 1;
                            return a;
                        }
                    }
                    else {
                        return 5;
                    }

                }
                else
                {
                    int a=2;
                    return  a;
                }

            }
            else {
                int a=3;
                return  a;
            }
        }

        int a=4;
        return  a;
    }
    public String getname(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{ id };
        String qeury="Select * FROM "+DB_TABLE +" where "+E_ID+ "= ?";
        Cursor cursor=db.rawQuery(qeury,params);
        if(cursor.getCount()>0)
        { cursor.moveToFirst();
            String name= cursor.getString(3);
            return name;
        }
        return "NR";

    }
    public boolean cleartables()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DB_TABLE,null,null);
        int result=db.delete(DB_TABLE_ATT,null,null);
        if(result!=-1) {
            return true;
        }else {
            return false;
        }
    }
    public String sup_id()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String qeury="Select * FROM "+DB_TABLE_SUP;
        Cursor cursor= (Cursor) db.rawQuery(qeury,null);
        if(cursor.getCount()>0)
        { cursor.moveToFirst();
            String id= cursor.getString(1);
            return id;
        }
        return "NR";
    }
    public boolean logout()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        int result=db.delete(DB_TABLE_SUP,null,null);
        if(result!=-1) {
            return true;
        }else {
            return false;
        }
    }
    public Cursor getemployess()
    {
        SimpleDateFormat date = new SimpleDateFormat("dd");
        String day = date.format(new Date());
        SimpleDateFormat s_month = new SimpleDateFormat("MM");
        String month = s_month.format(new Date());
        SimpleDateFormat s_year = new SimpleDateFormat("yyyy");
        String year = s_year.format(new Date());
        String s_date=year;


        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = new String[]{ day, month, year };
        String qeur="Select * FROM "+DB_TABLE_ATT +" where "+A_DATE+"= ? and "+A_MONTH+"= ? and "+A_YEAR+"= ?";
        Cursor curso=db.rawQuery(qeur,param);
        return  curso;
    }
}
