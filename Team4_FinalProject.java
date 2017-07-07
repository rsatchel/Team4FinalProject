
package team4_finalproject;


import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;




public class Team4_FinalProject extends JFrame {

    
    
    
    public Team4_FinalProject() throws IOException, JSONException {
        GridLayout grid = new GridLayout(2,1);
        setLayout(grid);              
        this.add(new Team4_GUI());
        
    }

    public static void main(String[] args) throws Exception {
        

        
//       WeatherData wd = new WeatherData();
//       Double todaysTemp = wd.getTemp();
//       System.out.println("Today's Temp : " + todaysTemp);
       
       // create frame and display it
       Team4_FinalProject t4fp = new Team4_FinalProject();
       t4fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       t4fp.setTitle("CSE360 Team 4 Final Project");
       t4fp.setSize(1000, 400);
       t4fp.setVisible(true);
       //t4fp.pack();
       
    }    
}