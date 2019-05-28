package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UsersDAO;

public class SignUpServlet extends HttpServlet {
    private UsersDAO dao;
    public SignUpServlet() {
        this.dao = new UsersDAO();
    }
    //sign up//регистрация
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        dao.insertUser(login,pass);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}