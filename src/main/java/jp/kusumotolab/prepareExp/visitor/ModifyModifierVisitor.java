package jp.kusumotolab.prepareExp.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class ModifyModifierVisitor extends ASTVisitor {
    @Override
    public boolean visit(final MethodDeclaration node) {
        return super.visit(node);
    }
}
