
package ca.sait.lab4login.servlets;

import ca.sait.lab4login.models.User;
import ca.sait.lab4login.services.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chinedu Alake
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // get username from the session object
        String username = (String) session.getAttribute("username");
        
        if(username != null){
          
        String query = request.getQueryString();
        
        if(query != null && query.contains("logout")){
                
             session.invalidate();
             request.setAttribute("message", "You have been logged out");
          
        } else {
                
                response.sendRedirect("home");
                return;
            }
        }
          
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
        
        
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            
            request.setAttribute("message", "Invalid Username/Password");
            
        } else{
            
            AccountService service = new AccountService();
            
            User user = service.login(username,password);
            
            if(user == null){
                
                request.setAttribute("message", "Invalid Username/Password");
                request.setAttribute("username", username);
                
            } else {
                
                request.getSession().setAttribute("username", username);
                response.sendRedirect("home");
                return;
                
        }
     }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
   }
}
