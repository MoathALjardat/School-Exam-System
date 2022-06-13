package com.example.usablesecurityproject;

import java.sql.SQLException;
import java.text.ParseException;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelpingPanes {
	public static Pane displayingStudentsInManeger(Stage primaryStage) {
		Image image = new Image("file:image.jpg");

		ImageView imageView = new ImageView(image);
		Button logOutButton = new Button("Log Out");
		logOutButton.setLayoutX(734);
		logOutButton.setLayoutY(20);
		logOutButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.programMainScene(primaryStage));
		});
		TableView<Student> table = new TableView<Student>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Student, String> nameColumn = new TableColumn<Student, String>("Student Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setMinWidth(173.0);
		TableColumn<Student, Integer> phoneNumberColumn = new TableColumn<Student, Integer>("Phone Number");

		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		phoneNumberColumn.setMinWidth(130);
		TableColumn<Student, Integer> idColumn = new TableColumn<Student, Integer>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(130);
		TableColumn<Student, String> emailColunmn = new TableColumn<Student, String>("Email");
		emailColunmn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailColunmn.setMinWidth(246);
		ObservableList<Student> studentsOb;
		try {
			studentsOb = Queries.getStudents();
			table.setItems(studentsOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		table.getColumns().addAll(idColumn, nameColumn, phoneNumberColumn, emailColunmn);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getStudents());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Label label = new Label("Students");
		label.setLayoutX(350);
		label.setLayoutY(35);
		label.setFont(new Font(20));

		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, logOutButton);

		return pane;

	}

	public static Pane displayingTeachersInManager(Stage primaryStage) {
		Button logOutButton = new Button("Log Out");
		logOutButton.setLayoutX(734);
		logOutButton.setLayoutY(20);
		logOutButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.programMainScene(primaryStage));
		});
		Image image = new Image("file:image.jpg");

		ImageView imageView = new ImageView(image);

		TableView<Teacher> table = new TableView<Teacher>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Teacher, String> nameColumn = new TableColumn<Teacher, String>("Teacher's Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
		nameColumn.setMinWidth(173.0);

		TableColumn<Teacher, Integer> idColumn = new TableColumn<Teacher, Integer>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("id"));

		idColumn.setMinWidth(130);
		TableColumn<Teacher, String> emailColunmn = new TableColumn<Teacher, String>("Email");
		emailColunmn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailColunmn.setMinWidth(246);
		ObservableList<Teacher> teachersOb;
		try {
			teachersOb = Queries.getTeachers();
			table.setItems(teachersOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		table.getColumns().addAll(idColumn, nameColumn, emailColunmn);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getTeachers());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Label label = new Label("Teachers");
		label.setLayoutX(350);
		label.setLayoutY(35);
		label.setFont(new Font(20));
		Button insertButton = new Button("Insert Teachers");
		insertButton.setLayoutX(54);
		insertButton.setLayoutY(503);

		insertButton.setOnAction(e -> {
			HelpingWindows.insertTeacherWindow(table);

		});
		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, insertButton, logOutButton);

		return pane;

	}

	public static Pane displayExamsInManager(Stage primaryStage) {
		Button logOutButton = new Button("Log Out");
		logOutButton.setLayoutX(734);
		logOutButton.setLayoutY(20);
		logOutButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.programMainScene(primaryStage));
		});
		Image image = new Image("file:image.jpg");

		ImageView imageView = new ImageView(image);

		TableView<Exam> table = new TableView<Exam>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Exam, String> typeColumn = new TableColumn<Exam, String>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		typeColumn.setMinWidth(130);
		TableColumn<Exam, Integer> teacherIdColumn = new TableColumn<Exam, Integer>("Teacher id ");

		teacherIdColumn.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
		teacherIdColumn.setMinWidth(130);
		TableColumn<Exam, Integer> idColumn = new TableColumn<Exam, Integer>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(130);
		TableColumn<Exam, String> subjectColunmn = new TableColumn<Exam, String>("Subject");
		subjectColunmn.setCellValueFactory(new PropertyValueFactory<>("subject"));
		subjectColunmn.setMinWidth(130);
		ObservableList<Exam> examsOb;
		try {
			examsOb = Queries.getExams();
			table.setItems(examsOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		table.getColumns().addAll(idColumn, subjectColunmn, typeColumn, teacherIdColumn);
		TableMethods.addButtonToTable(primaryStage, table);
		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {

			try {
				table.setItems(Queries.getExams());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Label label = new Label("Exams");
		label.setLayoutX(350);
		label.setLayoutY(35);
		label.setFont(new Font(20));

		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, logOutButton);

		return pane;

	}

	public static Pane displayStudentBudget(Stage primaryStage) {
		Button logOutButton = new Button("Log Out");
		logOutButton.setLayoutX(734);
		logOutButton.setLayoutY(20);
		logOutButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.programMainScene(primaryStage));
		});
		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);

		TableView<Fee> table = new TableView<Fee>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Fee, Integer> idColumn = new TableColumn<Fee, Integer>("Id");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(130);

		TableColumn<Fee, Integer> studentIdColumn = new TableColumn<Fee, Integer>("student ID");

		studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
		studentIdColumn.setMinWidth(130);

		TableColumn<Fee, Integer> amountPaidColumn = new TableColumn<Fee, Integer>("Amount Paid");

		amountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
		amountPaidColumn.setMinWidth(130);

		TableColumn<Fee, Integer> manageerColumn = new TableColumn<Fee, Integer>("Manager SSN");

		manageerColumn.setCellValueFactory(new PropertyValueFactory<>("managerSSN"));

		manageerColumn.setMinWidth(130);

		ObservableList<Fee> feesOb;
		try {
			feesOb = Queries.getStudentFees();
			table.setItems(feesOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		table.getColumns().addAll(idColumn, amountPaidColumn, studentIdColumn, manageerColumn);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getStudentFees());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Label label = new Label("Student Fees");
		label.setLayoutX(335);
		label.setLayoutY(35);
		label.setFont(new Font(20));

		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, logOutButton);

		return pane;

	}

	public static Pane displayTeacherBudget(Stage primaryStage) {
		Button logOutButton = new Button("Log Out");
		logOutButton.setLayoutX(734);
		logOutButton.setLayoutY(20);
		logOutButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.programMainScene(primaryStage));
		});
		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);

		TableView<Fee> table = new TableView<Fee>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Fee, Integer> idColumn = new TableColumn<Fee, Integer>("Id");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(130);

		TableColumn<Fee, Integer> teacherIdColumn = new TableColumn<Fee, Integer>("Teacher ID");

		teacherIdColumn.setCellValueFactory(new PropertyValueFactory<>("teacherId"));

		teacherIdColumn.setMinWidth(130);

		TableColumn<Fee, Integer> amountPaidColumn = new TableColumn<Fee, Integer>("Amount Paid");

		amountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
		amountPaidColumn.setMinWidth(130);

		TableColumn<Fee, Integer> manageerColumn = new TableColumn<Fee, Integer>("Manager SSN");

		manageerColumn.setCellValueFactory(new PropertyValueFactory<>("managerSSN"));

		manageerColumn.setMinWidth(130);

		ObservableList<Fee> feesOb;
		try {
			feesOb = Queries.getTeacherFees();
			table.setItems(feesOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		table.getColumns().addAll(idColumn, amountPaidColumn, teacherIdColumn, manageerColumn);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getTeacherFees());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Label label = new Label("Teachers Fees");
		label.setLayoutX(335);
		label.setLayoutY(35);
		label.setFont(new Font(20));

		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, logOutButton);

		return pane;

	}

	public static Pane report1Pane(Stage primaryStage) {
		Button logOutButton = new Button("Log Out");
		logOutButton.setLayoutX(734);
		logOutButton.setLayoutY(20);
		logOutButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.programMainScene(primaryStage));
		});
		Image image = new Image("file:image.jpg");

		ImageView imageView = new ImageView(image);

		TableView<Teacher> table = new TableView<Teacher>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Teacher, String> nameColumn = new TableColumn<Teacher, String>("Teacher's Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
		nameColumn.setMinWidth(173.0);

		TableColumn<Teacher, Integer> idColumn = new TableColumn<Teacher, Integer>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("id"));

		idColumn.setMinWidth(130);
		TableColumn<Teacher, String> emailColunmn = new TableColumn<Teacher, String>("Email");
		emailColunmn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailColunmn.setMinWidth(246);

		try {
			Queries.report1Updated(table);
		} catch (ClassNotFoundException | SQLException | ParseException e2) {
			AlertBox.display("Error in Report 1");
		}

		table.getColumns().addAll(idColumn, nameColumn, emailColunmn);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {
			try {
				Queries.report1Updated(table);
			} catch (ClassNotFoundException | SQLException | ParseException e2) {
				AlertBox.display("Error in Report 1");
			}

		});
		Label label = new Label("Teachers who wrote exams and some studetns got full mark in it");

		label.setLayoutX(54);
		label.setLayoutY(35);
		label.setFont(new Font(20));

		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, logOutButton);

		return pane;

	}

	

	public static Pane report2NewPane(Stage primaryStage) {
		Button logOutButton = new Button("Log Out");
		logOutButton.setLayoutX(734);
		logOutButton.setLayoutY(20);
		logOutButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.programMainScene(primaryStage));
		});
		Image image = new Image("file:image.jpg");
		
		ImageView imageView = new ImageView(image);

		TableView<Question> table = new TableView<Question>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Question, Integer> questionIdColumn = new TableColumn<Question, Integer>("Question ID");
		questionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		questionIdColumn.setMinWidth(130);
		TableColumn<Question, Integer> examIdColumn = new TableColumn<Question, Integer>("Exam's ID");
		examIdColumn.setCellValueFactory(new PropertyValueFactory<>("examId"));
		examIdColumn.setMinWidth(130);

		try {
			Queries.report2(table);
		} catch (ClassNotFoundException | SQLException | ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		table.getColumns().addAll(questionIdColumn, examIdColumn);
		TableMethods.addButtonToQuestionsTable(primaryStage, table);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {
			try {
				Queries.report2(table);
			} catch (ClassNotFoundException | SQLException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Label label = new Label("All Questions in java Quizez");

		label.setLayoutX(54);
		label.setLayoutY(35);
		label.setFont(new Font(20));
		
		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, logOutButton);

		return pane;
		
	}
}
