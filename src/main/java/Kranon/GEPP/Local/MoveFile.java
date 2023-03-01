package Kranon.GEPP.Local;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.Echo;

import static Kranon.GEPP.Utileria.ModelEmail.pathFileDestino;
import static Kranon.GEPP.Utileria.ModelEmail.pathFileorigen;


public class MoveFile {

    static {

        System.setProperty("datelog", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }
    private static final Logger voLogger = LogManager.getLogger("Reporte");
    static public int MEGABYTE = 1024;


    public static void moveFile(File src, File destino) {

       try {
           voLogger.info("moveFile() --> Recibiendo la ruta de Origen[" + src + "], Recibiendo la ruta de destino[" +destino+ "]");
           FileUtils.moveFile(src,destino);

       } catch (IOException e){
           voLogger.error("moveFile() --> ERROR IOEXCEPTION[" +e.getMessage()+ "]");
       } catch (Exception e){
           voLogger.error("moveFile() --> ERROR EXCEPTION[" +e.getMessage()+ "]");
       }


    } //Fin  del metodo moveFile


    public static boolean mover(String origen, String destino){

        boolean move = false;
        File from = new File(origen);
        File to = new File(destino);

        try {

            if (!from.exists()) {

                System.out.println("El archivo no existe en el directorio establecido [" + from + "]");
                voLogger.info("mover() --> El archivo no existe en el directorio establecido [" + from + "]");

                return false;

            } if (to.exists()){

                System.out.println("El archivo ya existe en el directorio [" +to+ "]");
                voLogger.warn("mover() --> El archivo ya existe en el directorio [" + to + "]");

                return false;

            } else{
                 //compromiser();
                System.out.println("El archivo se tomara de la ruta[ " + from + "]" );

                moveFile(from , to);

                System.out.println("Depsoitando el archivo a la ruta[ " + to + "] Tamanio del Archivo[" + to.length()/MEGABYTE + "KB] Movido Exitosamente");
                voLogger.info("mover() --> Depsoitando el archivo a la ruta[ " + to + "] Tamanio del Archivo[" + to.length()/MEGABYTE + "KB] Movido Exitosamente");


                move = true;

            }


        }catch (Exception e){

            System.err.println("Revisar el contenido en los directorios[No existen o tienen un nombre diferente] -->" + e.getMessage());
            voLogger.warn("mover() --> Revisar el contenido en los directorios[No existen o tienen un nombre diferente] -->" + e.getMessage());


        } finally {

            System.out.println("Finalizado!");
            voLogger.info("mover() --> Proceso Finalizado !");

        }

        voLogger.info("mover() --> Retornando Variable Booleana !");

        return move;


    }//Fin  del metodo mover()


    public static void main(String[] args) throws IOException {

        System.setProperty("user", System.getProperty("user.name"));
        System.setProperty("diagonal", File.separator);

        pathFileorigen = new File("").getAbsolutePath();
        pathFileDestino = new File("").getAbsolutePath();

        File from = new File(pathFileorigen + File.separator + "\\Origen\\archivo.txt");
        String origen = String.valueOf(from);
        File to = new File(pathFileDestino  + File.separator + "\\Destino\\archivo.txt");
        String destino = String.valueOf(to);

        MoveFile moveFile = new MoveFile();

        if (mover(origen, destino)){

            System.out.println("movido con exito");

        } else {
            System.out.println("no se movio");
        }

    }

    private void compromiser() {

        try {

            File fileDestino = new File(pathFileDestino + File.separator + "\\Destino\\archivo.txt");

            System.out.println("Iniciando la comprobacion de los archivos en " + fileDestino);
            System.out.println();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileDestino));

            if (bufferedReader.readLine() == null) {

                System.out.println("WARNING : El archivo esta vacio [" + fileDestino.length()/MEGABYTE + "KB]");

            } else {

                System.out.println("INFO: El archivo existe en este directorio [" + fileDestino  + "]");
                System.out.println("INFO: El archivo contiene informacion [" + fileDestino.length()/MEGABYTE + "KB]");
            }

        } catch (FileNotFoundException e){


            System.out.println("No se encuentran los Archivos --> "  + e.getMessage());

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        System.out.println("El programa continua -----> ");


        //Me quede aqui papu!

    }


}
