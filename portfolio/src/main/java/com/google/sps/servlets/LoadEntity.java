package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing tasks. */
@WebServlet("/list-tasks")
public class LoadEntity extends HttpServlet {
    int le=4;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String s=request.getParameter("no");
    try {
      le = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.err.println("Could not convert to int: " );
     // return -1;

    }
        // Redirect back to the HTML page.
    response.sendRedirect("/index.html");

  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
int count =1;
    List<Task> tasks = new ArrayList<>();
    
   // le =Integer.parseInt(s);
    for (Entity entity : results.asIterable()) {
        if(count >le)break;
      long id = entity.getKey().getId();
      String title = (String) entity.getProperty("title");
      long timestamp = (long) entity.getProperty("timestamp");

      Task task = new Task(id, title, timestamp);
      tasks.add(task);
      count++;
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(tasks));
  }
}
