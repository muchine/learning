package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MorseCodeDecoderTest {
    @Test
    public void testExampleFromDescription() throws Exception {
        test("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011", "HEY JUDE");
    }

    @Test
    public void test1() {
        String codes = MorseCodeDecoder.decodeBits("01110");
        System.out.println(codes);
        assertEquals("E", MorseCodeDecoder.decodeMorse(codes));
    }

    @Test
    public void test2() {
        test("1110111", "M");
    }

    @Test
    public void test3() {
        test("11111100111111", "M");
    }

    private void test(String bits, String decoded) {
        String codes = MorseCodeDecoder.decodeBits(bits);
        System.out.println(codes);
        assertEquals(decoded, MorseCodeDecoder.decodeMorse(codes));
    }

}

