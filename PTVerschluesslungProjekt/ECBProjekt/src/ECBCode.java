import java.util.HashMap;
import java.util.Scanner;

/**
 * Quellcodevorlage fuer das Projekt
 * 
 * ECB Verschluesselung
 * 
 * KLASSE: 11IT1B
 * 
 * @author Odai Al Fadel, Goran Vuksa
 */
public class ECBCode {

    static HashMap<Character, String> encryptionCodeMap = createEncryptionCodeMap();
    static HashMap<String, Character> decryptionCodeMap = createDecryptionCodeMap();

    public static void main(String[] args) {
        System.out.println("Willkommen bei PlanetExpress!");
        System.out.println("Welchen Modus moechten Sie nutzen?");
        System.out.println("1) Verschluesselung einer Nachricht");
        System.out.println("2) Entschluesselung einer Nachricht");

        Scanner sc = new Scanner(System.in); // anlegen von einem Scanner Objekt
        int userInput = sc.nextInt(); // Einlesen der Auswahl

        // Verschluesselung einer Nachricht
        if (userInput == 1) {
            System.out.println("Geben Sie den zu verschluesselnden Text ein:");
            String inputText = sc.next().toUpperCase(); // Einlesen des Textes

            System.out.println("Geben Sie die Laenger der Bloecke (r) an:");
            int blockSize = sc.nextInt(); // Einlesen der laenge r

            String encryptedText = encryption(inputText, blockSize); // Verschluesseln
            // der Nachricht
            System.out.println(encryptedText); // Ausgabe auf der Konsole
        }
        // Entschluesselung einer Nachricht
        if (userInput == 2) {
            System.out.println("Geben Sie den zu entschluesselnden Text ein:");
            String inputText = sc.next().toUpperCase(); // Einlesen des Textes
            System.out.println("Geben Sie die Laenger der Bloecke (r) an:");
            int blockSize = sc.nextInt(); // Einlesen der Laenge r

            String decryptedText = decryption(inputText, blockSize); // Entschluesseln der Nachricht
            System.out.println(decryptedText); // Ausgabe auf der Konsole
        }
        System.out.println("Danke fuer Ihre Nutzung und auf Wiedersehen!");
        sc.close(); // schliessen des Scanner Objekts
    }

    // ************************************************************
    //
    // AB HIER BEGINNT IHR QUELLCODE
    //
    // ************************************************************

    //
    // Darstellung eines Textes als Binaercode
    //

    /*
     * wandelt die symbole vom HashMap zu bits in char Array
     */
    static char[] symbolToBits(char symbol) {

        String tempString = createEncryptionCodeMap().get(symbol); // String gespeicher von HashMap Tabelle
        char[] finaleWert = new char[tempString.length()]; // Char Array speicher 5x Stellen

        for (int x = 0; x < tempString.length(); x++) { // String in (char array) umwandeln
            finaleWert[x] = tempString.charAt(x);
        }

        return finaleWert;
    }

    /*
     * diese Methode wandelt von Zeichenkette (String) zu char bits um mit Hilfe der
     * Methode symbolToBits
     */

    static char[] textToBits(String text) {

        String tempString = ""; // Temp Speicher als String
        for (int x = 0; x < text.length(); x++) { // um alle Chars in einem String zu speichern

            tempString += new String(symbolToBits(text.charAt(x)));// um die die Chars zu umwandeln und dann in einem
                                                                   // String zu speichern
        }
        char[] finaleWert = new char[tempString.length()]; // hier wurde das Char deklariert und intialisiert

        // tempString = 0000011011
        for (int j = 0; j < tempString.length(); j++) { // um die String in (char array) zu erfüllen
            finaleWert[j] = tempString.charAt(j); // erfült die finalWert Array vom String

        } // finaleWert = {'0','0','0','0','0','1','1','0','1','1'}
        return finaleWert;
    }

    //
    // Zerteilung in Bloecke der Laenge r
    //

    /*
     * Diese Methode ergibt uns char array und nimmt char[] bits und int n mit
     * Aufruf dieser Methode bekommen wir die ersten Zeichen (Buchstabe)
     */
    static char[] firstN(char[] bits, int n) {

        String temp = "";
        for (int i = 0; i < n; i++) {
            temp += bits[i];
        }
        return temp.toCharArray();
    }

    /*
     * Diese Methode ergibt uns char array und nimmt char[] bits und int n mit
     * Aufruf dieser Methode bekommen wir die restlichen Zeichen (Buchstabe) nach
     * ende unsere vorherige Methode firstN
     */
    static char[] restN(char[] bits, int n) {

        String temp = "";

        for (int i = firstN(bits, n).length; i < bits.length; i++) {
            temp += bits[i];
        }
        return temp.toCharArray();

    }
    /*
     * Diese Methode ergibt uns zweidemosionales char[][] und nimmt char[] bits und
     * int size mit Aufruf dieser Methode zerteilen wir die bits von unsere
     * Nachricht in Bloecke nach unsere Wahl, indem wir einen Zahl eingeben (Laenger
     * der Bloecke)
     */

    public static String a = "";
    static char[][] bitsToBlocks(char[] bits, int size) {
        /*
         * 110 110 000 011 011 [0]=110 [0][0]= 1; [0][1]= 1; [0][2]= 0;
         */
    	
        int tempArrayLaenge = bits.length / size;
        
        char[][] finaleWert = new char[tempArrayLaenge][size];

        for (int i = 0; i < bits.length / size; i++) {
            for (int j = 0; j < size; j++) {
                int x = i * size; // Z.B 1*3 -- 2*3 -- 3*3
                finaleWert[i][j] = bits[j + x]; // 0 oder 1 in der x stelle
            }
        }
        int temp = finaleWert[0].length * finaleWert.length;
        if(bits.length-temp !=0) {
        	 
        	for (int m = temp; m<bits.length; m++) {
        		a += bits[m];
        	}
    	}
        
        
        return finaleWert;
    }

    //
    // Verschluesselung von Bloecken
    //

    /*
     * Diese Methode ergibt uns zweidemosionales char[][] und nimmt char[] blocks
     * mit dieser Methode gehen wir zu jedem Block und mit Hilfe shiftRight Methode
     * schieben wir zu jedem Block einen bit nach recht um zu verschluesseln
     */

    static char[][] encryptBlocks(char[][] blocks) {

        /*
         * 110 110 000 011 011 == 011 011 000 101 101
         */
        int tempArrayLaenge = blocks[0].length;
        char[][] finaleWert = new char[blocks.length][tempArrayLaenge];
        for (int i = 0; i < blocks.length; i++) {
            finaleWert[i] = shiftRight(blocks[i]);
        }

        return finaleWert;
    }

    //
    // Zusammenfuegen von Bloecken
    //

    /*
     * Diese Methode ergibt uns char[] und nimmt zweidemosionales char[][] blocks
     * mit Aufruf dieser Methode fügen wir die bits von unsere verschuesselte oder
     * entschluesselte Nachricht in Bloecke nach unsere Wahl, indem wir einen Zahl
     * eingeben (Laenger der Bloecke)
     */

    static char[] blocksToBits(char[][] blocks) {

        /*
         * 110 110 000 011 011 [0]=110 [0][0]= 1; [0][1]= 1; [0][2]= 0; 11011000011011
         * finaleWert[0]
         */

        int tempArrayLaenge = blocks[0].length * blocks.length;
        char[] finaleWert = new char[tempArrayLaenge];
        int counter = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                finaleWert[counter] = blocks[i][j];
                counter++;
            }
        }
       String result = "";
        if(a.length()!=0) {
        	result += new String(finaleWert) + a;
        	return result.toCharArray();
        }
        return finaleWert;
    }

    //
    // Umwandlung eines Binaercodes als Text
    //

    /*
     * Diese Methode wandelt die bits mit Hilfe unsere HashMap Tabelle zu symbol in
     * char
     */
    static char bitsToSymbol(char[] bits) {
        String speicher = "";
        for (int x = 0; x < bits.length; x++) { // um die String in (char[]) zu erfüllen
            speicher += bits[x];
        }
        return createDecryptionCodeMap().get(speicher); // vom Hashmap in einem String gespeichert 00000 = 'A'
    }

    /*
     * Diese Methode gibt uns Chr[] und nimmt char[] bits und int size. diese
     * wandelt vom String (bits) zu Zecihen(Buchstabe oder Symbol) um, aber mit
     * Hilfe der Methode bitsToSymbol danach wandeln wir den String in char[]
     */

    static char[] bitsToText(char[] bits, int size) {

        String temp = "";

        char[] speicher = new char[size];
        int counter = 0;
        for (int i = 0; i < bits.length; i++) {

            speicher[counter] = bits[i];
            counter++;
            if (counter == size) {
                temp += bitsToSymbol(speicher);
                counter = 0;

            }
        }

        return temp.toCharArray();
    }

    //
    // Verschluesselung eines Textes
    //

    /*
     * Diese Methode ergibt uns ein String und nimmt String text und int blockSize
     * diese verschluesselt ein Symbol oder ein Text in dem wir mehrere methoden die
     * wir aufgebaut haben verwenden. wir erstellen ein neue String und rufen wir
     * die Methode textToBits mit Paramieter text und blockSize um unsere
     * eingegebene Werte zu bearbeiten in dieser Methode und mit hilfe anderen
     * methoden wandeln und schieben bits zum verschluesseln danach wandeln wir die
     * verschluesselte bits in Text
     */
    static String encryption(String text, int blockSize) {
        //
        // wir grenzen die blocks in der Methode blocksToBits mit dem Zahl 5, da die
        // Bits in unsere HashMap tabelle 5x sind
        //
        return new String(bitsToText(blocksToBits(encryptBlocks(bitsToBlocks(textToBits(text), blockSize))), 5));
    }

    //
    // Entschluesselung von Bloecken
    //

    /*
     * Diese Methode ergibt uns zweidemosionales char[][] und nimmt char[] blocks
     * mit dieser Methode gehen wir zu jedem Block und mit Hilfe shiftRight Methode
     * schieben wir zu jedem Block einen bit nach links um zu enstschluesseln
     */
    static char[][] decryptBlocks(char[][] blocks) {

        int tempArrayLaenge = blocks[0].length;
        char[][] finaleWert = new char[blocks.length][tempArrayLaenge];
        for (int i = 0; i < blocks.length; i++) {
            finaleWert[i] = shiftLeft(blocks[i]);
        }
        return finaleWert;
    }

    //
    // Entschluesselung eines Textes
    //

    /*
     * neue String erstellt und als erstes textToBits Methode aufgerufen mit dem
     * text und der lange danach die bitsToBlocks augerufen um in bloecke zu
     * zerteilen demnaechst decryptBlocks aufgerufen und die bloecke zu
     * verschluesseln danach die bloecke zu bits wandeln mit der Methode
     * blocksToBits am ende wandelt die Methode die bits in einem text
     */
    static String decryption(String text, int blockSize) {
        return new String(bitsToText(blocksToBits(decryptBlocks(bitsToBlocks(textToBits(text), blockSize))), 5));
    }

    // ************************************************************
    // HIER ENDET IHR QUELLCODE
    //
    // Quellcodevorlage
    //
    // BITTE NEHMEN SIE AB HIER KEINE AENDERUNGEN MEHR VOR!
    // ************************************************************

    /**
     * Gibt ein uebergebenes Character Array auf der Konsole aus.
     * 
     * @param text - a Character array
     */
    static void printCharArray(char[] text) {
        for (int i = 0; i < text.length; i++) {
            System.out.print(text[i]);
        }
        System.out.println();
    }

    /**
     * Bekommt ein Array uebergeben und verschiebt den Inhalt um eine Position nach
     * rechts. Das letzte Element wird somit zum ersten. Das verschobene Array wird
     * zurueckgegeben.
     */
    static char[] shiftRight(char[] bits) {
        char[] shiftedBits = new char[bits.length];
        for (int i = 0; i < bits.length; i++) {
            int targetPos = i + 1;
            if (targetPos >= bits.length) {
                targetPos = i - (bits.length - 1);
            }
            shiftedBits[targetPos] = bits[i];
        }
        return shiftedBits;
    }

    /**
     * Bekommt ein Array uebergeben und verschiebt den Inhalt um eine Possition nach
     * links im Array. Das erste Element wird somit zum letzten. Das verschobene
     * Array wird zurueckgegeben.
     */
    static char[] shiftLeft(char[] bits) {
        char[] shiftedBits = new char[bits.length];
        for (int i = 0; i < bits.length; i++) {
            int targetPos = i - 1;
            if (targetPos < 0) {
                targetPos = i + (bits.length - 1);
            }
            shiftedBits[targetPos] = bits[i];
        }
        return shiftedBits;
    }

    static int symbolLenght() {
        return 5;
    }

    /**
     * Erstellung des Binaercode fuer die Verschluesselung
     */
    static HashMap<Character, String> createEncryptionCodeMap() {
        HashMap<Character, String> codeMap = new HashMap<>();
        codeMap.put('A', "00000");
        codeMap.put('C', "00001");
        codeMap.put('E', "00010");
        codeMap.put('G', "00011");
        codeMap.put('I', "00100");
        codeMap.put('K', "00101");
        codeMap.put('M', "00110");
        codeMap.put('O', "00111");
        codeMap.put('Q', "01000");
        codeMap.put('S', "01001");
        codeMap.put('U', "01010");
        codeMap.put('W', "01011");
        codeMap.put('Y', "01100");
        codeMap.put('!', "01101");
        codeMap.put('?', "01110");
        codeMap.put('Z', "01111");

        codeMap.put('X', "10000");
        codeMap.put('V', "10001");
        codeMap.put('T', "10010");
        codeMap.put('R', "10011");
        codeMap.put('P', "10100");
        codeMap.put('N', "10101");
        codeMap.put('L', "10110");
        codeMap.put('J', "10111");
        codeMap.put('H', "11000");
        codeMap.put('F', "11001");
        codeMap.put('D', "11010");
        codeMap.put('B', "11011");
        codeMap.put('_', "11100");
        codeMap.put('=', "11101");
        codeMap.put('+', "11110");
        codeMap.put('-', "11111");

        return codeMap;
    }

    /**
     * Erstellung des Binaercode fuer die Entschluesselung
     */
    static HashMap<String, Character> createDecryptionCodeMap() {
        HashMap<String, Character> codeMap = new HashMap<>();
        codeMap.put("00000", 'A');
        codeMap.put("00001", 'C');
        codeMap.put("00010", 'E');
        codeMap.put("00011", 'G');
        codeMap.put("00100", 'I');
        codeMap.put("00101", 'K');
        codeMap.put("00110", 'M');
        codeMap.put("00111", 'O');
        codeMap.put("01000", 'Q');
        codeMap.put("01001", 'S');
        codeMap.put("01010", 'U');
        codeMap.put("01011", 'W');
        codeMap.put("01100", 'Y');
        codeMap.put("01101", '!');
        codeMap.put("01110", '?');
        codeMap.put("01111", 'Z');

        codeMap.put("10000", 'X');
        codeMap.put("10001", 'V');
        codeMap.put("10010", 'T');
        codeMap.put("10011", 'R');
        codeMap.put("10100", 'P');
        codeMap.put("10101", 'N');
        codeMap.put("10110", 'L');
        codeMap.put("10111", 'J');
        codeMap.put("11000", 'H');
        codeMap.put("11001", 'F');
        codeMap.put("11010", 'D');
        codeMap.put("11011", 'B');
        codeMap.put("11100", '_');
        codeMap.put("11101", '=');
        codeMap.put("11110", '+');
        codeMap.put("11111", '-');
        return codeMap;
    }
}