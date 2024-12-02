import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static void main(String[]args) throws IOException {
        boolean loop = true;
        Scanner input = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        File working_directory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(working_directory.getPath() +"\\src\\data.csv");

        do{
            names.clear();
            names.add(SafeInput.getNonZeroLenString(input,"What is your first name"));
            names.add(SafeInput.getNonZeroLenString(input,"What is your lastname"));
            names.add(SafeInput.getregExString(input,"What is your id","^\\d{6}$"));
            names.add(SafeInput.getregExString(input,"What is your email","^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new  BufferedWriter(new OutputStreamWriter(out));
            try{


                for(String add : names) {
                    writer.write(add, 0, add.length());
                    writer.write(",",0,",".length());
                }
                writer.newLine();


            } catch (IOException e)
            {
                e.printStackTrace();
            }
            loop = SafeInput.getYNconfirm(input,"Do you want to input more");
            if(!loop){
                writer.close();
            }
        }while (loop);

    }
}
