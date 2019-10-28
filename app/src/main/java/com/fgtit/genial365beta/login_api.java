package com.fgtit.genial365beta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class login_api {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    boolean a;
    public static boolean val=false;
    int c;
    String user_Name="";
    String pss="";
    String res="";


    public boolean load(String us_name,String pass) throws IOException, JSONException {
        URL url = new URL("http://construction.genial365.com/WebServices/AndroidAPI/UserLogin.asmx/ApiLoginUser?usereEmail="+us_name+"&userPassword="+pass+"");
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
            JSONArray JA = new JSONArray(data);
//            for(int i =0 ;i <JA.length(); i++){
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed =  "Name:" + JO.get("name") + "\n"+
//                        "Password:" + JO.get("password") + "\n"+
//                        "Contact:" + JO.get("contact") + "\n"+
//                        "Country:" + JO.get("country") + "\n";
//
//                dataParsed = dataParsed + singleParsed +"\n" ;
//
//
//            }
            JSONObject JO = (JSONObject) JA.get(0);
            res = (String) JO.get("Response");
            if (res.equals("200")) {
                login.id= (String) JO.get("UserId");
                login.name= (String) JO.get("UserName");
                login.username= (String) JO.get("Email");

                return true;
            } else {

                return false;
            }
        }else{
            return false;
        }




    }
}
