import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import java.util.Arrays;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ECB-Code-Tests-Modul")
public class ECBTest {


        @Test
        @DisplayName("Test der Methode symbolToBits")
        void symbolToBitsTest() {
                char[] a1 = ECBCode.symbolToBits('A');
                char[] e1 = { '0', '0', '0', '0', '0' };
                assertEquals(true, Arrays.equals(e1, a1), "Eingabe -> A Erwartete Ausgabe -> " + new String(e1)
                                + " Erhaltene Ausgabe -> " + new String(a1));

                char[] a2 = ECBCode.symbolToBits('H');
                char[] e2 = { '1', '1', '0', '0', '0' };
                assertEquals(true, Arrays.equals(e2, a2), "Eingabe -> H Erwartete Ausgabe -> " + new String(e2)
                                + " Erhaltene Ausgabe -> " + new String(a2));

                char[] a3 = ECBCode.symbolToBits('S');
                char[] e3 = { '0', '1', '0', '0', '1' };
                assertEquals(true, Arrays.equals(e3, a3), "Eingabe -> S Erwartete Ausgabe -> " + new String(e3)
                                + " Erhaltene Ausgabe -> " + new String(a3));
        }

        @Test
        @DisplayName("Test der Methode textToBits")
        void textToBitsTest() {
                char[] a1 = ECBCode.textToBits("ABC");
                char[] e1 = { '0', '0', '0', '0', '0', '1', '1', '0', '1', '1', '0', '0', '0', '0', '1' };
                assertEquals(true, Arrays.equals(e1, a1), "Eingabe -> ABC Erwartete Ausgabe -> " + new String(e1)
                                + " Erhaltene Ausgabe -> " + new String(a1));

                char[] a2 = ECBCode.textToBits("HJKLO");
                char[] e2 = { '1', '1', '0', '0', '0', '1', '0', '1', '1', '1', '0', '0', '1', '0', '1', '1', '0', '1',
                                '1', '0', '0', '0', '1', '1', '1' };
                assertEquals(true, Arrays.equals(e2, a2), "Eingabe -> HJKLO Erwartete Ausgabe -> " + new String(e2)
                                + " Erhaltene Ausgabe -> " + new String(a2));

                char[] a3 = ECBCode.textToBits("");
                char[] e3 = {};
                assertEquals(true, Arrays.equals(e3, a3),
                                "Eingabe -> '' Erwartete Ausgabe -> '' Erhaltene Ausgabe -> " + new String(a3));
        }

        @Test
        @DisplayName("Test der Methode firstN")
        void firstNTest() {
                char[] a1 = ECBCode.firstN(new char[] { 'A', 'B', 'C', 'D' }, 1);
                char[] e1 = { 'A' };
                assertEquals(true, Arrays.equals(e1, a1), "Eingabe -> A,B,C,D | n=1 Erwartete Ausgabe -> "
                                + new String(e1) + " Erhaltene Ausgabe -> " + new String(a1));

                char[] a2 = ECBCode.firstN(new char[] { 'A', 'B', 'C', 'D' }, 3);
                char[] e2 = { 'A', 'B', 'C' };
                assertEquals(true, Arrays.equals(e2, a2), "Eingabe -> A,B,C,D | n=3 Erwartete Ausgabe -> "
                                + new String(e2) + " Erhaltene Ausgabe -> " + new String(a2));

                char[] a3 = ECBCode.firstN(new char[] { 'A', 'B', 'C', 'D' }, 0);
                char[] e3 = {};
                assertEquals(true, Arrays.equals(e3, a3),
                                "Eingabe -> A,B,C,D | n=0 Erwartete Ausgabe -> '' Erhaltene Ausgabe -> "
                                                + new String(a3));
        }

        @Test
        @DisplayName("Test der Methode restN")
        void restNTest() {
                char[] a1 = ECBCode.restN(new char[] { 'A', 'B', 'C', 'D' }, 1);
                char[] e1 = { 'B', 'C', 'D' };
                assertEquals(true, Arrays.equals(e1, a1), "Eingabe -> A,B,C,D | n=1 Erwartete Ausgabe -> "
                                + new String(e1) + " Erhaltene Ausgabe -> " + new String(a1));

                char[] a2 = ECBCode.restN(new char[] { 'A', 'B', 'C', 'D' }, 3);
                char[] e2 = { 'D' };
                assertEquals(true, Arrays.equals(e2, a2), "Eingabe -> A,B,C,D | n=3 Erwartete Ausgabe -> "
                                + new String(e2) + " Erhaltene Ausgabe -> " + new String(a2));

                char[] a3 = ECBCode.restN(new char[] { 'A', 'B', 'C', 'D' }, 0);
                char[] e3 = { 'A', 'B', 'C', 'D' };
                assertEquals(true, Arrays.equals(e3, a3), "Eingabe -> A,B,C,D | n=0 Erwartete Ausgabe -> "
                                + new String(e3) + " Erhaltene Ausgabe -> " + new String(a3));
        }
    

       @Test
        @DisplayName("Test der Methode bitsToBlocks")
        void bitsToBlocksTest() {

               char[][] a1 = ECBCode.bitsToBlocks(new char[] { '1', '1', '0', '1', '1', '0', '0', '1', '0' }, 3);
                char[][] e1 = { { '1', '1', '0' }, { '1', '1', '0' }, { '0', '1', '0' } };
                assertEquals(true, equal(a1, e1), "Eingabe -> 110110010 | size=3 Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e1) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a1));

                char[][] a2 = ECBCode.bitsToBlocks(new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' }, 2);
                char[][] e2 = { { 'A', 'B' }, { 'C', 'D' }, { 'E', 'F', }, { 'G', 'H' } };
                assertEquals(true, equal(a2, e2), "Eingabe -> ABCDEFGH | size=2 Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e2) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a2));

                char[][] a3 = ECBCode.bitsToBlocks(new char[] { '0', '1', '2' }, 1);
                char[][] e3 = { { '0' }, { '1' }, { '2' } };
                assertEquals(true, equal(a3, e3), "Eingabe -> 012 | size=1 Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e3) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(e3));
        }

        @Test
        @DisplayName("Test der Methode encryptBlocks")
        void encryptBlocksTest() {
                char[][] a1 = ECBCode.encryptBlocks(
                                new char[][] { { '1', '1', '0' }, { '1', '1', '0' }, { '0', '1', '0' } });
                char[][] e1 = { { '0', '1', '1' }, { '0', '1', '1' }, { '0', '0', '1' } };
                assertEquals(true, equal(a1, e1), "Eingabe -> {110}{110}{010} Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e1) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a1));

                char[][] a2 = ECBCode
                                .encryptBlocks(new char[][] { { 'A', 'B' }, { 'C', 'D' }, { 'E', 'F' }, { 'G', 'H' } });
                char[][] e2 = { { 'B', 'A' }, { 'D', 'C' }, { 'F', 'E' }, { 'H', 'G' } };
                assertEquals(true, equal(a2, e2), "Eingabe -> {A,B}{C,D}{E,F}{G,H} Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e2) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a2));

                char[][] a3 = ECBCode.encryptBlocks(new char[][] { { '0' }, { '1' }, { '2' } });
                char[][] e3 = { { '0' }, { '1' }, { '2' } };
                assertEquals(true, equal(a3, e3), "Eingabe -> {0}{1}{2} Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e3) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a3));
        }

        @Test
        @DisplayName("Test der Methode blocksToBits")
        void blocksToBitsTest() {
                char[] a1 = ECBCode
                                .blocksToBits(new char[][] { { '1', '1', '0' }, { '1', '1', '0' }, { '0', '1', '0' } });
                char[] e1 = new char[] { '1', '1', '0', '1', '1', '0', '0', '1', '0' };
                assertEquals(true, Arrays.equals(a1, e1), "Eingabe -> {1,1,0}{1,1,0}{0,1,0} Erwartete Ausgabe -> "
                                + new String(e1) + " Erhaltene Ausgabe -> " + new String(a1));

                char[] a2 = ECBCode
                                .blocksToBits(new char[][] { { 'A', 'B' }, { 'C', 'D' }, { 'E', 'F' }, { 'G', 'H' } });
                char[] e2 = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
                assertEquals(true, Arrays.equals(a2, e2), "Eingabe -> {A,B}{C,D}{E,F}{G,H} Erwartete Ausgabe -> "
                                + new String(e2) + " Erhaltene Ausgabe -> " + new String(a2));

                char[] a3 = ECBCode.blocksToBits(new char[][] { { '0' }, { '1' }, { '2' } });
                char[] e3 = new char[] { '0', '1', '2' };
                assertEquals(true, Arrays.equals(a3, e3), "Eingabe -> {0}{1}{2} Erwartete Ausgabe -> " + new String(e3)
                                + " Erhaltene Ausgabe -> " + new String(a3));
        }

        @Test
        @DisplayName("Test der Methode bitsToSymbol")
        void bitsToSymbolTest() {
                char a1 = ECBCode.bitsToSymbol(new char[] { '0', '0', '0', '0', '1' });
                char e1 = 'C';
                assertEquals(e1, a1,
                                "Eingabe -> {0,0,0,0,1} Erwartete Ausgabe -> " + e1 + " Erhaltene Ausgabe -> " + a1);

                char a2 = ECBCode.bitsToSymbol(new char[] { '1', '0', '0', '0', '0' });
                char e2 = 'X';
                assertEquals(e2, a2,
                                "Eingabe -> {1,0,0,0,0} Erwartete Ausgabe -> " + e1 + " Erhaltene Ausgabe -> " + a2);

                char a3 = ECBCode.bitsToSymbol(new char[] { '0', '1', '1', '1', '0' });
                char e3 = '?';
                assertEquals(e3, a3,
                                "Eingabe -> {0,1,1,1,0} Erwartete Ausgabe -> " + e1 + " Erhaltene Ausgabe -> " + a3);
        }

        @Test
        @DisplayName("Test der Methode bitsToText")
        void bitsToTextTest() {
                char[] a1 = ECBCode.bitsToText(new char[] { '0', '0', '0', '0', '0', '1', '1', '0', '1', '1', '0', '0',
                                '0', '0', '1' }, 5);
                char[] e1 = { 'A', 'B', 'C' };
                assertEquals(true, Arrays.equals(e1, a1), "Eingabe -> 000001101100001 Erwartete Ausgabe -> "
                                + new String(e1) + " Erhaltene Ausgabe -> " + new String(a1));

                char[] a2 = ECBCode.bitsToText(new char[] { '1', '1', '0', '0', '0', '1', '0', '1', '1', '1', '0', '0',
                                '1', '0', '1', '1', '0', '1', '1', '0', '0', '0', '1', '1', '1' }, 5);
                char[] e2 = { 'H', 'J', 'K', 'L', 'O' };
                assertEquals(true, Arrays.equals(e2, a2), "Eingabe -> 1100010111001011011000111 Erwartete Ausgabe -> "
                                + new String(e2) + " Erhaltene Ausgabe -> " + new String(a2));

                char[] a3 = ECBCode.bitsToText(new char[] {}, 5);
                char[] e3 = {};
                assertEquals(true, Arrays.equals(e3, a3),
                                "Eingabe -> '' Erwartete Ausgabe -> '' Erhaltene Ausgabe -> " + new String(a3));
        }

        @Test
        @DisplayName("Test der Methode encryption")
        void encryptionTest() {
                String a1 = ECBCode.encryption("HALLO", 5);
                String e1 = "YAWWR";
                assertEquals(e1, a1,
                                "Eingabe -> HALLO | r=5 Erwartete Ausgabe -> " + e1 + " Erhaltene Ausgabe -> " + a1);

                String a2 = ECBCode.encryption("ICH_BIN_EIN_TEST", 4);
                String e2 = "EE_M=X?LVA?LHGIV";
                assertEquals(e2, a2, "Eingabe -> ICH_BIN_EIN_TEST | r=4 Erwartete Ausgabe -> " + e2
                                + " Erhaltene Ausgabe + " + a2);

                String a3 = ECBCode.encryption("TEST", 1);
                String e3 = "TEST";
                assertEquals(e3, a3, "Eingabe TEST | r=1 Erwartete Ausgabe -> " + e3 + " Erhaltene Ausgabe -> " + a3);
        }

        @Test
        @DisplayName("Test der Methode decryptBlocks")
        void decryptBlocksTest() {
                char[][] a1 = ECBCode.decryptBlocks(
                                new char[][] { { '1', '1', '0' }, { '1', '1', '0' }, { '0', '1', '0' } });
                char[][] e1 = { { '1', '0', '1' }, { '1', '0', '1' }, { '1', '0', '0' } };
                assertEquals(true, equal(a1, e1), "Eingabe -> {110}{110}{010} Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e1) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a1));

                char[][] a2 = ECBCode
                                .decryptBlocks(new char[][] { { 'A', 'B' }, { 'C', 'D' }, { 'E', 'F' }, { 'G', 'H' } });
                char[][] e2 = { { 'B', 'A' }, { 'D', 'C' }, { 'F', 'E' }, { 'H', 'G' } };
                assertEquals(true, equal(a2, e2), "Eingabe -> {A,B}{C,D}{E,F}{G,H} Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e2) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a2));

                char[][] a3 = ECBCode.decryptBlocks(new char[][] { { '0' }, { '1' }, { '2' } });
                char[][] e3 = { { '0' }, { '1' }, { '2' } };
                assertEquals(true, equal(a3, e3), "Eingabe -> {0}{1}{2} Erwartete Ausgabe -> "
                                + twoDimCharArrayToString(e3) + " Erhaltene Ausgabe -> " + twoDimCharArrayToString(a3));
        }

        @Test
        @DisplayName("Test der Methode decryption")
        void decryptionText() {
                String a1 = ECBCode.decryption("YAWWR", 5);
                String e1 = "HALLO";
                assertEquals(e1, a1,
                                "Eingabe -> YAWWR | r=5 Erwartete Ausgabe -> " + e1 + " Erhaltene Ausgabe -> " + a1);

                String a2 = ECBCode.decryption("EE_M=X?LVA?LHGIV", 4);
                String e2 = "ICH_BIN_EIN_TEST";
                assertEquals(e2, a2, "Eingabe -> EE_M=X?LVA?LHGIV | r=4 Erwartete Ausgabe -> " + e2
                                + " Erhaltene Ausgabe + " + a2);

                String a3 = ECBCode.decryption("TEST", 1);
                String e3 = "TEST";
                assertEquals(e3, a3, "Eingabe TEST | r=1 Erwartete Ausgabe -> " + e3 + " Erhaltene Ausgabe -> " + a3);
        }

        public static String twoDimCharArrayToString(char[][] arr) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < arr.length; i++) {
                        sb.append("{");
                        for (int j = 0; j < arr[i].length; j++) {
                                sb.append(arr[i][j]);
                        }
                        sb.append("}");
                }
                return sb.toString();
        }

        public static boolean equal(final char[][] arr1, final char[][] arr2) {
                if (arr1 == null || arr2 == null) {
                        return false;
                }
                if (arr1.length != arr2.length) {
                        return false;
                }
                for (int i = 0; i < arr1.length; i++) {
                        if (!Arrays.equals(arr1[i], arr2[i])) {
                                return false;
                        }
                }
                return true;
        }

}