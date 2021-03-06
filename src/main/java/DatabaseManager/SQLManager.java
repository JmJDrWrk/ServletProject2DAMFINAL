/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import com.tutotial.mavenproject9.ServletController;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLManager {
    // SQL BASIC SENTENCES
    // USUARIO TABLE
    private static final String INSERT_NEW_USUARIO = "INSERT INTO usuarios(id,name,username,password) VALUES (?,?,?,?)";

    private static final String DELETE_USER = "DELETE FROM usuarios WHERE id = ?";

    private static final String SELECT_GPU = "SELECT * FROM gpus";
    
    private static final String SELECT_COMPANY = "SELECT * FROM company";

    private static final String SELECT_GPU_BY_ID = "SELECT * FROM gpus WHERE id = ?";

    private static final String UPDATE_GPU_BY_ID = "UPDATE gpus SET "
            + "modelname = ?,"
            + "company =?,"
            + "year =?,"
            + "memory =?,"
            + "power =?,"
            + "socket=?,"
            + "price=?,"
            + "type=?,"
            + "imageurl=?"
            + " WHERE id =?";

    private static final String SQL_INSERT = "INSERT INTO gpus(modelname,company,year,memory,power,socket,price,type,imageurl,idcomp) VALUES(?,?,?,?,?,?,?,?,?,?)";

    private static final String DELETE_GPU_BY_ID = "DELETE FROM gpus WHERE id =?";

    public static List<Gpu> getGpus() {

        String imageBASE_NAME = "RESOURCES/";

        System.out.println("Listing gpus");
        List<Gpu> gpu_list = new ArrayList<Gpu>();
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String gpu;
            try {
                // conn = conn.getConnection();
                stmt = conn.prepareStatement(SELECT_GPU);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    // (`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`,
                    // `type`, `imageurl`)
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
                    int idcomp = rs.getInt("idcomp");

                    if (imageurl.startsWith("./")) {
                        imageurl = imageurl.replace("./", imageBASE_NAME);
                    }
                    if (imageurl.equals("default") || imageurl == "null") {
                        imageurl = "https://cdn-icons-png.flaticon.com/512/103/103085.png";
                    }
                    Gpu newGpu = new Gpu(id, modelname, company, year, memory, power, socket, price, type, imageurl, idcomp);

                    // System.out.println("ImageURL -- " + newGpu.getImageurl());

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

    public static List<Usuario> getUsers() {
        System.out.println("Listando usuarios");
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String usuario;
            try {
                // conn = conn.getConnection();
                stmt = conn.prepareStatement("SELECT * FROM usuario");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String username = rs.getString("username");
                    String pass = rs.getString("password");
                    usuarios.add(new Usuario(id, name, username, pass));
                    // usuario = new Usuario(idUsuario, nickUsuario, passwordUsuario);
                    // usuarios.add(usuario);
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

                stmt = conn.prepareStatement(SELECT_GPU_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    // (`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`,
                    // `type`, `imageurl`)
                    // int id = rs.getInt("id");
                    String modelname = rs.getString("modelname");
                    String company = rs.getString("company");
                    String year = rs.getString("year");
                    Double memory = rs.getDouble("memory");
                    Double power = rs.getDouble("power");
                    String socket = rs.getString("socket");
                    Double price = rs.getDouble("price");
                    String type = rs.getString("type");
                    String imageurl = rs.getString("imageurl");
                    int idcomp = rs.getInt("idcomp");

                    imageurl = imageurl.replace("./", imageBASE_NAME);

                    gpu = new Gpu(id, modelname, company, year, memory, power, socket, price, type, imageurl, idcomp);

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

    public static int updateGpuById(Gpu gpu) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            /*
             * +"modelname = ?,"
             * +"company =?,"
             * +"year =?,"
             * +"memory =?,"
             * +"power =?,"
             * +"socket=?,"
             * +"price=?,"
             * +"type=?,"
             * +"imageurl=?,"
             * +"WHERE id_cliente =?";
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
            //stmt.setInt(10, gpu.getIdcomp());
            stmt.setInt(10, gpu.getId());

            System.out.println("Update using \n" + stmt);

            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
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
            
            //[ERROR] Related with foreign keys     
            int IDCOMP = 7;
            stmt.setInt(10, IDCOMP);

            System.out.println("Insert using \n" + stmt);
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            conn.close();
            conn.close();
        }
        return rows;
    }

    public static int deleteGpuById(int id) {
        System.out.println("Deleting GPU with id " + id);
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(DELETE_GPU_BY_ID);
            stmt.setInt(1, id);
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows;
    }

    public static String exportGpu() {

        String imageBASE_NAME = "RESOURCES/";

        System.out.println("Listing gpus");
        List<Gpu> gpu_list = new ArrayList<Gpu>();

        String gpu_inserts = "INSERT INTO `gpus` (`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`, `type`, `imageurl`) VALUES \n";

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String gpu;
            try {
                // conn = conn.getConnection();
                stmt = conn.prepareStatement(SELECT_GPU);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    // (`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`,
                    // `type`, `imageurl`)
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
                    int idcomp = rs.getInt("idcomp");

                    if (imageurl.startsWith("./")) {
                        imageurl = imageurl.replace("./", imageBASE_NAME);
                    }
                    if (imageurl.equals("default") || imageurl == "null") {
                        imageurl = "https://cdn-icons-png.flaticon.com/512/103/103085.png";
                    }
                    Gpu newGpu = new Gpu(id, modelname, company, year, memory, power, socket, price, type, imageurl, idcomp);

                    // System.out.println("ImageURL -- " + newGpu.getImageurl());

                    gpu_list.add(newGpu);
                    gpu_inserts += newGpu.toString() + ",\n";

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
        // FINALLY WRITE
        try {
            FileWriter myWriter = new FileWriter(
                    "C:/Users/Jaime/Documents/NetBeansProjects/mavenproject9/DATABASE_EXPORT.txt");
            myWriter.write(gpu_inserts);
            myWriter.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("[ERROR] An error occurred.");
            e.printStackTrace();
        }

        return gpu_inserts;
    }
    
    public static List<Company> getCompanys() {

        String imageBASE_NAME = "RESOURCES/";

        System.out.println("Listing gpus");
        List<Company> comp_list = new ArrayList<Company>();
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String gpu;
            try {
                // conn = conn.getConnection();
                stmt = conn.prepareStatement(SELECT_COMPANY);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    // (`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`,
                    // `type`, `imageurl`)
                    int id = rs.getInt("id");   
                    String compname = rs.getString("compname");
                    String imageurl = rs.getString("imageurl");

                    if (imageurl.startsWith("./")) {
                        imageurl = imageurl.replace("./", imageBASE_NAME);
                    }
                    if (imageurl.equals("default") || imageurl == "null") {
                        imageurl = "https://cdn-icons-png.flaticon.com/512/103/103085.png";
                    }
                    //Gpu newGpu = new Gpu(id, modelname, company, year, memory, power, socket, price, type, imageurl);

                    // System.out.println("ImageURL -- " + newGpu.getImageurl());

                    comp_list.add(new Company(id,compname,imageurl));

                }
                System.out.println("--> " + comp_list);

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
        return comp_list;
    }
    
    
    //USERS DATABASES
    private static final String SELECT_PASS_BY_NAME = "SELECT * FROM users WHERE username=?";
    
    private static final String INSERT_USER_BY_NMPS = "INSERT INTO users(name,username,pass) VALUES (?,?,?)";
    
    public static String getPassby(String username) throws SQLException{
        System.out.println("Getting password from user " + username);
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String pass = null;
        
        
        stmt = conn.prepareStatement(SELECT_PASS_BY_NAME);
        stmt.setString(1, username);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            pass = rs.getString("pass");
        }
        
        return pass;
        
    }
    
    public static void regUser(String username, String pass){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(INSERT_USER_BY_NMPS);
            String name = username + new Timestamp(new Date().getTime());
            
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, pass);

            
 
            System.out.println("Insert using \n" + stmt);
            rows = stmt.executeUpdate();
            System.out.println("[SUCCESS] new user inserted");
        } catch (SQLException ex) {
            System.out.println("[ERROR] new user insertion");
            ex.printStackTrace(System.out);
        } finally {

            try {
                conn.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("[ERROR] new user insertion conn exception");
                Logger.getLogger(SQLManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
    
    
    //MAGIG QUERYS
    public static String queryfy(String mainarg) throws SQLException{
        
        String final_output = "not loaded";
        
        Connection conn = ConnectionManager.getConnection();
            
        if(mainarg.contains("INSERT INTO")){
            System.out.println("{querify} insert into mode");
            PreparedStatement stmt = conn.prepareStatement(mainarg);
            
            try{
                stmt.executeUpdate();final_output="INSERT SUCCESS\n\t"+mainarg;
            }catch(Exception e){
                System.out.println("[ERROR] querify insert into ");final_output="INSERT ERROR\n\t"+mainarg;
            }
            
        }else if(mainarg.contains("SELECT")){
            System.out.println("{querify} SELECT FROM mode");
            PreparedStatement stmt = conn.prepareStatement(mainarg.split("#")[0]);
            String[] props = mainarg.split("#")[1].split(",");
            try{
                String reg = null;
                ResultSet rs = stmt.executeQuery();
                int i =0;
                int y = 0;
                
                //For each reg
                while(rs.next()){
                    System.out.println("i--> " + i);
                    String columns = "(";
                    
                    //For each propertie of each reg
                    for(String propertie: props){
                        columns += rs.getObject(propertie) +",";
                    }columns+=")";
                    
                    columns = columns.replace(",)",")");
                    
                    reg += columns + "\n";
                    /*
                    if(i==props.length-1){
                        i=0;
                        cout+="\n\nNEW OBJECT\n";
                        System.out.println("props reset");
                    }*/
                    i++;
                    y++;
                }
                final_output="[SELECT SUCCESS]\n"+mainarg+"\n\n"+reg+"\n\tnumber of elements returned: "+y
                        +"rsvalues: " + i;
            }catch(Exception e){
                System.out.println("[ERROR] querify SELECT FROM ");final_output="SELECT ERROR\n\t"+mainarg;
                e.printStackTrace();
            }
        }else{
            System.out.println("mainarg="+mainarg);
            System.out.println("{querify} main arg not valid");final_output="{querify} mainarg not valid";
        }
        
        
        return final_output;
    }
}