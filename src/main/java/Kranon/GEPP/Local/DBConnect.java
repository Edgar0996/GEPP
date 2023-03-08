package Kranon.GEPP.Local;

import Kranon.GEPP.Utileria.ModelEmail;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class DBConnect {

    //Instacia de la clase ModelEmail --> para el uso de los metodos get, set y construcotres

    ModelEmail voMoEmail = new ModelEmail();

    //Variables para la conexion a la base de datos
    private String url = "jdbc:mysql://localhost:3306/emailinfo";
    private String username = "root";
    private String password = "root";

    public DBConnect() {
        //Constructor vacio
    }

    public String obtenerConexion() throws SQLException{

        String status = "";

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new Driver());


            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("ABRIENDO LA CONEXION A LA BASE DE DATOS --->");

            String query = "select desclog from process_log where id='12345' and  categoryLog = 'DownloadSFTP';";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("ENVIANDO CONSULTA ---> " + query);

            //System.out.println("Conectado Exitosamente!");
            status = "CONEXION ENLAZADA EXITOSAMENTE [OK]";
            printStatus(status);

            List<String> lista = new ArrayList<String>();
            List<List<Integer>> dsa = new ArrayList<List<Integer>>();

            String vsInicioProcesoRD = voMoEmail.getVsInicioProcesoRD();
            String vsConexionSFTP = voMoEmail.getVsConexionSFTPRD();
            String vsArchDescargados = voMoEmail.getVsArchDescargadosRD();

            while (rs.next()){

               // System.out.println(rs.getInt(1) +" " +  rs.getString(2)+" " + rs.getString(3)+" " +rs.getString(4)+" " +rs.getString(5));
               // id = rs.getInt(1);
                vsInicioProcesoRD = rs.getString(2);
                vsConexionSFTP = rs.getString(3);
                vsArchDescargados = rs.getString(4);

                voMoEmail.setVsFechaReporte(rs.getString(5));

                System.err.print("[RESULTADO DE LA BASE DE DATOS]");
                System.err.println("[id:id "+ vsInicioProcesoRD +" "+ vsConexionSFTP+" "+ vsArchDescargados+" "+ voMoEmail.getVsFechaReporte() + "]");
                System.err.println();

            }

            System.out.println("CERRANDO LA CONEXION DE LA BASE DE DATOS --->");
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
            status = "NO CONECTADO [ERROR CONECTION]";
            printStatus(status);
            //System.out.println("No Conectado Exitosamente!");
        }


        return status;
    }
    public void printStatus(String status) {
        String stado = status;
        System.out.println(stado);
    }

    public static void main(String[] args)  {

        try{

            System.out.println("EXTRAYENDO INFORMACION DE LA BASE DE DATOS");

            DBConnect nea = new DBConnect();
            nea.obtenerConexion();

            System.out.println("FIN DEL PROCESO HACIA LA BASE DE DATOS");

        }catch (SQLException e){
            System.out.println("[Error en la base de datos], Error Message[" + e.getMessage() + " ]");
            e.printStackTrace();
        }


    }






}
