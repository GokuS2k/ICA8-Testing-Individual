/**
 * Author: Gokul Subramanian
 */

package org.example;
import java.io.*;
import java.util.*;

public class Urinals {

    private static final String UFilePath = "Urinals.dat";

    public static Boolean goodString(String str) {
        if (str.length() < 1 || str.length() > 20)
            return false;

        Set<String> valchars = new HashSet<>(Arrays.asList("0", "1"));
        for (int i = 0; i < str.length(); i++) {
            if (!valchars.contains(Character.toString(str.charAt(i))))
                return false;
        }
        return true;
    }

    public static List<String> openFile(String path) throws FileNotFoundException, InvalidFileException {
        List<String> l = new ArrayList<>();

        File fileread = new File(path);
        Scanner sc = new Scanner(fileread);
        while (sc.hasNextLine()) {
            String inp = sc.nextLine();
            if (inp.equals("-1"))
                break;
            else if (!goodString(inp))
                throw new NumberFormatException();
            l.add(inp);
        }
        sc.close();

        if (l.isEmpty())
            throw new InvalidFileException();

        return l;
    }
}