package servlets;

import main.DBManager;
import main.Language;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet (name = "AccessErrorServlet", value = "/access-error" )
public class AccessErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Language> languages = DBManager.getLanguages();
        HashMap<String, HashMap<String, String>> locales = Language.getLocales();

        request.setAttribute("languages", languages);
        request.setAttribute("locales", locales);

        String error = request.getParameter("error");

        if (error != null) {
            if (error.equals("auth")) {
                request.getRequestDispatcher("JSPs/access-denied.jsp").forward(request, response);
            }
            else if (error.equals("404")) {
                request.getRequestDispatcher("JSPs/404.jsp").forward(request, response);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
