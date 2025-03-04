<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat with Bot</title>
    <style>
        .chat-box {
            width: 400px;
            height: 500px;
            border: 1px solid #ccc;
            padding: 10px;
            overflow-y: auto;
        }
        .message {
            margin-bottom: 10px;
        }
        .customer {
            color: blue;
        }
        .bot {
            color: green;
        }
    </style>
    
    <!-- Thêm script để tích hợp TuDongChat -->
    <script src="https://app.tudongchat.com/js/chatbox.js"></script>
</head>
<body>

<h2>Chat with Bot</h2>

<div class="chat-box" id="chat-box">
    <!-- Tin nhắn sẽ xuất hiện ở đây -->
</div>

<input type="text" id="message" placeholder="Type your message..." />
<button onclick="sendMessage()">Send</button>
 <!-- Khởi tạo chatbot TuDongChat -->
<script>
    const tudong_chatbox = new TuDongChat('D3s72L9-F3aq2GpR38aTL');
    tudong_chatbox.initial();
</script>

</body>
</html>
