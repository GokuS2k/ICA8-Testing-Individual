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
    public static int countUrinals(String input) {
        if (input.contains("11"))
            return -1;

        if (input.length() == 1)
            return input.contains("0") ? 1 : 0;

        char[] inpchar = input.toCharArray();
        int found = 0;
        if ( inpchar[0] == '0' && inpchar[1] == '0' ) {
            inpchar[0] = '1';
            found++;
        }

        for(int i = 1; i <inpchar.length - 1; i++) {
            if(inpchar[i] == '0' && inpchar[i-1] == '0' && inpchar[i+1] == '0') {
                found++;
                inpchar[i] = '1';
            }
        }

        if (inpchar[inpchar.length - 2] == '0' && inpchar[inpchar.length-1] == '0')
            found++;

        return found;
    }

    public static void writeFile(List<Integer> values) throws IOException {
        String opfile = "rule.txt";
        File fold = new File(".");
        List<String> fnames = Arrays.stream(fold.listFiles()).map(File::getName)
                .filter(name -> name.matches("rule.*.txt")).sorted().toList();
        if (!fnames.isEmpty()) {
            String hst = fnames.get(fnames.size() - 1);
            String vals = hst.substring(4, hst.length() - 4);
            if (vals.length() > 0)
                opfile = String.format("rule%d.txt", Integer.parseInt(vals) + 1);
            else
                opfile = "rule1.txt";

        }
        FileWriter fwriter = new FileWriter(opfile);
        for(Integer x:values) {
            fwriter.write(x.toString()+"\n");
        }
        fwriter.close();
    }



}