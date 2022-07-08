package servlets;

import accounts.AccountService;
import accounts.UserDAO;
import accounts.UserProfile;
import executor.DBException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }



    //регистрация
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Необходимо ввести логин");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Необходимо ввести пароль");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = null;
        try {
            userProfile = accountService.getUserByLogin(login);

        } catch (DBException e) {
            e.printStackTrace();
        }
        if (userProfile != null)
        {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Логин уже используется");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        try {
            accountService.addNewUser(new UserProfile(login, password));
        } catch (DBException e) {
            e.printStackTrace();
        }
        response.getWriter().println("Authorized: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
