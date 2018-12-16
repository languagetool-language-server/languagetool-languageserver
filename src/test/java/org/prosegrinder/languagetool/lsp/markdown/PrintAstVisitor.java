// In memory of Adam Voss, July 11, 1991 - July 11, 2018
// https://github.com/adamvoss
// http://schluterbalikfuneralhome.com/obituary/adam-voss
package org.prosegrinder.languagetool.lsp.markdown;

import com.vladsch.flexmark.ast.Node;

class PrintAstVisitor {
    public void visit(Node node, int indent) {
        String i = "";

        for (int j = 0; j < indent; j++) {
            i += "  ";
        }

        System.out.println(i + node.toAstString(true));

        node.getChildIterator().forEachRemaining(n -> this.visit(n, indent + 1));
    }
}
