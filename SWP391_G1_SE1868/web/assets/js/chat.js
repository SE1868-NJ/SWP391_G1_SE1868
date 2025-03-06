class TuDongChat {
    constructor(apiKey) {
        this.apiKey = apiKey;
        this.messages = [];
    }

    // Khởi tạo chatbox với API Key
    initial() {
        console.log("Chatbot initialized with API Key: " + this.apiKey);
    }

    // Gửi tin nhắn từ người dùng đến TuDongChat
    sendMessage(message) {
        // Đây là phần xử lý khi người dùng gửi tin nhắn
        console.log("Sending message:", message);
        
        // Giả lập gửi tin nhắn tới TuDongChat (thực tế có thể gửi request HTTP ở đây)
        this.messages.push({
            sender: 'Customer',
            text: message,
            time: new Date().toLocaleString()
        });

        // Tự động gọi API để gửi tin nhắn và nhận phản hồi (giả lập)
        this.getAIResponse(message); // Lấy phản hồi từ AI
    }

    // Lấy tất cả tin nhắn (bao gồm cả của người dùng và phản hồi từ AI)
    getMessages() {
        return new Promise((resolve) => {
            setTimeout(() => {
                resolve(this.messages);
            }, 500);
        });
    }

    // Lấy phản hồi từ AI dựa trên tin nhắn của người dùng
    getAIResponse(userMessage) {
        let aiResponse = "";

        // Dựa trên nội dung của tin nhắn, AI sẽ phản hồi (Giả lập phản hồi từ AI)
        if (userMessage.toLowerCase().includes("sản phẩm")) {
            aiResponse = "Bạn cần tìm sản phẩm nào? Tôi có thể giúp bạn.";
        } else if (userMessage.toLowerCase().includes("giao hàng")) {
            aiResponse = "Chúng tôi cung cấp dịch vụ giao hàng toàn quốc!";
        } else {
            aiResponse = "Xin chào! Bạn cần hỗ trợ gì hôm nay?";
        }

        // Giả lập gửi tin nhắn của AI vào hệ thống
        this.messages.push({
            sender: 'AI',
            text: aiResponse,
            time: new Date().toLocaleString()
        });

        console.log("AI Response: " + aiResponse);
    }
}

// Sử dụng API của TuDongChat trong trang web
const tudong_chatbox = new TuDongChat('jRfMiDxsrSMZdde91CE0C');
tudong_chatbox.initial();

// Gửi tin nhắn khi người dùng nhập và nhấn "Gửi"
function sendMessage() {
    let message = document.getElementById("chat-input").value;
    if (message.trim() === "") return; // Không gửi tin nhắn rỗng

    tudong_chatbox.sendMessage(message);

    // Clear input sau khi gửi
    document.getElementById("chat-input").value = "";
}

// Load tin nhắn hiện có từ TuDongChat và hiển thị
function loadMessages(receiver) {
    tudong_chatbox.getMessages().then(messages => {
        let chatBody = document.getElementById("chat-body");
        chatBody.innerHTML = "";
        messages.forEach(msg => {
            chatBody.innerHTML += `<p><strong>${msg.sender}</strong>: ${msg.text} <span>${msg.time}</span></p>`;
        });
        chatBody.scrollTop = chatBody.scrollHeight;  // Auto scroll to bottom
    });
}

// Chuyển đổi giữa AI và Shop trong chatbox
function switchChat(receiver) {
    currentChat = receiver;
    document.getElementById("chat-body").innerHTML = ""; 
    loadMessages(receiver);  // Tải lại tin nhắn cho AI hoặc Shop
    document.getElementById("chat-title").innerText = receiver === 'AI' ? "Chat với AI 🤖" : "Chat với Shop";
    document.getElementById("chat-avatar").src = receiver === 'AI' ? "assets/img/avatar_ai.png" : "assets/img/shop_avatar.png";
}
