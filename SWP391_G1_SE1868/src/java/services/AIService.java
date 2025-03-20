package services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Dịch vụ gọi API AI để xử lý tin nhắn
 */
public class AIService {
    private static final String API_URL = "https://app.tudongchat.com/nZfoUuZQ"; // TuDongChat API URL
    private static final String API_KEY = "swat:HS256.api:67c67b7a3bcaa40cc8dc9f42:1741662391:.+fj9np4cDt2mHfaQQSz8oJ1VkFRnYSK4xmNqSBPp8nw"; // TuDongChat API Key
    private final HttpClient client;

    public AIService() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Gửi tin nhắn đến API và nhận phản hồi
     */
    public String processMessage(String userMessage) throws IOException, InterruptedException {
        // Tạo body JSON gửi đến API
        String jsonBody = String.format("""
            {
                "message": "%s"
            }
            """, userMessage.replace("\"", "\\\""));  // Escape dấu ngoặc kép

        // Tạo yêu cầu HTTP để gửi đến API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            // Gửi yêu cầu và nhận phản hồi từ API
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Kiểm tra mã trạng thái HTTP
            if (response.statusCode() == 200) {
                try {
                    // Cố gắng phân tích phản hồi JSON
                    JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                    
                    // Kiểm tra nếu có trường response
                    if (jsonResponse.has("response")) {
                        return jsonResponse.get("response").getAsString();
                    }
                    
                    // Nếu không, trả về toàn bộ phản hồi dưới dạng chuỗi
                    return response.body();
                } catch (Exception e) {
                    // Nếu không phải JSON, trả về phản hồi gốc
                    return response.body();
                }
            } else {
                System.err.println("Lỗi từ API: " + response.statusCode() + " - " + response.body());
                return "Xin lỗi, đã có lỗi xảy ra khi kết nối với dịch vụ AI. Vui lòng thử lại sau.";
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi xử lý phản hồi: " + e.getMessage());
            e.printStackTrace();
            return "Xin lỗi, đã có lỗi xảy ra. Vui lòng thử lại sau.";
        }
    }
}