import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * The objective of this exercise is to get you familiar with
 * the types used in Java to handle IP addresses.
 */
public class MyInetAddressExample {

    /**
     * Entry point for the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            System.out.print("Please enter a host name: ");
            Scanner hostNameScanner = new Scanner(System.in);
            String hostNameInput = hostNameScanner.nextLine();
            InetAddress hostNameAddress = InetAddress.getByName(hostNameInput);
            String hostName = hostNameAddress.getHostName();
            String ipNumberString = hostNameAddress.getHostAddress();
            String[] ipAddressStringArray = ipNumberString.split("\\.");
            String binaryNumber = convertIpAddress(ipAddressStringArray, Integer::toBinaryString, ".");
            String hexNumber = convertIpAddress(ipAddressStringArray, Integer::toHexString, " ");
            String ipAddressDetails = getIpAddressInfo(hostName, binaryNumber, hexNumber, ipNumberString);
            System.out.println(ipAddressDetails);
            hostNameScanner.close();
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }

    /**
     * Converts the IP address to a format specified by the converter function.
     * @param ipAddressStringArray The IP address specified as an array.
     * @param converterFunction The function used to process the array into a specific format.
     * @param delimiter The delimiter character used to represent the converted IP address.
     * @return The converted IP adress string specified by the converter function.
     */
    public static String convertIpAddress(String[] ipAddressStringArray, Function<Integer, String> converterFunction, String delimiter) {
        List<String> convertedStringList = new ArrayList<String>();
        for (String decimalString : ipAddressStringArray) {
           int decimalNumber = Integer.parseInt(decimalString);
           String convertedString = converterFunction.apply(decimalNumber);
           convertedStringList.add(convertedString);
        }
        
        return String.join(delimiter, convertedStringList);
    }

    /**
     * Gets the details pertaining to the IP address.
     * @param hostName The host name of the IP address.
     * @param binaryNumber The binary representation of the IP address.
     * @param hexNumber The hexadecimal representation of the IP address.
     * @param ipNumberString The fully qualified representation of the IP address.
     * @return Condensed detail message of the specified IP address.
     */
    public static String getIpAddressInfo(String hostName, String binaryNumber, String hexNumber, String ipNumberString) {
        return "\tHost Name: " + hostName
                + "\n\tBinary format: " + binaryNumber
                + "\n\tHexadecimal dotted-quad format: " + hexNumber
                + "\n\tDecimal dotted-quad format: " + ipNumberString;
    }
}