package servlets;

import main.DBManager;
import main.Language;
import main.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet (name = "AdminServlet", value = "/admin" )
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Language> languages = DBManager.getLanguages();
        HashMap<String, HashMap<String, String>> locales = Language.getLocales();

        request.setAttribute("languages", languages);
        request.setAttribute("locales", locales);

        User user = (User) request.getSession().getAttribute("activeUser");

        if (user != null && user.getRole() == 1) {
            response.sendRedirect("admin_publications");
        }
        else {
            request.getSession().setAttribute("currentPage", "/admin");
            response.sendRedirect("/access-error?error=auth");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
