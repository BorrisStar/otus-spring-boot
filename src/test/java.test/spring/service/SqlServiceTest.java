package spring.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlServiceTest {

    @Test
    void getParameters_correctString() {
        String result = SqlService.getParameters("title");

        assertEquals("%title%", result);
    }

    @Test
    void prepareSqlQueryForDelete_correctString() {
        String result = SqlService.prepareSqlQueryForDelete("tableName");

        assertEquals("DELETE FROM tableName WHERE id = :id", result);
    }

    @Test
    void prepareSqlQueryForFindAll_correctString() {
        String result = SqlService.prepareSqlQueryForFindAll("tableName");

        assertEquals("SELECT * FROM tableName", result);
    }

    @Test
    void prepareSqlQueryForFindById_correctString() {
        String result = SqlService.prepareSqlQueryForFindById("tableName");

        assertEquals("SELECT * FROM tableName WHERE id = :id", result);
    }

    @Test
    void prepareSqlQueryForInsert_correctString() {
        String result = SqlService.prepareSqlQueryForInsert("tableName", new String[] {"firstname", "lastname"});

        assertEquals("INSERT INTO tableName (firstname, lastname) VALUES (:firstname, :lastname)", result);
    }

    @Test
    void prepareSqlQueryForUpdate_correctString() {
        String result = SqlService.prepareSqlQueryForUpdate("tableName", new String[] {"firstname", "lastname"});

        assertEquals("UPDATE tableName SET firstname = :firstname AND lastname = :lastname WHERE id = :id", result);
    }

}


