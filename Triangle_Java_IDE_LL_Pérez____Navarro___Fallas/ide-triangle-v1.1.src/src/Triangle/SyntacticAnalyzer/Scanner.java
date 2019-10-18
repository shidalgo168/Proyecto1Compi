/*
 * @(#)Scanner.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.SyntacticAnalyzer;




public final class Scanner {
  
  private SourceFile sourceFile;
  private char currentChar;
  private boolean debug;
  private StringBuffer currentSpelling;
  private boolean currentlyScanningToken;
  private String htmlText; //stores html text
  private String comment; //stores the comment


  private boolean isLetter(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  private boolean isDigit(char c) {
    return (c >= '0' && c <= '9');
  }

// isOperator returns true iff the given character is an operator character.

  private boolean isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/' ||
	    c == '=' || c == '<' || c == '>' || c == '\\' ||
	    c == '&' || c == '@' || c == '%' || c == '^' ||
	    c == '?');
  }


///////////////////////////////////////////////////////////////////////////////

   public String getHtmlText(){
       return htmlText;
   }
   
  public Scanner(SourceFile source) {

    sourceFile = source;
    currentChar = sourceFile.getSource();
    debug = false;
    
    
  }

  public void enableDebugging() {
    debug = true;
  }

  // takeIt appends the current character to the current token, and gets
  // the next character from the source program.

  private void takeIt() {
    if (currentlyScanningToken)
      currentSpelling.append(currentChar);
    currentChar = sourceFile.getSource();
  }

  // scanSeparator skips a single separator.

  private void scanSeparator() {
    switch (currentChar) {
    case '!':
      {
        comment += currentChar;
        takeIt();
        while ((currentChar != SourceFile.EOL) && (currentChar != SourceFile.EOT)){
            comment += currentChar;
            takeIt();
        }
        
        if (currentChar == SourceFile.EOL){
            comment += "<br>";
            takeIt();
        }
        insertHtmlText(comment,44);
        comment = "";
      }
      break;

    case ' ': case '\n': case '\r': case '\t':
        insertHtmlText(String.valueOf(currentChar),45);
        takeIt();
      break;
    }
  }

  private int scanToken()  {
    

    switch (currentChar) {

    case 'a':  case 'b':  case 'c':  case 'd':  case 'e':
    case 'f':  case 'g':  case 'h':  case 'i':  case 'j':
    case 'k':  case 'l':  case 'm':  case 'n':  case 'o':
    case 'p':  case 'q':  case 'r':  case 's':  case 't':
    case 'u':  case 'v':  case 'w':  case 'x':  case 'y':
    case 'z':
    case 'A':  case 'B':  case 'C':  case 'D':  case 'E':
    case 'F':  case 'G':  case 'H':  case 'I':  case 'J':
    case 'K':  case 'L':  case 'M':  case 'N':  case 'O':
    case 'P':  case 'Q':  case 'R':  case 'S':  case 'T':
    case 'U':  case 'V':  case 'W':  case 'X':  case 'Y':
    case 'Z':
      takeIt();
      while (isLetter(currentChar) || isDigit(currentChar))
        takeIt();
      return Token.IDENTIFIER;

    case '0':  case '1':  case '2':  case '3':  case '4':
    case '5':  case '6':  case '7':  case '8':  case '9':
      takeIt();
      while (isDigit(currentChar))
        takeIt();
      
     
      
      return Token.INTLITERAL;

    case '+':  case '-':  case '*': case '/':  case '=':
    case '<':  case '>':  case '\\':  case '&':  case '@':
    case '%':  case '^':  case '?':
      takeIt();
      while (isOperator(currentChar))
        takeIt();
      return Token.OPERATOR;

    case '\'':
      takeIt();
      takeIt(); // the quoted character
      if (currentChar == '\'') {
      	takeIt();
        return Token.CHARLITERAL;
      } else
        return Token.ERROR;

    case '.':
      takeIt();
      return Token.DOT;

    case ':':
      takeIt();
      if (currentChar == '=') {
        takeIt();
        return Token.BECOMES;
      } else
        return Token.COLON;

    case ';':
      takeIt();
      return Token.SEMICOLON;

    case ',':
      takeIt();
      return Token.COMMA;

    case '~':
      takeIt();
      return Token.IS;

    case '(':
      takeIt();
      return Token.LPAREN;

    case ')':
      takeIt();
      return Token.RPAREN;

    case '[':
      takeIt();
      return Token.LBRACKET;

    case ']':
      takeIt();
      return Token.RBRACKET;

    case '{':
      takeIt();
      return Token.LCURLY;

    case '}':
      takeIt();
      return Token.RCURLY;

    case SourceFile.EOT:
      return Token.EOT;

    default:
      takeIt();
      return Token.ERROR;
    }
  }

  public Token scan () {
    Token tok;
    SourcePosition pos;
    int kind;

    currentlyScanningToken = false;
    while (currentChar == '!'
           || currentChar == ' '
           || currentChar == '\n'
           || currentChar == '\r'
           || currentChar == '\t')
      scanSeparator();

    currentlyScanningToken = true;
    currentSpelling = new StringBuffer("");
    pos = new SourcePosition();
    pos.start = sourceFile.getCurrentLine();

    kind = scanToken();

    pos.finish = sourceFile.getCurrentLine();
    tok = new Token(kind, currentSpelling.toString(), pos);
    insertHtmlText(currentSpelling.toString(), tok.kind); //insert reserved word into html
    if (debug)
      System.out.println(tok);
    return tok;
  }
  
  // inserts the htmlText into the html
  public void insertHtmlText(String htmlT, int tokenType){
       switch(tokenType) {
           
          //insert reserved words 
          case Token.ARRAY:  
          case Token.CONST:  
          case Token.DO:
          case Token.ELSE:  
          case Token.END:   
          case Token.FUNC:
          case Token.IF:  
          case Token.IN:  
          case Token.INIT:  
          case Token.LET:
          case Token.OF:  
          case Token.PROC:
          case Token.RECORD:    
          case Token.THEN:    
          case Token.TYPE:  
          case Token.VAR:  
          case Token.WHILE: 
              //faltan and, for, local, to, recursive, repeat, skip, to, until 
              htmlText+=("<span class='reserved'>"+htmlT+"</span>");
              break;
              
          //inser literals
          case Token.CHARLITERAL:
          case Token.INTLITERAL:  
              htmlText+=("<span class='literal'>"+htmlT+"</span>");
              break;
              
          //insert comments   
          case 44:
              htmlText+=("<span class='comment'>"+htmlT+"</span>");
              break;
              
          //insert spaces 
          case 45:
            switch  (htmlT) {
                case "\n":
                    htmlText+="<br>";
                    break;
                case "\t":
                    htmlText+="&ensp;";
                    break;
                case " ":
                    htmlText+="&nbsp;";
                    break;
                case "null":
                    break;
                    
                default:
                    break;
        }   
          default:
              htmlText+=htmlT;
              break;
      }
   }
    

}
