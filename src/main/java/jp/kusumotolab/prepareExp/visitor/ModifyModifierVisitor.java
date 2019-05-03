package jp.kusumotolab.prepareExp.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import java.util.Iterator;

public class ModifyModifierVisitor extends ASTVisitor {

    @Override
    public boolean visit(final MethodDeclaration node) {
        if(node.isConstructor() && node.getParent() instanceof EnumDeclaration) {
            return false;
        }

        final Iterator<Object> iterator = node.modifiers().iterator();
        while(iterator.hasNext()) {
            final Object object = iterator.next();
            if(object instanceof Modifier) {
                final Modifier modifier = (Modifier) object;
                if(modifier.isPrivate() || modifier.isProtected()) {
                    iterator.remove();
                }
                if(modifier.isPublic()) {
                    return false;
                }
            }
        }

        node.modifiers().add(node.getAST().newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        return false;
    }
}
