import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import request.UpdateNoteRequest;
import response.Note;
import response.NotesResponse;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

class NoteApi {

    private static final String NOTES_API_HOST = "https://timesheet-1172.appspot.com/2a00ea83/notes/";
    private RestSingleSender restSingleSender;
    private ObjectMapper objectMapper;

    NoteApi() {
        restSingleSender = new RestSingleSender();
        objectMapper = new ObjectMapper();
    }

    String getNotes() throws JsonProcessingException {
        HttpRequest getNotesRequest = HttpRequest.newBuilder(URI.create(NOTES_API_HOST))
                .GET()
                .build();
        HttpResponse<String> response = restSingleSender.sendRequest(getNotesRequest);

        int statusCode = response.statusCode();
        if (200 != statusCode) {
            return printErrorMessage(statusCode, response.body());
        }

        NotesResponse notesResponse = objectMapper.readValue(response.body(), NotesResponse.class);
        StringBuilder notesListBuilder = new StringBuilder();
        notesResponse.getNotes().forEach(note -> notesListBuilder.append(display(note)));
        return notesListBuilder.toString();
    }

    private String printErrorMessage(int statusCode, String body) {
        return String.format("Something went wrong, server answered with: %s and body: %s ", statusCode, body);
    }

    private String display(Note note) {
        StringBuilder noteStringBuilder = new StringBuilder()
                .append("----------------------\n")
                .append(note.getTitle() + " : ")
                .append(note.getDescription() + "\n")
                .append("Id : " + note.getId() + "\n")
                .append("----------------------\n");
        return noteStringBuilder.toString();
    }

    String createNote(String title, String description) throws JsonProcessingException {
        UpdateNoteRequest newNote = new UpdateNoteRequest(title, description);
        HttpRequest updateNoteRequest = HttpRequest.newBuilder(URI.create(NOTES_API_HOST))
                .PUT(BodyPublishers.ofString(newNote.toString()))
                .build();
        HttpResponse<String> response = restSingleSender.sendRequest(updateNoteRequest);

        int statusCode = response.statusCode();
        if (200 != statusCode) {
            return printErrorMessage(statusCode, response.body());
        }

        Note note = objectMapper.readValue(response.body(), Note.class);
        return new StringBuilder().append("Note Created:")
                .append(display(note))
                .toString();
    }

    String getNote(String id) throws JsonProcessingException {
        HttpRequest getNotesRequest = HttpRequest.newBuilder(URI.create(NOTES_API_HOST + id))
                .GET()
                .build();
        HttpResponse<String> response = restSingleSender.sendRequest(getNotesRequest);

        int statusCode = response.statusCode();

        if (404 == statusCode) {
            return String.format("Note with id %s, does not exist", id);
        }
        if (200 != statusCode) {
            return printErrorMessage(statusCode, response.body());
        }

        Note note = objectMapper.readValue(response.body(), Note.class);
        return display(note);
    }

    String updateNote(String title, String description, String id) {
        UpdateNoteRequest newNote = new UpdateNoteRequest(title, description);
        HttpRequest updateNoteRequest = HttpRequest.newBuilder(URI.create(NOTES_API_HOST + id))
                .PUT(BodyPublishers.ofString(newNote.toString()))
                .build();
        HttpResponse<String> response = restSingleSender.sendRequest(updateNoteRequest);

        int statusCode = response.statusCode();
        String successMessage = String.format("Note with id %s, successfuly created", id);

        return verifyResponseStatus(id, response.body(), statusCode, successMessage);
    }

    private String verifyResponseStatus(String id, String body, int statusCode, String successMessage) {
        if (200 == statusCode) {
            return successMessage;
        } else if (404 == statusCode) {
            return String.format("Note with id %s, does not exist", id);
        } else {
            return printErrorMessage(statusCode, body);
        }
    }

    String deleteNote(String id) {
        HttpRequest deleteNoteRequest = HttpRequest.newBuilder(URI.create(NOTES_API_HOST + id))
                .DELETE()
                .build();
        HttpResponse<String> response = restSingleSender.sendRequest(deleteNoteRequest);

        int statusCode = response.statusCode();
        String successMessage = String.format("Note with id %s, successfuly deleted", id);
        return verifyResponseStatus(id, response.body(), statusCode, successMessage);
    }
}
