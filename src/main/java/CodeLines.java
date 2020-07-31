import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CodeLines {
    public static void main(String[] args) throws IOException {
        File folder = new File("TestCases");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = FileUtils.readFileToString(file);
                System.out.println("For file "+file.getName()+" counted strings: "+LinesCalc.calculateCodeLines(content));
            }
        }
    }

}
