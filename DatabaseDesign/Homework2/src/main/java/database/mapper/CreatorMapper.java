package database.mapper;

public interface CreatorMapper {
    void createAllTables();

    void insertAPlan();

    void dropAllTables();

    default void reset() {
        dropAllTables();
        createAllTables();
    }
}
