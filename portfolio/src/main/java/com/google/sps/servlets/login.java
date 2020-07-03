
package com.google.sps.servlets;
import com.google.gson.Gson;
import com.google.sps.data.Task;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    UserService userService = UserServiceFactory.getUserService();

    if (userService.isUserLoggedIn()) {
      String userEmail = userService.getCurrentUser().getEmail();
       String urlToRedirectToAfterUserLogsOut = "/";
      String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);
logstatus loggedin =new logstatus();
      loggedin.log =true;
      loggedin.url=logoutUrl;
      Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(loggedin));
    } else {
        String urlToRedirectToAfterUserLogsIn = "/";
      String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

   
      logstatus loggedin =new logstatus();
      loggedin.log =false;
      loggedin.url=loginUrl;
       Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(loggedin));
  }
}

private class logstatus{
    boolean log;
    String url;
}}
