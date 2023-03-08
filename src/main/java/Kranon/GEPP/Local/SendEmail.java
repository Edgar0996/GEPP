package Kranon.GEPP.Local;


import Kranon.GEPP.Utileria.ModelEmail;
import Kranon.GEPP.Utileria.Utilerias;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static Kranon.GEPP.Utileria.Utilerias.RecuperaArhivoConf;


public class SendEmail {

    static {
        System.setProperty("datelog", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }
    private Utilerias voUtil = new Utilerias();
    private Map<String, String> voMapConfiguration = new HashMap<String, String>();
    private static final Logger voLoggers = LogManager.getLogger("Reporte");


    public boolean sendMailCloudKranon(String asunto, String vsUII) {

        Utilerias voUtil = new Utilerias();
        String vsUUI = vsUII;

        voLoggers.info("sendMailCloudKranon()--> INICIO DE PROCESO: ENVIO DE REPORTE FINAL VIA EMAIL");
        voLoggers.info("sendMailCloudKranon()--> BUSCANDO LOS ARCHIVOS DE CONFIGURACION");

        Map<String, String> voMapConfiguration = RecuperaArhivoConf(vsUUI);

        if (voMapConfiguration.size() == 0 ){

            System.out.println("vacio o no encuentra nada");
            voLoggers.info("sendMailCloudKranon()--> NO SE ENCONTRARON ARCHIVOS DE CONFIGURACION");

        } else {

            voLoggers.info("sendMailCloudKranon()--> LEYENDO LOS ARCHIVOS DE CONFIGURACION");

            voUtil.getProperties(voMapConfiguration, "");
            final String username = voMapConfiguration.get("MailUsername");
            final String password = voMapConfiguration.get("MailPassword");
            final String mailAuth = voMapConfiguration.get("MailAuth");
            final String mailEnable = voMapConfiguration.get("MailEnable");
            final String mailHost = voMapConfiguration.get("MailHost");
            final String mailPort = voMapConfiguration.get("MailPort");
            final String recipients = voMapConfiguration.get("MailDestinatario");

            voLoggers.info("[" + vsUII +  "]sendMailCloudKranon() --> Working With: [" + username +"]," +
                    " MailAuth: [" + mailAuth+ "], MailEnable: [" +mailEnable+ "], MailHost: [" + mailHost +"]" +
                    " Mail Port: [" +mailPort+"], MailDestinatario: ["+recipients+ "]");

            Properties props = new Properties();
            props.put("mail.smtp.host", mailHost);
            props.put("mail.smtp.port", mailPort);
            props.put("mail.smtp.auth", mailAuth);
            props.put("mail.smtp.starttls.enable", mailEnable);
            props.put("mail.smtp", username);

            voLoggers.info("sendMailCloudKranon()--> AUTENTICANDO CON LAS CREDENCIALES PASSWORD Y USERNAME");
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            };

            Session sesion = Session.getDefaultInstance(props, auth);
            voLoggers.info("sendMailCloudKranon()--> SESION CREADA EXITOSAMENTE --> [" + sesion + "]" );
            sendMail(sesion, recipients, asunto);
        }
        return true;
    }

    /**
    public static void main(String[] args) throws MessagingException {


        try {

            String vsUUI = "123456789";
            Map<String, String> voMapConfiguration = RecuperaArhivoConf(vsUUI);


            SendEmail obj1 = new SendEmail();
            obj1.sendMailCloudKranon("sadsad","123456789");


        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Ocurrio un error en el llamado de las clases " + e.getMessage());
        }




        final String MailUsername="evazquez@kranon.com";
        final String MailPassword="!Kra_96Edgar";
        boolean MailAuth=true;
        boolean MailEnable=true;
        String MailHost="smtp.gmail.com";
        int MailPort=587;
        String MailDestinatario="vzzlor@gmail.com";
        //String MailDestinatarioGDS="amonroy@kranon.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", MailHost);
        props.put("mail.smtp.port", MailPort);
        props.put("mail.smtp.auth", MailAuth);
        props.put("mail.smtp.starttls.enable", MailEnable);

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(MailUsername, MailPassword);
            }
        };

        Session sesion = Session.getDefaultInstance(props, auth);
        System.out.println("creado!");

        sendMail(sesion, MailDestinatario, "Resumen de ejecucion Reporte GEPP", "resumen de reporte");

    }  */


    private static void sendMail(Session sesion, String recipients, String asunto) {
        try {

            Message msg = new MimeMessage(sesion);
            String[] parts = recipients.split(",");
            ArrayList<String> email = new ArrayList<String>(Arrays.asList(parts));
            InternetAddress[] address = new InternetAddress[email.size()];

            for (int i = 0; i <email.size(); i++){
                address[i] = new InternetAddress(email.get(i));
            }

            voLoggers.info("sendMail()--> SE HAN ENCONTRADO " + address.length+ " DIRECCIONES DE CORREO ELECTRONICO PARA SU ENVIO --> [" +Arrays.toString(address)+"]");

            msg.addRecipients(Message.RecipientType.TO, address);
            msg.setSubject(asunto);
            msg.setContent(getBodyHtml(), "text/html");

            System.out.println("Sending mensaje...............");
            voLoggers.info("sendMail()--> ENVIANDO CORREO ELECTRONICO DE REPORTE FINAL................");
            Transport.send(msg);
            System.out.println("Sent message successfully....");

        }catch (MessagingException  mex){
            voLoggers.error("sendMail()--> NO SE ENVIO EL CORREO ELECTRONICO DEL REPORTE FINAL" + mex.getMessage());

        }catch (Exception e){
            voLoggers.error("sendMail()--> ERROR DE CAPTURA DE EXCEPTION " + e.getMessage());
        } finally {
            voLoggers.info("sendMail()--> CORREO ENVIADO EXITOSAMENTE A LOS DESTINATARIOS");
            System.out.println("Correo enviado de manera satisfactoria");
        }
    }

    private static String getBodyHtml(){

        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\n" +
                "  <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n" +
                "\n" +
                "  <title>Reporte GEPP</title>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "  <table style=\"height: 100%; width: 800px;\">\n" +
                "    <tr style=\"align-items: center; text-align: center;\">\n" +
                "  \n" +
                "      <th>\n" +
                "        <h3 style=\"color: #2E3C40;\n" +
                "       \n" +
                "       text-transform: uppercase;\n" +
                "        font-size: 16px;\n" +
                "        height: 0px; width: 800px;\n" +
                "        font-weight: 800;\n" +
                "        text-align: center;\n" +
                "        line-height: 1;\n" +
                "        font-family: Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0.9 1.5em;\n" +
                "        transition: color .45s ease;\n" +
                "       \n" +
                "        \">Reporte: 27-Enero-2023\n" +
                "        </h3>\n" +
                "      </th>\n" +
                "    </tr> <!--Fin de encabezado fecha del reporte-->\n" +
                "  \n" +
                "  </table>\n" +
                "\n" +
                "    <table style=\"height: 80px; width: 800px;\">\n" +
                "    <tr>\n" +
                "\n" +
                "      <td style=\"width: 50%; \">\n" +
                "        <h3 style=\"color: #2E3C40;\n" +
                "                  font-size: 3.5em;\n" +
                "                  font-weight: 600;\n" +
                "                  line-height: 0;\n" +
                "                  padding-top: 0.1em;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  margin: 0 0 0.1em;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "                  transition: color .45s ease, border .45s ease;\">GEPP\n" +
                "\n" +
                "          <img src=\"https://logos-download.com/wp-content/uploads/2016/03/Pepsi_Logo_2014.png\"\n" +
                "            style=\"max-width: 140px; max-height: 150px; align-content:right\">\n" +
                "\n" +
                "        </h3>\n" +
                "\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\"width: 25%; \">\n" +
                "        <p style=\" \n" +
                "              \n" +
                "              text-align: right;\n" +
                "              font-size: 10px;\n" +
                "              font-weight: 600;\n" +
                "              line-height: 2.5;\n" +
                "              margin: 0 auto;\n" +
                "              color: black;\n" +
                "              text-decoration: solid;\n" +
                "              font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        \">Fecha De Inicio De Ejecucion:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" \n" +
                "              text-align: right;\n" +
                "              font-size: 10px;\n" +
                "              font-weight: 600;\n" +
                "              line-height: 0.1;\n" +
                "              margin: 0 auto;\n" +
                "              color: black;\n" +
                "              text-decoration: solid;\n" +
                "              font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        \">Fecha Fin Ejecucion:\n" +
                "        </p>\n" +
                "\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\"width: 25%;\">\n" +
                "\n" +
                "        <p style=\" \n" +
                "              text-align: left;\n" +
                "              font-size: 10px;\n" +
                "              font-weight: 600;\n" +
                "              margin: 0 auto;\n" +
                "              line-height: 2.5;\n" +
                "\n" +
                "              color: blue;\n" +
                "              text-decoration: solid;\n" +
                "              font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "              \">2023-02-03T12:21:12.00</p>\n" +
                "\n" +
                "        <p style=\"\n" +
                "             text-align: left;\n" +
                "              font-size: 10px;\n" +
                "              font-weight: 600;\n" +
                "              margin: 0 auto;\n" +
                "              line-height: 0.1;\n" +
                "\n" +
                "              color: blue;\n" +
                "              text-decoration: solid;\n" +
                "              font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "             \">2023-09-03T12:21:12.00</p>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "  </table> <!--Fin de la tabla para los encabezados-->\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <div> <!--Inicio de la division-->\n" +
                "    <p style=\"\n" +
                "        height: 0px; width: 800px;\n" +
                "        margin-top: 0%;\n" +
                "        border-bottom: 2px solid black;\n" +
                "        transition: color .45s ease, border .45s ease;\"></p>\n" +
                "  </div><!--Fin de la division de linea-->\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table style=\"height: 0px; width: 800px;\">\n" +
                "    <tr>\n" +
                "      <h4 style=\" color: #000000;\n" +
                "        text-transform: uppercase;\n" +
                "        font-size: 14px;\n" +
                "        height: 0px; width: 800px;\n" +
                "        font-weight: 800;\n" +
                "        text-align: center;\n" +
                "        line-height: 1;\n" +
                "        font-family: Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0.9 1.5em;\n" +
                "        transition: color .45s ease;\n" +
                "        \">Resumen de Descarga de Archivos Fuentes - Listas De Marcacion </h4>\n" +
                "    </tr>\n" +
                "  </table><!--Fin de los sub - encabezados-->\n" +
                "\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "\n" +
                "    <tr>\n" +
                "\n" +
                "      <td style=\"width: 20%; text-align: right;\">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Inicio del Proceso:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Conexión al SFTP:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Archivos Encontrados:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Archivos Descargados:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Archivos No cumplen:</p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <td style=\"width: 15%; text-align: left; \">\n" +
                "\n" +
                "        <p style=\" color: blue;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 5em;\n" +
                "                      margin: 0 auto;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">2/27/23 20:00 </p>\n" +
                "        <p style=\" color: green;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: red;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <p style=\" color: green;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #4</p>\n" +
                "        <p style=\" color: red;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #5</p>\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <!--Fin de los Textos del 50% de la tabla lado izquierdo :-->\n" +
                "\n" +
                "\n" +
                "      <td style=\"  width: 10%; text-align: right; vertical-align: bottom; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: right;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 2;\n" +
                "        margin: 0 auto;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "         Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: right;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 0.7;\n" +
                "        margin: 0 auto;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "        Detalle Archivos:\n" +
                "        </p>\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\" width: 30%; text-align: left; vertical-align: bottom; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: left;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.8;\n" +
                "        margin: 0 auto;\n" +
                "        padding-left: 0.5em;\n" +
                "        opacity: 0.5;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: left;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 0.8;\n" +
                "        padding-left: 0.5em;\n" +
                "        opacity: 0.5;\n" +
                "        margin: 0 auto;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "          C:\\Appl\\SiVale\\Reportes\\ERROR_API_Genesys_ALL.csv</p>\n" +
                "      </td>\n" +
                "    </tr><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "    <!--Fin de los Textos del 50% de la tabla lado derecho :-->\n" +
                "\n" +
                "  </table><!--Fin de la tabla Resumen de Descarga :-->\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <div> <!--Inicio de la division-->\n" +
                "    <p style=\"\n" +
                "    height: 0px; width: 800px;\n" +
                "        border-bottom: 3px solid black;\n" +
                "        transition: color .45s ease, border .45s ease;\"></p>\n" +
                "  </div><!--Fin de la division de linea-->\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "    <tr>\n" +
                "      <h4 style=\" color: #000000;\n" +
                "        text-transform: uppercase;\n" +
                "        font-size: 14px;\n" +
                "        font-weight: 800;\n" +
                "        height: auto; width: 800px;\n" +
                "        text-align: center;\n" +
                "        line-height: 1;\n" +
                "        font-family: Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0.9 1.5em;\n" +
                "        transition: color .45s ease;\n" +
                "        \">Validacion De Integridad De Datos - Listas De Marcacion </h4>\n" +
                "    </tr>\n" +
                "  </table><!--Fin de los sub - encabezados-->\n" +
                "\n" +
                "\n" +
                "  <!--Inicio de la tabla de validacion de integridad -->\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "\n" +
                "    <tr>\n" +
                "\n" +
                "      <td style=\"width: 20%; text-align: right;\">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Inicio del Proceso:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Archivos Analizados:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Archivos Finales:</p>\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <td style=\"width: 10%; text-align: left; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 5em;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Fecha</p>\n" +
                "        <p style=\" color: green;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: green;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <!--Fin de los Textos del 50% de la tabla lado izquierdo :-->\n" +
                "\n" +
                "\n" +
                "      <td style=\"  width: 15%; text-align: right; vertical-align: bottom; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: right;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 0.9;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "        Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: right;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.9;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "        Detalle Archivos:\n" +
                "        </p>\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\" width: 30%; text-align: left; vertical-align: bottom; \">\n" +
                "\n" +
                "        <p style=\" color: red;\n" +
                "        text-align: left;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        margin: 0 auto;\n" +
                "        padding-left: 0.5em;\n" +
                "        opacity: 0.5;\n" +
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_sadsadsaALL.csv\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: left;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 2;\n" +
                "        padding-left: 0.5em;\n" +
                "        margin: 0 auto;\n" +
                "        opacity: 0.5;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "          C:\\Appl\\SiVale\\Reportes\\ERROR_API_Genesys_ALL.csv</p>\n" +
                "      </td>\n" +
                "    </tr><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "    <!--Fin de los Textos del 50% de la tabla lado derecho :-->\n" +
                "\n" +
                "  </table><!--Fin de la tabla Validacion de integridad de datos :-->\n" +
                "\n" +
                "\n" +
                "  <div> <!--Inicio de la division-->\n" +
                "    <p style=\"\n" +
                "        height: auto; width: 800px;\n" +
                "        border-bottom: 3px solid black;\n" +
                "        transition: color .45s ease, border .45s ease;\"></p>\n" +
                "  </div><!--Fin de la division de linea-->\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "    <tr>\n" +
                "      <h4 style=\" color: #000000;\n" +
                "        text-transform: uppercase;\n" +
                "        font-size: 14px;\n" +
                "        font-weight: 800;\n" +
                "        height: auto; width: 800px;\n" +
                "        text-align: center;\n" +
                "        line-height: 1;\n" +
                "        font-family: Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0.9 1.5em;\n" +
                "        transition: color .45s ease;\n" +
                "        \">Objetos Genesys Cloud </h4>\n" +
                "    </tr>\n" +
                "  </table><!--Fin de los sub - encabezados-->\n" +
                "\n" +
                " <!--Iniciode la tabla de objetos genesys >-->\n" +
                "\n" +
                " <table style=\"height: auto; width: 800px;\">\n" +
                "\n" +
                "  <tr>\n" +
                "\n" +
                "    <td style=\"width: 20%; text-align: right;\">\n" +
                "\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Inicio del Proceso:</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Campañas GC:</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Listas Marcación GC:</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Correlaciones del Proceso:</p>\n" +
                "\n" +
                "      <br><!--salto de linea-->\n" +
                "\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Campañas Apagadas:</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Listas Descargadas:</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Listas Limpiadas:</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Archivos CSV Cargadas</p>\n" +
                "     \n" +
                "      <br><!--salto de linea-->\n" +
                "\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Revisión Contenido LM - OK:</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Revisión Contenido LM - Error:</p>\n" +
                "      <br><!--salto de linea-->\n" +
                "\n" +
                "    </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "    <td style=\"width: 10%; text-align: left; \">\n" +
                "\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: left;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  padding-left: 5em;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Fecha</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: left;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: left;\n" +
                "                  font-size: 10px;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: left;\n" +
                "                  font-size: 10px;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "\n" +
                "      <br><!--salto de linea-->\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   font-weight: 600;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #1</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   font-weight: 600;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   font-weight: 600;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   font-weight: 600;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "      <br><!--salto de linea-->\n" +
                "\n" +
                "      <p style=\" color: green;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    margin: 0 auto;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "      <p style=\" color:red;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "      <br><!--salto de linea-->\n" +
                "      \n" +
                "    </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "    <!--Fin de los Textos del 50% de la tabla lado izquierdo :-->\n" +
                "\n" +
                "\n" +
                "    <td style=\"width: 15%; text-align: left; vertical-align: bottom; \">\n" +
                "\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  padding-left: 5em;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  color: black;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      \n" +
                "\n" +
                "      <br><!--salto de linea-->\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: right;\n" +
                "                   font-size: 10px;\n" +
                "                   font-weight: 600;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: right;\n" +
                "                   font-size: 10px;\n" +
                "                   font-weight: 600;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: right;\n" +
                "                   font-size: 10px;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   font-weight: 600;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: right;\n" +
                "                   font-size: 10px;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   font-weight: 600;\n" +
                "                   margin: 0 auto;\n" +
                "                   color: black;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      \n" +
                "\n" +
                "\n" +
                "      <br><!--salto de linea-->\n" +
                "\n" +
                "      <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    margin: 0 auto;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Detalle Archivo</p>\n" +
                "      <br><!--salto de linea-->\n" +
                "     \n" +
                "\n" +
                "    </td>\n" +
                "\n" +
                "    <td style=\"width: 30%; text-align: left; vertical-align: bottom; \">\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: left;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  margin: 0 auto;\n" +
                "                  opacity: 0.5;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: left;\n" +
                "                  opacity: 0.5;\n" +
                "                  font-size: 10px;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                  text-align: left;\n" +
                "                  font-size: 10px;\n" +
                "                  padding-left: 0.7em;\n" +
                "                  font-weight: 600;\n" +
                "                  margin: 0 auto;\n" +
                "                  opacity: 0.5;\n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <br><!--salto de linea-->\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   font-weight: 600;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   margin: 0 auto;\n" +
                "                   opacity: 0.5;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   font-weight: 600;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   margin: 0 auto;\n" +
                "                   opacity: 0.5;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   font-weight: 600;\n" +
                "                   margin: 0 auto;\n" +
                "                   opacity: 0.5;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                   text-align: left;\n" +
                "                   font-size: 10px;\n" +
                "                   padding-left: 0.7em;\n" +
                "                   font-weight: 600;\n" +
                "                   margin: 0 auto;\n" +
                "                   opacity: 0.5;\n" +
                "                   text-decoration: solid;\n" +
                "                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <br><!--salto de linea-->\n" +
                "      <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    opacity: 0.5;\n" +
                "                    margin: 0 auto;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    font-weight: 600;\n" +
                "                    opacity: 0.5;\n" +
                "                    margin: 0 auto;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      <br><!--salto de linea-->\n" +
                "    </td>\n" +
                "  </tr><!--Fin de los Textos de lado izquierdo :-->\n" +
                "  <!--Fin de los Textos del 50% de la tabla lado derecho :-->\n" +
                "</table><!--Fin de la tabla de objetos genesys >-->\n" +
                " \n" +
                "\n" +
                "\n" +
                "\n" +
                "  <div> <!--Inicio de la division-->\n" +
                "    <p style=\"\n" +
                "      border-bottom: 3px solid black;\n" +
                "      height: auto; width: 800px;\n" +
                "      transition: color .45s ease, border .45s ease;\"></p>\n" +
                "  </div><!--Fin de la division de linea-->\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "    <tr>\n" +
                "      <h4 style=\" color: #000000;\n" +
                "      text-transform: uppercase;\n" +
                "      font-size: 14px;\n" +
                "      height: auto; width: 800px;\n" +
                "      font-weight: 800;\n" +
                "      text-align: center;\n" +
                "      line-height: 1;\n" +
                "      font-family: Tahoma, Geneva, Verdana, sans-serif;\n" +
                "      letter-spacing: 0.1em;\n" +
                "      margin: 0 0.9 1.5em;\n" +
                "      transition: color .45s ease;\n" +
                "      \">Encendido de Campañas GEPP </h4>\n" +
                "    </tr>\n" +
                "  </table><!--Fin de los sub - encabezados-->\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "    <tr>\n" +
                "      <td style=\"width: 20%; text-align: right;\">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Inicio del Proceso:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Campañas Apagadas:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Campañas Encendidas:</p>\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <td style=\"width: 10%; text-align: left; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    margin: 0 auto;\n" +
                "                    padding-left: 5em;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Fecha</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: green;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: green;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <!--Fin de los Textos del 50% de la tabla lado izquierdo :-->\n" +
                "\n" +
                "\n" +
                "      <td style=\"  width: 15%; text-align: right; vertical-align: bottom; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 2;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "      Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        \n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"> \n" +
                "      Detalle Archivos:\n" +
                "        </p>\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\" width: 30%; text-align: left; vertical-align: bottom; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.8;\n" +
                "      opacity: 0.5;\n" +
                "\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      opacity: 0.5;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1;\n" +
                "      padding-left: 0.5em;\n" +
                "      margin: 0 auto;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\ERROR_API_Genesys_ALL.csv\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "    <!--Fin de los Textos del 50% de la tabla lado derecho :-->\n" +
                "\n" +
                "  </table><!--Fin de la tabla Validacion de integridad de datos :-->\n" +
                "\n" +
                "\n" +
                "  <div> <!--Inicio de la division-->\n" +
                "    <p style=\"\n" +
                "    height: auto; width: 800px;\n" +
                "      border-bottom: 3px solid black;\n" +
                "      transition: color .45s ease, border .45s ease;\"></p>\n" +
                "  </div><!--Fin de la division de linea-->\n" +
                "\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "    <tr>\n" +
                "      <h4 style=\" color: #000000;\n" +
                "      text-transform: uppercase;\n" +
                "      font-size: 14px;\n" +
                "      font-weight: 800;\n" +
                "      text-align: center;\n" +
                "      height: auto; width: 800px;\n" +
                "      line-height: 1;\n" +
                "      font-family: Tahoma, Geneva, Verdana, sans-serif;\n" +
                "      letter-spacing: 0.1em;\n" +
                "      margin: 0 0.9 1.5em;\n" +
                "      transition: color .45s ease;\n" +
                "      \">Envio del Reporte Final </h4>\n" +
                "    </tr>\n" +
                "  </table><!--Fin de los sub - encabezados-->\n" +
                "\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "\n" +
                "    <tr>\n" +
                "\n" +
                "      <td style=\"width: 12%; text-align: right;\">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Inicio del Proceso:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Correos - TO:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Correos - CC:</p>\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <td style=\"width: 35%; text-align: left; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 5em;\n" +
                "                    line-height: 0.2;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Fecha</p>\n" +
                "\n" +
                "        <p style=\" color: green;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 2em;\n" +
                "                    line-height: 2.5;\n" +
                "                    margin: 0 auto;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">sistemas.automatizados@kranon.com\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 2em;\n" +
                "                    line-height: 0.2;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: blue;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">kranoncloud@kranon.com</p>\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "    </tr><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "    <!--Fin de los Textos del 50% de la tabla lado derecho :-->\n" +
                "\n" +
                "  </table><!--Fin de la tabla Validacion de integridad de datos :-->\n" +
                "\n" +
                "\n" +
                "  <div> <!--Inicio de la division-->\n" +
                "    <p style=\"\n" +
                "    height: auto; width: 800px;\n" +
                "      border-bottom: 3px solid black;\n" +
                "      \n" +
                "      transition: color .45s ease, border .45s ease;\"></p>\n" +
                "  </div><!--Fin de la division de linea-->\n" +
                "\n" +
                "  <table width=\"height: auto; width: 800px;\">\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\"width: 28%; text-align: right;\">\n" +
                "        <span style=\"\n" +
                "       text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 800;\n" +
                "                  line-height: 0;\n" +
                "                  color: dodgerblue;\n" +
                "                  margin: 0 auto;           \n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "      Visitar sitio web\n" +
                "        </span>\n" +
                "        </a>\n" +
                "        <footer>\n" +
                "          <div style=\"text-align: right ; margin-bottom: 2%;\">\n" +
                "\n" +
                "            <a href=\"https://kranon.com/\" target=\"_blank\" \n" +
                "            \n" +
                "            style=\"text-decoration: none;  color: dodgerblue;   \n" +
                "                  text-align: right;\n" +
                "                  font-size: 10px;\n" +
                "                  font-weight: 600;\n" +
                "                  line-height: 0;\n" +
                "                  margin: 0 auto;           \n" +
                "                  text-decoration: solid;\n" +
                "                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "              <Strong>Copyright (c) 2023, Promotora Kranon</Strong> </a>\n" +
                "\n" +
                "          </div>\n" +
                "        </footer>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
