import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> people = new ArrayList<>();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        boolean done = false;

        String ID = "";
        String FirstName = "";
        String LastName = "";
        String Title = "";
        int YearOfBirth = 0;
        String Record = "";

        Scanner in = new Scanner(System.in);

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter ID (6 digits)");
            FirstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            LastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            Title = SafeInput.getNonZeroLenString(in, "Enter Title");
            YearOfBirth = SafeInput.getRangedInt(in, "Enter Year of Birth", 1000, 9999);

            Record = ID + ", " + FirstName + ", " + LastName + ", " + Title + ", " + YearOfBirth;

            people.add(Record);

            done = SafeInput.getYNConfirm(in, "Are you finished? ");
        }
        while (!done);
            try
            {
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                for(String rec : people)
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
