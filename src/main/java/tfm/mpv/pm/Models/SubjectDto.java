package tfm.mpv.pm.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borja
 */
public class SubjectDto {

	public ResultSet Insert(Subject subject) {
		Connection conexion = null;
		ResultSet aux = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");

			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);
			conexion.setAutoCommit(false);

			String sql = "INSERT INTO SUBJECT (TITLE,COURSE) VALUES(?,?)";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, subject.getTitle());
			sentencia1.setInt(2, subject.getCourse());

			sentencia1.executeUpdate();
			aux = sentencia1.getGeneratedKeys();

			conexion.commit();

			sentencia1.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return aux;
	}

	public List<Subject> GetAll() {

		List<Subject> result = new ArrayList<>();
		Connection conexion = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");

			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM SUBJECT ");

			while (rs.next()) {
				result.add(new Subject(rs.getInt("ID"), rs.getString("TITLE"), rs.getInt("COURSE")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return result;
	}

	public Subject Get(int id) {
		Connection conexion = null;
		Subject subject = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			String sql = "SELECT * FROM SUBJECT WHERE ID = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			sentencia1.setInt(1, id);

			ResultSet rs = sentencia1.executeQuery();
			subject = new Subject();
			if (rs.next()) {
				subject = new Subject(rs.getInt("ID"), rs.getString("TITLE"), rs.getInt("COURSE"));
			}
			sentencia1.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return subject;
	}

	public void Remove(int id) {
		Connection conexion = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			String sql = "DELETE FROM  SUBJECT WHERE ID = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, id);

			sentencia1.executeUpdate();
			sentencia1.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void Update(Subject subject) {
		Connection conexion = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");

			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			String sql = "UPDATE SUBJECT  SET TITLE = ?, COURSE = ? WHERE ID= ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, subject.getTitle());
			sentencia1.setInt(2, subject.getCourse());
			sentencia1.setInt(3, subject.getId());

			sentencia1.executeUpdate();

			sentencia1.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conexion != null)
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
