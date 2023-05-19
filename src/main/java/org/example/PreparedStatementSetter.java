package org.example;

import java.sql.PreparedStatement;

public interface PreparedStatementSetter {
    void setter(PreparedStatement pstmt);
}
