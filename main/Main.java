package main;

import acountService.AccountService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        SignUpServlet signUpServlet = new SignUpServlet(accountService);
        SignInServlet signInServlet = new SignInServlet(accountService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(signInServlet), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
