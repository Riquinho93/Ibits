package br.com.ibits.dal;

import java.sql.*;
/**
 *
 * @author Ibits
 */
public class ModuloConexao {
    
    public static Connection conector(){
    
        java.sql.Connection conexao = null;
        //chamar o drive
        String driver = "com.mysql.jdbc.Driver";
        //informações referente ao banco
        String url="jdbc:mysql://localhost:3306/bdibits";
        String user = "root";
        String password = "";
        //Estabelecendo conexão com o banco
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        }catch(Exception e){
        //Esclarecer erro   
         System.out.println(e);
            return null;
        }
    }
    
}
