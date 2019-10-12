/*
 * @(#)Token.java                        2.1 2003/10/07
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


final class Token extends Object {

  protected int kind;
  protected String spelling;
  protected SourcePosition position;

  public Token(int kind, String spelling, SourcePosition position) {

    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;

      while (searching) {
        int comparison = tokenTable[currentKind].compareTo(spelling);
        if (comparison == 0) {
          this.kind = currentKind;
          searching = false;
        } else if (comparison > 0 || currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
        }
      }
    } else
      this.kind = kind;

    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling +
      ", position=" + position;
  }

  // Token classes...

  public static final int

    // literals, identifiers, operators...
    INTLITERAL	= 0,
    CHARLITERAL	= 1,
    IDENTIFIER	= 2,
    OPERATOR	= 3,

    // reserved words - must be in alphabetical order...
    ARRAY		= 4,
    BEGIN		= 5,
    CONST		= 6,
    DO			= 7,
    ELSE		         = 8,
    END			= 9,
    FOR                   =10, //ssm_changes Se agrega la palabra reservada FOR 
    FUNC		        = 11,
    IF			= 12,
    IN			= 13,
    LET			= 14,
    LOOP                  = 15, //ssm_changes Se agrega la palabra reservada LOOP 
    OF			= 16,
    PROC	             	= 17,
    RECORD		= 18,
    REPEAT                =19, //ssm_changes Se agrega la palabra reservada REPEAT
    SKIP                  =20, //ssm_changes Se agrega la palabra reservada SKIP
    THEN                  = 21,
    TO                    =22,     //ssm_changes Se agrega la palabra reservada TO
    TYPE		         = 23,
    UNTIL                 =24,  //ssm_changes Se agrega la palabra reservada UNTIL     
    VAR			= 25,
    WHILE		= 26,

    // punctuation...
    DOT			= 27,
    COLON		= 28,
    SEMICOLON	= 29,
    COMMA		= 30,
    BECOMES		= 31,
    IS			= 32,

    // brackets...
    LPAREN		= 33,      
    RPAREN		= 34,
    LBRACKET	= 35,
    RBRACKET	= 36,
    LCURLY		= 37,
    RCURLY		= 38,

    // special tokens...
    EOT			= 33,
    ERROR		= 34;

  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "array",
    "begin",
    "const",
    "do",
    "else",
    "end",
    "for",          //ssm_changes Se agrega la palabra reservada FOR
    "func",
    "if",
    "in",
    "let",
    "loop",         //ssm_changes Se agrega la palabra reservada LOOP
    "of",
    "proc",
    "record",
    "repeat",       //ssm_changes Se agrega la palabra reservada REPEAT
    "skip",         //ssm_changes Se agrega la palabra reservada SKIP
    "then",
    "to",           //ssm_changes Se agrega la palabra reservada TO
    "type",
    "until",        //ssm_changes Se a�ade la palabra reservada UNTIL
    "var",
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "~",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "<error>"
  };

  private final static int	firstReservedWord = Token.ARRAY,
  				lastReservedWord  = Token.WHILE;

}
