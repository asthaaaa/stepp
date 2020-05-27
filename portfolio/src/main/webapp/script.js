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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Joey doesn’t share food!', ' I wish I could, but I don’t want to.', 'How you doin?', 'They don’t know that we know they know we know.'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}
 function RandomProjects() {
  fetch('/list-tasks').then(response => response.json()).then((stats) => {
    
 const statsListElement = document.getElementById('randomproject');
    const l=stats.length;
    statsListElement.innerHTML = '';
   
     
var i;
    for( i=0;i<l;i++){
statsListElement.appendChild(
        createListElement( stats[i].title));
    }

    

  }).then(()=>LOGIN()).then(()=>showImage()).then(()=>fetchBlobstoreUrlAndShowForm());
}
function showImage()
{
     fetch('/show').then(response => response.json()).then((stats) => {
    // stats is an object, not a string, so we have to
    // reference its fields to create HTML content

    const statsListElement = document.getElementById('images');
    const l=stats.length;
    statsListElement.innerHTML = '';
   
const label3 = document.createElement('label');
   var i;
    for( i=0;i<l;i++){

  var x = document.createElement("IMG");
  x.setAttribute("src", stats[i].title);
  x.setAttribute("width", "304");
  x.setAttribute("height", "228");
  x.setAttribute("alt", "The Pulpit Rock");
  statsListElement.appendChild(x);
  const label3 = document.createElement('label');
  label3.innerHTML="<br/>";
  statsListElement.appendChild(label3);
    }
   

  });
}
 function RandoProjects() {
  fetch('/list-tasks').then(response => response.json()).then((stats) => {
    

    const statsListElement = document.getElementById('randomproject');
    const l=stats.length;
    statsListElement.innerHTML = '';
   
   var i;
    for( i=0;i<l;i++){
statsListElement.appendChild(
        createListElement( stats[i].title));
    }

  });
}
function fetchBlobstoreUrlAndShowForm() {
  fetch('/blobstore-upload-url')
      .then((response) => {
        return response.text();
      })
      .then((imageUploadUrl) => {
        const messageForm = document.getElementById('my-form');
        messageForm.action = imageUploadUrl;
        messageForm.classList.remove('hidden');
      });//.then(()=>RandomProjects());
}

function createLink(URL, type){
  const elem = document.getElementById("randomproject");
  const linkk= document.createElement('a');
  linkk.href =URL;
  linkk.innerText = type;
  elem.appendChild(linkk);
}
function check(){
    fetch('/login').then(response => response.json()).then((stats) => {if(stats.log==true)
{
          createLink(stats.url, "Logout");
createform();
createNoform();
createButton();
}
else{
    createLink(stats.url,"Login");
}
});
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

function deleteTask() {
  const params = new URLSearchParams();
  //params.append('id', task.id);
    fetch('/delete-task', {method: 'POST', body: params}).then(() =>{location.reload(true).RandomProjects()});
    //RandoProjects());
    
}
function LOGIN() {
  const params = new URLSearchParams();
  //params.append('id', task.id);
    fetch('/login', {method: 'POST', body: params}).then(() =>check());
}
function createform(){
      const elem = document.getElementById("for");
    const Formm = document.createElement('form');
  Formm.action = "/data";
  Formm.method ="POST";
  const label2 = document.createElement('label');
  label2.for = "Comments";
  label2.innerHTML ="<h4>Add Comments:</h4>";
  
  Formm.appendChild(label2);
  const label3 = document.createElement('label');
  label3.innerHTML="<br/>"
  Formm.appendChild(label3);

  const inputComment = document.createElement('input');
  inputComment.type = "text";
  inputComment.id = "commentBox";
  inputComment.name = "commentBox";
  Formm.appendChild(inputComment);

  const commentSubmit = document.createElement('input');
  commentSubmit.type = "submit";
  commentSubmit.id = "comment-submit";
    Formm.appendChild(commentSubmit);
    elem.appendChild(Formm);}

function createNoform(){
      const elem = document.getElementById("for");
    const Formm = document.createElement('form');
  Formm.action = "/list-tasks";
  Formm.method ="POST"

  const label2 = document.createElement('label');
  label2.for = "numberOfComments";
  label2.innerHTML ="<h4>Number of comments to be displayed:</h4>";
  
  Formm.appendChild(label2);
  const label3 = document.createElement('label');
  label3.innerHTML="<br/>"
  Formm.appendChild(label3);

  const inputComment = document.createElement('input');
  inputComment.type = "number";
  inputComment.id = "no";
  inputComment.name = "no";
  Formm.appendChild(inputComment);
  //document.write("<br>");

  const commentSubmit = document.createElement('input');
  commentSubmit.type = "submit";
  commentSubmit.id = "comment-submit";
    Formm.appendChild(commentSubmit);
     
    elem.appendChild(Formm);}
    
    function loadBody(){
        RandomProject();
        LOGIN();
    }

function createCommentSection(){
     const elem = document.getElementById("for");
    const comm= document.createElement(div);
    comm;
}
function createButton(){
const elem = document.getElementById("for");
const labell = document.createElement('label');
  labell.innerHTML="<br/>"
  elem.appendChild(labell);
    const buttonn = document.createElement('button');
    buttonn.id="delete";
    buttonn.name="delete";
    buttonn.textContent="DELETE";
   // buttonn.onclick="deleteTask()";
    buttonn.addEventListener("click",deleteTask);
    elem.appendChild(buttonn);
}