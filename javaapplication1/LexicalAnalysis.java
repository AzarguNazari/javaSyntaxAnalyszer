package javaapplication1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalysis {

    
    
    // class variables
    private Scanner input;
    private ArrayList<token> symbolTable;
    private TreeSet<String> keywords;
    private Pattern p;
    private Matcher match;
    
    
    
    // Java Language's Code Regex
    private final String IDENTIFIERS = "([_\\$a-zA-Z](\\w|\\$)*)|([\\][\\w\\$]*)";  // ok checked
    private final String NUMBERS = "((\\d+\\.\\d+)|(\\d+))";
    private final String LETERAL = "(\".*\")|('.')"; // ok checked
    private final String OPERATORS = "(\\+(\\+|=)?|-(-|=)?|\\/=?|\\*=?|%=?|==?|<<?=?|>>?=?|&(&|=)?|\\^=?|!=?|\\|\\|?|~=?)"; // ok checked
    private final String META_CHARACTERS = "(;|,|:|\\)|\\(|\\{|\\}|\\.)";   // ok checked
    
    
    
    
    public LexicalAnalysis(File file) throws FileNotFoundException{
        
        
        
        // if file does not exist
        if(!file.exists()){
            throw new FileNotFoundException("Sorry! The file do not exist");
        }
        
        
        input = new Scanner(file);
        symbolTable = new ArrayList<>();
        keywords = new TreeSet<>();
        
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
        
        
        
        p = Pattern.compile(IDENTIFIERS + "|" + OPERATORS + "|" + LETERAL + "|" + META_CHARACTERS);
    }
    
    public ArrayList<token> getTokens(){
        
        StringBuilder s = new StringBuilder();
        
        while (input.hasNextLine()) {
            s.append(input.nextLine());
        }

        input.close();

        match = p.matcher(s);

        String word;
        while (match.find()) {
            word = match.group();

            // identifers
            if (word.matches("[a-zA-Z](\\w|\\$)*")) {
                if (keywords.contains(word)) {
                    symbolTable.add(new token(word, "keyword"));
                } else {
                    symbolTable.add(new token(word, "identifier"));
                }
            } 
            // number
            else if (word.matches("([\\d]+(\\s)?\\.(\\s)?[\\d]+)|(\\d+)")) {
                symbolTable.add(new token(word, "number"));
            } 
            // String or char leteral
            else if(word.matches("(\".*\")|('.')")){
                symbolTable.add(new token(word, "leteral"));
            }
            
            // operators
            else if (word.matches("\\+|-|\\*|\\/|%")) {
                symbolTable.add(new token(word, "ar_operator"));
            }
            
            // assignment operator
            else if (word.matches("\\+=|-=|\\/=|\\*=|%=|==?|<<=|>>=|&=|\\^=|~=")) {
                symbolTable.add(new token(word, "assign_operator"));
            }
            
            // comparison opeartor
            else if (word.matches("<|>|<=|>=|!=|")) {
                symbolTable.add(new token(word, "comparison_operator"));
            }
            
            // logical operator
            else if (word.matches("&&|\\|\\||!")) {
                symbolTable.add(new token(word, "logical_operator"));
            }
            
            // bit_operator
            else if (word.matches("&|\\||\\^|~|<<|>>>?")) {
                symbolTable.add(new token(word, "simicolon"));
            }
            
            // simicolon
            else if (word.equals(";")) {
                symbolTable.add(new token(word, "simicolon"));
            } 
            
            // left-parenthese
            else if (word.equals("(")) {
                symbolTable.add(new token(word, "left-parenthese"));
            } 
            
            // right-parenthtese
            else if (word.equals(")")) {
                symbolTable.add(new token(word, "right-parenthese"));
            } 
            
            // left-curley braces
            else if (word.equals("{")) {
                symbolTable.add(new token(word, "left-brace"));
            }
            
            // right-curley braces
            else if (word.equals("}")) {
                symbolTable.add(new token(word, "right-brace"));
            } 
            
            // colon
            else if (word.equals(":")) {
                symbolTable.add(new token(word, "colon"));
            } 
            
            // comma
            else if (word.equals(",")) {
                symbolTable.add(new token(word, "comma"));
            }
            
            // point
            else if (word.equals(".")) {
                symbolTable.add(new token(word, "point"));
            } 
            
        }
        
        return symbolTable;
    }
    
}

class token {

        String key, value;

        public token(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "  =>  " + value;
        }
    }