package Kranon.GEPP.Local;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.*;

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
 private static String newpathFileCSVD = "";
 private static String newpathFileCSVSIN = "";

 private static double pesoArchivo = 0D;

    /*
     * Lectura de CSV con BEANS en Java
     */


    /**
     * Recibe el numero de cabeceras en el metodo validarCabeceras();
     */

    private void validarCabeceras (String fileToRead, int numCabeceras) throws IOException {

        int viCountEmpty = 0;
        int viCountFull = 0;

        File fileToread = new File(fileToRead);

        try {

            int viNumeroCabeceras = leerCabecera(fileToRead); //Enviamos el archivo a leer - o el String con las cabeceras

            voLogger.info("validarCabeceras() --> NUMERO DE CABECERAS OBTENIDAS ---> ["+ viNumeroCabeceras +"]");

            if (viNumeroCabeceras == numCabeceras){

                voLogger.info("validarCabeceras() --> EL NUMERO DE CABECERAS COINCIDE --> ["+ viNumeroCabeceras +"][" + numCabeceras+ "]");
                System.out.println("cabeceras coinciden");
                System.out.println("Obteniendo los archivos del derectorio " + fileToread);

                try {

                    if(fileToread.exists()) {

                        voLogger.info("validarCabeceras() --> LEYENDO LA INFORMACION DEL ARCHIVO--> ["+ fileToread +"]");
                        BufferedReader br = new BufferedReader(new FileReader(fileToread));
                        FileInputStream archivo = new FileInputStream(fileToread);
                        InputStreamReader lectorStream = new InputStreamReader(archivo, StandardCharsets.UTF_8);
                        BufferedReader lector = new BufferedReader(lectorStream);

                        String linea;

                        List<String> vlRowsToPrint = new ArrayList<String>();

                        while ((linea = br.readLine())!= null) {
                            viCountFull ++;

                            try{

                                StringTokenizer st = new StringTokenizer(linea, ",");
                                boolean linea_vacia = true;

                                while (st.hasMoreTokens()) {

                                    String token = st.nextToken().trim();
                                    if (!token.equals("")) {
                                        linea_vacia = false;
                                        viCountEmpty ++;
                                        break;
                                    }
                                }

                                if (linea_vacia) {

                                    System.out.println("Ignorando linea vacia: " + linea);

                                }else {

                                    String linOK = comprobarColrow(linea);
                                    vlRowsToPrint.add(linOK); //Se agregan las lineas limpiadas a la linea vlRowsToPrint

                                    /*
                                     *
                                     FileWriter writer = new FileWriter(newpathFileCSVD, true);
                                     writer.write(linOK +"\n");
                                     writer.flush();
                                     writer.close();
                                     *
                                     */


                                }

                            }catch (Exception w){
                                voLogger.warn("validarCabeceras() --> HA OCURRIDO UNA EXCEPCION --> ["+ w.getMessage() + "]");
                            }
                        }
                        archivo.close();
                        lectorStream.close();
                        lector.close();
                        br.close();

                        try {

                            for (String linOK: vlRowsToPrint){
                                FileWriter writer = new FileWriter(newpathFileCSVD, true);
                                writer.write(linOK +"\n");
                                writer.flush();
                                writer.close();
                            }
                        } catch (IOException e)   {
                            voLogger.error("validarCabeceras() --> ERORR AL ESCRIBIR EL ARCHIVO NUEVO  --> ["+ newpathFileCSVD +"], IOEXCEPTION=["+e.getMessage()+"]");
                        }

                        voLogger.info("validarCabeceras() --> NO SE ENCONTRARON MAS LINEAS PARA PROCESAR --> ["+ 0 +"]");
                        voLogger.info("validarCabeceras() --> NUMERO DE LINEAS PROCESADAS  --> ["+ viCountFull +"], NUMERO DE FILAS VACIAS["+ (viCountFull - viCountEmpty)+"]");
                        voLogger.info("validarCabeceras() --> NUEVO CSV CREADO EN RUTA --> ["+ newpathFileCSVD +"]");

                    }else{

                        System.out.println("No existe el archivo --------------->");
                        voLogger.warn("validarCabeceras() --> ESTE ARCHIVO NO EXISTE O NO ESTA EN ESTE DIRECTORIO--> ["+ fileToRead + "]");
                    }

                } catch (IOException q) {
                    voLogger.error("validarCabeceras() --> IOEXCEOTION --> ["+ q.getMessage() + "]");

                }catch (Exception d){
                    voLogger.error("validarCabeceras() --> EXCEPTION --> ["+ d.getMessage() + "]");
                }
            } else {

                System.out.println("cabeceras no coinciden !");
                voLogger.error("validarCabeceras() --> EL NUMERO DE CABECERAS NO COINCIDE --> ["+ viNumeroCabeceras +"]!=[" + fileToRead+ "]");
            }

        } catch (Exception e){
            voLogger.error("validarCabeceras() --> EXCEPTION--> ["+ e.getMessage() + "]");
        } finally {
            //Obtenemos el numero de lineas impresas del CSV generado
            File file = new File(newpathFileCSVD);
            int viRowsPrinted = getRowsPrinted(newpathFileCSVD);
            voLogger.info("validarCabeceras() --> NUMERO DE LINEAS IMPRESAS  --> ["+ viRowsPrinted +"], RUTA DEL ARCHIVO LEIDO["+  file +"]" );
        }

        //return variableBoolean;
    }

    private int getRowsPrinted(String file) throws IOException {

        voLogger.info("getRowsPrinted() --> OBTENIENDO EL NUMERO DE FILAS IMPRESAS");

        Set<String> uniqueItems = new HashSet<String>();
        List<String> uniqueItemsPrin = new ArrayList<String>();

        int viRowsPrinted = 0 ;
        try{
            File csv = new File(file);
            if (csv.exists()){
                BufferedReader bfLector = new BufferedReader(new FileReader(csv));
                String linea ;
                while ((linea = bfLector.readLine()) != null){
                    String element = obtenerElements(linea);

                    if (uniqueItems.contains(element)){
                        //System.out.println("Valor Duplicado Encontrado : " + element);
                        System.out.println("linea duplicada "  + linea);
                    }else{
                        //System.out.println("Valor Encontrado Y Agregado al SET: " + element);
                        System.out.println("linea agregada "  + linea);
                        uniqueItems.add(element); //Agremamos el valor unico
                        uniqueItemsPrin.add(linea);
                    }
                    if (!linea.equals("")){
                        viRowsPrinted++;
                    }
                }
                bfLector.close();
                voLogger.info("getRowsPrinted() --> SIN MAS ELEMENTOS POR RECORRER --> [" + 0 +"]");

            }else{

                voLogger.warn("getRowsPrinted() --> ESTE ARCHIVO NO EXISTE --> ["+ csv + "]");
            }
        }catch (IOException e){
            voLogger.error("getRowsPrinted() --> IOEXCEPTION --> ["+ e.getMessage() + "]");
        }

        for (String lineClean : uniqueItemsPrin){
            System.out.println("Linea Unica impresa --- " + lineClean + " --- ");
            try {

                FileWriter vwNewCsv = new FileWriter(newpathFileCSVSIN, true);
                vwNewCsv.write(lineClean +"\n");
                vwNewCsv.flush();
                vwNewCsv.close();

            }catch (IOException e){
                voLogger.error("getRowsPrinted() --> IOEXCEPTION --> ["+ e.getMessage() + "]");
            }

        }
        System.err.println("NUMERO DE FILAS IMPRESAS SIN DUPLICADOS: " + uniqueItemsPrin.size());
        System.out.println("NUMERO DE FILAS IMPRESAS CON DUPLICADOS: " + viRowsPrinted);
        voLogger.info("getRowsPrinted() --> NUEVO CSV SIN DUPLICADOS GENERADO EN --> [" + newpathFileCSVSIN +"]");

        return viRowsPrinted;
    }

    private String obtenerElements(String linea) {
        String elemento = "";
        try{
            StringTokenizer st = new StringTokenizer(linea, ",");
            for (int i = 0; i < 2; i++) { //itera cada linea por posiciones 2 --> (nud) Cabecer
                st.nextToken();
               // System.out.println(" ---------------------------------->" + linea) ;
            }
            elemento = st.nextToken();
            //Almacena la posicion 2 --> Nud y lo retorna

        }catch (Exception e){
            voLogger.error("obtenerElements() -->EXCEPTION : ERROR DE LECTURA DE LINEA=["+ e.getMessage() + "]");
        }
        //System.out.println(elemento);
        return elemento;
    }

    private String comprobarColrow(String linea)  {
        int columempty = 0 ;
        int columfull = 0 ;
        int suma = 0;
        String lineaOK = "";

        try{

            CSVParser parser = new CSVParserBuilder().withSeparator(',')
                    .withQuoteChar(CSVParser.DEFAULT_QUOTE_CHARACTER)
                    .build();

            String[] valores = parser.parseLine(linea);
            
            for (String valore : valores){
                if (!valore.equals("")){
                    columfull ++;
                }else{
                    columempty ++;
                }
            }
            suma = (columfull + columempty); //SUMAR LAS COLUMNAS VACIAS Y NO VACIAS
        } catch (Exception e){
            voLogger.error("comprobarColrow() --> EXCEPTION - ERROR AL PARSEAR LA FILA --> ["+ e.getMessage() + "]");
        }

        try {

            if (columfull + columempty == 50){

                lineaOK = linea;
                System.out.println("Regresando Linea con :" + suma + " Columnas" + linea);

            }else{
                System.out.println("La linea no cumple con las columnas requeridas");
            }
        }catch (Exception w){
            voLogger.error("comprobarColrow() --> EXCEPTION --> ["+ w.getMessage() + "]");
        }
        return lineaOK;
    }
    private int leerCabecera(String pathFileCSV) {

        voLogger.info("leerCabecera() --> INICIANDO LECTURA DE CABECERAS");
        voLogger.info("leerCabecera() --> RUTA DE LECTURA DE ARCHIVO=[" + pathFileCSV+ "]");

        System.out.println("leerCabecera() --> RUTA DE LECTURA DE ARCHIVO=[" + pathFileCSV+ "]");

        int numeroDeCabeceras = 0 ;

        try {

            File file = new File(pathFileCSV);

            if (file.exists()) {

                System.out.println("leerCabecera() --> ARCHIVO EXISTE EN=[" + pathFileCSV+ "]");

                if (file.length() != 0){

                    pesoArchivo = Math.ceil(file.length()/MEGABYTE);

                    voLogger.info("leerCabecera() --> EL ARCHIVO CONTIENE INFORMACION=[" + pesoArchivo + "KB]");
                    System.out.println("leerCabecera() --> EL ARCHIVO CONTIENE INFORMACION=[" + pesoArchivo + "KB]");

                    BufferedReader br = new BufferedReader(new FileReader(pathFileCSV));
                    CSVReader reader = new CSVReader(br);

                    String[] lineaCabecera = reader.readNext();
                    voLogger.info("leerCabecera() -->  CONTENIDO DE CABECERAS ---> [" + Arrays.toString(lineaCabecera)  + "]");

                    System.out.println("leerCabecera() --> EL ARCHIVO CONTIENE=[" + pesoArchivo + "KB]");
                    numeroDeCabeceras = lineaCabecera.length;
                    br.close();
                    reader.close();

                }else{

                    voLogger.error("LeerCabecera() --> EL ARCHIVO NO CONTIENE INFORMACION=[" + file.length()+ "]");
                }

            }else {

                System.out.println("Este documento no existe ---> ["  + 0 + "]");
                voLogger.error("leerCabecera() --> EL ARCHIVO NO EXISTE=[" +pathFileCSV+ "]");
            }

        }catch (IOException io){
            voLogger.error("leerCabecera() --> ERROR IOEXCEPTION. [" + io.getMessage()+ "]");

        }catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        voLogger.info("leerCabecera() --> RETORNANDO EL NUMERO DE CABECERAS -->["+ numeroDeCabeceras + "]");
        return  numeroDeCabeceras;
    }

    public static void main(String[] args) throws IOException, CsvValidationException {

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

        pathFileCSV = new File("").getAbsolutePath() + File.separator + "CSV\\LM_Genesys.csv";
        newpathFileCSVD = new File("").getAbsolutePath() + File.separator + "CSV\\LM_NormalizadaConDuplicados.csv";
        newpathFileCSVSIN = new File("").getAbsolutePath() + File.separator + "CSV\\LM_NormalizadaSinDuplicados.csv";

        ValidateFields va = new ValidateFields();
        va.validarCabeceras(pathFileCSV, 50);

    }
}

/*
--------------------------------------------------------------------------------

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
                        Writer output = new BufferedWriter(new FileWriter("file.csv"","true));
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