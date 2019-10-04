package coding.programmers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StickerTest {

    @Test
    public void testCase1() {
        int[] stickers = new int[] { 12, 80, 14, 22, 100 };
        Sticker sticker = new Sticker();
        int sum = sticker.solution(stickers);
        assertEquals(180, sum);
    }

    @Test
    public void testCase2() {
        int[] stickers = new int[] { 12, 12, 12, 12, 12 };
        Sticker sticker = new Sticker();
        int sum = sticker.solution(stickers);
        assertEquals(36, sum);
    }

}
