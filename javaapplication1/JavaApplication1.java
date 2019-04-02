package javaapplication1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaApplication1 {

    static TreeSet<String> keywords = new TreeSet<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // special keywords
        // #Needs ordering
        keywords.add("abstract");
        keywords.add("class");
        keywords.add("void");
        keywords.add("class");
        keywords.add("static");
        keywords.add("assert");
        keywords.add("new");
        keywords.add("int");
        keywords.add("boolean");
        keywords.add("true");
        keywords.add("for");
        keywords.add("if");
        keywords.add("break");
        keywords.add("byte");
        keywords.add("switch");
        keywords.add("case");
        keywords.add("finally");
        keywords.add("catch");
        keywords.add("try");
        keywords.add("char");
        keywords.add("continue");
        keywords.add("do");
        keywords.add("while");
        keywords.add("double");
        keywords.add("else");
        keywords.add("enum");
        keywords.add("extends");
        keywords.add("final");
        keywords.add("float");
        keywords.add("implements");
        keywords.add("instanceof");
        keywords.add("long");
        keywords.add("native");
        keywords.add("package");
        keywords.add("private");
        keywords.add("protected");
        keywords.add("return");
        keywords.add("short");
        keywords.add("interface");
        keywords.add("strictfp");
        keywords.add("super");
        keywords.add("synchronized");
        keywords.add("this");
        keywords.add("throw");
        keywords.add("throws");
        keywords.add("transient");
        keywords.add("volatile");
        keywords.add("String");
        keywords.add("public");

        Scanner input;
        File inputFile = new File("file.goood");

        StringBuilder totalSourceCode;
        if (!inputFile.exists()) {
            System.err.println("Sorry! the file code is not found");
            System.exit(0);
        } else {
            input = new Scanner(inputFile);
            totalSourceCode = new StringBuilder();
            while (input.hasNextLine()) {
                totalSourceCode.append(input.nextLine());
            }
            // call to takenizer to generate tokens
            tokenizer(totalSourceCode);
            System.out.println("Tokens have been identified successfully....!!!");
            //getch();
        }

    }

    public static void tokenizer(StringBuilder sourceCode) {

        int state = 0;		// integer variable representing states
        //char specifiers[] = {'n', 'a', 't', 'r', 'v', 'o', 'f', '?', '"', 'b', 39, 92};
        // specifiers  39 represents ' and 92 represents \ 
        int k = 0;
        boolean flag = false;
        String lexeme = "";
        //Read one character from the input file and store it in currentChar 
        //variable
        char currentChar;
        
        int index = 0;
        currentChar = sourceCode.charAt(index);
        while(index < sourceCode.length()) {
            
            

            switch (state) {

                case 0:
                    if (Character.isWhitespace(currentChar)) {
                        state = 0;
                        currentChar = sourceCode.charAt(++index);
                    }
                    else if (Character.isJavaIdentifierStart(currentChar)){
                        state = 1;
                        lexeme += currentChar;
                    } else if (Character.isDigit(currentChar)) {
                        state = 3;
                        lexeme += currentChar;
                    } else if (currentChar == ',') {
                        state = 5;
                        lexeme += currentChar;
                    } else if (currentChar == ';') {
                        state = 6;
                        lexeme += currentChar;
                    } else if (currentChar == ':') {
                        state = 7;
                        lexeme += currentChar;
                    } else if (currentChar == '?') {
                        state = 8;
                        lexeme += currentChar;
                    } else if (currentChar == '{') {
                        state = 9;
                        lexeme += currentChar;
                    } else if (currentChar == '}') {
                        state = 10;
                        lexeme += currentChar;
                    } else {
                        System.err.println("Sorry, there is an error in code at index  (" + index + ")");
                        state = 0;
                    }

                    break;

                case 1:
                    //Read the next character from the input file
                    currentChar = sourceCode.charAt(++index);
                    if (Character.isJavaIdentifierPart(currentChar)) {
                        state = 1;
                        lexeme += currentChar;
                    } else {
                        state = 2;
                        index--;
                    }

                    break;

                case 2:
                    state = 0;
                    //The following code will unget the last character 
                    //read from the input file
                    currentChar = sourceCode.charAt(++index);
                    if (keywords.contains(lexeme)) {
                        flag = true;
                        
                    }

                    //The following code is used to write the lexeme and its token 
                    //in the output file
                    if (flag) //If it is reserved word
                    {
                        //Write statements here to print the lexeme 
                        //and the reserved word
                        System.out.println(lexeme + " (Reserved Word)");
                        flag = false;
                    } else //If it is identifier
                    {
                        //Write statements here to print the lexeme 
                        //and its corresponding token “ID”					}
                        System.out.println(lexeme + " (" + " ID");
                    }
                    lexeme = "";
                    break;

                case 3:
                    //Write statement to read one character from the 
                    //input file and store it in currentChar variable
                    currentChar = sourceCode.charAt(++index);
                    if (Character.isDigit(currentChar)) {
                        state = 3;
                        lexeme += currentChar;
                    } else {
                        state = 4;
                    }
                    break;
                case 4:
                    state = 0;
                    //lexeme[i]='\0';
                    System.out.println(lexeme + " (INTEGER)");
                    index--;
                    //Write statements here to print the lexeme 
                    //and its corresponding token “INTEGER”					
                    lexeme = "";
                    currentChar = sourceCode.charAt(++index);
                    break;
                //The following cases are used to recognize delimeters or punctuation marks
                case 5:
                    state = 0;
                    System.out.println(lexeme + " (COMMA)");
                    //lexeme[i]='\0';
                    //Write statements here to print the lexeme 
                    //and its corresponding token “COMMA”						
                    lexeme = "";
                    //Write statement here to read one character from 
                    //the input file and store it in currentChar variable
                    currentChar = sourceCode.charAt(++index);
                    break;

                case 6:
                    state = 0;
                    //lexeme[i]='\0';
                    System.out.println(lexeme + " (SEMI_COMMA)");
                    //Write statements here to print the lexeme 
                    //and its corresponding token “SEMI_COMMA”	
                    lexeme = "";
                    currentChar = sourceCode.charAt(++index);
                    break;
                //Write statement here to read one character from 
                //the input file and store it in currentChar variable					
        

                case 7:
                    state = 0;
                    //lexeme[i] = '\0';
                    System.out.println(lexeme + " (COLON)");
                    //Write statements here to print the lexeme 
                    //and its corresponding token “COLON”							
                    lexeme = "";
                    //Write statement here to read one character from 
                    //the input file and store it in currentChar variable
                    currentChar = sourceCode.charAt(++index);
                    break;
                case 8:
                    state = 0;
                    //lexeme[i] = '\0';
                    System.out.println(lexeme + " (QUESTION_MARK)");
                    //Write statements here to print the lexeme 
                    //and its corresponding token 
                    //“QUESTION_MARK”					
                    lexeme = "";
                    //Write statement here to read one character from 
                    //the input file and store it in currentChar variable
                    currentChar = sourceCode.charAt(++index);
                    break;

                case 9:
                    state = 0;
                    //lexeme[i] = '\0';
                    //Write statements here to print the lexeme 
                    //and its corresponding token 
                    //“LEFT_CURLY”'
                    System.out.println(lexeme + " (LEFT_CURLY)");
                    lexeme = "";
                    //Write statement here to read one character from 
                    //the input file and store it in currentChar variable						
                    currentChar = sourceCode.charAt(++index);
                    break;
                    
                case 10:
                    state = 0;
                    //lexeme[i] = '\0';
                    System.out.println(lexeme + " (RIGHT_CURLY)");
                    //Write statements here to print the lexeme 
                    //and its corresponding token 
                    //“RIGHT_CURLY”										
                    lexeme = "";
                    //Write statement here to read one character from 
                    //the input file and store it in currentChar variable						
                    currentChar = sourceCode.charAt(++index);
                    break;
            }
        }

    }
}
