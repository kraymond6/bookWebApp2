package edu.wctc.krr.bookwebapp2.controller;

import edu.wctc.krr.bookwebapp2.model.Author;
import edu.wctc.krr.bookwebapp2.model.AuthorDao;
import edu.wctc.krr.bookwebapp2.model.AuthorDaoStrategy;
import edu.wctc.krr.bookwebapp2.model.AuthorService;
//import edu.wctc.web.demo.bookwebapp.model.ConnPoolAuthorDao;
import edu.wctc.krr.bookwebapp2.model.DBStrategy;
import edu.wctc.krr.bookwebapp2.model.MySqlDb;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * The main controller for author-related activities
 *
 * @author jlombardo
 */
//@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    // NO MAGIC NUMBERS!
    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/listAuthors.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String UPDATE_PAGE = "/update.jsp";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
    private static final String SAVE_ACTION = "save";

    private String driver;
    private String url;
    private String userName;
    private String password;
    private String dbClassName;
    private String daoClassName;
    private DBStrategy db;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private AuthorService injectDependenciesGetAuthorService() throws Exception {
        AuthorDaoStrategy authorDao = null;


            Class dbClass = Class.forName(dbClassName);
            DBStrategy db = (DBStrategy) dbClass.newInstance();

            Class daoClass = Class.forName(daoClassName);
            Constructor constructor = null;
            constructor = daoClass.getConstructor(new Class[]{DBStrategy.class, String.class, String.class, String.class, String.class});
            Object[] constructorArgs = new Object[]{db, driver, url, userName, password};
            authorDao = (AuthorDaoStrategy) constructor.newInstance(constructorArgs);
 
        return new AuthorService(authorDao);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            String destination = LIST_PAGE;
            String action = request.getParameter(ACTION_PARAM);
        //sample code for getting info from web xml file
        try {
            AuthorService authService = injectDependenciesGetAuthorService();

            //In index.html action refers to the querystring parameter
            

            //This was my instantiation code, now to change to web xml injection
//        DBStrategy db = new MySqlDb();
//        AuthorDaoStrategy authDao
//                = new AuthorDao(db, "com.mysql.jdbc.Driver",
//                        "jdbc:mysql://localhost:3306/book", "root", "admin");
//        AuthorService authService = new AuthorService(authDao);
            if (action.equals(LIST_ACTION)) {
                //empty list to store results
                List<Author> authors = null;
                //service talks to dao talks to service ect gets all authors
                authors = authService.getAllAuthors();
                request.setAttribute("authors", authors);
                destination = LIST_PAGE;

            } else if (action.equals(ADD_ACTION)) {
                destination = UPDATE_PAGE;
            } else if (action.equals(UPDATE_ACTION)) {

                String authorId = request.getParameter("authorId");
                Author author = authService.getAuthorById(authorId);
                request.setAttribute("author", author);
                destination = UPDATE_PAGE;

            } else if (action.equals(DELETE_ACTION)) {
                String authorId = request.getParameter("authorId");
                authService.deleteAuthorById(authorId);
                this.refresh(request, authService);
                destination = LIST_PAGE;
            } else if (action.equals(SAVE_ACTION)){
               String authorName = request.getParameter("authorName");
               String authorId = request.getParameter("authorId");
               authService.saveAuthor(authorId, authorName);
               this.refresh(request, authService);
               destination = LIST_PAGE;
            } else {
                // no param identified in request, must be an error
                request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                destination = LIST_PAGE;
            }

        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }
        
        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);

    }

    private void refresh(HttpServletRequest request, AuthorService authService) throws Exception {
        List<Author> authors = authService.getAllAuthors();
        request.setAttribute("authors", authors);
    }
    public void init() throws ServletException {
        driver = getServletConfig().getInitParameter("driver");
        url = getServletConfig().getInitParameter("url");
        userName = getServletConfig().getInitParameter("userName");
        password = getServletConfig().getInitParameter("password");
        dbClassName = getServletConfig().getInitParameter("dbStrategy");
        daoClassName = getServletConfig().getInitParameter("authorDao");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
