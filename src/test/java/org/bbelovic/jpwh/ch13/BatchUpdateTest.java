package org.bbelovic.jpwh.ch13;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BatchUpdateTest {

    final String INSERT = "insert into items (name, version) values (?, ?)";
    final List<String> ITEM_NAMES = Arrays.asList("item x", "item y", "item z");
    final List<String> EMPTY = Collections.emptyList();

    @Test
    public void batchUpdateTest() throws Exception {
        DatasourceConnectionProvider provider = new DatasourceConnectionProvider();
        PreparedStatement ps = null;
        try (Connection c = provider.getConnection();
             ) {
            c.setAutoCommit(false);
            ps = c.prepareStatement(INSERT);
            for (String each: ITEM_NAMES) {
                ps.setString(1, each);
                ps.setLong(2, 0L);
                ps.addBatch();
            }
            int[] result = ps.executeBatch();
            c.commit();
            System.out.println("Result=" + Arrays.toString(result));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

    }
}
