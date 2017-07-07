
package team4_finalproject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class CalculateQuestionScore {
    
    private WeatherData temperature;
    private List randomValues;
    private List questionGrades;
    
    ////////////////////////
////////    pass gradedAnswer, answerTime, (check answerTimeFactor)
    ///////////////////////
    public CalculateQuestionScore(){                // constructor 
        questionGrades = new ArrayList<>();
        Iterator itr = questionGrades.iterator();
        
        // get 10 random values 
        randomValues = new ArrayList<>();
        Collections.fill(randomValues, getRandom());
        
        //System.out.println("List of Random Vals: " + randomValues.toString());
        
        
        
        
        

    }
    
    public double getGradeFromBoolean(List gradedAnswer, int index){
        double booleanToDoubleGrade;
        List booleanAnswer = gradedAnswer;
        String grade = gradedAnswer.get(index).toString();
        
        if (grade == "true"){
            booleanToDoubleGrade = 0.2;
        }
        else
            booleanToDoubleGrade = 3.0;
      
        //System.out.println("Boolean to Double : " + booleanToDoubleGrade);
        return booleanToDoubleGrade;
    }
    
    // get current temperature from Darksky
    public double getCurrentTemp() throws IOException, JSONException{
        WeatherData wd = new WeatherData();
        Double temperature = wd.getTemp();
        //System.out.println("Temperature: " + temperature);
        return temperature;                
    }
    
    // scales temperature from Darksky between 0-120F to 0.00-1.00
    public double getScaledTemp(Double temp){
        double temperatureScaleFactor = 0.00833333;
        double scaledTemperature = temp * temperatureScaleFactor;
        //System.out.println("Scaled Temperature : " + scaledTemperature);
        return scaledTemperature;
    }
    
    // gets random factor for scoring answers
    public double getRandom(){
        double randomFactor = Math.random();
        return randomFactor;
    }
    
    // returns the current hour from 00 to 23
    public int getCurrentHour(){
        SimpleDateFormat format;
        Date now = new Date();
        String df = DateFormat.getTimeInstance(DateFormat.SHORT).format(now);
        //System.out.println(df);
        format = new SimpleDateFormat("H");
        String timeToString = format.format(now);
        //System.out.println("Current hour: " + Integer.parseInt(timeToString));
        return Integer.parseInt(timeToString);
    }

//// not used    
    public double getAnswerTimeFactor(List<Long> timeToAnswer){
         Iterator itr = timeToAnswer.iterator();
////////        if  answerTime is is some range....
        double scale = 0.0001;
        
        return scale;
        
    }

}