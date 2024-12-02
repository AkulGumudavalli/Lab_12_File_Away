import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

class FileInspector{
    public static void main(String[] args){
        JFileChooser chooser = new JFileChooser();
        File selected_file;
        try{
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                selected_file = chooser.getSelectedFile();
                Path file = selected_file.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int line = 0;
                int chars = 0;
                String rec = "";
                while (reader.ready()) {
                    rec = reader.readLine();
                    for(int i = 0; i<rec.length();i++){
                        chars++;
                    }
                    line++;
                }
                reader.close();
                System.out.printf("\nAmount of lines %4d and amount of characters in the entire file %4d ", line, chars);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}