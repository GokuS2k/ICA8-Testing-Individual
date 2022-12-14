/**
 * @author Gokul Subramanian
 */

package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

class UrinalsTest {
    @Test
    void returntrueifvalid() {
        System.out.println("====== Gokul SUbramanian == TEST ONE EXECUTED =======");
        Assertions.assertEquals(true, Urinals.goodString("10001"));
    }

    @Test
    void returnfalsewhenstringissmall() {
        System.out.println("====== Gokul Subramanian == TEST TWO EXECUTED =======");
        Assertions.assertFalse(Urinals.goodString(""));
    }

    @Test
    void returnfalsewhenstringhasinvalidchars() {
        System.out.println("====== Gokul Subramanian == TEST THREE EXECUTED =======");
        Assertions.assertFalse(Urinals.goodString("123"));
    }

    @Test
    void returnfalsewhenipstrislong() {
        System.out.println("====== Gokul Subramanian == TEST SEVEN EXECUTED =======");
        Assertions.assertFalse(Urinals.goodString("123456789012345678901234567890"));
    }

    @Test
    void notfoundexcepwhenfilenotfound() {
        System.out.println("====== Gokul Subramanian == TEST FOUR EXECUTED =======");
        Assertions.assertThrows(FileNotFoundException.class, () -> Urinals.openFile("INVALID"));
    }

    @Test
    void invalexcepwhenIPfileisempty() {
        System.out.println("====== Gokul Subramanian == TEST FIVE EXECUTED =======");
        Assertions.assertThrows(InvalidFileException.class, () -> Urinals.openFile("EmptyFile.dat"));
    }

    @Test
    void noformatexcwhenfilecontainsInvalidNos() {
        System.out.println("====== Gokul Subramanian == TEST SIX EXECUTED =======");
        Assertions.assertThrows(NumberFormatException.class, () -> Urinals.openFile("InvalidNumberFormat.dat"));
    }

    @Test
    void returnnoOFfreeplaces() {
        System.out.println("====== Gokul Subramanian == TEST EIGHT EXECUTED =======");
        Assertions.assertEquals(1, Urinals.countUrinals("10001"));
        Assertions.assertEquals(0, Urinals.countUrinals("1001"));
        Assertions.assertEquals(3, Urinals.countUrinals("00000"));
        Assertions.assertEquals(2, Urinals.countUrinals("0000"));
        Assertions.assertEquals(1, Urinals.countUrinals("01000"));
        Assertions.assertEquals(-1, Urinals.countUrinals("011"));
    }

    private static List<String> ruleFilesList() {
        File filefolder = new File(".");
        return Arrays.stream(filefolder.listFiles()).map(File::getName)
                .filter(name -> name.matches("rule.*.txt")).sorted().toList();
    }

    private static void deleteRuleFiles() {
        ruleFilesList().forEach(file -> {
            File ruleFile = new File(file);
            try {
                Files.deleteIfExists(ruleFile.toPath());
            } catch (IOException e) {
                System.out.println("Exception when deleting files");
            }
        });
    }

    @Test
    void createsOPFiletw() throws IOException {
        System.out.println("====== Gokul Subramanian == TEST NINE EXECUTED =======");
        deleteRuleFiles();
        File OPfile = new File("rule.txt");
        List<Integer> vals = Arrays.asList(1,3);
        Urinals.writeFile(vals);
        Assertions.assertTrue(OPfile.exists());
    }

    @Test
    void genrulefileifwhencounterRuleFileExist() throws IOException {
        System.out.println("====== Gokul Subramanian == TEST TEN EXECUTED =======");
        deleteRuleFiles();
        FileWriter OPfile = new FileWriter("rule.txt");
        OPfile.close();
        List<Integer> vals = Arrays.asList(1,3);
        File fileExp = new File("rule1.txt");
        Urinals.writeFile(vals);
        Assertions.assertTrue(fileExp.exists());
        fileExp = new File("rule2.txt");
        Urinals.writeFile(vals);
        Assertions.assertTrue(fileExp.exists());
    }

    @Test
    void readIPfileWcountTOOPfile() throws InvalidFileException, IOException {
        System.out.println("====== Gokul Subramanian == TEST ELEVEN EXECUTED =======");
        int olc = ruleFilesList().size();
        Urinals.perform();
        int nwc = ruleFilesList().size();
        Assertions.assertEquals(olc+1, nwc);
    }

}