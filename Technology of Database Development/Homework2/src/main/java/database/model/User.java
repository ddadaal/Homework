package database.model;

public class User {
    private int id;
    private String name;
    private int basicCostId;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(String name, int basicCostId, String phoneNumber) {
        this.name = name;
        this.basicCostId = basicCostId;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public int getBasicCostId() {
        return basicCostId;
    }

    public void setBasicCostId(int basicCostId) {
        this.basicCostId = basicCostId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
