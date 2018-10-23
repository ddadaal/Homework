package database.model;

public class User {
    private int id;
    private String name;
    private int basicCostId;

    public User(String name, int basicCostId) {
        this.name = name;
        this.basicCostId = basicCostId;
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
