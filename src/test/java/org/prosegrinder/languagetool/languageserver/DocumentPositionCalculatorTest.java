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
package org.prosegrinder.languagetool.languageserver;

import org.eclipse.lsp4j.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DocumentPositionCalculatorTest {

    public final DocumentPositionCalculator sut = new DocumentPositionCalculator("Hello\n" +
            "Enthusiastic\r\n" +
            "Reader!");

    @Test
    public void testGetPosition_first_character() {
        Position position = sut.getPosition(0);

        Assertions.assertEquals(new Position(0, 0), position);
    }

    @Test
    public void testGetPosition_starts_with_newline() {
        Position position = new DocumentPositionCalculator("\nHi").getPosition(1);

        Assertions.assertEquals(new Position(1, 0), position);
    }

    @Test
    public void testGetPosition_second_line_start() {
        Position position = sut.getPosition(6);

        Assertions.assertEquals(new Position(1, 0), position);
    }

    @Test
    public void testGetPosition_second_line_second_character() {
        Position position = sut.getPosition(7);

        Assertions.assertEquals(new Position(1, 1), position);
    }

    @Test
    public void testGetPosition_arbitrary_position() {
        Position position = sut.getPosition(12);

        Assertions.assertEquals(new Position(1, 6), position);
    }
}
