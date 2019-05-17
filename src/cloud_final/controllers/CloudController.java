package cloud_final.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cloud_final.models.User;

@WebServlet("/CloudController")
public class CloudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CloudController() {
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute("user");
    	if(user == null) {
    		request.getRequestDispatcher("LoginController").forward(request, response);
    	}
    	else
    		request.getRequestDispatcher("/WEB-INF/cloud_final/views/CloudView.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
