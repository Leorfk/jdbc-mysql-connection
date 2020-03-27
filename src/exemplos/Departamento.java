package exemplos;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;

public class Departamento {

    public static void consultarDepartamentos(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

            try {
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select * from department");

            while (rs.next()){
                System.out.println(rs.getInt("Id") + ":" + rs.getString("Name"));
            }
        } catch (
        SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            //DB.closeConnection();
        }
    }

    public static void deletarDepartamento(){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "DELETE  FROM department WHERE Id= ?"
            );

            ps.setInt(1, 6);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Rows affected = " + rowsAffected);
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }
}
