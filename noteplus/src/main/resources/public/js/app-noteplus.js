$(document).ready(function () {
        const noteDocument = $(document)
        const createListButton = $('#create-list-btn')
        const createToDoButton = $('#create-todo-btn')
        const loginButton =  $('#login')
        const notesList    = $('.list-group.list-group-flush')
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
                  data: { title: titleOfNote, todoId = relatedToDoId },
                  success: function (data){
                     if(result.length > 0){
                        $("#messageSpan").text("Note uploaded!");
                        window.location.href = "./index.html";
                     }else{
                        $("#messageSpan").text("Error! note is not uploaded!!");
                     }                            
                    }
                });               
            },

            createTodoAndRedirect: evt => {
                hash = btoa(localStorage.getItem('username')  + ":" + localStorage.getItem('pass'));
                var titleOfNote = $("#noteName").val();
                var relatedToDoId = $("#todoId").val();
                
                $.ajax
                ({
                  type: "POST",
                  url: "http://localhost:8080/noteplus/user/todo",
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " +   hash
                  },
                  data: { title: titleOfNote, todoId = relatedToDoId },
                  success: function (data){
                     if(result.length > 0){
                        $("#messageSpan").text("Note uploaded!");
                        window.location.href = "./index.html";
                     }else{
                        $("#messageSpan").text("Error! note is not uploaded!!");
                     }                            
                    }
                });               
            },            

            /**
             * Load the notes to the page...
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
                        for (const key in notes) {
                            if (notes.hasOwnProperty(key)) {
                                notesList.append(
                                    `<a href="/notes/${notes[key].Slug}" class="list-group-item list-group-item-action">
                                        ${notes[key].title}
                                    </a>`
                                )
                            }
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
        else{
              noteDocument.ready(helpers.loadNotes)
              createListButton.click(helpers.createListAndRedirect) 
              createToDoButton.click(helpers.createTodoAndRedirect) 
        }

});