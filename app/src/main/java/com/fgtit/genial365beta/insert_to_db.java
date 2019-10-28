package com.fgtit.genial365beta;

        import com.fgtit.utils.DBHelper;

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

public class insert_to_db {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    boolean a;
    boolean value;
     public static boolean val;

    int c;
    String user_Name="";
    String pss="";
    String res="";
    DBHelper db;
    List<String> list= new ArrayList<>();




 public void execute(String sup_id) {

        try {
            URL url = new URL("http://construction.genial365.com/WebServices/AndroidAPI/GetEmployees.asmx/GetEmployes?supervisorId="+sup_id+"&shiftTime=morning");
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

                    DBHelper.users[c]=usr_name;
                    DBHelper.users2[c]=usr_id;
                    c++;
                    //BluetoothReader.list.add(a);
//                singleParsed =  "Name:" + JO.get("name") + "\n"+
//                        "Password:" + JO.get("password") + "\n"+
//                        "Contact:" + JO.get("contact") + "\n"+
//                        "Country:" + JO.get("country") + "\n";
//
//                dataParsed = dataParsed + singleParsed +"\n" ;


                }
                val=true;

                if (c>0) {
                    dash.txt_val.setText("Data Inserted");
                    a = true;
                } else {

                    dash.txt_val.setText("Check Your Internet Conection and Login again");
                }
            }else{
                a=false;
                dash.txt_val.setText("Check Your Internet Conection and Login again");
            }

        } catch(MalformedURLException e){
            a = false;
            dash.txt_val.setText("Check Your Internet Conection and Login again");
        } catch(IOException e){
            a = false;
            dash.txt_val.setText("Check Your Internet Conection and Login again");
        } catch(JSONException e){
            a = false;
            dash.txt_val.setText("Check Your Internet Conection and Login again");
        }

    }

    public boolean ok()
    {
        return value;
    }
}
