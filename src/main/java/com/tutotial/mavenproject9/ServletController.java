
package com.tutotial.mavenproject9;

import DatabaseManager.SQLManager;
import DatabaseManager.Usuario;
import DatabaseManager.Gpu;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletController", urlPatterns = { "/ServletController" })
public class ServletController extends HttpServlet {
    private Gpu gpu;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ProcessRequest() joined");
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet() joined");


        // DO GET PREPARED FOR GPU LIST
        List<Gpu> gpu_list = SQLManager.getGpus();
        request.setAttribute("gpus", gpu_list);
        request.setAttribute("totalgpus", gpu_list.size());
        
        //request.getRequestDispatcher("clientes.jsp").forward(request, response); Con esto recargaría otra vez clientes.jsp
        String request_value = request.getParameter("accion");
        processRequest(request, response);
        if(request_value==null){
            String jspEditar = "/clientes.jsp";
            request.getRequestDispatcher(jspEditar).forward(request, response);    
        }
        else if (request_value.equals("editGpu")) {
            System.out.println("Selected GPU --> " + request.getParameter("id"));
            
            //Make a query to obtain rest of data
            //Create a gpu to set as a request attribute
            gpu = SQLManager.getGpuById(Integer.parseInt(request.getParameter("id")));
            
            //Set the attribute to edit.jsp
            request.setAttribute("gpu", gpu);
           
            //Redirect to edit.jsp
            String jspEditar = "/edit.jsp";
            request.getRequestDispatcher(jspEditar).forward(request, response);
        
        }else if(request_value.equals("AddGpu")){
            //Redirect to edit.jsp
            gpu = new Gpu(0);
            String jspEditar = "/edit.jsp";
            request.getRequestDispatcher(jspEditar).forward(request, response);
            
        }else if(request_value.equals("deleteGpu")){
            String jspEditar = "/index.jsp";
            request.getRequestDispatcher(jspEditar).forward(request, response);
            
        }else{
            String jspEditar = "/clientes.jsp";
            request.getRequestDispatcher(jspEditar).forward(request, response);    
        }

        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost() joined");
     

        
        try{
            String request_value = request.getParameter("power");
            System.out.println("[doPost()] accion -- " + request_value);
            
            String spc = request.getParameter("btt");
            System.out.println("spc -- " + spc);

        
        if(spc.equals("confirm") || spc.equals("cancel")){
            System.out.println("OneditGpu mode");
            
            
            //If confirm
            if(spc.equals("confirm")){
            
                //Modify the client with new attributes
                String modelname = request.getParameter("modelname");
                String company = request.getParameter("company");
                int year = Integer.parseInt(request.getParameter("year"));
                Double memory = Double.parseDouble(request.getParameter("memory"));
                Double power = Double.parseDouble(request.getParameter("power"));
                String socket = request.getParameter("socket");
                Double price = Double.parseDouble(request.getParameter("price"));
                String type = request.getParameter("type");
                String imageurl = request.getParameter("imageurl");
                
                //Testing for encoding problems
                String strabc1 = URLDecoder.decode ( imageurl, "UTF-8" );
                String strabc2 = java.net.URLDecoder.decode(imageurl, "UTF-8");
                String strabc3 = new String(imageurl.getBytes("iso-8859-1"),"utf-8");
                String utf8EncodedString = new String(imageurl.getBytes(), StandardCharsets.ISO_8859_1);
                //String strabc4 = new String(imageurl.getBytes("iso-8859-1"),"utf-8mb4");

                System.out.println("imageurl = " + imageurl);
                System.out.println("imageurl = " + strabc1);
                System.out.println("imageurl = " + strabc2);
                System.out.println("imageurl = " + strabc3);
                System.out.println("imageurl = " + utf8EncodedString);
                
                //Temporal Arrangement -- Error image if null field
                if(imageurl==null || imageurl=="default"){gpu.setImageurl("https://wpdirecto.com/wp-content/uploads/2017/08/solucionar-error-wordpress.jpg");}
                
                                                                                                            //utf error solve it
                Gpu newGpu = new Gpu(gpu.getId(),modelname,company,""+year,memory,power,socket,price,type,imageurl);
                System.out.println("New gpu updated as \n" + newGpu.toString());
                
                
                //Detect if we are adding info or inserting new based on id = 0
                if(gpu.getId()==0){//Note that we are 
                    SQLManager.insertGpuByObject(newGpu);
                    
                }else{//Now we are updating
                    //Call update query
                    System.out.println("Edit confirmed");
                    SQLManager.updateGpuById(newGpu);
                }


                //Redirect to gpu list
                //String jspEditar = "clientes.jsp";
                //request.getRequestDispatcher(jspEditar).forward(request, response);
                
                response.sendRedirect("http://localhost:8080/mavenproject9/ServletController?");
                    
      
                
                
            }/*else if(spc.equals("cancel")){
                //Return to list_attempt1.jsp
                System.out.println("Edit canceled");
                
                
            }else{System.out.println("[WARNING] Untracked parameters at OneditGpu mode");
                //Redirect to error .jsp
            }  */
            
        }else{System.out.println("[ERROR/WARNING] Untracked parameters at doPost() method");}
        }catch(Exception e){System.out.println("[Error] getParameter() failed");}

        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo() joined");
        return "Short description";
    }// </editor-fold>

    
    public void doFilter(HttpServletRequest request,
                     HttpServletResponse response,
                     FilterChain chain) throws ServletException, UnsupportedEncodingException, IOException {
    request.setCharacterEncoding("UTF-8");
    chain.doFilter(request, response);
}
}