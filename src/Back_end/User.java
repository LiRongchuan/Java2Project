package Back_end;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class User {
    private final String id;
    private final String nickname;
    private final String phone_number;
    private final String email;

    private final String goal;

    public User(String id, String nickname, String phone_number, String email, String goal) {
        this.id = id;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.email = email;
        this.goal = goal;
    }
    public static User loadUserInfo() throws IOException {
        String fileName = "user.txt";
        File file = new File(fileName);
        Scanner scanner;
        if (!file.exists()) {
            file.createNewFile();
        }
        scanner = new Scanner(file);
        String id, nickname, phone_number, email, goal;
        id = scanner.nextLine();
        nickname = scanner.nextLine();
        phone_number = scanner.nextLine();
        email = scanner.nextLine();
        goal = scanner.nextLine();
        return new User(id, nickname, phone_number, email, goal);
    }

    public String getGoal() {
        return goal;
    }


    public String getNickname() {
        return nickname;
    }


    public String getPhone_number() {
        return phone_number;
    }


    public String getEmail() {
        return email;
    }

}
