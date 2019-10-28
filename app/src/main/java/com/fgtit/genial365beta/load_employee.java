package com.fgtit.genial365beta;

        import android.os.AsyncTask;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

public class load_employee extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    boolean a;
    public static boolean val=false;
    int c;
    String user_Name="";
    String pss="";
    String res="";
    List<String> list= new ArrayList<>();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public void parameters(String userName, String userPassword)
    {
        user_Name=userName;
        pss=userPassword;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://construction.genial365.com/WebServices/AndroidAPI/GetEmployees.asmx/GetEmployes?supervisorId=12168&shiftTime=morning");
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
                int c=0;
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
               String usr_name= (String) JO.get("userName");
                String usr_id= (String) JO.get("userId");
               c++;
               BluetoothReader.users[c]=usr_name;
                BluetoothReader.users2[c]=usr_id;
                //BluetoothReader.list.add(a);
//                singleParsed =  "Name:" + JO.get("name") + "\n"+
//                        "Password:" + JO.get("password") + "\n"+
//                        "Contact:" + JO.get("contact") + "\n"+
//                        "Country:" + JO.get("country") + "\n";
//
//                dataParsed = dataParsed + singleParsed +"\n" ;


            }
                JSONObject JO = (JSONObject) JA.get(0);
                res = (String) JO.get("Response");
                if (res.equals("200")) {

                    a = true;
                } else {

                    a = false;
                }
            }else{
                a=false;
            }

        } catch(MalformedURLException e){
            a = false;
        } catch(IOException e){
            a = false;
        } catch(JSONException e){
            a = false;
        }


        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);




    }
}
