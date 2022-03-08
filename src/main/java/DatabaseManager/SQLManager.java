/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import com.tutotial.mavenproject9.ServletController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLManager {
    //SQL BASIC SENTENCES
    //USUARIO TABLE
    private static final String 
            INSERT_NEW_USUARIO = "INSERT INTO usuarios(id,name,username,password) VALUES (?,?,?,?)";
    
    private static final String 
            DELETE_USER = "DELETE FROM usuarios WHERE id = ?";
    
    private static final String
            SELECT_GPU = "SELECT * FROM gpus";
    
    private static final String
            SELECT_GPU_BY_ID = "SELECT * FROM gpus WHERE id = ?";
    
    private static final String UPDATE_GPU_BY_ID = "UPDATE gpus SET "
            +"modelname = ?,"
            +"company =?,"
            +"year =?,"
            +"memory =?,"
            +"power =?,"
            +"socket=?,"
            +"price=?,"
            +"type=?,"
            +"imageurl=?"
            +"WHERE id =?";
    
    private static final String SQL_INSERT = 
            "INSERT INTO gpus(modelname,company,year,memory,power,socket,price,type,imageurl) VALUES(?,?,?,?,?,?,?,?,?)";
    
    public static List<Gpu> getGpus(){
        
        String imageBASE_NAME = "RESOURCES/";
        
        System.out.println("Listing gpus");
        List<Gpu> gpu_list = new ArrayList<Gpu>();
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String gpu;
        try {
            //conn = conn.getConnection();
            stmt = conn.prepareStatement(SELECT_GPU);
            rs = stmt.executeQuery();            
        while(rs.next()) {
            //(`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`, `type`, `imageurl`) 
            int id = rs.getInt("id");
            String modelname = rs.getString("modelname");
            String company = rs.getString("company");
            String year = rs.getString("year");
            Double memory = rs.getDouble("memory");
            Double power = rs.getDouble("power");
            String socket = rs.getString("socket");
            Double price = rs.getDouble("price");
            String type = rs.getString("type");
            String imageurl = rs.getString("imageurl");
            
            if(imageurl.startsWith("./")){
               imageurl = imageurl.replace("./", imageBASE_NAME);
            }
            if(imageurl.equals("default") || imageurl=="null"){
                imageurl = "https://cdn-icons-png.flaticon.com/512/103/103085.png";
            }
            Gpu newGpu = new Gpu(id,modelname,company,year,memory,power,socket,price,type,imageurl);
            
            //System.out.println("ImageURL -- " + newGpu.getImageurl());
            
            gpu_list.add(newGpu);

        }
            System.out.println("--> " + gpu_list);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conn.close();
            conn.close();
            conn.close();           
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gpu_list;
    }
    
    public static List<Usuario> getUsers(){
        System.out.println("Listando usuarios");
        List<Usuario> usuarios = new ArrayList<Usuario>();
            try {
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement stmt = null;
                ResultSet rs = null;
                String usuario;
                try {
                    //conn = conn.getConnection();
                    stmt = conn.prepareStatement("SELECT * FROM usuario");
                    rs = stmt.executeQuery();            
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String username = rs.getString("username");
                    String pass = rs.getString("password");
                    usuarios.add(new Usuario(id,name,username,pass));
                    //usuario = new Usuario(idUsuario, nickUsuario, passwordUsuario);
                    //usuarios.add(usuario);
                }
                    System.out.println("--> " + usuarios);

                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                } finally {
                    conn.close();
                    conn.close();
                    conn.close();           
                }
        } catch (SQLException ex) {
            Logger.getLogger(ServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public static Gpu getGpuById(int id) {
        
            
        String imageBASE_NAME = "RESOURCES/";
        
        System.out.println("Finding Gpu with id " + id);
        Gpu gpu = null;
        Connection conn = null;
        
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
        try {
            
            stmt = conn.prepareStatement(SELECT_GPU_BY_ID,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();            
        while(rs.next()) {
            //(`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`, `type`, `imageurl`) 
            //int id = rs.getInt("id");
            String modelname = rs.getString("modelname");
            String company = rs.getString("company");
            String year = rs.getString("year");
            Double memory = rs.getDouble("memory");
            Double power = rs.getDouble("power");
            String socket = rs.getString("socket");
            Double price = rs.getDouble("price");
            String type = rs.getString("type");
            String imageurl = rs.getString("imageurl");
            
            imageurl = imageurl.replace("./", imageBASE_NAME);
            
            gpu = new Gpu(id,modelname,company,year,memory,power,socket,price,type,imageurl);
            
            System.out.println("Graphic Card Found");

        }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conn.close();
            conn.close();
            conn.close();           
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gpu;
    }
    
    public static int updateGpuById(Gpu gpu) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            /*
            +"modelname = ?,"
            +"company =?,"
            +"year =?,"
            +"memory =?,"
            +"power =?,"
            +"socket=?,"
            +"price=?,"
            +"type=?,"
            +"imageurl=?,"
            +"WHERE id_cliente =?";
            */
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(UPDATE_GPU_BY_ID);
            stmt.setString(1, gpu.getModelname());
            stmt.setString(2, gpu.getCompany());
            stmt.setInt(3, Integer.parseInt(gpu.getYear()));
            stmt.setDouble(4, gpu.getMemory());
            stmt.setDouble(5, gpu.getPower());
            stmt.setString(6, gpu.getSocket());
            stmt.setDouble(7, gpu.getPrice());
            stmt.setString(8, gpu.getType());
            stmt.setString(9, gpu.getImageurl());
            stmt.setInt(10, gpu.getId());
            
            System.out.println("Update using \n" + stmt);
            
            rows = stmt.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{ 
            conn.close();
        }
        return rows;
    }
    
    public static int insertGpuByObject(Gpu gpu) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, gpu.getModelname());
            stmt.setString(2, gpu.getCompany());
            stmt.setInt(3, Integer.parseInt(gpu.getYear()));
            stmt.setDouble(4, gpu.getMemory());
            stmt.setDouble(5, gpu.getPower());
            stmt.setString(6, gpu.getSocket());
            stmt.setDouble(7, gpu.getPrice());
            stmt.setString(8, gpu.getType());
            stmt.setString(9, gpu.getImageurl());
            
            System.out.println("Insert using \n" + stmt);
            rows = stmt.executeUpdate();          
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            
            conn.close();
            conn.close();
        }
        return rows;
    }
    
}