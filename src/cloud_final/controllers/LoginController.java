package cloud_final.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cloud_final/views/LoginView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("username").equals("Albert")
			&& request.getParameter("password").equals("abcd"))
		{
			request.getSession().setAttribute("user", "Albert");
			request.getRequestDispatcher("/WEB-INF/cloud_final/views/CloudView.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/WEB-INF/cloud_final/views/LoginView.jsp").forward(request, response);
		}
	}

}
