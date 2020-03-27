package exemplos;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Vendedor {

    public static void inserirVendedor(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DB.getConnection();

            ps = conn.prepareStatement(
                    "INSERT INTO seller" +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentId)" +
                            "VALUES" +
                            "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, "Ozzy Osbourne");
            ps.setString(2, "ozzy@bat.gov");
            ps.setDate(3, new java.sql.Date(sdf.parse("21/04/1940").getTime()));
            ps.setDouble(4, 3000.00);
            ps.setInt(5, 4);

            int rowsAffected = ps.executeUpdate();
            //System.out.println("Rows affected: " + rowsAffected);
            if (rowsAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("ID = " + id);
                }
            }else {
                System.out.println("No rows affected");
            }

        } catch (SQLException | ParseException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);;
            //DB.closeConnection();
        }
    }
}
