package com.example.usablesecurityproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Queries {
	private static String dbURL;
	private static String dbUsername = "root";
	private static String dbPassword = "root";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "OnlineExams";
	private static Connection con;
	static ArrayList<Exam> data = new ArrayList<Exam>();

	public static void connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.example.usablesecurityproject.Driver");

		con = DriverManager.getConnection(dbURL, p);

	}

	public static void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {
			AlertBox.display("SQL statement is not executed!");

		}

	}

	public static ObservableList<Student> getStudents() throws ClassNotFoundException, SQLException {
		ObservableList<Student> studentsOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select ID,Name,PhoneNumber,Email from Students order by ID";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			int id = Integer.parseInt(rs.getString(1));
			int phoneNumber = Integer.parseInt(rs.getString(3));

			Student student = new Student(id, rs.getString(2), phoneNumber, rs.getString(4));

			studentsOb.add(student);

		}
		rs.close();
		stmt.close();

		con.close();
		return studentsOb;

	}

	public static ObservableList<Teacher> getTeachers() throws ClassNotFoundException, SQLException {
		ObservableList<Teacher> teachersOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select ID,Name,Email from teachers order by ID";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			
			int id = Integer.parseInt(rs.getString(1));

			Teacher teacher = new Teacher(id, rs.getString(2), rs.getString(3));

			teachersOb.add(teacher);

		}
		rs.close();
		stmt.close();

		con.close();
		return teachersOb;

	}

	public static ObservableList<Exam> getExams() throws ClassNotFoundException, SQLException {
		ObservableList<Exam> examsOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select * from Exams;";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString(1));
			int teacherId = Integer.parseInt(rs.getString(4));
			Exam e = new Exam(id, rs.getString(2), rs.getString(3), teacherId);
			
			examsOb.add(e);

		}
		rs.close();
		stmt.close();

		con.close();
		return examsOb;

	}

	public static ObservableList<Question> getQuestions(Exam exam) throws ClassNotFoundException, SQLException {
		ObservableList<Question> questionsOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select * from Questions where ExamID=" + exam.getId() + ";";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			int questionId = Integer.parseInt(rs.getString(1));
			int examId = Integer.parseInt(rs.getString(4));
			int trueAnswer = Integer.parseInt(rs.getString(3));
			Question q = new Question(questionId, examId, trueAnswer, rs.getString(2));
			questionsOb.add(q);

		}
		rs.close();
		stmt.close();

		con.close();
		return questionsOb;

	}

	public static ObservableList<Fee> getStudentFees() throws ClassNotFoundException, SQLException {
		ObservableList<Fee> feesOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select FeeID,amountPaid,StudentID,ManagerSSN from fees where TeacherID is Null" + ";";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString(1));
			int amountPaid = Integer.parseInt(rs.getString(2));
			int studentId = Integer.parseInt(rs.getString(3));
			int managerSSN = Integer.parseInt(rs.getString(4));
			Fee f = new Fee(id, amountPaid, studentId, managerSSN);

			feesOb.add(f);

		}
		rs.close();
		stmt.close();

		con.close();
		return feesOb;

	}

	public static ObservableList<Fee> getTeacherFees() throws ClassNotFoundException, SQLException {
		ObservableList<Fee> feesOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select FeeID,amountPaid,TeacherID,ManagerSSN from fees where StudentID is Null" + ";";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString(1));
			int amountPaid = Integer.parseInt(rs.getString(2));
			int taecherID = Integer.parseInt(rs.getString(3));
			int managerSSN = Integer.parseInt(rs.getString(4));
			Fee f = new Fee(id, amountPaid, taecherID, managerSSN);

			feesOb.add(f);

		}
		rs.close();
		stmt.close();

		con.close();
		return feesOb;

	}

	public static void insertTeacher(Teacher teacher) {
		try {
			connectDB();

			
			ExecuteStatement("Insert into teachers (Name,Email,Password,budget) values('" + teacher.getName() + "','"
					+ teacher.email + "','" + teacher.getPassword() + "',0);");

			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("SQL statement is not executed!");
		}
	}

	public static void insertStudent(Student student) {
		try {
			connectDB();
			

			ExecuteStatement("Insert into students (password,Name,PhoneNumber,Email,budget) values ('"
					+ student.getPassword() + "','" + student.getName() + "'," + student.getPhoneNumber() + ",'"
					+ student.email + "'," + student.budget + ");");

			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("SQL statement is not executed!");
		}
	}

	public static Manager getManager() throws ClassNotFoundException, SQLException {
		connectDB();

		String SQL = "select * from manager where SSN=1";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		Manager m = null;
		while (rs.next()) {

			int SSN = Integer.parseInt(rs.getString(1));
			int phoneNumber = Integer.parseInt(rs.getString(2));
			String name = rs.getString(3);
			int budget = Integer.parseInt(rs.getString(4));
			String password = rs.getString(5);
			m = new Manager(SSN, phoneNumber, name, budget, password);
			
		}
		rs.close();
		stmt.close();

		con.close();
		return m;

	}

	public static Teacher getTeacher(int id) throws ClassNotFoundException, SQLException {
		connectDB();

		String SQL = "select * from teachers where ID=" + id;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		Teacher t = null;
		while (rs.next()) {

			String name = rs.getString(2);

			int budget = Integer.parseInt(rs.getString(4));
			String password = rs.getString(5);
			t = new Teacher(id, name, rs.getString(3), budget, password);

		}
		rs.close();
		stmt.close();

		con.close();
		return t;

	}

	public static void updateBudget(int teacherId, int newBudget)
			throws SQLException, ClassNotFoundException, ParseException {

		connectDB();

		ExecuteStatement("UPDATE teachers set budget = " + newBudget + " where ID = " + teacherId + "");

		con.close();

	}

	public static void report1(TableView tv) throws SQLException, ClassNotFoundException, ParseException {
		tv.getItems().clear();

		String SQL;

		connectDB();

		SQL = "select t.*  from student2exam s , exams e , teachers t where \r\n" + "s.ExamID = e.ID \r\n" + "and \r\n"
				+ "e.TeacherID = t.ID\r\n" + "and \r\n" + "s.StudentResult >  4";

		// (int id, String name, String email, int budget)

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			Teacher t = new Teacher(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					Integer.parseInt(rs.getString(4)), rs.getString(5));
			tv.getItems().add(t);
		}
		rs.close();
		stmt.close();

		con.close();

	}

	public static void report2(TableView tv) throws SQLException, ClassNotFoundException, ParseException {
		tv.getItems().clear();

		String SQL;

		connectDB();

		SQL = "select q.* from questions q , exams e where e.id = q.ExamID and e.type = 'quiz' and e.Subject='Java'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			Question t = new Question(Integer.parseInt(rs.getString(1)), rs.getString(2),
					Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)));
			tv.getItems().add(t);
		}
		rs.close();
		stmt.close();
		con.close();

	}

	public static int getMaxIdForExam() throws SQLException, ClassNotFoundException, ParseException {

		String SQL;

		connectDB();

		SQL = "select max(id) from exams";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		int i = -1;
		while (rs.next()) {
			if (rs.getString(1) == null) {
				return 999;
			}
			i = Integer.parseInt(rs.getString(1));

		}

		rs.close();
		stmt.close();

		con.close();
		if (i == -1) {
			return 0;

		}
		return i;

	}

	public static void getData() throws SQLException, ClassNotFoundException, ParseException {

		String SQL;

		connectDB();

		SQL = "select * from exams order by ID";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			Exam exam = new Exam(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					Integer.parseInt(rs.getString(4)));

			data.add(exam);
		}

		rs.close();
		stmt.close();

		con.close();

	}

	public static void insertData(Exam exam) {
		try {

			connectDB();
			

			ExecuteStatement("insert into exams (Type,Subject,TeacherID) values (" + "'" + exam.getType() + "'" + ",'"
					+ exam.getSubject() + "'," + exam.teacherId + ")");
			con.close();
		} catch (ClassNotFoundException e) {
			AlertBox.display("SQL statement is not executed!");
		} catch (SQLException e1) {
			AlertBox.display("SQL statement is not executed!");
		}

	}

	public static void insertData(ArrayList<Question> ar, int examId) throws ClassNotFoundException, SQLException {
		connectDB();

		for (int i = 0; i < ar.size(); i++) {

			int id = ar.get(i).id - 1;
			

			ExecuteStatement("insert into Questions values(" + id + "," + "'" + ar.get(i).text + "' ,"
					+ ar.get(i).answer + "," + examId + ");");

		}
		con.close();
	}

	public static int getMaxQId() throws SQLException, ClassNotFoundException, ParseException {

		String SQL;

		connectDB();

		SQL = "select max(QuestionID) from questions";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		int i = -1;
		while (rs.next()) {
			if (rs.getString(1) == null) {
				return 0;

			}
			i = Integer.parseInt(rs.getString(1));

		}

		

		rs.close();
		stmt.close();

		con.close();

		if (i != -1)
			return i;
		else
			return -1;
	}

	public static ObservableList<Exam> getExams(Teacher t) throws ClassNotFoundException, SQLException {
		ObservableList<Exam> examsOb = FXCollections.observableArrayList();
		String SQL;
		connectDB();
		SQL = "select * from Exams where TeacherId = " + t.getId() + ";";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString(1));
			int teacherId = Integer.parseInt(rs.getString(4));

			Exam e = new Exam(id, rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)));

			
			examsOb.add(e);
		}
		rs.close();
		stmt.close();
		con.close();
		return examsOb;
	}

	public static Teacher getTeacherForSignIn(int id, String password, String name)
			throws ClassNotFoundException, SQLException {
		connectDB();

		String SQL = "select * from teachers t where t.ID=" + id + " and t.Password='" + password + "' and t.Name='"
				+ name + "';";
		// select * from teachers t where t.ID=3002 and t.Password=1234 and t.Name=
		// muath
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		Teacher t = null;

		while (rs.next()) {
			int teacherId = Integer.parseInt(rs.getString(1));
			String teacherName = rs.getString(2);
			String teacherEmail = rs.getString(3);
			int teacherBudget = Integer.parseInt(rs.getString(4));
			String teacherPassword = rs.getString(5);
			t = new Teacher(teacherId, teacherName, teacherEmail, teacherBudget, teacherPassword);

		}
		rs.close();
		stmt.close();

		con.close();
		return t;

	}

	public static Student getStudentForSignIn(String password, String userName)
			throws ClassNotFoundException, SQLException {
		connectDB();

		String sql = "select * from  students s where s.Password='" + password + "' and s.Name='"
				+ userName + "';";

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		Student student = null;

		while (resultSet.next()) {
			int id = Integer.parseInt(resultSet.getString(1));
			int phoneNumber = Integer.parseInt(resultSet.getString(4));
			int budget = Integer.parseInt(resultSet.getString(6));
			String answer = resultSet.getString(7) == null ? "" : resultSet.getString(7);
			String question = resultSet.getString(8) == null ? "" : resultSet.getString(8);
			student = new Student(id, password, userName, phoneNumber, resultSet.getString(5), budget);
			student.setAnswer(answer);
			student.setQuestion(question);

		}
		resultSet.close();
		statement.close();

		con.close();
		return student;

	}

	public static Student getStudentForSignInByAnswerAQuestion(String answer, String question)
			throws ClassNotFoundException, SQLException {
		connectDB();

		String sql = "select * from  students s where s.answer='" + answer + "' and s.question='"
				+ question + "';";

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		Student student = null;

		while (resultSet.next()) {
			int id = Integer.parseInt(resultSet.getString(1));
			int phoneNumber = Integer.parseInt(resultSet.getString(4));
			int budget = Integer.parseInt(resultSet.getString(6));

			student = new Student(id, answer, question, phoneNumber, resultSet.getString(5), budget);

		}
		resultSet.close();
		statement.close();

		con.close();
		return student;

	}

	public static void upgradeStudentsBudget(int studentId, int newBudget) {
		try {

			connectDB();

			ExecuteStatement("update students s set s.budget=" + newBudget + " where s.ID=" + studentId + ";");

			con.close();
		} catch (ClassNotFoundException e) {
			AlertBox.display("SQL statement is not executed!");
		} catch (SQLException e1) {
			AlertBox.display("SQL statement is not executed!");
		}
	}

	public static int getMaxIdForStudents() throws SQLException, ClassNotFoundException, ParseException {

		String SQL;

		connectDB();

		SQL = "select max(ID) from students";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		int i = -1;
		while (rs.next()) {
			if (rs.getString(1) == null) {
				return 999;
			}
			i = Integer.parseInt(rs.getString(1));

		}

		rs.close();
		stmt.close();

		con.close();
		if (i == -1) {
			return 0;

		}
		return i;

	}

	public static Exam getRandomExam(String type, String subject)
			throws SQLException, ClassNotFoundException, ParseException {

		/* ; */

		String SQL;

		connectDB();

		SQL = "SELECT * FROM exams e where  e.type = '" + type + "' and e.subject ='" + subject
				+ "'  ORDER BY RAND()LIMIT 1";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			if (rs.getString(1) == null) {
				return null;

			}
			Exam i = new Exam();

			i.id = Integer.parseInt(rs.getString(1));
			i.type = rs.getString(2);
			i.subject = rs.getString(3);
			i.teacherId = Integer.parseInt(rs.getString(4));
			rs.close();
			stmt.close();

			con.close();
			return i;

		}

		rs.close();
		stmt.close();

		con.close();

		return null;

	}

	public static ArrayList<Question> getQuestionsArrayList(Exam exam) throws ClassNotFoundException, SQLException {
		ArrayList<Question> questionsList = new ArrayList<Question>();

		String SQL;

		connectDB();

		SQL = "select * from Questions where ExamID=" + exam.getId() + ";";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			int questionId = Integer.parseInt(rs.getString(1));
			int examId = Integer.parseInt(rs.getString(4));
			int trueAnswer = Integer.parseInt(rs.getString(3));
			Question q = new Question(questionId, examId, trueAnswer, rs.getString(2));
			questionsList.add(q);

		}
		rs.close();
		stmt.close();

		con.close();
		return questionsList;

	}

	public static void insertExamResult(int studentResult, int StudentID, int ExamID, int totalMark)
			throws ClassNotFoundException, SQLException {

		connectDB();

		ExecuteStatement("insert into student2exam (StudentResult,studentID,ExamID,totalExamsMark) values ("
				+ studentResult + "," + StudentID + "," + ExamID + "," + totalMark + ")");
		con.close();

	}

	public static void updateBudgetforStudent(int studentId, int newBudget)
			throws SQLException, ClassNotFoundException, ParseException {

		connectDB();

		ExecuteStatement("UPDATE students set budget = " + newBudget + " where ID = " + studentId);

		con.close();

	}

	public static void report1Updated(TableView<Teacher> tv)
			throws SQLException, ClassNotFoundException, ParseException {
		tv.getItems().clear();

		String SQL;

		connectDB();

		SQL = "select t.* , e.type , s.studentresult,s.totalExamsMark from student2exam s , exams e , teachers t where s.ExamID = e.ID and  e.TeacherID = t.ID";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			int totalexamResult = Integer.parseInt(rs.getString(8));
			int studentsResult = Integer.parseInt(rs.getString(7));

			if (totalexamResult == studentsResult) {
				Teacher t = new Teacher(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
						Integer.parseInt(rs.getString(4)), rs.getString(5));

				tv.getItems().add(t);
			}
		}
		rs.close();
		stmt.close();

		con.close();

	}

	public static void savePersonalQuestion(Student student) throws SQLException, ClassNotFoundException {

		connectDB();

		ExecuteStatement("UPDATE students set answer = '" + student.getAnswer() + "' where ID = " + student.getId());
		ExecuteStatement("UPDATE students set question = '" + student.getQuestion() + "' where ID = " + student.getId());

		con.close();

	}
}