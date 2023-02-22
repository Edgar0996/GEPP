package Kranon.GEPP.Utileria;


import Kranon.GEPP.Local.SendEmail;
import jdk.jfr.internal.tool.Main;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Utilerias {





    static {
        System.setProperty("dateLog", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }



    private static final Logger voLogger = LogManager.getLogManager().getLogger("Reporte");
    private  String vsPathConfiguration = ModelEmail.pathConfigurations + File.separator +"conf.properties";


    @SuppressWarnings("rawtypes")
    public boolean getProperties(Map<String, String> voMapConfiguration, String vsUUI){
        //Atrapamos las excepciones
        try {

            System.out.println(vsPathConfiguration);

            System.out.println("pasa por aqui hermano");
            Properties props = new Properties();
            System.out.println("pasa por aqui hermano");

            InputStream in = new FileInputStream(vsPathConfiguration);
            props.load(in);

            System.out.println("pasa por aqui hermano");
            System.out.println("Pasa por InputStream" + vsPathConfiguration);
            System.out.println("pasa por aqui hermano");
            for (Enumeration<Object> voEnum = props.keys(); voEnum.hasMoreElements();) {
                System.out.println("pasa por aqui hermano");
                System.out.println("Entra al for");
                String vsProperty = String.valueOf(voEnum.nextElement());
                if (!vsProperty.contains("##")) {
                    voMapConfiguration.put(vsProperty, props.getProperty(vsProperty));
                }
            }System.out.println("pasa por aqui hermano");
            return true;

        }catch (Exception e){

            voLogger.severe("[" +  vsUUI +  " Error Exception]");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public static Map<String, String> RecuperaArhivoConf(String vsUUI) {

        Map<String, String> voMapConfiguration = new HashMap<String, String>();
        Utilerias voUtil = null;
        voUtil = new Utilerias();
        voUtil.getProperties(voMapConfiguration, vsUUI);
        return voMapConfiguration;

    }

}
