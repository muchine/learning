package gathering.entity;

public class Member {

    private static int idSequence = 0;

    public enum Grade {
        ADMIN, NORMAL;
    }

    private final int id;

    private final User user;

    private final String nickname;

    private final Grade grade;

    public Member(int id, User user, String nickname, Grade grade) {
        this.id = id;
        this.user = user;
        this.nickname = nickname;
        this.grade = grade;
    }

    public boolean isAdmin() {
        return grade == Grade.ADMIN;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public User getUser() {
        return user;
    }

    public Grade getGrade() {
        return grade;
    }

    public static int newId() {
        return idSequence++;
    }

}
