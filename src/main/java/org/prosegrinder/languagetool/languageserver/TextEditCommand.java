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
 */
package org.prosegrinder.languagetool.languageserver;

import org.eclipse.lsp4j.*;

import java.util.Collections;

class TextEditCommand extends Command {

  static final String CommandName = "langugageTool.acceptSuggestion";

  public TextEditCommand(String title, Range range, TextDocumentItem document) {
    this.setCommand(CommandName);

    VersionedTextDocumentIdentifier id = new VersionedTextDocumentIdentifier(document.getUri(), document.getVersion());
    id.setUri(document.getUri());
    this.setArguments(
        Collections.singletonList(new TextDocumentEdit(id, Collections.singletonList(new TextEdit(range, title)))));
    this.setTitle(title);
  }
}
