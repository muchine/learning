package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StripCommentsTest {

    @Test
    public void stripComments() throws Exception {
        String stripped = StripComments.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } );
        System.out.println(stripped);
        assertEquals("apples, pears\ngrapes\nbananas", stripped);


        stripped = StripComments.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } );
        System.out.println(stripped);
        assertEquals("a\nc\nd", stripped);
    }

    @Test
    public void test1() {
        String text = "!\n" +
                "\n" +
                "d\n" +
                "\n" +
                "c\n" +
                "\n" +
                "ccca\n" +
                "\n" +
                "be\n" +
                "\n" +
                "\n" +
                "!b\n" +
                "\n" +
                "a\n" +
                "\n" +
                "!\n" +
                "\n" +
                "ee\n" +
                "\n" +
                "f\n" +
                "\n" +
                "!af!!bcf\n" +
                "\n" +
                "ebaf\n" +
                "\n" +
                "eacca\n" +
                "\n" +
                "d\n" +
                "\n" +
                "!\n" +
                "a\n" +
                "\n" +
                "cf\n" +
                "\n" +
                "efdc\n" +
                "\n" +
                "b\n" +
                "\n" +
                "e\n" +
                "\n" +
                "e\n" +
                "\n" +
                "d\n" +
                "\n" +
                "ed\n" +
                "\n" +
                "\n" +
                "bb\n" +
                "\n" +
                "af\n" +
                "\n" +
                "d\n" +
                "\n" +
                "!d\n" +
                "\n" +
                "f\n" +
                "\n" +
                "be\n" +
                "\n" +
                "!cf\n" +
                "\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "cb\n" +
                "\n" +
                "e!f\n" +
                "\n" +
                "\n" +
                "d\n" +
                "\n" +
                "d\n" +
                "\n" +
                "e\n" +
                "\n" +
                "e!c\n" +
                "\n" +
                "ca\n" +
                "\n" +
                "c\n" +
                "\n" +
                "!\n" +
                "\n" +
                "!!bb\n" +
                "\n" +
                "\n" +
                "ae\n" +
                "\n" +
                "!\n" +
                "\n" +
                "c!\n" +
                "\n" +
                "bd\n" +
                "\n" +
                "fd\n" +
                "\n" +
                "f\n" +
                "\n" +
                "\n" +
                "!\n" +
                "\n" +
                "d!\n" +
                "\n" +
                "\n" +
                "c\n" +
                "\n" +
                "e\n" +
                "\n" +
                "cc\n" +
                "\n" +
                "cd!ae\n" +
                "\n" +
                "a\n" +
                "\n" +
                "e\n" +
                "\n" +
                "bdfcc\n" +
                "\n" +
                "!e\n" +
                "\n" +
                "\n" +
                "c!ab\n" +
                "b\n" +
                "\n" +
                "a\n" +
                "\n" +
                "c\n" +
                "\n" +
                "c\n" +
                "\n" +
                "a";

        String[] symbols = new String[] { "#", "$", "!", "-" };
        String stripped = StripComments.stripComments(text, symbols);
        System.out.println(stripped);
    }

}

