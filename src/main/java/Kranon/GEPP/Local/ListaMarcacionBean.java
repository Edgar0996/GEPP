package Kranon.GEPP.Local;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.FuzzyMappingStrategyBuilder;
import com.opencsv.bean.MappingStrategy;

import java.io.FileReader;
import java.util.List;

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
                            throw new RuntimeException(e);
                        }

*
*
*
*
*
*
*
*
*
*
    *
    * */


    /*
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
     *
     *
     *
     *
     *
     *
     *
     *
     **/















}
