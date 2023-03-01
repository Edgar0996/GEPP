package Kranon.GEPP.Local;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBConnect {

    //Variables para la conexion a la base de datos
    private String url = "jdbc:mysql://localhost:3306/emailinfo";
    private String username = "root";
    private String password = "root";


    public DBConnect() {

    }

    public String obtenerConexion() throws SQLException{

        String status = "";

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new Driver());


            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Abrindo la conexion a la base de datos --->");


            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mailcontent");
            System.out.println("Enviando Consulta  SELECT * FROM mailcontent --->");



            //System.out.println("Conectado Exitosamente!");
            status = "Succesfull Conection to database";
            printStatus(status);

            List<String> lista = new ArrayList<String>();

            List<List<Integer>> dsa = new ArrayList<List<Integer>>();


            int id = 0;
            String columnIndex1 = "";
            String columnIndex2 = "";
            String columnIndex3 = "";
            String columnIndex4 = "";


            while (rs.next()){

               // System.out.println(rs.getInt(1) +" " +  rs.getString(2)+" " + rs.getString(3)+" " +rs.getString(4)+" " +rs.getString(5));

                id = rs.getInt(1);
                columnIndex1 = rs.getString(2);
                columnIndex2 = rs.getString(3);
                columnIndex3 = rs.getString(4);
                columnIndex4 = rs.getString(5);

                System.err.println( "id:" + id +" "+ columnIndex1 +" "+ columnIndex2+" "+ columnIndex3+" "+ columnIndex4);
            }


            System.out.println("Cerrando la conexion a la base de datos --->");
            connection.close();


        } catch (Exception e){
            e.printStackTrace();
            status = "No conectado";
            printStatus(status);
            //System.out.println("No Conectado Exitosamente!");

        }

        return status;
    }

    public void printStatus(String status) throws SQLException {
        String stado = status;
        System.out.println(stado);
    }

    public static void main(String[] args) throws SQLException {

        DBConnect nea = new DBConnect();
        nea.obtenerConexion();

    }






}
