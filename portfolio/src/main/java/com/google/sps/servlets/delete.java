
package com.google.sps.servlets;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for deleting tasks. */
@WebServlet("/delete-task")
public class delete extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

       ArrayList<Long> deleteID;
       deleteID =new ArrayList<>();
     Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);

    DatastoreService datatore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datatore.prepare(query);
     
    for (Entity entity : results.asIterable()) {
        
      long id = entity.getKey().getId();
    deleteID.add(id); }
    int noOfDeletion=deleteID.size();

    for(int i=0;i< noOfDeletion;i++){
Key taskEntityKey = KeyFactory.createKey("Task", deleteID.get(i));
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.delete(taskEntityKey);
    }

    response.sendRedirect("/index.html");

  }
}