/*
 * In memory of Adam Voss, original creator
 * July 11, 1991 - July 11, 2018
 * https://github.com/adamvoss
 * http://schluterbalikfuneralhome.com/obituary/adam-voss
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * Copyright (c) 2018, MPL Adam Voss vossad01@gmail.com
 *
 */
package org.prosegrinder.languagetool.languageserver.markdown;

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
