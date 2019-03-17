$(document).ready(function () {
        const noteDocument = $(document)
        const createListButton = $('#create-list-btn')
        const createToDoButton = $('#create-todo-btn')
        const deleteListButton = $('#delete-list-btn')
        const deleteToDoButton = $('#delete-todo-btn')
        const loginButton =  $('#login')
        const notesList    = $('.list-group.list-group-flush')
        const todosList    = $('.list-group.list-tgroup-flush')
        var username = "";
        var pass = "";
        var hash = "";

        const helpers = {
          loginUser: evt => {
                if($('#userName').length){
                    username = $("#userName").val();
                    localStorage.setItem('username', username);
                }
                if($('#inputPassword').length){
                    pass = $("#inputPassword").val();
                    localStorage.setItem('pass', pass);
                }

                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));

                $.ajax
                ({
                  type: "GET",
                  url: "http://localhost:8080/noteplus/user/notes",
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " + hash
                  },
                  //data: '{ "comment" }',
                  success: function (data){
                    if (result.length > 0) {
                        $("#messageSpan").text("Login Successful, Redireting to your profile page.");
                        window.location.href = "./index.html";
                        //$.redirect('./index.html');
                        //$.redirect('./index.html', {"username": username.val(), "pass": pass.val()});                        
                    }
                    else
                    {
                        $("#messageSpan").text("Login failed, Please try again.");
                    }
                  }
                  /*,
                  error: function () { // xhr, status, error
                        //$("#dbData").html("Result: " + status + " " + error + " " + xhr.status + " " + xhr.statusText)
                        $("#messageSpan").text("Unknown error!");
                  }*/
                });

          },
            /**
             * On click, create a new list& relation and redirect to home page.
             */
            createListAndRedirect: evt => {
                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));
                var titleOfNote = $("#noteName").val();
                var relatedToDoId = $("#todoId").val();
                
                $.ajax
                ({
                  type: "POST",
                  url: "http://localhost:8080/noteplus/user/note",
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " +   hash
                  },
                  data: { title: titleOfNote, todoId : relatedToDoId },
                  success: function (data){
                     if(result.length > 0){
                        $("#messageSpan").text("Note is added!");
                        window.location.href = "./index.html";
                     }else{
                        $("#messageSpan").text("Error! note can not be addded!!");
                     }                            
                    }
                });               
            },

            createTodoAndRedirect: evt => {
                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));
                var nameOfTodo = $("#todoName").val();
                var desc = $("#html").val();
                var tdeadline = $("#tdeadline").val();
                var rTodoId = $("#rTodoId").val(); 

                $.ajax
                ({
                  type: "POST",
                  url: "http://localhost:8080/noteplus/user/todo",
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " +   hash
                  },
                  data: {
                            name:nameOfTodo,
                            description:desc,
                            deadline:tdeadline,
                            relatedTodoId:rTodoId,
                            status:"true"                    
                        },
                  success: function (data){
                     if(result.length > 0){
                        $("#messageSpan").text("Todo item is added!");
                        window.location.href = "./index.html";
                     }else{
                        $("#messageSpan").text("Error! todo item is can not be added!!");
                     }                            
                    }
                });               
            },            

            /**
             * Load the to do lists
             */
            loadNotes: evt => {
                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));

                $.ajax
                ({
                  type: "GET",
                  url: "http://localhost:8080/noteplus/user/notes",
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " + hash
                  },
                  //data: '{ "comment" }',
                  success: function (data){
                        const notes = data

                          notesList.append(
                                  `<table class="table table-bordered">
                                  <thead>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">Name</th>
                                      <th scope="col">Related To Do Id</th>
                                      <th scope="col">Delete</th>
                                    </tr>
                                  </thead>
                                  <tbody>`
                              )

                        for (const key in notes) {
                            if (notes.hasOwnProperty(key)) {
                                notesList.append(
                                    `<tr>
                                      <th scope="row">${notes[key].noteId}"</th>
                                      <td><a href="/todo/${notes[key].noteId}" style=" padding: 0.75rem;">${notes[key].title}</a></td>
                                      <td><a href="/todo/${notes[key].todoId}"style=" padding: 0.75rem;">${notes[key].todoId}</a></td>
                                      <td><form action="/todo/${notes[key].todoId}" method="delete"><button id="delete-list-btn" type="submit" value="Submit">Delete</button></form></td>
                                    </tr>`
                                )                              
                            }
                        }                            
                    }
                }); 

            },

                // load all to do lists 

            loadTodos: evt => {
                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));

                $.ajax
                ({
                  type: "GET",
                  url: "http://localhost:8080/noteplus/user/todos",
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " + hash
                  },
                  //data: '{ "comment" }',
                  success: function (data){
                        const todos = data

                             todosList.append(
                                  `<table class="table table-bordered">
                                  <thead>
                                    <tr>
                                      <th scope="col">#</th>
                                      <th scope="col">Name</th>
                                      <th scope="col">Description</th>
                                      <th scope="col">Deadline</th>
                                      <th scope="col">Related To Do Id</th>
                                      <th scope="col">Delete</th>
                                    </tr>
                                  </thead>
                                  <tbody>`
                              )

                        for (const key in todos) {

                            if (todos.hasOwnProperty(key)) {
                                todosList.append(
                                    `<tr>
                                      <th scope="row">${todos[key].todoId}"</th>
                                      <td><a href="/todo/${todos[key].todoId}" style=" padding: 0.75rem;">${todos[key].name}</a></td>
                                      <td>${todos[key].description}</td>
                                      <td>${new Date(todos[key].deadline).getFullYear().toString() + "/" + new Date(todos[key].deadline).getMonth().toString() + "/" + new Date(todos[key].deadline).getDate().toString()}</td>
                                      <td><a href="/todo/${todos[key].relatedTodoId}"style=" padding: 0.75rem;">${todos[key].relatedTodoId}</a></td>
                                      <td><form action="/todo/${todos[key].todoId}" method="delete"><button id="delete-todo-btn" type="submit" value="Submit">Delete</button></form></td>
                                    </tr>`
                                )
                            }
                        }

                             todosList.append(
                                  `</tbody></table>`
                              )                                                    
                    }
                });         

            },

            deleteListAndRedirect: evt => {
                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));
                var nnoteId = $("#noteId").val();
                
                $.ajax
                ({
                  type: "DELETE",
                  url: "http://localhost:8080/noteplus/user/note/" + nnoteId,
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " +   hash
                  },
                  success: function (data){
                     if(result.length > 0){
                        $("#messageSpan").text("Note is deleted!");
                        window.location.href = "./index.html";
                     }else{
                        $("#messageSpan").text("Error! note can not be deleted!!");
                     }                            
                    }
                });               
            },

            deleteTodoAndRedirect: evt => {
                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));
                var todoId = $("#todoId").val(); 

                $.ajax
                ({
                  type: "DELETE",
                  url: "http://localhost:8080/noteplus/user/todo/" + todoId,
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " +   hash
                  },
                  success: function (data){
                     if(result.length > 0){
                        $("#messageSpan").text("Todo item is deleted!");
                        window.location.href = "./index.html";
                     }else{
                        $("#messageSpan").text("Error! todo item can not be deleted!!");
                     }                            
                    }
                });               
            }



        }

        if($('#userName').length){
          //loginButton.submit(helpers.loginUser)
          loginButton.click(helpers.loginUser)
        }
        else if($('#html').length){
            tinyMCE.init({
              selector: "#html",
              // content_css: 'https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css',
              plugins: ["code visualblocks"],
              valid_elements : '*[*]',
              toolbar: "undo redo | styleselect | bold italic | fontsizeselect | alignleft aligncenter alignright alignjustify | preview",
              schema: "html5",
            });      
        }
        else if($('#list-notes').length){
            noteDocument.ready(helpers.loadNotes)
        }
        else if($('#list-todos').length){
            noteDocument.ready(helpers.loadTodos)
        }        
        else{
              createListButton.click(helpers.createListAndRedirect) 
              createToDoButton.click(helpers.createTodoAndRedirect) 
              deleteListButton.click(helpers.deleteListAndRedirect)
              deleteToDoButton.click(helpers.deleteTodoAndRedirect)
        }

});