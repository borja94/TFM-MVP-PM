/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class StudentDto {

	public ResultSet Insert(Student student) {
		Connection conexion = null;
		ResultSet aux = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");

			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			String sql = "INSERT INTO STUDENT (NAME,SURNAME) VALUES(?,?)";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, student.getName());
			sentencia1.setString(2, student.getSurname());

			sentencia1.executeUpdate();
			aux = sentencia1.getGeneratedKeys();
			int id = -1;
			if (aux.next()) {
				id = aux.getInt(1);
			}

			for (Subject subject : student.getSubjectCollection()) {
				InsertSubjectStudent(id, subject.getId());
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

		return aux;
	}

	public void Update(Student student) {
		Connection conexion = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");

			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			String sql = "UPDATE STUDENT  SET NAME = ?, SURNAME = ? WHERE ID= ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, student.getName());
			sentencia1.setString(2, student.getSurname());
			sentencia1.setInt(3, student.getId());

			RemoveAllStudentSubjects(student.getId());
			for (Subject subject : student.getSubjectCollection()) {
				InsertSubjectStudent(student.getId(), subject.getId());
			}

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

	public List<Student> GetAll() {

		List<Student> result = new ArrayList<Student>();
		Connection conexion = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");

			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = sentencia.executeQuery("SELECT * FROM STUDENT ");

			rs.afterLast();
			while (rs.previous()) {

				Student student = new Student(rs.getInt("ID"), rs.getString(2), rs.getString(3));
				String sql = "SELECT * FROM SUBJECT S " + "INNER JOIN STUDENT_SUBJECT SS " + "ON S.ID = SS.ID_SUBJECT "
						+ "WHERE SS.ID_STUDENT = ?";

				PreparedStatement sentenciaSubject = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				sentenciaSubject.setInt(1, rs.getInt("ID"));

				ResultSet rsSuebjects = sentenciaSubject.executeQuery();
				while (rsSuebjects.next()) {
					student.getSubjectCollection().add(new Subject(rsSuebjects.getInt("ID"),
							rsSuebjects.getString("TITLE"), rsSuebjects.getInt("COURSE")));
				}
				result.add(student);

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

	public Student Get(int id) {
		Connection conexion = null;
		Student student = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			String sql = "SELECT S.* , SUB.ID AS SUBJECT_ID , SUB.TITLE , SUB.COURSE " + "FROM STUDENT S "
					+ "LEFT JOIN STUDENT_SUBJECT SS " + "ON S.ID = SS.ID_STUDENT " + "LEFT JOIN SUBJECT SUB "
					+ "ON SS.ID_SUBJECT = SUB.ID " + " WHERE S.ID = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			sentencia1.setInt(1, id);

			ResultSet rs = sentencia1.executeQuery();
			student = new Student();
			List<Subject> subjectCollection = new ArrayList<>();
			if (rs.next()) {
				student = new Student(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SURNAME"));

				do {
					if (rs.getString("TITLE") != null) {
						subjectCollection
								.add(new Subject(rs.getInt("SUBJECT_ID"), rs.getString("TITLE"), rs.getInt("COURSE")));
					}
				} while (rs.next());
			}
			student.setSubjectCollection(subjectCollection);

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
		return student;
	}

	public void Remove(int id) {
		Connection conexion = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);

			String sql = "DELETE FROM  STUDENT WHERE ID = ?";
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

	public void RemoveAllStudentSubjects(int idStudent) {
		Connection conexion = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);
			String sql = "DELETE FROM  STUDENT_SUBJECT WHERE ID_STUDENT = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql);
			sentencia1.setInt(1, idStudent);

			sentencia1.executeUpdate();
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

	public void InsertSubjectStudent(int idStudent, int idSubject) {

		Connection conexion = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);
			String sql = "INSERT INTO STUDENT_SUBJECT (ID_SUBJECT,ID_STUDENT) VALUES(?,?)";
			PreparedStatement sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, idSubject);
			sentencia1.setInt(2, idStudent);

			sentencia1.executeUpdate();
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
