import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

class RestSingleSender {

    private final HttpClient httpClient;

    RestSingleSender() {
        httpClient = HttpClient.newHttpClient();
    }

    HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return httpClient.send(request, BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Uncaught exception while sending request, this should not happen!");
    }
}
