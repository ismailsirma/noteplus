$(document).ready(function () {
        const noteDocument = $(document)
        const createButton = $('#create-btn')
        const notesList    = $('.list-group.list-group-flush')
        var uname = 'sirmam';
        var pass = 'abc123';

        const helpers = {
            /**
             * On click, create a new note and redirect to it...
             */
            createNoteAndRedirect: evt => {
                //axios.post('http://localhost:8080/noteplus/user/notes').then(response => (window.location = '/notes/'+response.data.data.Slug))
            },

            /**
             * Load the notes to the page...
             */
            loadNotes: evt => {

                $.ajax
                ({
                  type: "GET",
                  url: "http://localhost:8080/noteplus/user/notes",
                  dataType: 'json',
                  headers: {
                    "Authorization": "Basic " + btoa(uname + ":" + pass)
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

        noteDocument.ready(helpers.loadNotes)
        createButton.click(helpers.createNoteAndRedirect)	

});