
package team4_finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherData {
    private Double temp = 0.0;
    DecimalFormat oneDigit = new DecimalFormat("#,##0.0");//format to 1 decimal place 

    public WeatherData(){   // constructor
        
    }
    
    public Double getTemp() throws IOException, JSONException {
        String yourKey = "cab82799b5b1e817dbccab51d6d7ec40"; 
        JSONObject json = readJsonFromUrl("https://api.darksky.net/forecast/"
                + yourKey +"/33.421968,-111.936642");
        
        DecimalFormat oneDigit = new DecimalFormat("#,##0.0");//format to 1 decimal place  
        
        Object timeZone = json.get("timezone");
        temp = json.getJSONObject("currently").getDouble("temperature");                
        return temp;
    }
    
    


private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }


  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
        
      is.close();
    }
  }
}
