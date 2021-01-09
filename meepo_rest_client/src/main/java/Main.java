import response.NotesResponse;

public class Main {

  public static void main(String[] params){
    NoteApi noteApi = new NoteApi();
    noteApi.getNotes();
  }
}
