package team4_finalproject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import org.json.JSONException;

public class Team4_GUI extends JPanel implements ActionListener {

    private static JLabel questionLabel;
    private static JLabel answer1label;
    private static JLabel answer2label;
    private static JLabel answer3label;
    private static JLabel answer4label;
    private static String correctAnswer;
    private static int questionCounter = 0;

    private static JButton ansButton1;
    private static JButton ansButton2;
    private static JButton ansButton3;
    private static JButton ansButton4;

    private static String question;
    private static String answer1;
    private static String answer2;
    private static String answer3;
    private static String answer4;
    private static String answer_correct;
    private List<Boolean> gradedAnswer = new ArrayList<Boolean>();
    private List<Long> answerTime = new ArrayList<Long>();
    
    private static long timeAsked;
    private static long timeAnswered;
    private static CalculateQuestionScore cqs;
    private static double scaledTemp;

    private static JPanel questionPanel;
    private static JPanel answerPanel;
    private static JPanel basePanel;

    private static QuestionsAndAnswers questionAndAnswerObject;
    private static List<QuestionsAndAnswers> listOfQsAndAs;
    
    public static CalculateQuestionScore calcQuesScore; 

    public Team4_GUI() throws IOException, JSONException {      // constructor

        listOfQsAndAs = new ArrayList<QuestionsAndAnswers>();
        String CSVfilename = "src/team4_finalproject/QuestionsAndAnswer.csv"; 
        CSVUtils util = new CSVUtils();
        CalculateQuestionScore cqs = new CalculateQuestionScore();
        //calcQuesScore = new CalculateQuestionScore();

        // create an ArrayList of QuestionsAndAnswers objects
        listOfQsAndAs = util.CSVReadFile(CSVfilename);
/*
        // print all of the QuestionAndAnswer Objects
        /////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < 10; i++) {     
            // print the contents of the whole file of questions and answers
            listOfQsAndAs.get(i).printQandAobject();
        }
*/
        ///////////////////////////////////////////////////////////////////////////
        // questionPanel and answerPanel sit on top of basePanel
        basePanel = new JPanel(new GridLayout(2, 1));
        questionPanel = new JPanel(new GridLayout(1, 1));
        answerPanel = new JPanel(new GridLayout(1, 4));

        questionLabel = new JLabel();
        scaledTemp = cqs.getScaledTemp(cqs.getCurrentTemp());

        ansButton1 = new JButton();
        ansButton2 = new JButton();
        ansButton3 = new JButton();
        ansButton4 = new JButton();

        ansButton1.addActionListener(this);
        ansButton2.addActionListener(this);
        ansButton3.addActionListener(this);
        ansButton4.addActionListener(this);

        // ask first question
        setQuestion(questionCounter);
        
        // time that the quiz has begun
        long timeStart = System.currentTimeMillis();        

    }

    public void setQuestion(int questionCounter) {        
        question = listOfQsAndAs.get(questionCounter).getQuestion();
        questionLabel.setText(question);           // get question, add it                        
        questionPanel.add(questionLabel);               // to the label

        answer1 = listOfQsAndAs.get(questionCounter).getAnswer1();
        answer2 = listOfQsAndAs.get(questionCounter).getAnswer2();
        answer3 = listOfQsAndAs.get(questionCounter).getAnswer3();
        answer4 = listOfQsAndAs.get(questionCounter).getAnswer4();

        ansButton1.setText(answer1);
        ansButton2.setText(answer2);
        ansButton3.setText(answer3);
        ansButton4.setText(answer4);

        answerPanel.add(ansButton1);
        answerPanel.add(ansButton2);
        answerPanel.add(ansButton3);
        answerPanel.add(ansButton4);

        basePanel.add(questionPanel);
        basePanel.add(answerPanel);
        this.add(basePanel);
        timeAsked = System.currentTimeMillis();
    }

    public JPanel setQuestionPanel(QuestionsAndAnswers qAndA) {
        questionPanel = new JPanel();
        questionLabel = new JLabel(qAndA.getQuestion());
        questionPanel.add(questionLabel);
        return questionPanel;
    }

    public JPanel setAnswerPanel(QuestionsAndAnswers qAndA) {
        answer1label = new JLabel(qAndA.getAnswer1());
        answer2label = new JLabel(qAndA.getAnswer2());
        answer3label = new JLabel(qAndA.getAnswer3());
        answer4label = new JLabel(qAndA.getAnswer4());

        answerPanel.setLayout(new GridLayout(1, 4));
        answerPanel.add(answer1label);
        answerPanel.add(answer2label);
        answerPanel.add(answer3label);
        answerPanel.add(answer4label);

        return answerPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {      
        // get time when user clicked answer
        timeAnswered = System.currentTimeMillis();  

        // questionCounter runs from 0 to 9
        if (questionCounter <= 9 ) {
            String selectedAnswer;
            System.out.println("Question Counter: " + questionCounter);

            if (e.getSource() == ansButton1) {
                System.out.println("Button 1 clicked");
                selectedAnswer = listOfQsAndAs.get(questionCounter).getAnswer1();
            } else if (e.getSource() == ansButton2) {
                System.out.println("Button 2 clicked");
                selectedAnswer = listOfQsAndAs.get(questionCounter).getAnswer2();
            } else if (e.getSource() == ansButton3) {                
                System.out.println("Button 3 clicked");
                selectedAnswer = listOfQsAndAs.get(questionCounter).getAnswer3();
            } else {
                System.out.println("Button 4 clicked");
                selectedAnswer = listOfQsAndAs.get(questionCounter).getAnswer4();
            }
            
            System.out.println("Selected Answer: " + selectedAnswer);
            
            // tell user if they have selected correct answer or not
            if (selectedAnswer.equals(listOfQsAndAs.get(questionCounter).getCorrect_answer())){
                System.out.println("QUESTION COUNTER:  " + questionCounter);
                System.out.println("CORRECT!");
                gradedAnswer.add(Boolean.TRUE);
            }
            else {
                System.out.println("WRONG!");
                System.out.println("QUESTION COUNTER:  " + questionCounter);
                gradedAnswer.add(Boolean.FALSE);
            }
            // make a new CalculateQuestionScore object for each answer
            
            // increment counter, add answer time to arraylist and select next question            
            answerTime.add(timeAnswered - timeAsked);
            
            calcQuesScore = new CalculateQuestionScore();
            double getBooleanGradeAsDouble = calcQuesScore.getGradeFromBoolean(gradedAnswer, questionCounter);                
            double rando = calcQuesScore.getRandom();
            double hour = (double)calcQuesScore.getCurrentHour();                
            double scaledFactor = getBooleanGradeAsDouble * scaledTemp * rando * hour;
            System.out.println("Scaled Answer Factor: " + scaledFactor);
            
            if(scaledFactor < 2){
                System.out.println("Wow, you're clever!");                
            }
            else if(scaledFactor >=2 && scaledFactor < 4){
                System.out.println("Pretty good!");
            }
            else if (scaledFactor >=4 && scaledFactor < 6){
                System.out.println("Doing well, keep up the good work!");
            }
            else if (scaledFactor >= 6 && scaledFactor < 8){
                System.out.println("You're above average!");
            }
            else if (scaledFactor >= 8 && scaledFactor < 10){
                System.out.println("You are perfectly average!");
            }
            else if (scaledFactor >= 10 && scaledFactor < 12){
                System.out.println("Slightly below average, lift your game!");
            }
            else if (scaledFactor >= 12 && scaledFactor < 14){
                System.out.println("You're struggling a bit!");                
            }
            else if (scaledFactor >= 14 && scaledFactor < 16){
                System.out.println("Did you have to Google that one?");
            }
            else if (scaledFactor >= 16 && scaledFactor < 18){
                System.out.println("Looks like you Googled it and still got it wrong!");
            }
            else if (scaledFactor > 18){
                System.out.println("You should probably go back to hig school!");
            }
            
            
            
            
            
            questionCounter++;
            
            
            
            // if at end of quiz give results and answre times
            if(questionCounter == 10){
                System.out.println("Results: " + gradedAnswer.toString());
                System.out.println("Answer times: " + answerTime.toString());
                System.out.println("End Of Test"); 
                
                return;
            }
            else
                setQuestion(questionCounter);

        }   
    }

}