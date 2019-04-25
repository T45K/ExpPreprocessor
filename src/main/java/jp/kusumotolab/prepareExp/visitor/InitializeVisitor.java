package jp.kusumotolab.prepareExp.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class InitializeVisitor extends ASTVisitor {
    @Override
    public boolean visit(final VariableDeclarationExpression node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(final VariableDeclarationStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(final SingleVariableDeclaration node) {
        return super.visit(node);
    }
}
