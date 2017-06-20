import java.util.Arrays;
import java.util.List;

/**
 * Created by carlmccann2 on 06/12/2016.
 */
final class Conversion {

    private Conversion(){};

    public static String integerTo32BitBinary(int integer){
        String binaryString = Integer.toBinaryString(integer);
        int length = 32 - binaryString.length();
        char[] padArray = new char[length];
        Arrays.fill(padArray, '0');
        String padString = new String(padArray);
        binaryString = padString + binaryString;
        return binaryString;
    }

    public static String integerToPaddedHex(int integer){

        String hexString = Integer.toHexString(integer);
        if(hexString.length() == 8) return hexString;
        int length = 8 - hexString.length();
        char[] padArray = new char[length];
        Arrays.fill(padArray,'0');
        String padString = new String(padArray);
        hexString = padString + hexString;
        return hexString;
    }

    public static String integerToPaddedOctal(int integer){
        String octalString = Integer.toOctalString(integer);
        int length = 6 - octalString.length();
        char[] padArray = new char[length];
        Arrays.fill(padArray, '0');
        String padString = new String(padArray);
        octalString = padString + octalString;
        return octalString;
    }


    public static void conversionInfo(List<Integer> nums){
        for (Integer i :   nums) {
            System.out.println("binary Value:\t" + integerTo32BitBinary(i));
            System.out.println("octal Value:\t" + integerToPaddedOctal(i));
            System.out.println("int value:\t\t" + i);
            System.out.println("hex Value:\t\t" + integerToPaddedHex(i));
            System.out.println();
        }
    }
}
