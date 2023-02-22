package Kranon.GEPP.Local;
import java.io.*;

import org.apache.commons.io.FileUtils;

import static Kranon.GEPP.Utileria.ModelEmail.pathFileDestino;
import static Kranon.GEPP.Utileria.ModelEmail.pathFileorigen;


public class MoveFile {

   static public int MEGABYTE = 1024;

    public static void moveFile(File src, File destino) throws IOException {

            FileUtils.moveFile(src,destino);

    }

    public static void mover(){

        File from = new File(pathFileorigen + File.separator + "\\Origen\\archivo.txt");
        File to = new File(pathFileDestino  + File.separator + "\\Destino\\archivo.txt");

        try {

            if (!from.exists()) {

                System.out.println("El archivo no existe en el directorio establecido [" + from + "]");

            } if (to.exists()){

                System.out.println("El archivo ya existe en el directorio [" +to+ "]");

            } else{
                 //compromiser();
                System.out.println("El archivo se tomara de la ruta[ " + from + "]" );

                moveFile(from , to);

                System.out.println("Depsoitando el archivo a la ruta[ " + to + "] Tamanio del Archivo[" + to.length()/MEGABYTE + "KB] \nMovido Exitosamente");
            }


        }catch (Exception e){

            System.err.println("Revisar el contenido en los directorios[No existen o tienen un nombre diferente] -->" + e.getMessage());

        } finally {

            System.out.println("Finalizado!");

        }
    }


    public static void main(String[] args) throws IOException {

        System.setProperty("user", System.getProperty("user.name"));
        System.setProperty("diagonal", File.separator);

        pathFileorigen = new File("").getAbsolutePath();
        pathFileDestino = new File("").getAbsolutePath();

        MoveFile moveFile = new MoveFile();
        moveFile.mover();
        moveFile.compromiser();

    }

    private static void compromiser() {


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
