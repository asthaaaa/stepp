// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
import com.google.gson.Gson;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private List<String> quotes =new ArrayList<>();;
private int  i=0;
//   @Override
//   public void init() {
    
//     quotes.add(
//         "Test various scheduling policy in xv6 \n"
//         +"An Application which tests the impact of different scheduling policies in xv6 using c language.\n");
//     quotes.add("8-BIT PROCESSOR DESIGN IN VERILOG \n"
//     +"design and implementation of a simple microprocessor with a custom instruction set using Verilog.\n");
//     quotes.add("Shortest Path using Java Swing\n"
//     +"developed a Graphical user interface that finds shortest path between different points.\n");
//     quotes.add("Minesweeper in Java\n"
//     +"A simple Minesweeper game developed in Java.\n");
//     quotes.add("Bouncing Ball\n"
//     +"Java Application that enables user to visualise how various coefficient of restitution affect the motion of a ball after impact using JavaFX. The application simulates motion of the ball as it bounces\n");

//     quotes.add(
//         "Terms and Conditions Reader\n"
//         +"Java Application that read aloud the terms and conditions that the user has to accept"
//         +" before proceeding further in various websites as many times the user do not read the"
//         +" terms and condition before accepting . This application ensures that the user is aware of the terms and condition before selecting the I agree checkbox.\n");
    
//   }
//   @Override
//   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//     response.setContentType("application/json;");
//     Gson gson = new Gson();
//     String json = gson.toJson(quotes);
//     response.getWriter().println(json);
  
//   }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
 String comment=request.getParameter("commentBox");
 long timestamp = System.currentTimeMillis();
 quotes.add(comment);
 Entity taskEntity = new Entity("Task");
    taskEntity.setProperty("title", comment);
    taskEntity.setProperty("timestamp", timestamp);

   

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);

  response.sendRedirect("/index.html");

  }
}

   
  
