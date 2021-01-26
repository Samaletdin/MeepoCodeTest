package response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Note {
    private String id;
    private String title;
    private String description;

    @JsonCreator
    public Note(@JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
