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

}