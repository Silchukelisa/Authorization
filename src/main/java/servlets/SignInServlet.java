package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import executor.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //вход
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
        if (userProfile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Профиль не зарегистрирован");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        if (userProfile.getLogin().equals(login) && (!userProfile.getPassword().equals(password))) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неверно введен password");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("In: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
