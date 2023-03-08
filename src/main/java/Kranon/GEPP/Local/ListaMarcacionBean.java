package Kranon.GEPP.Local;

import com.opencsv.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ListaMarcacionBean {

    public ListaMarcacionBean(){

    }


    /*
    * for (ModelListaMarcacion li : listaMarcaciones) {

                            //System.out.println("linea impresa" + listaMarcaciones);
                            // System.out.println(lista.toString());
                        }


                        try {

                            if (file.exists()){
                                System.out.println("Inicio de Escritura de archivos  ------------->");

                                System.out.println("Inicia a escribir el archivo csv..........");
                                Writer writer = new FileWriter("fileGenerated.csv");
                                StatefulBeanToCsv<ModelListaMarcacion> beanToCsv = new StatefulBeanToCsvBuilder<ModelListaMarcacion>(writer)
                                        .withMappingStrategy(strategy)
                                        .withOrderedResults(true)
                                        .build();

                                beanToCsv.write(listaMarcaciones);
                                writer.close();

                                System.out.println("Cerrar el archivo csv..........");
                            }else{

                                System.out.println("No se creo  el archivo CSV nuevo");
                            }

                        }catch (IOException e){
                            e.getMessage();

                        } catch (CsvRequiredFieldEmptyException e) {
                            throw new RuntimeException(e);

                        } catch (CsvDataTypeMismatchException e) {

         *
         *
         *
     * Guarda informacion del csv leido en un bean
     * withIgnoreEmptyLine -- Ignora las lineas en blanco
     *
     *
     *
     *  beanListaMarcacion = new CsvToBeanBuilder<ModelListaMarcacion>(reader)
            .withType(ModelListaMarcacion.class)
                                .build()
                                .parse();


                        System.out.println("Informacion retenniada en un Bean");

    MappingStrategy<ModelListaMarcacion> strategy = new FuzzyMappingStrategyBuilder<ModelListaMarcacion>().build();
                        strategy.setType(ModelListaMarcacion.class);
    //  strategy.setColumnMapping("inin-outbound-id","idBodega","nud","NOMBRE","TELUNO","TIPOTELUNO","TELDOS","TIPOTELDOS","TELTRES","TIPOTELTRES","TELCUATRO","TIPOTELCUATRO","FRECUENCIA","CORREO","CONTACTO","ruta","token","DIASINCOMPRA","marcador","appId","opcion","uq","INTENTOS_NUMERO_UNO","INTENTOS_NUMERO_DOS","INTENTOS_NUMERO_TRES","INTENTOS_NUMERO_CUATRO","INTENTOS_TOTAL","ContactCallable","ContactableByVoice","ContactableBySms","ContactableByEmail","ZipCodeAutomaticTimeZone","CallRecordLastAttempt-TELUNO","CallRecordLastResult-TELUNO","CallRecordLastAgentWrapup-TELUNO","SmsLastAttempt-TELUNO","SmsLastResult-TELUNO","Callable-TELUNO","ContactableByVoice-TELUNO","ContactableBySms-TELUNO","AutomaticTimeZone-TELUNO","CallRecordLastAttempt-TELDOS","CallRecordLastResult-TELDOS","CallRecordLastAgentWrapup-TELDOS","SmsLastAttempt-TELDOS","SmsLastResult-TELDOS","Callable-TELDOS","ContactableByVoice-TELDOS","ContactableBySms-TELDOS","AutomaticTimeZone-TELDOS"); // the fields to bind to in your bean

    CsvToBean<ModelListaMarcacion> csvToBean = new CsvToBeanBuilder<ModelListaMarcacion>(new FileReader(newpathFileCSV))
            //.withType(ModelListaMarcacion.class)
            //.withIgnoreEmptyLine(true)
            .withMappingStrategy(strategy).build();

    List<ModelListaMarcacion> listaMarcaciones = csvToBean.parse();

                        System.out.println("Informacion retenniada en un Bean");

     *
     **/


    public static void main(String[] args) throws IOException, CsvException {

        String pathFile = new File("").getAbsolutePath() + File.separator + "CSV\\LM_Genesys.csv";


        try {
        /*Para el uso de openCSV se puede usar un modelo iterador o leer todas las lineas a la vez*/
        System.out.println("Inicio de lectura a un bean -->");
        System.out.println("Accediento al archivo en: " + pathFile);

        MappingStrategy<ModelListaMarcacion> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(ModelListaMarcacion.class);




        List<ModelListaMarcacion> vlListaMarcaciones = new CsvToBeanBuilder<ModelListaMarcacion>(new FileReader(pathFile))
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();


            for ( ModelListaMarcacion vl : vlListaMarcaciones
                 ) {
                System.out.println(vl.toString());

            }

            Writer writer = new FileWriter("archivo.csv");


            StatefulBeanToCsv<ModelListaMarcacion> beanToCsv = new StatefulBeanToCsvBuilder<ModelListaMarcacion>(writer)
                    .withMappingStrategy(strategy)
                    .withOrderedResults(true)
                    .build();

            beanToCsv.write(vlListaMarcaciones);
            writer.close();



            String csvFile = "archivo.csv";
            String outputFile = "output.csv";
            String line = "";
            BufferedReader br = null;
            OutputStreamWriter osw = null;


            try {
                // Crear FileReader y BufferedReader para leer el archivo CSV
                br = new BufferedReader(new FileReader(csvFile));

                // Crear OutputStreamWriter para escribir en un archivo con la codificación UTF-8
                osw = new OutputStreamWriter(Files.newOutputStream(Paths.get(outputFile)), StandardCharsets.UTF_8);

                // Leer cada línea del archivo CSV y escribirla en el archivo de salida con UTF-8
                while ((line = br.readLine()) != null) {
                    osw.write(line + "\n");
                }

                System.out.println("Archivo CSV leído y codificado a UTF-8 exitosamente!");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (osw != null) {
                        osw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

       // System.out.println(bean.toString());

        }catch ( IOException e ) {  e.printStackTrace();

        } catch (CsvRequiredFieldEmptyException   | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }


    }












}
