package exemplos;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Transacoes {

    public static void realizarTransacao(){
        Connection conn = null;

        Statement st = null;

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);;
            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET  BaseSalary = 12090 WHERE DepartmentId = 1");

            int x = 1;
            if(x < 2){
                throw new SQLException("Internal Server Error");
            }
            int rows2 = st.executeUpdate("UPDATE seller SET  BaseSalary = 13090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("Row 1 = " + rows1);
            System.out.println("Row 2 = " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback! Caused by: " + e.getMessage());
            }
        }finally {
            DB.closeStatement(st);
        }
    }
}
