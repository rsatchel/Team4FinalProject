/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4_finalproject;

 
public class QuestionsAndAnswers {
    private String question, answer1, answer2, answer3, answer4, correct_answer;
    
    public QuestionsAndAnswers(){}

// Constructor
    public QuestionsAndAnswers(String q, String a1, String a2, String a3, String a4, String a_correct){
        this.question = q;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3;
        this.answer4 = a4;
        this.correct_answer = a_correct;
        
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @return the answer1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * @return the answer2
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * @return the answer3
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * @return the answer4
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * @return the correct_answer
     */
    public String getCorrect_answer() {
        return correct_answer;
    }
    
    /**
     * @print each object pulled from the CSV file
     */
    public void printQandAobject(){
        System.out.println("Printing object: " + this.getQuestion() + " " + this.getAnswer1() + " " + this.getAnswer2() + " " + this.getAnswer3() 
                + this.getAnswer4() + " " + this.getCorrect_answer());
                
    }
    
}