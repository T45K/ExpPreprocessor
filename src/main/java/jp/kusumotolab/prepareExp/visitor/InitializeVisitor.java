package jp.kusumotolab.prepareExp.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class InitializeVisitor extends ASTVisitor {
    @Override
    public boolean visit(final VariableDeclarationStatement node) {
        for (final Object object : node.fragments()) {
            final VariableDeclarationFragment fragment = (VariableDeclarationFragment) object;
            if (fragment.getInitializer() == null) {
                if(Modifier.isFinal(node.getModifiers())){
                    node.modifiers().remove(Modifier.ModifierKeyword.FINAL_KEYWORD);
                }
            }

            final Type type = node.getType();
            if(type instanceof PrimitiveType){
                if(type.toString().endsWith("boolean")){
                    fragment.setInitializer(node.getAST().newBooleanLiteral(false));
                }else {
                    fragment.setInitializer((node.getAST().newNumberLiteral("0")));

                }
            }else{
                fragment.setInitializer(node.getAST().newNullLiteral());
            }
        }

        return true;
    }
}
