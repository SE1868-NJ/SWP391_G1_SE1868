<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat with AI</title>
    <link rel="stylesheet" href="assets/css/chatbox.css">
</head>
<body>
    <div id="chat-icon" onclick="toggleChatbox()">? Chat</div>

    <div id="chatbox">
        <div id="chat-header">
            <img id="chat-avatar" src="assets/img/avatar_ai.png" alt="AI Chatbot">
            <span id="chat-title">Chat v?i AI ?</span>
            <button onclick="toggleChatbox()">?</button>
        </div>

        <div id="shop-list">
            <button onclick="switchChat('AI')">Chat v?i AI ?</button>
            <button onclick="switchChat(2)">Shop ?i?n t?</button>
            <button onclick="switchChat(5)">Shop M? ph?m</button>
        </div>

        <div id="chat-body"></div>

        <div id="chat-footer">
            <input type="text" id="chat-input" placeholder="Nh?p tin nh?n..." onkeydown="if(event.key === 'Enter') sendMessage()">
            <button onclick="sendMessage()">G?i</button>
        </div>
    </div>

    <script src="https://app.tudongchat.com/js/chatbox.js"></script>
    <script>
        const tudong_chatbox = new TuDongChat('jRfMiDxsrSMZdde91CE0C');
        tudong_chatbox.initial();

        function toggleChatbox() {
            let chatbox = document.getElementById("chatbox");
            chatbox.style.display = (chatbox.style.display === "none" || chatbox.style.display === "") ? "block" : "none";
        }

        function switchChat(receiver) {
            currentChat = receiver;
            document.getElementById("chat-body").innerHTML = ""; 
            loadMessages(receiver);  
            document.getElementById("chat-title").innerText = receiver === 'AI' ? "Chat v?i AI ?" : "Chat v?i Shop";
            document.getElementById("chat-avatar").src = receiver === 'AI' ? "assets/img/avatar_ai.png" : "assets/img/shop_avatar.png";
        }

        function sendMessage() {
            let message = document.getElementById("chat-input").value;
            if (message.trim() === "") return; 

            tudong_chatbox.sendMessage(message);

            document.getElementById("chat-input").value = "";
        }

        function loadMessages(receiver) {
            tudong_chatbox.getMessages().then(messages => {
                let chatBody = document.getElementById("chat-body");
                chatBody.innerHTML = "";
                messages.forEach(msg => {
                    chatBody.innerHTML += `<p><strong>${msg.sender}</strong>: ${msg.text} <span>${msg.time}</span></p>`;
                });
                chatBody.scrollTop = chatBody.scrollHeight;
            });
        }
    </script>
</body>
</html>
