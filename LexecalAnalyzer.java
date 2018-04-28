
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Group Members: Azargul (1414322) and Zaki ()
 * 
 * In this project the following lexemes can be generated:
 *  1. Arithmatic (+, -, *, /, %)
 *  2. Increment and decrement (++, --)
 *  3. Comparision Operators (>, <, <=, >=,!=, ==)
 *  4. Logical Opertors (!, &&, ||)
 *  5. Bit mode operationis (<<, >>>, >>, ^, ~, |, &)
 *  6. Assignment Operators (=, +=, -=, *=, /=, %=, <<=, >>=, ^=, ~=, &=, !=)
 *  7. String and char ("", ''). Note: the character lexeme doesn't include the skip sequence characters
 *  8. Number (int and double)
 *  9. ID (indentfiers)
 *  10. Special symbols ([], (), {}, ., ,, :, ?, ;)
 *  11. Ignores the comments (Single Line or Block line comment)
*/


public class LexecalAnalyzer {

    // HashSet for storing the keywords
    static HashSet<String> keywords;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        
        
        // special keywords
        String[] keys = {"abstract","class","void","class","static","assert","new","int","boolean","true","for","if","break","byte","switch","case","finally","catch","try","char","continue","do","while","double","else","enum","extends","final","float","implements","instanceof","long","native","package","private","protected","return","short","interface","strictfp","super","synchronized","this","throw","throws","transient","volatile","String","public"};
        keywords = new HashSet<>(Arrays.asList(keys));

        
        
        // Scanner for scanning the file
        Scanner input;
        File inputFile = new File("file.goood");

        
        // to store all the code
        StringBuilder totalSourceCode;
        
        
        // if file doesnt exist terminates the execution
        if (!inputFile.exists()) {
            System.out.println("Sorry! the file code is not found");
            System.exit(0);
            
        }
        else {
            
            input = new Scanner(inputFile);
            totalSourceCode = new StringBuilder();
            
            while (input.hasNextLine()) {
                totalSourceCode.append(input.nextLine()).append("\n");
            }
            
            // The ## denotes the end of our file
            totalSourceCode.append("##");
            
            // call to takenizer to generate tokens
            tokenizer(totalSourceCode);
            
            
            System.out.println("Tokens have been identified successfully....!!!");
        }

    }

    
    // This method generates the tokens
    public static void tokenizer(StringBuilder sourceCode) {

        // state of finite automat
        int state = 0;
        
        // to store the lexems
        String lexeme = "";
        
        // to read the character
        char currentChar;
        
        int index = 0;
        
        
        while (true) {

            

            switch (state) {

                case 0:
                    // if it's single space, tab or any other type of space
                    currentChar = sourceCode.charAt(index++);
                    
                    if (Character.isWhitespace(currentChar)) {
                        state = 0;
                    } 

                    // if the read character is the matched with the first character type of identifier 
                    else if (Character.isJavaIdentifierStart(currentChar)) {
                        state = 1;
                        lexeme += currentChar;
                    }
                    
                    // if the read character is the digit 
                    else if (Character.isDigit(currentChar)) {
                        state = 3;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '+') {
                        state = 5;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '-') {
                        state = 6;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '*') {
                        state = 7;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '/') {
                        state = 8;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '%') {
                        state = 9;
                        lexeme += currentChar;
                    }
                    
                    else if (currentChar == '=') {
                        state = 10;
                        lexeme += currentChar;
                    }
                    
                    
                    else if (currentChar == '<') {
                        state = 11;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '>') {
                        state = 12;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '!') {
                        state = 13;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '&') {
                        state = 14;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '|') {
                        state = 15;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '^') {
                        state = 16;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '~') {
                        state = 17;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '.') {
                        state = 18;
                        lexeme += currentChar;
                    }
                    
                    else if (currentChar == ',') {
                        state = 19;
                        lexeme += currentChar;
                    } else if (currentChar == ';') {
                        state = 20;
                        lexeme += currentChar;
                    } else if (currentChar == ':') {
                        state = 21;
                        lexeme += currentChar;
                    }
                    
                    else if (currentChar == '?') {
                        state = 22;
                        lexeme += currentChar;
                    } 
                    else if (currentChar == '{') {
                        state = 23;
                        lexeme += currentChar;
                    } else if (currentChar == '}') {
                        state = 24;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '[') {
                        state = 25;
                        lexeme += currentChar;
                    }
                    else if (currentChar == ']') {
                        state = 26;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '(') {
                        state = 27;
                        lexeme += currentChar;
                    }
                    else if (currentChar == ')') {
                        state = 28;
                        lexeme += currentChar;
                    }
                    else if (currentChar == '"') {
                        state = 29;
                    }
                    else if (currentChar == '\'') {
                        state = 30;
                    }
                    
                    else if(currentChar == '#'){
                        state = 500;
                    }

                    
                    break;

                    
                    
                // Case 1 is for identifer    
                case 1:
                    
                    currentChar = sourceCode.charAt(index++);
                    if (Character.isJavaIdentifierPart(currentChar)) {
                        state = 1;
                        lexeme += currentChar;
                    }else {
                        state = 2;
                        index--;
                    }
                    break;

                // After complete read of identifier name
                case 2:
                    state = 0;
                    if (keywords.contains(lexeme)) {
                        System.out.printf("%-15s%s\n", lexeme ,"Reserved Word");
                    } else {
                        System.out.printf("%-15s%s\n", lexeme ,"ID");
                    }
                    lexeme = "";
                    break;

                    
                // After complete read of integer    
                case 3:
                    
                    currentChar = sourceCode.charAt(index++);
                    if (Character.isDigit(currentChar)) {
                        state = 3;
                        lexeme += currentChar;
                    }
                    
                    // if after integer there was point then it means there's possibility of being double value
                    else if (currentChar == '.') {
                        state = 200;
                        lexeme += currentChar;
                    }else {
                        state = 4;
                        index--;
                    }
                    break;

                // if the read lexeme was integer
                case 4:
                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"INTEGER");
                    lexeme = "";
                    break;

                // if the read character is +
                case 5:

                    currentChar = sourceCode.charAt(index++);
                    
                    // ++ operator
                    if (currentChar == '+') {
                        state = 0;System.out.printf("%-15s%s\n", lexeme ,"INCREAMENT");
                    }
                    
                    // += operator
                    else if (currentChar == '=') {
                        // code for detecting the +=
                        System.out.printf("%-15s%s\n", lexeme ,"INCREAMENT ASSIGNMENT");
                        state = 0;
                    }
                    
                    // + operator
                    else {
                        // the chacter is +    
                        state = 0;
                        System.out.printf("%-15s%s\n", lexeme ,"ARITHMATIC");
                        index--;
                    }
                    lexeme = "";

                    break;

                    
                    
                // if the read character is -
                case 6:

                    currentChar = sourceCode.charAt(index++);
                    
                    // -- operator
                    if (currentChar == '-') {
                        // code for finding the --
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"DECREAMENT");
                    }
                    
                    // -= operator
                    else if (currentChar == '=') {
                        // code for detecting the -=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"DECREAMENT ASSIGNMENT");
                    } 
                    
                    // - operator
                    else {
                        // the chacter is -    
                        System.out.printf("%-15s%s\n", lexeme ,"ARTIHAMTIC");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is *
                case 7:

                    currentChar = sourceCode.charAt(index++);
                    
                    // *= opeartor
                    if (currentChar == '=') {
                        // code for finding the *=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"MUL ASSIGNMENT");
                        state = 0;
                    } 
                    
                    // * operator
                    else {
                        // the chacter is *   
                        state = 0;
                        System.out.printf("%-15s%s\n", lexeme ,"MUL");
                        index--;
                    }
                    lexeme = "";

                    break;

                // if the read character is /
                case 8:

                    currentChar = sourceCode.charAt(index++);
                    
                    // /= operator
                    if (currentChar == '=') {
                        // code for finding the /=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"DIV ASSIGNMENT");
                        state = 0;
                    }
                    
                    // single line comment
                    else if(currentChar == '/'){
                        while((currentChar = sourceCode.charAt(index++)) != '\n'){
                            
                        }
                        state = 0;
                    }
                    
                    // Block line commet
                    else if(currentChar == '*'){
                        while(true){
                            if(currentChar == '*'){
                                currentChar = sourceCode.charAt(index++);
                                if(currentChar == '/') break;
                            }
                            currentChar = sourceCode.charAt(index++);
                        }
                        state = 0;
                    }
                    
                    // / operator
                    else {
                       
                        state = 0;
                        System.out.printf("%-15s%s\n", lexeme ,"DIVISION");
                        index--;
                    }
                    lexeme = "";

                    break;

                    
                // if the read character is %
                case 9:

                    currentChar = sourceCode.charAt(index++);
                    
                    // %= operator
                    if (currentChar == '=') {
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"MOD ASSIGNMENT");
                    } 
                    
                    // % operator
                    else {
                        System.out.printf("%-15s%s\n", lexeme ,"MODULE");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is =
                case 10:
                    currentChar = sourceCode.charAt(index++);
                    
                    // == operator
                    if (currentChar == '=') {
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"EQUAL");
                       
                    } 
                    
                    // = operator
                    else {
                        // the chacter is =  
                        System.out.printf("%-15s%s\n", lexeme ,"ASSIGNMENT");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is <
                case 11:
                    currentChar = sourceCode.charAt(index++);
                    
                    // << operator
                    if (currentChar == '<') {
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"LEFT-SHIFT");
                    }
                    
                    // <= operator
                    else if (currentChar == '=') {
                        // code for finding the <=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"LE");
                    } 
                    
                    // < operator
                    else {
                        // the chacter is <   
                        System.out.printf("%-15s%s\n", lexeme ,"LT");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is >
                case 12:

                    currentChar = sourceCode.charAt(index++);
                    
                    // >> operator
                    if (currentChar == '>') {
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"RIGHT-SHIFT");
                    }
                    
                    // >= operator
                    else if (currentChar == '=') {
                        // code for finding the >=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"GE");
                    }
                    
                    // > operator
                    else {
                        // the chacter is > 
                        System.out.printf("%-15s%s\n", lexeme ,"GT");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is !
                case 13:

                    currentChar = sourceCode.charAt(index++);
                   
                    // != operator
                    if (currentChar == '=') {
                        // code for finding the !=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"NOT EQUAL");
                    } 
                    
                    // ! operator
                    else {
                        // the chacter is !
                        System.out.printf("%-15s%s\n", lexeme ,"NOT");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is &
                case 14:

                    currentChar = sourceCode.charAt(index++);
                    
                    // && operator
                    if (currentChar == '&') {
                        // code for finding the &&
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"AND");
                    }
                    
                    // &= operator
                    else if (currentChar == '=') {
                        // code for findign the &=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"AND EQUAL");
                    } 
                    
                    // & operator
                    else {
                        // the chacter is &
                        System.out.printf("%-15s%s\n", lexeme ,"AND");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is |
                case 15:

                    currentChar = sourceCode.charAt(index++);
                   
                    // || operator
                    if (currentChar == '|') {
                        // code for finding the ||
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"(");
                    } 
                    
                    // |= operator
                    else if (currentChar == '=') {
                        // code for findign the |=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"OR ASSIGN");
                    } 
                    
                    // | operator
                    else {
                        // the chacter is |
                        System.out.printf("%-15s%s\n", lexeme ,"OR");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is ^
                case 16:

                    currentChar = sourceCode.charAt(index++);
                   
                    if (currentChar == '=') {
                        // code for finding the ^=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"XOR ASSIGN");
                    } else {
                        // the chacter is ^
                        System.out.printf("%-15s%s\n", lexeme ,"XOR");
                        index--;
                    }
                    state = 0;
                    lexeme = "";
                    break;

                // if the read character is ~
                case 17:

                    currentChar = sourceCode.charAt(index++);
                    if (currentChar == '=') {
                        // code for finding the ~=
                        lexeme += currentChar;
                        System.out.printf("%-15s%s\n", lexeme ,"NOT ASSIGNMENT");
                    } else {
                        // the chacter is ~
                        System.out.printf("%-15s%s\n", lexeme ,"NOR");
                        index--;
                    }
                    state = 0;
                    lexeme = "";

                    break;

                // if the read character is .
                case 18:

                    // the chacter is .
                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"DOT");
                    
                    lexeme = "";

                    break;

                case 19:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"COMMA");
                    lexeme = "";

                    break;

                // if the read character is ;
                case 20:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"SIMI-COLON");
                    lexeme = "";

                    break;

                // if the read character is :
                case 21:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"COLON");
                    lexeme = "";
                    
                    break;

                // if the read character is ?
                case 22:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"Q-MARK");
                    lexeme = "";

                    break;

                // if the read character is {
                case 23:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"LEFT-BRACE");
                    lexeme = "";

                    break;

                // if the read character is }
                case 24:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"RIGHT-BRACE");
                    lexeme = "";

                    break;

                    
                    
                // if the read character is [
                case 25:

                    state = 0;
                     System.out.printf("%-15s%s\n", lexeme ,"LEFT-BRACKET");
                    lexeme = "";

                    break;

                // if the read character is ]
                case 26:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"RIGHT-BRACKET");
                    lexeme = "";

                    break;

                // if the read character is (
                case 27:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"LEFT-PARENTHESE");
                    lexeme = "";

                    break;

                // if the read character is )
                case 28:

                    state = 0;
                    System.out.printf("%-15s%s\n", lexeme ,"RIGHT-PARENTHESE");
                    lexeme = "";

                    break;
                    
                case 29:
                    
                    currentChar = sourceCode.charAt(index++);
                    
                    // string 
                    if(currentChar == '"'){
                        state = 0;
                        System.out.printf("%-" + (lexeme.length() + 5) + "s%s\n", lexeme ,"STRING");
                        lexeme = "";
                    }
                    else{
                       state = 29;
                       lexeme += currentChar;
                    }
             
                    break;    
                
                case 30:
                    
                    currentChar = sourceCode.charAt(index++);
                    
                    // to check the character format
                    if(currentChar != '\''){
                        state = 300;
                        lexeme += currentChar;
                    }
                    else{
                       System.out.println("Error");
                       System.exit(0);
                    }
             
                    break;      
                    
                    // for checking the decimal point
                case 200:
                    
                    currentChar = sourceCode.charAt(index++);
                    if(Character.isDigit(currentChar)){
                        state = 200;
                        lexeme += currentChar;
                    }
                    else{
                        state = 0;
                        System.out.printf("%-15s%s\n", lexeme ,"Double");
                        lexeme = "";
                        index--;
                    }
                    
                    break;
                    
                    
                 // character lexeme   
                case 300:
                    
                    currentChar = sourceCode.charAt(index++);
                    if(currentChar == '\''){
                        state = 0;
                        System.out.printf("%-15s%s\n", lexeme ,"Character");  
                    }
                    else{
                        System.out.println("Error");
                        System.exit(0);
                    }
                    
                    break;
                    
                    
                    
                 // the end of file   
                 case 500:
                    
                    currentChar = sourceCode.charAt(index++);
                    if(currentChar == '#'){
                        return;
                    }
                    else{
                        System.out.println("Error");
                        System.exit(0);
                    }
                    
                    break;   
            }
        }

    }

}
