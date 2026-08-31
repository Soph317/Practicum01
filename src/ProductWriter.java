import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        ArrayList<String> products = new ArrayList<>();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        Scanner in = new Scanner(System.in);

        boolean done = false;
        String ID = "";
        String Name = "";
        String Description = "";
        double Cost = 0.0;
        String Record = "";

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter Product ID (6 digits)");
            Name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            Description = SafeInput.getNonZeroLenString(in, "Enter Product Description");
            Cost = SafeInput.getRangedDouble(in, "Enter Product Cost", 0, 10000);
            Record = ID + ", " + Name + ", " + Description + ", " + Cost;
            products.add(Record);

            done = SafeInput.getYNConfirm(in, "Are you finished? ");
        }
        while (!done);
            try
            {
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                for(String rec : products)
                {
                    writer.write(rec, 0, rec.length());
                    writer.newLine();
                }
                writer.close();
                System.out.println("Data file written!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}
