package com.fgtit.genial365beta;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class push {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    boolean a;
    public static boolean val=false;
    int c;
    String user_Name="";
    String pss="";
    String res="";




    public int load(Cursor cursor,String sup_id,String t_in,String t_out) throws IOException, JSONException {

        SimpleDateFormat date = new SimpleDateFormat("d");
        String day = date.format(new Date());
        SimpleDateFormat s_month = new SimpleDateFormat("M");
        String month = s_month.format(new Date());
        SimpleDateFormat s_year = new SimpleDateFormat("yyyy");
        String year = s_year.format(new Date());
        String c_date=year+"-"+month+"-"+day;
        cursor.moveToFirst();
        String time="00:00:00";
        String clor="white";
        String status="present";
        URL url1 = new URL("http://construction.genial365.com/WebServices/AndroidAPI/AddEmployeeAttendence.asmx/CheckEmployeesAttendenceStaus?supervisorId="+sup_id+"&attendenceDate="+c_date+"");
        HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
        InputStream inputStream1 = httpURLConnection1.getInputStream();
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));
        String line1 = "";

        while (line1 != null) {
            c++;               // MainActivity.Info.setText(bufferedReader.readLine());
            line1 = bufferedReader1.readLine();
            data = data + line1;
        }
        if(c>0)
        {
            JSONArray JA = new JSONArray(data);
            JSONObject JO = (JSONObject) JA.get(0);
            res = (String) JO.get("status");
            if (!res.equals("found")) {

                cursor.moveToFirst();
                int c=0;
                while (cursor.moveToNext()) {
                    String b_in=t_in;
                    String b_out=t_out;
                    if(c==0)
                    {
                        cursor.moveToPrevious();
                    }
                    c++;
                    String timein=cursor.getString(3);
                    String timeout=cursor.getString(4);
                    String name=cursor.getString(1);

                    if(timein.equals("")&&timeout.equals(""))
                    {
                        timein="00:00:00";
                        timeout="00:00:00";
                        b_in="00:00:00";
                        b_out="00:00:00";
                        clor="pink";
                        status="Absent";
                    }
                    else
                    {
                        if(timeout.equals(""))
                        {
                            timeout=b_out;
                        }
                        clor="white";
                        status="Present";
                    }
                    URL url = new URL("http://construction.genial365.com/WebServices/AndroidAPI/AddEmployeeAttendence.asmx/AddEmployeeAttendeceMainNew?supervisorId=" + sup_id + "&chartId=" + cursor.getString(2) + "&month=" + month + "&year=" + year + "&morningTimeIn=" + timein+ "&morningTimeOut=" + b_in + "&eveningTimeIn=" + b_out+ "&eveningTimeOut=" + timeout + "&date=" + c_date + "&attendenceStatus=" + status + "&color=" + clor + "");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";

                    while (line != null) {
                        c++;               // MainActivity.Info.setText(bufferedReader.readLine());
                        line = bufferedReader.readLine();
                        data = data + line;

                    }
                    if (c > 0) {

                    } else {
                       //return error msg
                        return 0;
                    }
                }
            }
            else {
//return already insertd
                return 2;
            }

        }
        else {
            //return already insertd
            return 2;
        }

//retun successfully inserted
        return 1;


    }
}



