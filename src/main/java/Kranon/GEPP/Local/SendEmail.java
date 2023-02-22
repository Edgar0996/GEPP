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
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<body style=\"box-sizing: border-box;\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table>\n" +
                "\n" +
                "    <td>\n" +
                "\n" +
                "      <tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "        <h3 style=\"color: #2E3C40;\n" +
                "        font-size: 3.5em;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        padding-bottom: .5em;\n" +
                "        padding-top: .5em;\n" +
                "        margin: 0 0 0.142857143em;\n" +
                "        border-bottom: 5px solid dodgerblue;\n" +
                "        transition: color .45s ease, border .45s ease;\">GEPP\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "          <img src=\"https://logos-download.com/wp-content/uploads/2016/03/Pepsi_Logo_2014.png\"\n" +
                "            style=\"max-width: 120px; max-height: 150px; align-content:right\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        </h3>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "      </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    </td>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "\n" +
                "\n" +
                "\n" +
                "        <h4 style=\" color: #000000;\n" +
                "\n" +
                "        text-transform: uppercase;\n" +
                "        font-size: 1.125em;\n" +
                "        font-weight: 800;\n" +
                "        \n" +
                "        line-height: 1;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0 1.5em;\n" +
                "        transition: color .45s ease;\n" +
                "        \">Resumen de la ejecucion del reporte custon GEPP</h4>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <tr >\n" +
                "      <td style=\" border-left: 5px solid whitesmoke;\">\n" +
                "\n" +
                "        <h3 style=\" color: #000000;\n" +
                "        opacity: 1;\n" +
                "        text-transform: uppercase;\n" +
                "        font-size: 1em;\n" +
                "        font-weight: 600;\n" +
                "        font-size: small;\n" +
                "        line-height: 1.1;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0 0;\n" +
                "        transition: color .45s ease;\n" +
                "        \"> Configuracion de ejecucion</h3>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "        color: #000000;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.2;\n" +
                "        margin: 0 0 0em;\n" +
                "        transform: translateY(0.1em);\n" +
                "        transition: opacity .45s ease, transform .5s ease;\">Tipo de Interacciones consultadas: </p>\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "        color: #000000;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.2;\n" +
                "        margin: 0 0 0;\n" +
                "        transform: translateY(0.1em);\n" +
                "        transition: opacity .45s ease, transform .5s ease;\">Fecha de ejecucion del reporte: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "        color: #000000;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.2;\n" +
                "        margin: 0 0 0em;\n" +
                "        transform: translateY(0.1em);\n" +
                "        transition: opacity .45s ease, transform .5s ease;\">Intervalos de tiempo: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "        color: #000000;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.2;\n" +
                "        margin: 0 0 0;\n" +
                "        transform: translateY(0.1em);\n" +
                "        transition: opacity .45s ease, transform .5s ease; \n" +
                "        border-bottom: 3px dotted  dodgerblue;\">Duracion de intervalo: </p>\n" +
                "      </td>\n" +
                "\n" +
                "\n" +
                "      <td style=\" border-left: 5px solid whitesmoke; width: 50%;\">\n" +
                "\n" +
                "        <h3 style=\" color: #000000;\n" +
                "        opacity: 1;\n" +
                "        text-transform: uppercase;\n" +
                "        font-size: 1em;\n" +
                "        font-weight: 600;\n" +
                "        font-size: small;\n" +
                "        line-height: 1.1;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0 0;\n" +
                "        transition: color .45s ease;\n" +
                "        \"> Configuracion de ejecucion</h3>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "        color: #000000;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.2;\n" +
                "        margin: 0 0 0em;\n" +
                "        transform: translateY(0.1em);\n" +
                "        transition: opacity .45s ease, transform .5s ease;\">Tipo de Interacciones consultadas: </p>\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Fecha de ejecucion del reporte: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0em;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Intervalos de tiempo: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease; \n" +
                "      border-bottom: 3px dotted  dodgerblue;\">Duracion de intervalo: </p>\n" +
                "      </td>\n" +
                "\n" +
                "    </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\" border-left: 5px solid whitesmoke;\"\">\n" +
                "\n" +
                "        <h3 style=\" color: #000000; opacity: 1; text-transform: uppercase; font-size: 1em; font-weight: 600; font-size:\n" +
                "        small; line-height: 1.1; letter-spacing: 0.1em; margin: 0 0 0; margin-top: 0.7em; transition: color .45s ease;\"> Configuracion de ejecucion</h3>\n" +
                "\n" +
                "        <p style=\" opacity: 0.5; color: #000000; font-weight: 600; line-height: 1.2; margin: 0 0 0em; transform:\n" +
                "        translateY(0.1em); transition: opacity .45s ease, transform .5s ease;\">Tipo de Interacciones consultadas: </p>\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Fecha de ejecucion del reporte: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0em;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Intervalos de tiempo: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.0;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      border-bottom: 3px dotted  dodgerblue;\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Duracion de intervalo: </p>\n" +
                "\n" +
                "\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\" border-left: 5px solid whitesmoke;\"\">\n" +
                "\n" +
                "        <h3 style=\" color: #000000; opacity: 1; text-transform: uppercase; font-size: 1em; font-weight: 600; font-size:\n" +
                "        small; line-height: 1.1; letter-spacing: 0.1em; margin: 0 0 0; margin-top: 0.7em; transition: color .45s ease; \"> Configuracion de ejecucion</h3>\n" +
                "\n" +
                "        <p style=\" opacity: 0.5; color: #000000; font-weight: 600; line-height: 1.2; margin: 0 0 0em; transform:\n" +
                "        translateY(0.1em); transition: opacity .45s ease, transform .5s ease;\">Tipo de Interacciones consultadas: </p>\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Fecha de ejecucion del reporte: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0em;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Intervalos de tiempo: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      border-bottom: 3px dotted  dodgerblue;\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Duracion de intervalo: </p>\n" +
                "\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\" border-left: 5px solid whitesmoke;\"\">\n" +
                "\n" +
                "        <h3 style=\" color: #000000; opacity: 1; text-transform: uppercase; font-size: 1em; font-weight: 600; font-size:\n" +
                "        small; line-height: 1.1; letter-spacing: 0.1em; margin: 0 0 0; margin-top: 0.7em; transition: color .45s ease;\"> Configuracion de ejecucion</h3>\n" +
                "\n" +
                "        <p style=\" opacity: 0.5; color: #000000; font-weight: 600; line-height: 1.2; margin: 0 0 0em; transform:\n" +
                "        translateY(0.1em); transition: opacity .45s ease, transform .5s ease;\">Tipo de Interacciones consultadas: </p>\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Fecha de ejecucion del reporte: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0em;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Intervalos de tiempo: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.0;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      border-bottom: 3px dotted  dodgerblue;\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Duracion de intervalo: </p>\n" +
                "\n" +
                "\n" +
                "      </td>\n" +
                "      <td style=\" border-left: 5px solid whitesmoke;\">\n" +
                "\n" +
                "        <h3 style=\" color: #000000;\n" +
                "        opacity: 1;\n" +
                "        text-transform: uppercase;\n" +
                "        font-size: 1em;\n" +
                "        font-weight: 600;\n" +
                "        font-size: small;\n" +
                "        line-height: 1.1;\n" +
                "        letter-spacing: 0.1em;\n" +
                "        margin: 0 0 0;\n" +
                "        margin-top: 0.7em;\n" +
                "        transition: color .45s ease;\n" +
                "       \"> Configuracion de ejecucion</h3>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "        color: #000000;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1.2;\n" +
                "        margin: 0 0 0em;\n" +
                "        transform: translateY(0.1em);\n" +
                "        transition: opacity .45s ease, transform .5s ease;\">Tipo de Interacciones consultadas: </p>\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Fecha de ejecucion del reporte: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\" opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0em;\n" +
                "      transform: translateY(0.1em);\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Intervalos de tiempo: </p>\n" +
                "\n" +
                "\n" +
                "        <p style=\"  opacity: 0.5;\n" +
                "      color: #000000;\n" +
                "      font-weight: 600;\n" +
                "      line-height: 1.2;\n" +
                "      margin: 0 0 0;\n" +
                "      transform: translateY(0.1em);\n" +
                "      border-bottom: 3px dotted  dodgerblue;\n" +
                "      transition: opacity .45s ease, transform .5s ease;\">Duracion de intervalo: </p>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "\n" +
                "  </table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <td>\n" +
                "      <tr>\n" +
                "\n" +
                "\n" +
                "        <span style=\"\n" +
                "    display: block;\n" +
                "    color: dodgerblue;\n" +
                "    font-size: 1.125em;\n" +
                "    font-weight: 600;\n" +
                "    line-height: 1.2;\n" +
                "    margin: 2em 0 0;\n" +
                "    transition: color .45s ease; box-sizing: border-box;\">\n" +
                "          Visitar sitio web\n" +
                "          <svg width=\"25\" height=\"16\" viewBox=\"0 0 25 16\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\" style=\" margin-left: .5em;\n" +
                "      transition: transform .6s ease;\">\n" +
                "            <path fill-rule=\"evenodd\" clip-rule=\"evenodd\"\n" +
                "              d=\"M17.8631 0.929124L24.2271 7.29308C24.6176 7.68361 24.6176 8.31677 24.2271 8.7073L17.8631 15.0713C17.4726 15.4618 16.8394 15.4618 16.4489 15.0713C16.0584 14.6807 16.0584 14.0476 16.4489 13.657L21.1058 9.00019H0.47998V7.00019H21.1058L16.4489 2.34334C16.0584 1.95281 16.0584 1.31965 16.4489 0.929124C16.8394 0.538599 17.4726 0.538599 17.8631 0.929124Z\"\n" +
                "              fill=\"#753BBD\" style=\" transition: fill .45s ease;\" />\n" +
                "          </svg>\n" +
                "        </span>\n" +
                "        </a>\n" +
                "\n" +
                "      </tr>\n" +
                "    </td>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <td>\n" +
                "      <tr>\n" +
                "\n" +
                "        <footer style=\"align-items: left;\">\n" +
                "          <div style=\"text-align: left\">\n" +
                "\n" +
                "            <a href=\"https://kranon.com/\" target=\"_blank\" style=\"text-decoration: none;  color: dodgerblue;\">\n" +
                "              <Strong>Copyright (c) 2023, Promotora Kranon</Strong> </a>\n" +
                "\n" +
                "          </div>\n" +
                "\n" +
                "        </footer>\n" +
                "\n" +
                "      </tr>\n" +
                "    </td>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  </table>\n" +
                "\n" +
                "\n" +
                "  <table>\n" +
                "\n" +
                "    <td>\n" +
                "\n" +
                "      <tr>\n" +
                "\n" +
                "\n" +
                "        <h3 style=\"color: #2E3C40;\n" +
                "        font-size: 3.5em;\n" +
                "        font-weight: 600;\n" +
                "        line-height: 1;\n" +
                "        padding-bottom: .5em;\n" +
                "        padding-top: .5em;\n" +
                "        margin: 0 0 0.142857143em;\n" +
                "        border-bottom: 5px solid dodgerblue;\n" +
                "        transition: color .45s ease, border .45s ease;\"></h3>\n" +
                "\n" +
                "      </tr>\n" +
                "\n" +
                "    </td>\n" +
                "\n" +
                "  </table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";

    }

}
