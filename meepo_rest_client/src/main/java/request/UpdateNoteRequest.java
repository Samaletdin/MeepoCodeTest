package request;

public class UpdateNoteRequest {

    private String title;
    private String description;

    public UpdateNoteRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{")
                .append("\"title\":\"" + title + "\",")
                .append("\"description\":\"" + description + "\"")
                .append("}")
                .toString();
    }
}
