package main;

//import accounts.AccountService;
//import accounts.UserProfile;
import dao.UsersDAO;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
//import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
//import servlets.UsersServlet;
import util.*;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet()),"/signup");//регистрация
        context.addServlet(new ServletHolder(new SignInServlet()),"/signin");//вход

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
