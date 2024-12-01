import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author wulft
 *
 * Use the thread safe NIO IO library to read a file
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir") + "/src");
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0;
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;
                while ((rec = reader.readLine()) != null) // used google for help
                {
                   lineCount ++;
                   wordCount += rec.split("\\s+").length; // got help for regex
                   charCount += rec.length();
                    System.out.println(rec);
                }
                reader.close();
                System.out.println("\n\n--- Summary Report ---");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Line Count: " + lineCount);
                System.out.println("Word Count: " + wordCount);
                System.out.println("Char Count: " + charCount);

            } else
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
