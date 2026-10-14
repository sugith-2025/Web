import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class HelloServlet extends HttpServlet{


    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

        String name=request.getParameter("name");

        response.setContentType("text/html");

        PrintWriter out=response.getWriter();

        out.println("<h3>Hello "+name+"</h3>");
        out.close();


    }
    
}
