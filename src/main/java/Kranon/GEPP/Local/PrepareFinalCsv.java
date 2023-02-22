package Kranon.GEPP.Local;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrepareFinalCsv {


    public static void main(String[] args) {


        try {


        String path = new File("").getAbsolutePath() + File.separator + "CSV\\archivo2.csv";


            System.out.println(path);

        String output = path;
        File file = new File(output);
        Scanner scanner = new Scanner(System.in);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER , CSVWriter.DEFAULT_LINE_END);

            List<String[]> data = new ArrayList<String[]>();
            System.out.println("Entra una rows");
            int rows = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < rows; i++) {

                String row = scanner.nextLine();
                String [] rowdata = row.split(" ");
                data.add(rowdata);
            }

            writer.writeAll(data);
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
