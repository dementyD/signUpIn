package servlets;

import acountService.AccountService;
import acountService.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User(login, password);
        if (accountService.getUserByLogin(login) == null || !accountService.getUserByLogin(login).equals(user)) {
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        response.getWriter().println("Authorized: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
