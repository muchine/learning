package gathering.entity;

public class User {

    private final int id;

    private final String name;

    private final String company;

    private final String department;

    private final String title;

    public User(int id, String name, String company, String department, String title) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.department = department;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getDepartment() {
        return department;
    }

    public String getTitle() {
        return title;
    }

}
