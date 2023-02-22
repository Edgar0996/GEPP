package Kranon.GEPP.Local;

import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvValidationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**Use OpenCSV to read CSV Files
 **/

public class ValidateFields {

    static {

        System.setProperty("datelog", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }
    private static final Logger voLogger = LogManager.getLogger("Reporte");
    private static final double MEGABYTE = 1024;


    public ValidateFields(){
        /*Constructor vacio!*/
    }
 private static String pathFileCSV = "";
 private static String newpathFileCSV = "";
 private static double pesoArchivo = 0D;

    /** Lectura de CSV con BEANS en Java*/



    /**Recibe el numero de cabeceras en el metodo validarCabeceras();*/
    private boolean validarCabeceras (int numeroCabeceras){

        int viNumeroCabeceras = 0;
        viNumeroCabeceras = leerCabecera();

        voLogger.info("validarCabeceras() --> NUMERO DE CABECERAS  ---> ["+ viNumeroCabeceras +"]");
        System.err.println("NUMERO DE CABECERAS  --->"  + viNumeroCabeceras+ "*********");

        try {

            if (viNumeroCabeceras == 50){

                System.out.println("cabeceras coinciden");

            } else {

                System.out.println("cabeceras no coinciden !");
                return false;
            }


        } catch (Exception e){

            System.out.println(e.getMessage());
        }

        return true;

    }

    private int leerCabecera() {

        voLogger.info("leerCabecera() --> INICIANDO LA LECTURA DE CABECERAS.");
        voLogger.info("leerCabecera() --> RUTA DE LECTURA DE ARCHIVO. [" + pathFileCSV+ "]");

        int numeroDeCabeceras = 0 ;
        String nombreCabeceras = "";


        try {

            File file = new File(pathFileCSV);

            if (file.exists()) {

                if (file.length() != 0){

                    pesoArchivo = Math.ceil(file.length()/MEGABYTE);

                    voLogger.info("leerCabecera() --> EL ARCHIVO CONTIENE INFORMACION. [" + pesoArchivo + "]KB");

                    BufferedReader br = new BufferedReader(new FileReader(pathFileCSV));
                    CSVReader reader = new CSVReader(br);

                    String[] lineaCabecera = reader.readNext();

                    System.out.println(Arrays.toString(lineaCabecera));
                    System.err.println("Este archivo CSV contiene [" + lineaCabecera.length + "] cabeceras ----------------" );

                    numeroDeCabeceras = lineaCabecera.length;

                    /*
                     * Las filas con menos de 50 elementos no entran en la condicion
                     * Las filas vacias o sin elementos no entraran a la lista
                     *
                    */

                    List<String> ListaContenidoOK = new ArrayList<String>();
                    List<String> ListaContenidoKO = new ArrayList<String>();
                    String str = "";



                    if (numeroDeCabeceras == 50){ //si son 50 cabeceras
                        while ((str = br.readLine()) != null ){

                            if (str.contains("")){

                              //  System.err.println("SKIP LINEA CONTENIDO=[" + str + "]");

                                ListaContenidoKO.add(str);
                            }

                            ListaContenidoOK.add(str);

                        } //Fin de while
                        System.out.println("ULTIMO ELEMENTO=[SIN LINEAS PARA PROCESAR]" );
                    } else {

                        //si no son 50 cabeceras

                        System.out.println("No cumples con las columnas especificas ---> [50]");

                    }

                    for (String as : ListaContenidoOK){
                        System.out.println(as);
                    }


                    int contador = 0 ;

                    while ((str = br.readLine()) != null ){



                        if (numeroDeCabeceras <= 50 ){

                            contador = contador + 1;

                            System.out.println(contador  + " 50 lineas" + str);

                          //  System.out.println("Linea con menos columnas" + str);

                        }

                        if( numeroDeCabeceras <= 50  &&  str.contains(" ")){

                            System.err.println( contador + "50 lineas and  '------'");


                           // System.out.println("fila con elementos faltantes" + str);

                        }else {

                            System.out.println("Agregada correctamente");
                            ListaContenidoOK.add(str);
                            //System.out.println("Agregado a la lista ---> " + str);

                        }

                   //    System.err.println("linea cargada a la lista---->" + str);
                    }


                }else{

                    voLogger.error("LeerCabecera() --> EL ARCHIVO NO CONTIENE INFORMACION. [" + file.length()+ "]");
                }

            }else {

                numeroDeCabeceras = 0 ;
                System.out.println("Este documento no existe ---> ["  + numeroDeCabeceras + "]");
                voLogger.error("leerCabecera() --> EL ARCHIVO NO EXISTE [" +pathFileCSV+ "]");
            }

        }catch (IOException io){
            voLogger.error("leerCabecera() --> ERROR IOEXCEPTION. [" + io.getMessage()+ "]");
            io.getMessage();

        }catch (CsvValidationException e) {

            throw new RuntimeException(e);
        }

        return  numeroDeCabeceras;
    }

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

        -------------------------------
         **/

        pathFileCSV = new File("").getAbsolutePath() + File.separator + "CSV\\Lista1.csv";
        newpathFileCSV = new File("").getAbsolutePath() + File.separator + "CSV\\";


        ValidateFields va = new ValidateFields();
        va.validarCabeceras(49);




}
}

/*



System.out.println("Obteniendo los archivos del derectorio " + pathFileCSV);
        File file = new File(pathFileCSV);
        System.out.println(pathFileCSV);

        List<ModelListaMarcacion> beanListaMarcacion  = null;

        try {

            if(file.exists()) {
                System.out.println("Leyendo los archivos CSV");
                FileReader reader = new FileReader(pathFileCSV);
                System.out.println("Parseando los archivos CSV");
                /*
                 *
                 * Guarda informacion del csv leido en un bean
                 * withIgnoreEmptyLine -- Ignora las lineas en blanco
                 *



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
        }else{

        System.out.println("No existe el archivo no digas mamadas flaco---------------->");
        }


        } catch (IOException q) {

        q.printStackTrace();
        q.getMessage();

        }catch (Exception d){
        d.getMessage();
        }



        try {



        if (file.exists()){
        System.out.println("Inicio de Escritura de archivos  ------------->");

        System.out.println("Inicia a escribir el archivo csv..........");
        Writer writer = new FileWriter(newpathFileCSV  + "fileGenerated.csv");
        StatefulBeanToCsv<ModelListaMarcacion> beanToCsv = new StatefulBeanToCsvBuilder<ModelListaMarcacion>(writer).build();
        beanToCsv.write(beanListaMarcacion);
        writer.flush();
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




++++++++++++++++++++++++++++++++++++++++++


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