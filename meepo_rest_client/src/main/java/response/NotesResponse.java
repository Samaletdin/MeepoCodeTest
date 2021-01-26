package response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NotesResponse {
    @JsonProperty
    private List<Note> notes;

    @JsonCreator
    public NotesResponse(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }
}
