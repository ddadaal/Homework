package database.mapper;

public interface CreatorMapper {
    void createAllTables();

    void insertAPlan();

    void insertAUser();

    void dropAllTables();

    default void reset() {
        dropAllTables();
        createAllTables();
    }
}
