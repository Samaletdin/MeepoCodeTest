import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import response.NotesResponse;

public class NoteApi {

  private static final String NOTES_API_HOST = "https://timesheet-1172.appspot.com/2a00ea83/notes";
  private final HttpClient httpClient;

  public NoteApi() {
    httpClient = HttpClient.newHttpClient();
  }

  public void getNotes() {
    HttpRequest getNotesRequest = HttpRequest.newBuilder(URI.create(NOTES_API_HOST))
        .GET()
        .build();

    NotesResponse notesResponse = null;
    try {
      notesResponse = httpClient.send(getNotesRequest, BodyHandlers.ofString());
    } catch (RuntimeException e) {
      e.printStackTrace();
    }

    notesResponse.getNotes().stream()
        .forEach(note -> {
          System.out.println(note.getId());
          System.out.println(note.getTitle());
          System.out.println(note.getDescription());
        });
  }
}
