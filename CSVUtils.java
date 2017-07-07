/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4_finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CSVUtils {
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private String csvFile;
    private QuestionsAndAnswers first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth;
    private List<String> line;
    private QuestionsAndAnswers arrayOfQuestions[];     // array of QuestionAndAnswerObjects
    private List<QuestionsAndAnswers> qAndAlist = new ArrayList<QuestionsAndAnswers>();
    
    
    
    
    public CSVUtils(){} // constructor
    
    
    public List<QuestionsAndAnswers> CSVReadFile(String filename){
        try {
            csvFile = filename;
            
            Scanner scanner = new Scanner(new File(csvFile));
            
            line = parseLine(scanner.nextLine());                
            first = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));
            qAndAlist.add(first);
            
            line = parseLine(scanner.nextLine());   
            second = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));
            qAndAlist.add(second);
            
            line = parseLine(scanner.nextLine());  
            third = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));
            qAndAlist.add(third);
            
            line = parseLine(scanner.nextLine());   
            fourth = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));            
            qAndAlist.add(fourth);
            
            line = parseLine(scanner.nextLine());   
            fifth = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));            
            qAndAlist.add(fifth);
                        
            line = parseLine(scanner.nextLine());   
            sixth = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));            
            qAndAlist.add(sixth);
            
            line = parseLine(scanner.nextLine());   
            seventh = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));            
            qAndAlist.add(seventh);
            
            line = parseLine(scanner.nextLine());   
            eighth = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));            
            qAndAlist.add(eighth);
            
            line = parseLine(scanner.nextLine());   
            ninth = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));            
            qAndAlist.add(ninth);
            
            line = parseLine(scanner.nextLine());   
            tenth = new QuestionsAndAnswers(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4), line.get(5));            
            qAndAlist.add(tenth);
          
            scanner.close();
                        
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qAndAlist;
    }
    
    
    
    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
 
}