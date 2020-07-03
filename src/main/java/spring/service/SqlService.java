package spring.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlService {
    public static Map<String, Object> getNamedParameters(String key, Object value) {
        return Collections.singletonMap(key, value);
    }

    public static String getParameters(String param) {
        return "%" + param + "%";
    }

    public static String prepareSqlQueryForFindAll(String table) {
        return String.format("SELECT * FROM %s", table);
    }

    public static String prepareSqlQueryForFindById(String table) {
        return String.format("SELECT * FROM %s WHERE id = :id", table);
    }

    public static String prepareSqlQueryForDelete(String table) {
        return String.format("DELETE FROM %s WHERE id = :id", table);
    }

    public static String prepareSqlQueryForInsert(String table, String[] params) {
        String columns = prepareSqlQuery(params, true);
        String values = prepareSqlQuery(params, false);
        return String.format("INSERT INTO %s %s VALUES %s", table, columns, values);
    }

    public static String prepareSqlQueryForUpdate(String table, String[] params) {
        String updateString = prepareSqlQuery(params);
        return String.format("UPDATE %s SET %s WHERE id = :id", table, updateString);
    }

    private static String prepareSqlQuery(String[] params) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(params)
                .forEach(s -> {
                    if (sb.length() > 1) {
                        sb.append(" AND ");
                    }
                    sb.append(s).append(" = :").append(s);
                });
        return sb.toString();
    }

    private static String prepareSqlQuery(String[] params, boolean isColumn) {
        String delimiter;
        if (isColumn) { delimiter = ", "; } else { delimiter = ", :"; }
        String openBracket;
        if (isColumn) { openBracket = "("; } else { openBracket = "(:"; }
        return Arrays.stream(params)
                .collect(Collectors.joining(delimiter, openBracket, ")"));
    }

}
