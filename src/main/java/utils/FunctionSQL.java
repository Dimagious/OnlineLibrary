package utils;

import java.sql.SQLException;

@FunctionalInterface
public interface FunctionSQL<T, R> {
    R apply(T obj) throws SQLException;
}