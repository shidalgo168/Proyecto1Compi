/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.TreeWriterXML;

import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.InitDeclaration;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.LocalDeclaration;
import Triangle.AbstractSyntaxTrees.LoopDoUntilCommand;
import Triangle.AbstractSyntaxTrees.LoopDoWhileCommand;
import Triangle.AbstractSyntaxTrees.LoopForCommand;
import Triangle.AbstractSyntaxTrees.LoopUntilCommand;
import Triangle.AbstractSyntaxTrees.LoopWhileCommand;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.RecursiveDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Marco Gamboa
 */
public class XMLWriterVisitor implements Visitor{

    private FileWriter fileWriter;
    private int qTabs;

    XMLWriterVisitor(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
        qTabs=0;
    }

    // Commands
    @Override
    public Object visitAssignCommand(AssignCommand ast, Object obj) {
        writeLineXML("<AssignCommand>");
        qTabs++;
        ast.V.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</AssignCommand>");
        return null;
    }

    @Override
    public Object visitCallCommand(CallCommand ast, Object obj) {
        writeLineXML("<CallCommand>");
        qTabs++;
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        qTabs--;
        writeLineXML("</CallCommand>");
        return null;
    }

    @Override
    public Object visitEmptyCommand(EmptyCommand ast, Object obj) {
        writeLineXML("<EmptyCommand/>");
        return null;
    }

    @Override
    public Object visitIfCommand(IfCommand ast, Object obj) {
        writeLineXML("<IfCommand>");
        qTabs++;
        ast.E.visit(this, null);
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
        qTabs--;
        writeLineXML("</IfCommand>");
        return null;
    }

    @Override
    public Object visitLetCommand(LetCommand ast, Object obj) {
        writeLineXML("<LetCommand>");
        qTabs++;
        ast.D.visit(this, null);
        ast.C.visit(this, null);
        qTabs--;
        writeLineXML("</LetCommand>");
        return null;
    }

    @Override
    public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
        writeLineXML("<SequentialCommand>");
        qTabs++;
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
        qTabs--;
        writeLineXML("</SequentialCommand>");
        return null;
    }

   /* @Override
    public Object visitWhileCommand(WhileCommand ast, Object obj) {
        return null;
    }*/


    // Expressions
    @Override
    public Object visitArrayExpression(ArrayExpression ast, Object obj) {
        writeLineXML("<ArrayExpression>");
        qTabs++;
        ast.AA.visit(this, null);
        qTabs--;
        writeLineXML("</ArrayExpression>");
        return null;
    }

    @Override
    public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
        writeLineXML("<BinaryExpression>");
        qTabs++;
        ast.E1.visit(this, null);
        ast.O.visit(this, null);
        ast.E2.visit(this, null);
        qTabs--;
        writeLineXML("</BinaryExpression>");
        return null;
    }
    @Override
    public Object visitCallExpression(CallExpression ast, Object obj) {
        writeLineXML("<CallExpression>");
        qTabs++;
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        qTabs--;
        writeLineXML("</CallExpression>");
        return null;
    }

    @Override
    public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
        writeLineXML("<CharacterExpression>");
        qTabs++;
        ast.CL.visit(this, null);
        qTabs--;
        writeLineXML("</CharacterExpression>");
        return null;
    }

    @Override
    public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
        writeLineXML("<EmptyExpression/>");
        return null;
    }

    @Override
    public Object visitIfExpression(IfExpression ast, Object obj) {
        writeLineXML("<IfExpression>");
        qTabs++;
        ast.E1.visit(this, null);
        ast.E2.visit(this, null);
        ast.E3.visit(this, null);
        qTabs--;
        writeLineXML("</IfExpression>");
        return null;
    }

    @Override
    public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
        writeLineXML("<IntegerExpression>");
        qTabs++;
        ast.IL.visit(this, null);
        qTabs--;
        writeLineXML("</IntegerExpression>");
        return null;
    }

    @Override
    public Object visitLetExpression(LetExpression ast, Object obj) {
        writeLineXML("<LetExpression>");
        qTabs++;
        ast.D.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</LetExpression>");
        return null;
    }

    @Override
    public Object visitRecordExpression(RecordExpression ast, Object obj) {
        writeLineXML("<RecordExpression>");
        qTabs++;
        ast.RA.visit(this, null);
        qTabs--;
        writeLineXML("</RecordExpression>");
        return null;
    }

    @Override
    public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
        writeLineXML("<UnaryExpression>");
        qTabs++;
        ast.O.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</UnaryExpression>");
        return null;
    }

    @Override
    public Object visitVnameExpression(VnameExpression ast, Object obj) {
        writeLineXML("<VnameExpression>");
        qTabs++;
        ast.V.visit(this, null);
        qTabs--;
        writeLineXML("</VnameExpression>");
        return null;
    }


    // Declarations
    @Override
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
        writeLineXML("<BinaryOperatorDeclaration>");
        qTabs++;
        ast.O.visit(this, null);
        ast.ARG1.visit(this, null);
        ast.ARG2.visit(this, null);
        ast.RES.visit(this, null);
        qTabs--;
        writeLineXML("</BinaryOperatorDeclaration>");
        return null;
    }

    @Override
    public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
        writeLineXML("<ConstDeclaration>");
        qTabs++;
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</ConstDeclaration>");
        return null;
    }

    @Override
    public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
        writeLineXML("<FuncDeclaration>");
        qTabs++;
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.T.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</FuncDeclaration>");
        return null;
    }
    
    @Override
    public Object visitInitDeclaration(InitDeclaration ast, Object obj){
        writeLineXML("<InitDeclaration>");
        qTabs++;
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</InitDeclaration>");
        return null;
    }

    @Override
    public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
        writeLineXML("<ProcDeclaration>");
        qTabs++;
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.C.visit(this, null);
        qTabs--;
        writeLineXML("</ProcDeclaration>");
        return null;
    }

    @Override
    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
        writeLineXML("<SequentialDeclaration>");
        qTabs++;
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
        qTabs--;
        writeLineXML("</SequentialDeclaration>");
        return null;
    }

    @Override
    public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
        writeLineXML("<TypeDeclaration>");
        qTabs++;
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        qTabs--;
        writeLineXML("</TypeDeclaration>");
        return null;
    }

    @Override
    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
        writeLineXML("<UnaryOperatorDeclaration>");
        qTabs++;
        ast.O.visit(this, null);
        ast.ARG.visit(this, null);
        ast.RES.visit(this, null);
        qTabs--;
        writeLineXML("</UnaryOperatorDeclaration>");
        return null;
    }

    @Override
    public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
        writeLineXML("<VarDeclaration>");
        qTabs++;
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        qTabs--;
        writeLineXML("</VarDeclaration>");
        return null;
    }


    @Override
    // Array Aggregates
    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
        writeLineXML("<MultipleArrayAggregate>");
        qTabs++;
        ast.E.visit(this, null);
        ast.AA.visit(this, null);
        qTabs--;
        writeLineXML("</MultipleArrayAggregate>");
        return null;
    }

    @Override
    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
        writeLineXML("<SingleArrayAggregate>");
        qTabs++;
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</SingleArrayAggregate>");
        return null;
    }


    @Override
    // Record Aggregates
    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
        writeLineXML("<MultipleRecordAggregate>");
        qTabs++;
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        ast.RA.visit(this, null);
        qTabs--;
        writeLineXML("</MultipleRecordAggregate>");
        return null;
    }

    @Override
    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
        writeLineXML("<SingleRecordAggregate>");
        qTabs++;
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</SingleRecordAggregate>");
        return null;
    }

    @Override
    // Formal Parameters
    public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
        writeLineXML("<ConstFormalParameter>");
        qTabs++;
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        qTabs--;
        writeLineXML("</ConstFormalParameter>");
        return null;
    }

    @Override
    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
        writeLineXML("<FuncFormalParameter>");
        qTabs++;
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.T.visit(this, null);
        qTabs--;
        writeLineXML("<FuncFormalParameter>");
        return null;
    }

    @Override
    public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
        writeLineXML("<ProcFormalParameter>");
        qTabs++;
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        qTabs--;
        writeLineXML("</ProcFormalParameter>");
        return null;
    }

    @Override
    public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
        writeLineXML("<VarFormalParameter>");
        qTabs++;
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        qTabs--;
        writeLineXML("</VarFormalParameter>");
        return null;
    }

    @Override
    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
       
        writeLineXML("<EmptyFormalParameterSequence/>");
        return null;
    }

    @Override
    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
        writeLineXML("<MultipleFormalParameterSequence>");
        qTabs++;
        ast.FP.visit(this, null);
        ast.FPS.visit(this, null);
        qTabs--;
        writeLineXML("</MultipleFormalParameterSequence>");
        return null;
    }

    @Override
    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
        writeLineXML("<SingleFormalParameterSequence>");
        qTabs++;
        ast.FP.visit(this, null);
        qTabs--;
        writeLineXML("</SingleFormalParameterSequence>");
        return null;
    }

    @Override
    // Actual Parameters
    public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
        writeLineXML("<ConstActualParameter>");
        qTabs++;
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</ConstActualParameter>");
        return null;
    }

    @Override
    public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
        writeLineXML("<FuncActualParameter>");
        qTabs++;
        ast.I.visit(this, null);
        qTabs--;
        writeLineXML("</FuncActualParameter>");
        return null;
    }

    @Override
    public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
        writeLineXML("<ProcActualParameter>");
        qTabs++;
        ast.I.visit(this, null);
        qTabs--;
        writeLineXML("</ProcActualParameter>");
        return null;
    }

    @Override
    public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
        writeLineXML("<VarActualParameter>");
        qTabs++;
        ast.V.visit(this, null);
        qTabs--;
        writeLineXML("</VarActualParameter>");
        return null;
    }

    @Override
    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
       
        writeLineXML("<EmptyActualParameterSequence/>");
        return null;
    }

    @Override
    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
        writeLineXML("<MultipleActualParameterSequence>");
        qTabs++;
        ast.AP.visit(this, null);
        ast.APS.visit(this, null);
        qTabs--;
        writeLineXML("</MultipleActualParameterSequence>");
        return null;
    }

    @Override
    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
        writeLineXML("<SingleActualParameterSequence>");
        qTabs++;
        ast.AP.visit(this, null);
        qTabs--;
        writeLineXML("</SingleActualParameterSequence>");
        return null;
    }

    @Override
    // Type Denoters
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
       
        writeLineXML("<AnyTypeDenoter/>");
        return null;
    }

    @Override
    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
        writeLineXML("<ArrayTypeDenoter>");
        qTabs++;
        ast.IL.visit(this, null);
        ast.T.visit(this, null);
        qTabs--;
        writeLineXML("</ArrayTypeDenoter>");
        return null;
    }

    @Override
    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
       
        writeLineXML("<BoolTypeDenoter/>");
        return null;
    }

    @Override
    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
       
        writeLineXML("<CharTypeDenoter/>");
        return null;
    }

    @Override
    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
       
        writeLineXML("<ErrorTypeDenoter/>");
        return null;
    }

    @Override
    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
        writeLineXML("<SimpleTypeDenoter>");
        qTabs++;
        ast.I.visit(this, null);
        qTabs--;
        writeLineXML("</SimpleTypeDenoter>");
        return null;
    }

    @Override
    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
       
        writeLineXML("<IntTypeDenoter/>");
        return null;
    }

    @Override
    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
        writeLineXML("<RecordTypeDenoter>");
        qTabs++;
        ast.FT.visit(this, null);
        qTabs--;
        writeLineXML("</RecordTypeDenoter>");
        return null;
    }


    @Override
    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
        writeLineXML("<MultipleFieldTypeDenoter>");
        qTabs++;
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        ast.FT.visit(this, null);
        qTabs--;
        writeLineXML("</MultipleFieldTypeDenoter>");
        return null;
    }
    @Override
    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
        writeLineXML("<SingleFieldTypeDenoter>");
        qTabs++;
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        qTabs--;
        writeLineXML("</SingleFieldTypeDenoter>");
        return null;
    }

    @Override
    // Literals, Identifiers and Operators
    public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
        writeLineXML("<CharacterLiteral value=\"" + ast.spelling + "\"/>");
        return null;
    }
    
    @Override
    public Object visitIdentifier(Identifier ast, Object obj) {
        writeLineXML("<Identifier value=\"" + ast.spelling + "\"/>");
        return null;
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
        writeLineXML("<IntegerLiteral value=\"" + ast.spelling + "\"/>");
        return null;
    }

    @Override
    public Object visitOperator(Operator ast, Object obj) {
        writeLineXML("<Operator value=\"" + transformOperator(ast.spelling) + "\"/>");
        return null;
    }

    @Override
    // Value-or-variable names
    public Object visitDotVname(DotVname ast, Object obj) {
        writeLineXML("<DotVname>");
        qTabs++;
        ast.V.visit(this, null);
        ast.I.visit(this, null);
        qTabs--;
        writeLineXML("</DotVname>");
        return null;
    }
    @Override
    public Object visitSimpleVname(SimpleVname ast, Object obj) {
        writeLineXML("<SimpleVname>");
        qTabs++;
        ast.I.visit(this, null);
        qTabs--;
        writeLineXML("</SimpleVname>");
        return null;
    }

    @Override
    public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
        writeLineXML("<SubscriptVname>");
        qTabs++;
        ast.V.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</SubscriptVname>");
        return null;
    }
    
    @Override
    public Object visitLoopWhileCommand(LoopWhileCommand ast, Object o) {
        writeLineXML("<WhileCommand>");
        qTabs++;
        ast.C.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</WhileCommand>");
        return null;
    }

    @Override
    public Object visitLoopUntilCommand(LoopUntilCommand ast, Object o) {
        writeLineXML("<Until Command>");
        qTabs++;
        ast.C.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        writeLineXML("</Until Command>");
        return null;
    }

    @Override
    public Object visitLoopDoWhileCommand(LoopDoWhileCommand ast, Object o) {
        qTabs++;
        ast.C.visit(this, null);
        ast.E.visit(this, null);
        qTabs--;
        return null;
    }

    @Override
    public Object visitLoopDoUntilCommand(LoopDoUntilCommand ast, Object o) {
        qTabs++;
        ast.C.visit(this, null);
        ast.E.visit(this, null);        
        qTabs--;
        return null; 
    }

    @Override
    public Object visitLoopForCommand(LoopForCommand ast, Object o) {
        qTabs++;
        ast.C.visit(this, null);
        ast.D.visit(this, null);
        ast.E.visit(this, null);        
        qTabs--;
        return null; 
    }


    @Override
    public Object visitLocalDeclaration(LocalDeclaration ast, Object o) {
        qTabs++;
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
        qTabs--;
        return null;
    }

    @Override
    public Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o) {
        qTabs++;
        ast.PF1.visit(this, null);
        ast.PF2.visit(this, null);
        qTabs--;
        return null;
    }
    
    @Override
    // Programs
    public Object visitProgram(Program ast, Object obj) {
        writeLineXML("<Program>");
        qTabs++;
        ast.C.visit(this, null);
        qTabs--;
        writeLineXML("</Program>");
        return null;
        
    }

    
    private void writeLineXML(String line) {
        try {          
            if(qTabs>=0)
                for(int i = 0; i<qTabs; i++){ fileWriter.write("\t"); }
            fileWriter.write(line);
            fileWriter.write('\n');
        } catch (IOException e) {
            System.err.println("Error while writing file for print the AST");
            e.printStackTrace();
        }
    }
    
    /*
     * Convert the characters "<" & "<=" to their equivalents in html
     */ 
    private String transformOperator(String operator) {
        if (operator.compareTo("<") == 0)
            return "&lt;";
        else if (operator.compareTo("<=") == 0)
            return "&lt;=";
        else
            return operator;
    }    
 
}
