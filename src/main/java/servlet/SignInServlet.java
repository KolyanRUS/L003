package servlet;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UsersDAO;

public class SignInServlet extends HttpServlet {
    private UsersDAO dao;
    public SignInServlet() {
        this.dao = new UsersDAO();
    }
//[sign up = регистрация]
    //sign in//вход
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        User usprofile = dao.getUserId_hql(login);//dbbService.getUser(login);
        if(usprofile == null || !usprofile.getPassword().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Authorized: "+login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}