package Kranon.GEPP.Local;


import Kranon.GEPP.Utileria.ModelEmail;
import Kranon.GEPP.Utileria.Utilerias;

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
    private static final org.apache.log4j.Logger voLoggers = org.apache.log4j.LogManager.getLogger("Reporte");
    public boolean sendMailCloudKranon(String asunto, String vsUII) throws MessagingException {




        Utilerias voUtil = new Utilerias();

        String vsUUI = vsUII;

        Map<String, String> voMapConfiguration = RecuperaArhivoConf(vsUUI);


        if (voMapConfiguration.size() <= 0 ){

            System.out.println("vacio o no encuentra nada");

        } else {


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


            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            };

            Session sesion = Session.getDefaultInstance(props, auth);
            System.out.println("creado!");

            sendMail(sesion, recipients, "Resumen de ejecucion Reporte GEPP", "resumen de reporte");

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

    private static void sendMail(Session sesion, String recipients, String texto, String textodos) throws MessagingException {

        try {

            MimeMessage msg = new MimeMessage(sesion);
            String[] parts = recipients.split(",");
            ArrayList<String> email = new ArrayList<String>(Arrays.asList(parts));
            InternetAddress[] address = new InternetAddress[email.size()];

            for (int i = 0; i <email.size(); i++){
                address[i] = new InternetAddress(email.get(i));
            }

            msg.addRecipients(Message.RecipientType.TO, address);
         //   msg.setFrom(new InternetAddress("vzzlor@gmail.com"));
            msg.setSubject(texto);
            msg.setContent(getBodyHtml(), "text/html");

            System.out.println("Sending mensaje...............");
            Transport.send(msg);
            System.out.println("Sent message successfully....");

        }catch (MessagingException  mex){
            mex.printStackTrace();
            voLoggers.error("No se envio el correo electronico" + mex.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            voLoggers.error("Error de Exception " + e.getMessage());

        }

        voLoggers.info("Correo enviado de manera satisfactoria");

        System.out.println("Correo enviado de manera satisfactoria");
    }

    private static String getBodyHtml(){

        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Reporte GEPP</title>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body> \n" +
                "\n" +
                "  <table style=\"height: 100%; width: 800px;\">\n" +
                "  \n" +
                "    \n" +
                "\n" +
                "    <tr style=\"align-items: center; text-align: center;\">\n" +
                "  \n" +
                "  \n" +
                "      <th>\n" +
                "        <h3 style=\"color: #2E3C40;\n" +
                "        text-align: center;\n" +
                "        font-size: 14px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        padding-bottom: 0em;\n" +
                "        padding-top: 0.9em;\n" +
                "        font-family: Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        margin: 0 0 0.0;\n" +
                "        transition: color .45s ease, border .45s ease;\">Reporte: 27-Enero-2023\n" +
                "        </h3>\n" +
                "  \n" +
                "      </th>\n" +
                "  \n" +
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
                "              color: black;\n" +
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
                "              color: black;\n" +
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
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Conexion al SFTP:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Archivos Encontrados:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Archivos Descargados:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: right;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Archivos No cumplen:</p>\n" +
                "\n" +
                "\n" +
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
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: green;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: #1aa0b8;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #4</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: red;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #5</p>\n" +
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
                "        line-height: 1.8;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: right;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"red\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
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
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: left;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        padding-left: 0.5em;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
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
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: green;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: green;\n" +
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
                "        line-height: 1.8;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: right;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
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
                "        color: black;\n" +
                "        text-decoration: solid;\n" +
                "        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "        text-align: left;\n" +
                "        font-size: 10px;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        padding-left: 0.5em;\n" +
                "        margin: 0 auto;\n" +
                "        color: black;\n" +
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
                "\n" +
                "  <!--Iniciode la tabla de objetos genesys >-->\n" +
                "\n" +
                "  <table style=\"height: auto; width: 800px;\">\n" +
                "\n" +
                "    <tr>\n" +
                "\n" +
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
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Campañas GC:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Listas Marcación GC:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Correlaciones del Proceso:</p>\n" +
                "\n" +
                "        <br><!--salto de linea-->\n" +
                "\n" +
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
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Listas Descargadas:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Listas Eliminadas:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Listas Creadas:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Archivos CSV Cargados:</p>\n" +
                "        <br><!--salto de linea-->\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Revisión Contenido LM - OK:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Revisión Contenido LM - Error:</p>\n" +
                "        <br><!--salto de linea-->\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Asignación LM a Campañas:</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: right;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\"># Respaldo Archivos CSVs:</p>\n" +
                "        <br><!--salto de linea-->\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <td style=\"width: 10%; text-align: left; \">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 5em;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Fecha</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    padding-left: 0.7em;\n" +
                "                    font-weight: 600;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: black;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "\n" +
                "        <br><!--salto de linea-->\n" +
                "        <p style=\" color: #000000;\n" +
                "                     text-align: left;\n" +
                "                     font-size: 10px;\n" +
                "                     font-weight: 600;\n" +
                "                     padding-left: 0.7em;\n" +
                "                     margin: 0 auto;\n" +
                "                     color: black;\n" +
                "                     text-decoration: solid;\n" +
                "                     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #1</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                     text-align: left;\n" +
                "                     font-size: 10px;\n" +
                "                     font-weight: 600;\n" +
                "                     padding-left: 0.7em;\n" +
                "                     margin: 0 auto;\n" +
                "                     color: black;\n" +
                "                     text-decoration: solid;\n" +
                "                     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                     text-align: left;\n" +
                "                     font-size: 10px;\n" +
                "                     padding-left: 0.7em;\n" +
                "                     font-weight: 600;\n" +
                "                     margin: 0 auto;\n" +
                "                     color: black;\n" +
                "                     text-decoration: solid;\n" +
                "                     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                     text-align: left;\n" +
                "                     font-size: 10px;\n" +
                "                     padding-left: 0.7em;\n" +
                "                     font-weight: 600;\n" +
                "                     margin: 0 auto;\n" +
                "                     color: black;\n" +
                "                     text-decoration: solid;\n" +
                "                     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: black;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #1</p>\n" +
                "\n" +
                "\n" +
                "        <br><!--salto de linea-->\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      font-weight: 600;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: green;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #2</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: red;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <br><!--salto de linea-->\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: green;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "                      text-align: left;\n" +
                "                      font-size: 10px;\n" +
                "                      padding-left: 0.7em;\n" +
                "                      font-weight: 600;\n" +
                "                      margin: 0 auto;\n" +
                "                      color: green;\n" +
                "                      text-decoration: solid;\n" +
                "                      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">Valor #3</p>\n" +
                "        <br>\n" +
                "\n" +
                "\n" +
                "      </td><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "      <!--Fin de los Textos del 50% de la tabla lado izquierdo :-->\n" +
                "\n" +
                "\n" +
                "      <td style=\"  width: 15%; text-align: right; \">\n" +
                "\n" +
                "\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <br> <!-------------------------------------------------------->\n" +
                "\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "\n" +
                "        <br><!--Salto de linea-->\n" +
                "\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"red\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <br><!--Salto de linea-->\n" +
                "\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\" width: 30%; text-align: left;\">\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "\n" +
                "        <br>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <br>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "\n" +
                "        <br>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "      </td>\n" +
                "    </tr><!--Fin de los Textos de lado izquierdo :-->\n" +
                "\n" +
                "    <!--Fin de los Textos del 50% de la tabla lado derecho :-->\n" +
                "\n" +
                "  </table><!--Fin de la tabla de objetos genesys >-->\n" +
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
                "\n" +
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
                "      line-height: 1.8;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
                "        </p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: right;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "          <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"#1aa0b8\" class=\"bi bi-paperclip\"\n" +
                "            viewBox=\"0 0 16 16\">\n" +
                "            <path\n" +
                "              d=\"M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z\" />\n" +
                "          </svg>Detalle Archivos:\n" +
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
                "      margin: 0 auto;\n" +
                "      padding-left: 0.5em;\n" +
                "      color: black;\n" +
                "      text-decoration: solid;\n" +
                "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">C:\\Appl\\SiVale\\Reportes\\OK_API_Genesys_ALL.csv</p>\n" +
                "\n" +
                "        <p style=\" color: #000000;\n" +
                "      text-align: left;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1;\n" +
                "      padding-left: 0.5em;\n" +
                "      margin: 0 auto;\n" +
                "      color: black;\n" +
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
                "        <p style=\" color: #000000;\n" +
                "                    text-align: left;\n" +
                "                    font-size: 10px;\n" +
                "                    font-weight: 600;\n" +
                "                    padding-left: 2em;\n" +
                "                    line-height: 2.5;\n" +
                "                    margin: 0 auto;\n" +
                "                    color: green;\n" +
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
                "                    color: green;\n" +
                "                    text-decoration: solid;\n" +
                "                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">kranoncloud@kranon.com</p>\n" +
                "\n" +
                "\n" +
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
                "\n" +
                "  <table width=\"height: auto; width: 800px;\">\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\"width: 28%; text-align: right;\">\n" +
                "\n" +
                "        <span style=\"\n" +
                "      display: block;\n" +
                "      color: dodgerblue;\n" +
                "      font-size: 10px;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      transition: color .45s ease; box-sizing: border-box;\">\n" +
                "      Visitar sitio web\n" +
                "        </span>\n" +
                "        </a>\n" +
                "        <footer>\n" +
                "          <div style=\"text-align: right ; margin-bottom: 2%;\">\n" +
                "\n" +
                "            <a href=\"https://kranon.com/\" target=\"_blank\" style=\"text-decoration: none;  color: dodgerblue; font-size: 10px;\">\n" +
                "              <Strong>Copyright (c) 2023, Promotora Kranon</Strong> </a>\n" +
                "\n" +
                "          </div>\n" +
                "        </footer>\n" +
                "\n" +
                "      </td>\n" +
                "\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
