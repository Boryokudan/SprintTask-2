package servlets;

import main.DBManager;
import main.Language;
import main.Source;
import main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet (name = "AuthenticationServlet", value = "/authentication")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("currentPage", "/authentication");

        ArrayList<Language> languages = DBManager.getLanguages();
        HashMap<String, HashMap<String, String>> locales = Language.getLocales();

        request.setAttribute("languages", languages);
        request.setAttribute("locales", locales);

        request.getRequestDispatcher("JSPs/authentication.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Language> languages = DBManager.getLanguages();
        HashMap<String, HashMap<String, String>> locales = Language.getLocales();

        request.setAttribute("languages", languages);
        request.setAttribute("locales", locales);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = DBManager.getUser(email);

        if (user != null && password.equals(user.getPassword())) {
            request.getSession().setAttribute("activeUser", user);
            response.sendRedirect("/");
        }
        else {
            request.getRequestDispatcher("JSPs/authentication.jsp?invalid").forward(request, response);
        }
    }
}
