package org.example;



import Kranon.GEPP.Local.SendEmail;
import Kranon.GEPP.Utileria.ModelEmail;
import Kranon.GEPP.Utileria.Utilerias;

import javax.mail.MessagingException;
import java.io.File;

/**
 * Hello world!
 *
 */
public class App


{
    public static void main( String[] args ) throws MessagingException {

        System.out.println("Ejecucion pricipal del programa ---> "  );

        //Tomas las variables propiedades del sistema
        System.setProperty("user", System.getProperty("user.name"));
        System.setProperty("diagonal", File.separator);
        ModelEmail.pathConfigurations = new File("").getAbsolutePath();

        //Anñade la ruta absoluta a la variable pathConfigurations
        System.setProperty("pathConfigurations", new File("").getAbsolutePath());

        //Creamos un objeto de la clase send email y le mandamos dos variables al metodo sendmailKranon()
        SendEmail sendEmail = new SendEmail();

        if (sendEmail.sendMailCloudKranon("Reporte de Ejecucion Reporte GEPP from builder", "123456")) {
            System.out.println("Email enviado de manera exitosa a su destinatario");

        }else{
            System.out.println("EL email no fue enviado a sus destinatarios");
        }

        System.out.println("El programa finalizara aqui!");
        System.exit(0);
    }
}
