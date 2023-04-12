package servlets;

import acountService.AccountService;
import acountService.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("") || password.equals("")) {
            response.getWriter().println("incorrect input");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        User user = new User(login, password);
        if (accountService.checkLoginFree(login)) {
            response.getWriter().println(login + " this login already exists");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        accountService.addUser(user);
        response.getWriter().println("Welcome! " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
