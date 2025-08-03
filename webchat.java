import java.util.*;

// ✅ Pillar 1: ENCAPSULATION (private + public accessors)
class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

// ✅ Base Message Class
class Message {
    private User sender;
    private String text;

    public Message(User sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public User getSender() { return sender; }
    public String getText() { return text; }

    // ✅ Pillar 4: POLYMORPHISM (can be overridden)
    public void print() {
        System.out.println(sender.getUsername() + ": " + text);
    }
}

// ✅ Pillar 3: INHERITANCE
class MediaMessage extends Message {
    private String mediaUrl;

    public MediaMessage(User sender, String text, String mediaUrl) {
        super(sender, text);
        this.mediaUrl = mediaUrl;
    }

    @Override
    public void print() {
        System.out.println(getSender().getUsername() + ": " + getText() + " [Media: " + mediaUrl + "]");
    }
}

// ✅ Chat Room — handles users and messages
class Chat {
    private List<User> participants = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public void addUser(User user) {
        participants.add(user);
        System.out.println(user.getUsername() + " joined the chat.");
    }

    // ✅ Pillar 2: ABSTRACTION
    public void sendMessage(User sender, String text) {
        Message msg = new Message(sender, text);
        messages.add(msg);
    }

    public void sendMediaMessage(User sender, String text, String mediaUrl) {
        Message msg = new MediaMessage(sender, text, mediaUrl);
        messages.add(msg);
    }

    public void showChat() {
        System.out.println("----- Chat History -----");
        for (Message msg : messages) {
            msg.print(); // Polymorphic call
        }
    }
}

public class Main {
    public static void main(String[] args) {
        User alice = new User("Alice");
        User bob = new User("Bob");

        Chat chat = new Chat();
        chat.addUser(alice);
        chat.addUser(bob);

        chat.sendMessage(alice, "Hi Bob!");
        chat.sendMessage(bob, "Hey Alice!");
        chat.sendMediaMessage(alice, "Check this image!", "http://img.com/pic.jpg");

        chat.showChat();
    }
}
