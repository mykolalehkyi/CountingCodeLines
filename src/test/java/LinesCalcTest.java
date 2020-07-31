import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("When running LinesCalc")
class LinesCalcTest {
    @Nested
    @DisplayName("calculateCodeLines method")
    class TestCalculateCodeLines{
        @Test
        @DisplayName("example 1")
        void testExample1() {
            assertEquals(3,LinesCalc.calculateCodeLines(loadDataFromFile("TestCases/Example.txt")),"Should return right amount of strings");
        }

        @Test
        @DisplayName("example 2")
        void testExample2() {
            assertEquals(5,LinesCalc.calculateCodeLines(loadDataFromFile("TestCases/Example2.txt")),"Should return right amount of strings");
        }

        @Test
        @DisplayName("two enters in row Windows style")
        void testTwoEnters() {
            assertEquals(2,LinesCalc.calculateCodeLines(loadDataFromFile("TestCases/TwoEnters.txt")),"Should return right amount of strings");
        }

        @Test
        @DisplayName("two enters in row Linux style")
        void testTwoEntersLinux() {
            assertEquals(2,LinesCalc.calculateCodeLines("s\n\ns"),"Should return right amount of strings");
        }

        @Test
        @DisplayName("nested comments")
        void testNestedComments() {
            assertEquals(3,LinesCalc.calculateCodeLines(loadDataFromFile("TestCases/NestedComments.txt")),"Should return right amount of strings");
        }

        @Test
        @DisplayName("empty string")
        void testEmptyString() {
            assertEquals(0,LinesCalc.calculateCodeLines(new String()),"Should return right amount of strings");
        }

        @Test
        @DisplayName("one line")
        void testOneLine() {
            assertEquals(1,LinesCalc.calculateCodeLines("str"),"Should return right amount of strings");
        }

        @Test
        @DisplayName("only enters")
        void testEnters() {
            assertEquals(0,LinesCalc.calculateCodeLines("\n\n\n\r\n\r\n"),"Should return right amount of strings");
        }

        @Test
        @DisplayName("spaces tabs enters")
        void testSpacedString() {
            assertEquals(0,LinesCalc.calculateCodeLines(" \n \n\t\n      \t\r   \n\r\n  "),"Should return right amount of strings");
        }

        @Test
        @DisplayName("null")
        void testNullString() {
            assertThrows(java.lang.IllegalArgumentException.class,()->LinesCalc.calculateCodeLines(null),"Should return right exception");
        }

    }


    String loadDataFromFile(String pathname){
        File file = new File(pathname);
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    String content = FileUtils.readFileToString(file);
                    return content;
                } catch (IOException e) {
                    e.printStackTrace();
                    return new String();
                }
            }
        return new String();
    }
}