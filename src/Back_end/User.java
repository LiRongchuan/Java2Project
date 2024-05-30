package Back_end;

public class User {
    private String id;
    private String nickname;
    private String phone_number;
    private String email;

    public User(String id, String nickname, String phone_number, String email) {
        this.id = id;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
