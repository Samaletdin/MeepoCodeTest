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
                    messageBuilder.append(noteApi.updateNote("placeholder title", "placeholder description", "1"));
                    break;
                case "3":
                    messageBuilder.append(noteApi.deleteNote("1"));
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

    private static String displayWelcomeMessage() {
        return new StringBuilder()
                .append("Hello and welcome to Meepo Notes!\n")
                .append("Would you like to:\n")
                .append("1) Read your notes\n")
                .append("2) Update a note\n")
                .append("3) Delete a note\n")
                .append("q to quit\n")
                .toString();
    }
}
