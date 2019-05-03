package jp.kusumotolab.prepareExp.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import java.util.Iterator;

public class VariableInitializeVisitor extends ASTVisitor {
    @Override
    public boolean visit(final VariableDeclarationStatement node) {
        for (final Object object : node.fragments()) {
            final VariableDeclarationFragment fragment = (VariableDeclarationFragment) object;

            if (fragment.getInitializer() != null) {
                continue;
            }

            if (Modifier.isFinal(node.getModifiers())) {
                node.modifiers().removeIf(modifier -> modifier.toString().equals("final"));
            }

            final Type type = node.getType();
            if (!(type instanceof PrimitiveType)) {
                fragment.setInitializer(node.getAST().newNullLiteral());
                continue;
            }

            if (type.toString().endsWith("boolean")) {
                fragment.setInitializer(node.getAST().newBooleanLiteral(false));
            } else {
                fragment.setInitializer((node.getAST().newNumberLiteral("0")));
            }
        }

        return true;
    }
}
