/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author sergi ssm_changes add class
 */

import Triangle.SyntacticAnalyzer.SourcePosition;

public class RecursiveDeclaration extends Declaration {

    public RecursiveDeclaration(Declaration d1AST, Declaration d2AST, 
            SourcePosition thePosition) {
        super(thePosition);
        PF1 = d1AST;
        PF2 = d2AST;
    }

    public Object visit(Visitor v, Object o) {
        return v.visitRecursiveDeclaration(this, o);
    }
    
    public Declaration PF1, PF2;
}
