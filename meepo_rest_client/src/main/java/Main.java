import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class Main {

    private static NoteApi noteApi = new NoteApi();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] params) throws JsonProcessingException {
        System.out.println(displayWelcomeMessage());
        while (true) {
            StringBuilder messageBuilder = new StringBuilder();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    messageBuilder.append(noteApi.getNotes());
                    break;
                case "2":
                    messageBuilder.append(handleCreateNote());
                    break;
                case "3":
                    messageBuilder.append(handleGetNote());
                    break;
                case "4":
                    messageBuilder.append(handleUpdateNote());
                    break;
                case "5":
                    messageBuilder.append(handleDeleteNote());
                    break;
                case "q":
                    scanner.close();
                    System.out.println("Goodbye!");
                    return;
                default:
                    messageBuilder.append(displayWelcomeMessage());
            }
            System.out.println(messageBuilder);
        }

    }

    private static String displayWelcomeMessage() {
        return new StringBuilder()
                .append("Hello and welcome to Meepo Notes!\n")
                .append("Would you like to:\n")
                .append("1) Read all notes\n")
                .append("2) Create a new note\n")
                .append("2) Read a note by id\n")
                .append("3) Update a note by id\n")
                .append("4) Delete a note by id\n")
                .append("q to quit\n")
                .toString();
    }

    private static String handleCreateNote() throws JsonProcessingException {
        System.out.println("Please write title and description of your note:");
        String title = scanner.nextLine();
        String description = scanner.nextLine();
        return noteApi.createNote(title, description);
    }

    private static String handleGetNote() throws JsonProcessingException {
        System.out.println("Please enter the id for the note you would like to read:");
        String readId = scanner.nextLine();
        return noteApi.getNote(readId);
    }

    private static String handleUpdateNote() {
        System.out.println("Please write; title, description and id of the note you would like to update. Separate by new row:");
        String title = scanner.nextLine();
        String description = scanner.nextLine();
        String updateId = scanner.nextLine();
        return noteApi.updateNote(title, description, updateId);
    }

    private static String handleDeleteNote() {
        System.out.println("Please enter what note id you would like to delete:");
        String deleteId = scanner.nextLine();
        return noteApi.deleteNote(deleteId);
    }
}
