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

import org.eclipse.lsp4j.Position;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

class DocumentPositionCalculator {

    private final int[] lineStartPositions;

    public DocumentPositionCalculator(String text) {
        lineStartPositions = getLineStartPositions(text);
    }

    @NotNull
    private static Position getPosition(int pos, int[] lineStarts) {

        int line = Arrays.binarySearch(lineStarts, pos);

        if (line < 0) {
            int insertion_point = -1 * line - 1;
            line = insertion_point - 1;
        }

        return new Position(line, pos - lineStarts[line]);
    }

    private static int[] getLineStartPositions(String text) {
        ArrayList<Integer> lineStartPositions = new ArrayList<>();
        lineStartPositions.add(0);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '\r') {
                if (i + 1 < text.length() && text.charAt(i + 1) == '\n') {
                    i++;
                }

                lineStartPositions.add(i + 1);
            } else if (c == '\n') {
                lineStartPositions.add(i + 1);
            }
        }

        return lineStartPositions.stream().mapToInt(i -> i).toArray();
    }

    public Position getPosition(int pos) {
        return getPosition(pos, lineStartPositions);
    }
}