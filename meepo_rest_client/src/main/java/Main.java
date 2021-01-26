import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class Main {

    public static void main(String[] params) throws JsonProcessingException {
        NoteApi noteApi = new NoteApi();
        Scanner scanner = new Scanner(System.in);
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(displayWelcomeMessage());
        System.out.println(messageBuilder);
        while (true) {
            messageBuilder = new StringBuilder();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    messageBuilder.append(noteApi.getNotes());
                    break;
                case "2":
                    handleGetNote(noteApi, scanner, messageBuilder);
                    break;
                case "3":
                    handleUpdateNote(noteApi, scanner, messageBuilder);
                    break;
                case "4":
                    handleDeleteNote(noteApi, scanner, messageBuilder);
                    break;
                case "q":
                    scanner.close();
                    return;
                default:
                    messageBuilder.append(displayWelcomeMessage());
            }
            System.out.println(messageBuilder);
        }

    }

    private static void handleGetNote(NoteApi noteApi, Scanner scanner, StringBuilder messageBuilder) throws JsonProcessingException {
        System.out.println("Please enter the id for the note you would like to read:");
        String readId = scanner.nextLine();
        messageBuilder.append(noteApi.getNote(readId));
    }

    private static void handleUpdateNote(NoteApi noteApi, Scanner scanner, StringBuilder messageBuilder) {
        System.out.println("Please write describe title, description and id, separate by new row:");
        String title = scanner.nextLine();
        String description = scanner.nextLine();
        String updateId = scanner.nextLine();
        messageBuilder.append(noteApi.updateNote(title, description, updateId));
    }

    private static void handleDeleteNote(NoteApi noteApi, Scanner scanner, StringBuilder messageBuilder) {
        System.out.println("Please enter what note id you would like to delete:");
        String deleteId = scanner.nextLine();
        messageBuilder.append(noteApi.deleteNote(deleteId));
    }

    private static String displayWelcomeMessage() {
        return new StringBuilder()
                .append("Hello and welcome to Meepo Notes!\n")
                .append("Would you like to:\n")
                .append("1) Read all notes\n")
                .append("2) Read notes by id\n")
                .append("3) Update a note\n")
                .append("4) Delete a note\n")
                .append("q to quit\n")
                .toString();
    }
}
