package Kranon.GEPP.Local;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import sun.nio.cs.StandardCharsets.*;

import java.io.*;
import java.util.EmptyStackException;
import java.util.List;

/**Use OpenCSV to read CSV Files
 **/

public class ValidateFields {
 private static String pathFileCSV = "";
    /**Lectura de CSV con BEANS en Java*/
    public static void main(String[] args) throws FileNotFoundException {

        /*
        LECTURA DE CSV DE INGRESADA POR EL TECLADO
        pathFileCSV = new File("").getAbsolutePath();

        CSVReader CSVStrings = new CSVReaderBuilder(new FileReader(pathFileCSV + File.separator + "\\CSV\\archivo.csv")).build();
        CSVIterator iterator = new CSVIterator(CSVStrings);

        for (CSVIterator it = iterator; it.hasNext(); ) {
            String[] nextLine = it.next();

            System.out.println("[ " + nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3]  + " ]");
        }

         **/
        pathFileCSV = new File("").getAbsolutePath() + File.separator + "CSV\\L1.csv";
        System.out.println("Obteniendo los archivos del derectorio " + pathFileCSV);
        File file = new File(pathFileCSV);
        System.out.println(pathFileCSV);

        List<ModelListaMarcacion> beanListaMarcacion  = null;

        try {

            System.out.println("Leyendo los archivos CSV");

            FileReader reader = new FileReader(pathFileCSV);

            System.out.println("Parseando los archivos CSV");
            /*
             *
             * Guarda informacion del csv leido en un bean
             * withIgnoreEmptyLine -- Ignora las lineas en blanco
             *
             */


            beanListaMarcacion = new CsvToBeanBuilder<ModelListaMarcacion>(reader)
                    .withType(ModelListaMarcacion.class)
                    .withIgnoreEmptyLine(true)
                    .withOrderedResults(false)
                    .build()
                    .parse();

            System.out.println("Informacion retenniada en un Bean");

            for (ModelListaMarcacion lista : beanListaMarcacion) {

                System.out.println("linea impresa");
               // System.out.println(lista.toString());
            }


        } catch (IOException q) {

            q.printStackTrace();
            q.getMessage();

        }catch (Exception d){
            d.getMessage();
        }




        System.out.println("Inicio de Escritura de archivos  ------------->");

        try {


            System.out.println("Inicia a escribir el archivo csv..........");
            Writer writer = new FileWriter("fileGenerated.csv");
            StatefulBeanToCsv<ModelListaMarcacion> beanToCsv = new StatefulBeanToCsvBuilder<ModelListaMarcacion>(writer).build();
            beanToCsv.write(beanListaMarcacion);
            writer.flush();
            writer.close();

            System.out.println("Cerrar el archivo csv..........");

        }catch (IOException e){
            e.getMessage();

        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);

        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }









}
}

/*
 *if (file.exists()){

                    try {
                        Writer output = new BufferedWriter(new FileWriter("file.csv", true));
                        StringBuilder linea = new StringBuilder();

                        linea.append("apellido");
                        linea.append(',');
                        linea.append("Visitantestowebsite");
                        linea.append(',');
                        linea.append("nombre");
                        linea.append('\n');
                        output.write(linea.toString());

                        output.append(cr.getInin_outbound_id());
                        output.append(",");
                        output.append(cr.getNombre());
                        output.append(",");
                        output.append(cr.getApellidopaterno());
                        output.append("\n");
                        output.close();

                    }catch (IOException e){
                        e.getMessage();

                    }

                }else {

                    try {

                        System.out.println("El archivo no existe y se va a crear  con la cabecera");
                        PrintWriter writer = new PrintWriter(new File("files.csv"));
                        StringBuilder linea = new StringBuilder();


                        linea.append("apellido");
                        linea.append(',');
                        linea.append("Visitantestowebsite");
                        linea.append(',');
                        linea.append("nombre");
                        linea.append('\n');

                        linea.append(cr.getInin_outbound_id());
                        linea.append(',');
                        linea.append(cr.getNombre());
                        linea.append(',');
                        linea.append(cr.getApellidopaterno());
                        linea.append('\n');

                        writer.write(linea.toString());
                        writer.close();
                        writer.write(linea.toString());

                    }catch (FileNotFoundException wee){

                        wee.getMessage();

                    }


                }

 **/



