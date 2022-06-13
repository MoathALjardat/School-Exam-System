package com.example.usablesecurityproject;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Scenes {
	public static Scene manegarMainScene(Stage primaryStage) {

		TabPane tabPane = new TabPane();
		Tab studentsTab = new Tab("Students");
		Tab teachersTab = new Tab("Teachers");
		Tab feesTab = new Tab("Fees");
		Tab examsTab = new Tab("exams");
		Tab additionalTab = new Tab("additional Tables");

		studentsTab.setClosable(false);
		teachersTab.setClosable(false);
		feesTab.setClosable(false);
		examsTab.setClosable(false);
		studentsTab.setContent(HelpingPanes.displayingStudentsInManeger(primaryStage));
		teachersTab.setContent(HelpingPanes.displayingTeachersInManager(primaryStage));
		examsTab.setContent(HelpingPanes.displayExamsInManager(primaryStage));

		// -------------------fees tab
		TabPane insideFeesPane = new TabPane();
		Tab studentFees = new Tab("Display students' tranactions");
		Tab teachersFees = new Tab("Display teachers' tranactions");

		insideFeesPane.getTabs().addAll(studentFees, teachersFees);
		teachersFees.setContent(HelpingPanes.displayTeacherBudget(primaryStage));

		studentFees.setContent(HelpingPanes.displayStudentBudget(primaryStage));
		studentFees.setClosable(false);

		feesTab.setContent(insideFeesPane);
		// --------------------
		// reports tabs
		TabPane insideAdditional = new TabPane();
		Tab report1Tab = new Tab("Query 1");
		Tab report2Tab = new Tab("Query 2");

		report1Tab.setContent(HelpingPanes.report1Pane(primaryStage));
		report2Tab.setContent(HelpingPanes.report2NewPane(primaryStage));
		insideAdditional.getTabs().addAll(report1Tab, report2Tab);

		additionalTab.setContent(insideAdditional);

		// -----------------------
		tabPane.getTabs().addAll(studentsTab, teachersTab, feesTab, examsTab, additionalTab);
		Scene scene = new Scene(tabPane, 800, 600);
		return scene;

	}

	public static Scene questionsScene(Stage primaryStage, Exam exam) {

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

		ObservableList<Question> questionsOb;
		try {
			questionsOb = Queries.getQuestions(exam);
			table.setItems(questionsOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		table.getColumns().addAll(questionIdColumn, examIdColumn);
		TableMethods.addButtonToQuestionsTable(primaryStage, table);

		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getQuestions(exam));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		Label label = new Label("Questions");

		label.setLayoutX(350);
		label.setLayoutY(35);
		label.setFont(new Font(20));
		Button backButton = new Button("back");
		backButton.setLayoutX(54);
		backButton.setLayoutY(503);
		backButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.manegarMainScene(primaryStage));

		});
		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, refreshButton, label, backButton);

		Scene scene = new Scene(pane, 800, 600);
		return scene;

	}

	public static Scene logInAsTeacherScene(Stage AnwarsStage) {
		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);

		Label idLabel = new Label("ID");
		idLabel.setLayoutX(6.0);
		idLabel.setLayoutY(70.0);
		idLabel.setPrefHeight(35.0);
		idLabel.setPrefWidth(42.0);

		Label nameLabel = new Label("Name");
		nameLabel.setLayoutX(3.0);
		nameLabel.setLayoutY(127.0);
		nameLabel.setPrefHeight(17.0);
		nameLabel.setPrefWidth(49.0);

		Label PasswordLabel = new Label("Password");
		PasswordLabel.setLayoutX(6.0);
		PasswordLabel.setLayoutY(172.0);
		PasswordLabel.setPrefHeight(17.0);
		PasswordLabel.setPrefWidth(66.0);

		TextField idTF = new TextField();
		idTF.setLayoutX(100);
		idTF.setLayoutY(75);

		TextField NameTF = new TextField();
		NameTF.setLayoutX(100);
		NameTF.setLayoutY(123);

		PasswordField PasswordTF = new PasswordField();
		PasswordTF.setLayoutX(100);
		PasswordTF.setLayoutY(168);

		Button signIn = new Button("Sign In");
		signIn.setLayoutX(482);
		signIn.setLayoutY(99);
		signIn.setPrefHeight(56);
		signIn.setPrefWidth(118);
		signIn.setOnAction(e -> {
			if (idTF.getText().isEmpty() || NameTF.getText().isEmpty() || PasswordTF.getText().isEmpty()) {
				AlertBox.display("Please fill all the fields");
			} else {
				int id = Integer.parseInt(idTF.getText());
				String password = PasswordTF.getText();
				String name = NameTF.getText();
				Teacher t;
				try {
					t = Queries.getTeacherForSignIn(id, password, name);
					if (t == null) {
						AlertBox.display("Maybe there is an error in the name or password or id ");

					} else {
						AnwarsStage.setScene(muathFirstTeacherScene(AnwarsStage, t));

					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		Button back = new Button("Back");
		back.setLayoutX(299);
		back.setLayoutY(99);
		back.setPrefHeight(56);
		back.setPrefWidth(118);

		back.setOnAction(h -> {
			AnwarsStage.close();
			AnwarsStage.setScene(programMainScene(AnwarsStage)); // scene1

			AnwarsStage.show();
		});

		Image computers = new Image("file:computers.jpg");
		ImageView computersIV = new ImageView(computers);
		computersIV.setFitHeight(296);
		computersIV.setFitWidth(704);
		computersIV.setLayoutX(27);
		computersIV.setLayoutY(273);

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, computersIV, back, signIn, idLabel, nameLabel, PasswordLabel, idTF, NameTF,
				PasswordTF);
		Scene scene8 = new Scene(pane, 800, 600);
		return scene8;

	}

	public static Scene logInAsStudentScene(Stage AnwarsStage) {
		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);

		Label nameLabel = new Label("Name");
        nameLabel.setLayoutX(6.0);
        nameLabel.setLayoutY(70.0);
        nameLabel.setPrefHeight(35.0);
        nameLabel.setPrefWidth(42.0);

		Label PasswordLabel = new Label("Password");
		PasswordLabel.setLayoutX(6.0);
		PasswordLabel.setLayoutY(172.0);
		PasswordLabel.setPrefHeight(17.0);
		PasswordLabel.setPrefWidth(66.0);

		TextField NameTF = new TextField();
        NameTF.setLayoutX(100);
        NameTF.setLayoutY(75);

		PasswordField PasswordTF = new PasswordField();
		PasswordTF.setLayoutX(100);
		PasswordTF.setLayoutY(168);

		Button signIn = new Button("Sign In");
		signIn.setLayoutX(482);
		signIn.setLayoutY(99);
		signIn.setPrefHeight(56);
		signIn.setPrefWidth(118);

		signIn.setOnAction(g -> {
			if (PasswordTF.getText().isEmpty() || NameTF.getText().isEmpty() ) {
				AlertBox.display("Please fill all the fields");
			} else {
				try {
					Student s = Queries.getStudentForSignIn( PasswordTF.getText(), NameTF.getText());
					if (s == null) {
						AlertBox.display("please check if the data entered is correct");

					} else {
						AnwarsStage.setScene(studentMainScene(AnwarsStage, s));
					}

				} catch (ClassNotFoundException | SQLException e) {
					AlertBox.display("Error in DataBase");

				}

			}

		});

		Button back = new Button("Back");
		back.setLayoutX(299);
		back.setLayoutY(99);
		back.setPrefHeight(56);
		back.setPrefWidth(118);

		back.setOnAction(h -> {
			AnwarsStage.close();
			AnwarsStage.setScene(programMainScene(AnwarsStage));
			AnwarsStage.show();
		});

		Image computers = new Image("file:computers.jpg");
		ImageView computersIV = new ImageView(computers);
		computersIV.setFitHeight(296);
		computersIV.setFitWidth(704);
		computersIV.setLayoutX(27);
		computersIV.setLayoutY(273);

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, computersIV, back, signIn, nameLabel, PasswordLabel, NameTF,
				PasswordTF);
		Scene scene7 = new Scene(pane, 800, 600);
		return scene7;

	}

	public static Scene logInAsManagerScene(Stage AnwarsStage) {
		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);

		Label idLabel = new Label("SSN");
		idLabel.setLayoutX(6.0);
		idLabel.setLayoutY(70.0);
		idLabel.setPrefHeight(35.0);
		idLabel.setPrefWidth(42.0);

		Label nameLabel = new Label("Name");
		nameLabel.setLayoutX(3.0);
		nameLabel.setLayoutY(127.0);
		nameLabel.setPrefHeight(17.0);
		nameLabel.setPrefWidth(49.0);

		Label PasswordLabel = new Label("Password");
		PasswordLabel.setLayoutX(6.0);
		PasswordLabel.setLayoutY(172.0);
		PasswordLabel.setPrefHeight(17.0);
		PasswordLabel.setPrefWidth(66.0);

		TextField idTF = new TextField();
		idTF.setLayoutX(100);
		idTF.setLayoutY(75);

		TextField NameTF = new TextField();
		NameTF.setLayoutX(100);
		NameTF.setLayoutY(123);

		PasswordField PasswordTF = new PasswordField();
		PasswordTF.setLayoutX(100);
		PasswordTF.setLayoutY(168);

		Button signIn = new Button("Sign In");
		signIn.setLayoutX(482);
		signIn.setLayoutY(99);
		signIn.setPrefHeight(56);
		signIn.setPrefWidth(118);
		signIn.setOnAction(e -> {
			if (idTF.getText().isEmpty() || NameTF.getText().isEmpty() || PasswordTF.getText().isEmpty()) {
				AlertBox.display("Please fill all the fields");
			} else {
				String name = NameTF.getText();
				int SSN = Integer.parseInt(idTF.getText());
				String password = PasswordTF.getText();

				Manager m = null;
				try {
					m = Queries.getManager();
					if (m.getSSN() == SSN && name.equals(m.getName()) && m.password.equals(password)) {
						AnwarsStage.setScene(Scenes.manegarMainScene(AnwarsStage));
					} else {
						AlertBox.display("Wrong name or password");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		Button back = new Button("Back");
		back.setLayoutX(299);
		back.setLayoutY(99);
		back.setPrefHeight(56);
		back.setPrefWidth(118);

		back.setOnAction(h -> {

			AnwarsStage.setScene(programMainScene(AnwarsStage));

		});

		Image computers = new Image("file:computers.jpg");
		ImageView computersIV = new ImageView(computers);
		computersIV.setFitHeight(296);
		computersIV.setFitWidth(704);
		computersIV.setLayoutX(27);
		computersIV.setLayoutY(273);

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, computersIV, back, signIn, idLabel, nameLabel, PasswordLabel, idTF, NameTF,
				PasswordTF);
		Scene scene9 = new Scene(pane, 800, 600);
		return scene9;

	}

	public static Scene takeAnExamScene(Stage AnwarsStage, Student student) {
		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);

		ChoiceBox<String> subjectChoiceBox = new ChoiceBox();
		subjectChoiceBox.setValue("English");

		subjectChoiceBox.getItems().addAll("English", "Math", "Biology", "Technology", "Arabic", "Java");

		subjectChoiceBox.setLayoutX(113.0);
		subjectChoiceBox.setLayoutY(85.0);
		subjectChoiceBox.setPrefWidth(150.0);

		Label subjectLabel = new Label("Subject");
		subjectLabel.setLayoutX(49.0);
		subjectLabel.setLayoutY(89.0);

		ToggleGroup typeTG = new ToggleGroup();

		RadioButton quizRB = new RadioButton("Quiz");
		quizRB.setLayoutX(113.0);
		quizRB.setLayoutY(139.0);
		quizRB.setToggleGroup(typeTG);

		RadioButton midtermRB = new RadioButton("Midterm");
		midtermRB.setLayoutX(196.0);
		midtermRB.setLayoutY(139.0);
		midtermRB.setToggleGroup(typeTG);

		RadioButton finalRB = new RadioButton("Final");
		finalRB.setLayoutX(292.0);
		finalRB.setLayoutY(139.0);
		finalRB.setToggleGroup(typeTG);

		Label typeLabel = new Label("Type");
		typeLabel.setLayoutX(49.0);
		typeLabel.setLayoutY(139.0);

		Label note1 = new Label();
		note1.setLayoutX(45);
		note1.setLayoutY(203);
		note1.setFont(new Font(14));

		Label note2 = new Label();
		note2.setLayoutX(45);
		note2.setLayoutY(243);
		note2.setFont(new Font(14));
		Image takeExam = new Image("file:takeExam.gif");
		ImageView takeExamIV = new ImageView(takeExam);
		takeExamIV.setFitHeight(242.0);
		takeExamIV.setFitWidth(336.0);
		takeExamIV.setLayoutX(49.0);
		takeExamIV.setLayoutY(312.0);

		Button startExam = new Button("Start Exam.");
		startExam.setLayoutX(614.0);
		startExam.setLayoutY(486.0);
		startExam.setPrefHeight(56.0);
		startExam.setPrefWidth(118.0);
		startExam.setOnAction(e -> {
			if (!quizRB.isSelected() && !midtermRB.isSelected() && !finalRB.isSelected()) {
				AlertBox.display("Please pick the type of the exam");

			} else {
				int moneyToPay = 0;
				String type = "";

				if (quizRB.isSelected()) {
					moneyToPay = 3;
					type = "Quiz";

				} else if (midtermRB.isSelected()) {
					moneyToPay = 6;
					type = "Midterm";

				} else {
					moneyToPay = 10;
					type = "Final";

				}

				if (student.budget < moneyToPay) {
					AlertBox.display("You don't have enough money to take the exam");
				} else {
					String subject = subjectChoiceBox.getValue();
					try {
						Exam exam = Queries.getRandomExam(type, subject);
						if (exam == null) {
							AlertBox.display("This exam doesn't exist");
						} else {
							int newBudget = student.budget - moneyToPay;
							Queries.updateBudgetforStudent(student.getId(), newBudget);
							student.setBudget(newBudget);
							ArrayList<Question> questions = Queries.getQuestionsArrayList(exam);
							HelpingWindows.studentQuestions(student, questions, 0, questions.size(), 0, exam);
						}

					} catch (ClassNotFoundException | SQLException | ParseException e1) {
						AlertBox.display("Error in dataBase");

					}

				}

			}
		});
		Button backToSelect = new Button("Back");
		backToSelect.setLayoutX(465.0);
		backToSelect.setLayoutY(486.0);
		backToSelect.setPrefHeight(56.0);
		backToSelect.setPrefWidth(118.0);

		backToSelect.setOnAction(d -> {
			AnwarsStage.close();
			AnwarsStage.setScene(studentMainScene(AnwarsStage, student));
			AnwarsStage.show();
		});

		quizRB.setOnAction(e -> {

			note1.setText("*Note:The Quiz will cost you 3$");
			note2.setText("*Note:The Quiz contains 5 questions");
		});

		midtermRB.setOnAction(e -> {
			note1.setText("*Note:The Midterm will cost you 6$");
			note2.setText("*Note:The Midterm contains 10 questions");
		});
		finalRB.setOnAction(e -> {
			note1.setText("*Note:The Final will cost you 10$");
			note2.setText("*Note:The Final contains 15 questions");

		});

		Image sofoufLogo = new Image("file:SofoufLogo.png");
		ImageView sofoufLogoIV = new ImageView(sofoufLogo);
		sofoufLogoIV.setLayoutX(430);
		sofoufLogoIV.setLayoutY(70);
		sofoufLogoIV.setFitHeight(230);
		sofoufLogoIV.setFitWidth(324);

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, takeExamIV, startExam, subjectChoiceBox, backToSelect, subjectLabel,
				typeLabel, note1, note2, quizRB, midtermRB, finalRB, sofoufLogoIV);
		Scene scene5 = new Scene(pane, 800, 600);
		return scene5;

	}

	public static Scene viewPersonalInfoScene(Stage AnwarsStage, Student student) {

		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);
		Label idLabel = new Label("ID");
		idLabel.setLayoutX(6.0);
		idLabel.setLayoutY(70.0);
		idLabel.setPrefHeight(35.0);
		idLabel.setPrefWidth(42.0);

		Label nameLabel = new Label("Name");
		nameLabel.setLayoutX(3.0);
		nameLabel.setLayoutY(127.0);
		nameLabel.setPrefHeight(17.0);
		nameLabel.setPrefWidth(49.0);

		Label PasswordLabel = new Label("Password");
		PasswordLabel.setLayoutX(6.0);
		PasswordLabel.setLayoutY(172.0);
		PasswordLabel.setPrefHeight(17.0);
		PasswordLabel.setPrefWidth(66.0);

		Label BudgetLabel = new Label("Budget");
		BudgetLabel.setLayoutX(8.0);
		BudgetLabel.setLayoutY(223.0);

		Label addBudgetLabel = new Label("Add Budget");
		addBudgetLabel.setLayoutX(8.0);
		addBudgetLabel.setLayoutY(283.0);
		addBudgetLabel.setPrefHeight(17.0);
		addBudgetLabel.setPrefWidth(72.0);

		TextField idTF = new TextField();
		String idString = "";
		idString += student.getId();
		idTF.setText(idString);
		idTF.setEditable(false);
		idTF.setLayoutX(100);
		idTF.setLayoutY(75);

		TextField NameTF = new TextField();
		NameTF.setLayoutX(100);
		NameTF.setLayoutY(123);
		NameTF.setText(student.getName());
		NameTF.setEditable(false);

		TextField PasswordTF = new TextField();
		PasswordTF.setLayoutX(100);
		PasswordTF.setLayoutY(168);
		PasswordTF.setText(student.getPassword());
		PasswordTF.setEditable(false);

		TextField BudgetTF = new TextField();
		BudgetTF.setLayoutX(100);
		BudgetTF.setLayoutY(219);
		String budgetString = "";
		budgetString += student.budget;

		BudgetTF.setText(budgetString);
		BudgetTF.setEditable(false);

		TextField AddBudgetTF = new TextField();
		AddBudgetTF.setLayoutX(100);
		AddBudgetTF.setLayoutY(279);
		Button addBudgetButton = new Button("Add Budget");
		addBudgetButton.setLayoutX(270);
		addBudgetButton.setLayoutY(279);
		addBudgetButton.setOnAction(e -> {
			if (AddBudgetTF.getText().isEmpty()) {
				AlertBox.display("Please enter a budget");
			} else {
				try {
					int budgetToBeAdded = Integer.parseInt(AddBudgetTF.getText());
					int newBudget = budgetToBeAdded + student.budget;
					Queries.upgradeStudentsBudget(student.id, newBudget);
					student.setBudget(newBudget);

					BudgetTF.setText(newBudget + "");
				} catch (NumberFormatException eee) {
					AlertBox.display("Please enter an amount of money");

				}

			}
		});
		Button signOutToChooseMenu = new Button("Sign out");
		signOutToChooseMenu.setLayoutX(614);
		signOutToChooseMenu.setLayoutY(486);
		signOutToChooseMenu.setPrefHeight(56);
		signOutToChooseMenu.setPrefWidth(118);

		signOutToChooseMenu.setOnAction(g -> {
			AnwarsStage.close();
			AnwarsStage.setScene(programMainScene(AnwarsStage));
			AnwarsStage.show();
		});

		Button backToChooseMenu = new Button("Back");
		backToChooseMenu.setLayoutX(465);
		backToChooseMenu.setLayoutY(486);
		backToChooseMenu.setPrefHeight(56);
		backToChooseMenu.setPrefWidth(118);

		backToChooseMenu.setOnAction(h -> {
			AnwarsStage.close();
			AnwarsStage.setScene(studentMainScene(AnwarsStage, student));
			AnwarsStage.show();
		});

		Image computersPic = new Image("file:computers.jpg");
		ImageView computersPicIV = new ImageView(computersPic);
		computersPicIV.setFitHeight(248);
		computersPicIV.setFitWidth(400);
		computersPicIV.setLayoutX(27);
		computersPicIV.setLayoutY(321);

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, computersPicIV, backToChooseMenu, signOutToChooseMenu, idTF, NameTF,
				PasswordTF, BudgetTF, AddBudgetTF, idLabel, nameLabel, PasswordLabel, BudgetLabel, addBudgetLabel,
				addBudgetButton);
		Scene scene6 = new Scene(pane, 800, 600);
		return scene6;

	}

	public static Scene studentMainScene(Stage AnwarsStage, Student student) {

		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);
		Button takeAnExam = new Button("Take An Exam");
		takeAnExam.setLayoutX(66.0);
		takeAnExam.setLayoutY(109.0);
		takeAnExam.setPrefHeight(140.0);
		takeAnExam.setPrefWidth(408.0);
		takeAnExam.setStyle("-fx-background-color: #00ff00; ");

		takeAnExam.setOnAction(s -> {
			AnwarsStage.setScene(Scenes.takeAnExamScene(AnwarsStage, student));
		});

		Image takeAnExamPic = new Image("file:TakeAnExam.png");
		ImageView takeAnExamIV = new ImageView(takeAnExamPic);
		takeAnExamIV.setFitHeight(140.0);
		takeAnExamIV.setFitWidth(200.0);
		takeAnExamIV.setLayoutX(527.0);
		takeAnExamIV.setLayoutY(109.0);

		Button viewPersonalInfo = new Button("View My Personal Info");
		viewPersonalInfo.setLayoutX(66.0);
		viewPersonalInfo.setLayoutY(284.0);
		viewPersonalInfo.setPrefHeight(140.0);
		viewPersonalInfo.setPrefWidth(408.0);
		viewPersonalInfo.setStyle("-fx-background-color: #ff0000; ");

		viewPersonalInfo.setOnAction(f -> {
			AnwarsStage.setScene(Scenes.viewPersonalInfoScene(AnwarsStage, student));

		});

		Image viewPersonalInfoPic = new Image("file:PersonalInfo.png");
		ImageView viewPersonalInfoIV = new ImageView(viewPersonalInfoPic);
		viewPersonalInfoIV.setFitHeight(140.0);
		viewPersonalInfoIV.setFitWidth(200.0);
		viewPersonalInfoIV.setLayoutX(533.0);
		viewPersonalInfoIV.setLayoutY(284.0);

		Button signOutAfterWalkthrough = new Button("Sign out");
		signOutAfterWalkthrough.setLayoutX(337.0);
		signOutAfterWalkthrough.setLayoutY(525.0);
		signOutAfterWalkthrough.setPrefHeight(47.0);
		signOutAfterWalkthrough.setPrefWidth(137.0);
		signOutAfterWalkthrough.setOnAction(z -> {
			AnwarsStage.close();
			AnwarsStage.setScene(programMainScene(AnwarsStage));
			AnwarsStage.show();
		});

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, viewPersonalInfoIV, takeAnExamIV, takeAnExam, viewPersonalInfo,
				signOutAfterWalkthrough);
		Scene scene4 = new Scene(pane, 800, 600);
		return scene4;

	}

	public static Scene tutorialScene(Stage AnwarsStage, Student student) {

		Button continueButton = new Button("Continue");
		continueButton.setLayoutX(609.0);
		continueButton.setLayoutY(513.0);
		continueButton.setPrefHeight(51.0);
		continueButton.setPrefWidth(135.0);
		/* Continue button after walkthrough actions start here */
		continueButton.setOnAction(b -> {
			AnwarsStage.setScene(Scenes.studentMainScene(AnwarsStage, student));

		});
		/* Continue button ends here */
		Image SaeedsBackground = new Image("file:Tutorial.png");
		ImageView SaeedsBackgroundIV = new ImageView(SaeedsBackground);
		Pane pane = new Pane();
		pane.getChildren().addAll(SaeedsBackgroundIV, continueButton);
		Scene scene3 = new Scene(pane, 800, 600);
		return scene3;

	}

	public static Scene areYouNewScene(Stage AnwarsStage) {

		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);
		Image welcomeToSofouf = new Image("file:WelcomeToSofouf.png");
		ImageView welcomeToSofoufIV = new ImageView(welcomeToSofouf);
		welcomeToSofoufIV.setFitHeight(63.0);
		welcomeToSofoufIV.setFitWidth(549.0);
		welcomeToSofoufIV.setLayoutX(116.0);
		welcomeToSofoufIV.setLayoutY(14.0);

		Image sofoufLogo = new Image("file:SofoufLogo.png");
		ImageView sofoufLogoIV = new ImageView(sofoufLogo);
		sofoufLogoIV.setFitHeight(292.0);
		sofoufLogoIV.setFitWidth(339.0);
		sofoufLogoIV.setLayoutX(326.0);
		sofoufLogoIV.setLayoutY(125.0);

		Label firstNameLabel = new Label();
		firstNameLabel.setText("First Name");
		firstNameLabel.setLayoutX(8.0);
		firstNameLabel.setLayoutY(100);
		firstNameLabel.setPrefHeight(38.0);
		firstNameLabel.setPrefWidth(63.0);

		TextField firstNameLabelTF = new TextField();
		firstNameLabelTF.setLayoutX(78.0);
		firstNameLabelTF.setLayoutY(107.0);

		Label lastNameLabel = new Label();
		lastNameLabel.setText("Last Name");
		lastNameLabel.setLayoutX(8.0);
		lastNameLabel.setLayoutY(150);
		lastNameLabel.setPrefHeight(38.0);
		lastNameLabel.setPrefWidth(63.0);

		TextField lastNameLabelTF = new TextField();
		lastNameLabelTF.setLayoutX(78.0);
		lastNameLabelTF.setLayoutY(156.0);

		Label passwordLabel = new Label();
		passwordLabel.setText("Password");
		passwordLabel.setLayoutX(8.0);
		passwordLabel.setLayoutY(199.0);
		passwordLabel.setPrefHeight(38.0);
		passwordLabel.setPrefWidth(63.0);

		TextField passwordLabelTF = new TextField();
		passwordLabelTF.setLayoutX(78.0);
		passwordLabelTF.setLayoutY(206.0);

		Label numberLabel = new Label();
		numberLabel.setText("Number");
		numberLabel.setLayoutX(8.0);
		numberLabel.setLayoutY(244.0);
		numberLabel.setPrefHeight(38.0);
		numberLabel.setPrefWidth(63.0);

		TextField numberLabelTF = new TextField();
		numberLabelTF.setLayoutX(78.0);
		numberLabelTF.setLayoutY(250.0);

		Label emailLabel = new Label();
		emailLabel.setText("Email");
		emailLabel.setLayoutX(8.0);
		emailLabel.setLayoutY(294.0);
		emailLabel.setPrefHeight(38.0);
		emailLabel.setPrefWidth(63.0);

		TextField emailLabelTF = new TextField();
		emailLabelTF.setLayoutX(78.0);
		emailLabelTF.setLayoutY(300.0);

		Label creditCardLabel = new Label();
		creditCardLabel.setText("Credit card");
		creditCardLabel.setLayoutX(8.0);
		creditCardLabel.setLayoutY(343.0);
		creditCardLabel.setPrefHeight(38.0);
		creditCardLabel.setPrefWidth(63.0);

		TextField creditCardLabelTF = new TextField();
		creditCardLabelTF.setLayoutX(78.0);
		creditCardLabelTF.setLayoutY(349.0);

		Label budgetLabel = new Label();
		budgetLabel.setText("Budget");
		budgetLabel.setLayoutX(8.0);
		budgetLabel.setLayoutY(392);
		budgetLabel.setPrefHeight(38.0);
		budgetLabel.setPrefWidth(63.0);

		TextField budgetLabelTF = new TextField();
		budgetLabelTF.setLayoutX(78.0);
		budgetLabelTF.setLayoutY(398.0);

		Label genderLabel = new Label();
		genderLabel.setText("Gender");
		genderLabel.setLayoutX(8.0);
		genderLabel.setLayoutY(451);
		genderLabel.setPrefHeight(38.0);
		genderLabel.setPrefWidth(63.0);

		ToggleGroup maleFemaleTG = new ToggleGroup();

		RadioButton maleRB = new RadioButton();
		maleRB.setLayoutX(78.0);
		maleRB.setLayoutY(462.0);
		maleRB.setText("Male");
		maleRB.setToggleGroup(maleFemaleTG);

		RadioButton femaleRB = new RadioButton();
		femaleRB.setLayoutX(152.0);
		femaleRB.setLayoutY(462.0);
		femaleRB.setText("Female");
		femaleRB.setToggleGroup(maleFemaleTG);

		Button backFromSignUp = new Button("Back");
		backFromSignUp.setLayoutX(20.0);
		backFromSignUp.setLayoutY(532.0);
		backFromSignUp.setPrefHeight(40.0);
		backFromSignUp.setPrefWidth(78.0);

		backFromSignUp.setOnAction(c -> {

			AnwarsStage.setScene(programMainScene(AnwarsStage));
		});

		Button signUpNowButton = new Button();
		signUpNowButton.setText("Sign Up Now!");
		signUpNowButton.setLayoutX(616.0);
		signUpNowButton.setLayoutY(517.0);
		signUpNowButton.setPrefHeight(50.0);
		signUpNowButton.setPrefWidth(119.0);

		/* "Sign up Now" button set on action */
		signUpNowButton.setOnAction(a -> {
			if (passwordLabelTF.getText().isEmpty() || firstNameLabelTF.getText().isEmpty()
					|| emailLabelTF.getText().isEmpty() || numberLabelTF.getText().isEmpty()
					|| budgetLabelTF.getText().isEmpty()) {
				AlertBox.display("Please fill all the fields");

			} else {

				String password = passwordLabelTF.getText();
				String name = firstNameLabelTF.getText();
				String email = emailLabelTF.getText();
				int number = Integer.parseInt(numberLabelTF.getText());
				int budget = Integer.parseInt(budgetLabelTF.getText());
				Student student = new Student(password, name, number, email, budget);
				Queries.insertStudent(student);
				try {
					int maxStudentId = Queries.getMaxIdForStudents();
					student.setId(maxStudentId);

				} catch (ClassNotFoundException | SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				AnwarsStage.setScene(Scenes.tutorialScene(AnwarsStage, student));
			}
		});
		/* "Sign up now" button action ends here */

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, sofoufLogoIV, welcomeToSofoufIV, signUpNowButton, backFromSignUp, femaleRB,
				maleRB, passwordLabelTF, creditCardLabelTF, emailLabelTF, numberLabelTF, lastNameLabelTF, budgetLabelTF,
				firstNameLabelTF, firstNameLabel, lastNameLabel, passwordLabel, numberLabel, emailLabel,
				creditCardLabel, budgetLabel, genderLabel);

		Scene scene2 = new Scene(pane, 800, 600);
		return scene2;

	}

	public static Scene programMainScene(Stage AnwarsStage) {
		Image image = new Image("file:pic1.jpg");
		ImageView imageView = new ImageView(image);

		Button loginAsStudent = new Button("Log In As Student");
		loginAsStudent.setLayoutX(151.0);
		loginAsStudent.setLayoutY(56.0);
		loginAsStudent.setPrefHeight(109.0);
		loginAsStudent.setPrefWidth(461.0);

		loginAsStudent.setOnAction(j -> {
			AnwarsStage.setScene(Scenes.logInAsStudentScene(AnwarsStage));

		});

		Button loginAsTeacher = new Button("Log In As Teacher");
		loginAsTeacher.setLayoutX(149.0);
		loginAsTeacher.setLayoutY(204.0);
		loginAsTeacher.setPrefHeight(109.0);
		loginAsTeacher.setPrefWidth(461.0);

		loginAsTeacher.setOnAction(k -> {
			AnwarsStage.setScene(Scenes.logInAsTeacherScene(AnwarsStage));

		});

		Button loginAsManager = new Button("Log In As Manager");
		loginAsManager.setLayoutX(146.0);
		loginAsManager.setLayoutY(357.0);
		loginAsManager.setPrefHeight(109.0);
		loginAsManager.setPrefWidth(461.0);
		loginAsManager.setFont(new Font("Candara", 15));

		loginAsManager.setOnAction(ac -> {

			AnwarsStage.setScene(Scenes.logInAsManagerScene(AnwarsStage));
		});

		Button areYouNew = new Button("New Student? Sign Up Here!");
		areYouNew.setLayoutX(492.0);
		areYouNew.setLayoutY(514.0);
		areYouNew.setPrefHeight(58.0);
		areYouNew.setPrefWidth(233.0);
		/* "SIGN UP AS STUDENT" ACTIONS START HERE */
		areYouNew.setOnAction(e -> {
			AnwarsStage.setScene(Scenes.areYouNewScene(AnwarsStage));

		});
		/* "Sign up as student" set on action ends here */
		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, loginAsStudent, loginAsTeacher, loginAsManager, areYouNew);
		Scene scene1 = new Scene(pane, 800, 600);
		return scene1;

	}

	static Scene muathFirstTeacherScene(Stage muathStage, Teacher teacher) {

		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);

		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		Text t = new Text("Teacher Table");
		t.setEffect(ds);
		t.setCache(true);
		t.setX(10.0f);
		t.setY(270.0f);
		t.setFill(Color.RED);
		t.setFont(Font.font(null, FontWeight.BOLD, 32));

		Button b1 = new Button("Write Exam");
		Button b2 = new Button("View Exams");
		Button b3 = new Button("View Personal Info");

		b1.setPrefSize(300, 75);
		b2.setPrefSize(300, 75);
		b3.setPrefSize(300, 75);

		VBox v1 = new VBox(75, t, b1, b2, b3);
		v1.setLayoutX(227);

		v1.setLayoutY(49);
		Button logOutButton = new Button("Log out");
		logOutButton.setPrefSize(168, 51);

		logOutButton.setLayoutX(632);
		logOutButton.setLayoutY(549);
		logOutButton.setOnAction(e -> {
			muathStage.setScene(programMainScene(muathStage));
		});
		v1.setAlignment(Pos.TOP_CENTER);

		Pane spane = new Pane(imageView, v1, logOutButton);

		Scene scene = new Scene(spane, 800, 600);

		b1.setOnAction(e -> {

			muathStage.setScene(Scenes.muathWriteExamScene(muathStage, teacher));

		});
		b2.setOnAction(e -> {

			Scene s = new Scene(Scenes.displayExamsInManager(muathStage, teacher), 800, 600);

			muathStage.setScene(s);

		});

		b3.setOnAction(e -> {

			muathStage.setScene(teachersPersonalInfo(teacher, muathStage));

		});

		return scene;

	}

	static void muathQT() throws ParseException {

		Stage window = new Stage();

		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);

		TableView<Teacher> table = new TableView<Teacher>();
		table.setLayoutX(54);
		table.setLayoutY(80);
		table.setPrefHeight(409);
		table.setPrefWidth(670);

		TableColumn<Teacher, Integer> idColumn = new TableColumn<Teacher, Integer>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setMinWidth(130);

		TableColumn<Teacher, String> nameColumn = new TableColumn<Teacher, String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setMinWidth(130);

		TableColumn<Teacher, String> emailColumn = new TableColumn<Teacher, String>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailColumn.setMinWidth(130);

		TableColumn<Teacher, Integer> budgetColunmn = new TableColumn<Teacher, Integer>("Budget");
		budgetColunmn.setCellValueFactory(new PropertyValueFactory<>("budget"));
		budgetColunmn.setMinWidth(130);

		ObservableList<Teacher> teacherOb = FXCollections.observableArrayList();

		table.getColumns().addAll(idColumn, nameColumn, emailColumn, budgetColunmn);

		try {
			table.setItems(teacherOb);
			Queries.report1(table);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		Button exitButton = new Button("Exit");

		exitButton.setOnAction(e -> {

			window.close();

		});

		exitButton.setLayoutX(350);
		exitButton.setLayoutY(503);

		exitButton.setPrefSize(100, 30);

		Label label = new Label("Teachers ");
		label.setLayoutX(350);
		label.setLayoutY(35);
		label.setFont(new Font(20));
		Pane pane = new Pane();

		pane.getChildren().addAll(imageView, table, label, exitButton);

		Scene scene = new Scene(pane, 800, 600);
		window.setScene(scene);
		window.showAndWait();

	}

	static void muathViewBudgetScene(Teacher teacher) {

		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(teacher.getName() + " Budget ");
		window.setMinWidth(250);

		Label label = new Label();
		label.setText("Your budget is " + teacher.getBudget());
		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> window.close());

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 700, 500);

		window.setScene(scene);
		window.show();

	}

	static Scene muathWriteExamScene(Stage muathStage, Teacher teacher) {

		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);

		DropShadow ds1 = new DropShadow();
		ds1.setOffsetY(3.0f);
		ds1.setColor(Color.color(0.4f, 0.4f, 0.4f));
		Text t1 = new Text("Choose Type Of Exam ");
		t1.setEffect(ds1);
		t1.setCache(true);
		t1.setX(10.0f);
		t1.setY(270.0f);
		t1.setFill(Color.RED);
		t1.setFont(Font.font(null, FontWeight.BOLD, 32));

		Button b4 = new Button("Begin");
		Button b5 = new Button("Back");

		b5.setPrefSize(200, 75);
		b4.setPrefSize(200, 75);

		RadioButton rb1 = new RadioButton("Quiz");
		RadioButton rb2 = new RadioButton("Midterm");
		RadioButton rb3 = new RadioButton("Final");

		ToggleGroup tg1 = new ToggleGroup();
		rb1.setToggleGroup(tg1);
		rb2.setToggleGroup(tg1);
		rb3.setToggleGroup(tg1);

		// for comboBox

		ComboBox<String> cb1 = new ComboBox<String>();
		cb1.getItems().addAll("English", "Math", "Biology", "Technology", "Arabic", "Java");
		Label l1 = new Label("Subject");
		HBox hb1 = new HBox(10, rb1, rb2, rb3);
		HBox hb2 = new HBox(10, l1, cb1);
		HBox hb3 = new HBox(100, b5, b4);
		HBox hb4 = new HBox();

		hb1.setAlignment(Pos.CENTER);
		hb2.setAlignment(Pos.CENTER);
		hb3.setAlignment(Pos.CENTER);

		VBox vb2 = new VBox(75, hb4, t1, hb1, hb2, hb3);

		vb2.setAlignment(Pos.TOP_CENTER);
		StackPane spane1 = new StackPane(imageView, vb2);
		Scene scene1 = new Scene(spane1, 800, 600);

		b5.setOnAction(e -> {

			muathStage.setScene(muathFirstTeacherScene(muathStage, teacher));

		});

		ArrayList<Question> alq = new ArrayList<Question>();

		b4.setOnAction(e -> {

			if (cb1.getValue() == null) {
				AlertBox.display("the subject not selected");
			}

			if (!rb1.isSelected() && !rb2.isSelected() && !rb3.isSelected())
				AlertBox.display("please select type of exam");

			if (cb1.getValue() != null && (rb1.isSelected() || rb2.isSelected() || rb3.isSelected())) {
				String type = "";
				if (rb1.isSelected())
					type = "quiz";
				else if (rb2.isSelected())
					type = "midterm";
				else if (rb3.isSelected())
					type = "final";

				String sub = (String) cb1.getValue();

				Exam ex1 = null;

				try {
					ex1 = new Exam(Queries.getMaxIdForExam() + 1, type, sub, teacher.getId());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				muathStage.setTitle("Exam's table");
				ArrayList<Question> qal = new ArrayList<Question>();

				ex1.teacherId = teacher.getId();

				try {
					collectQuestions(qal, ex1, teacher);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		return scene1;
	}

	public static void collectQuestions(ArrayList<Question> ar, Exam ex, Teacher t)
			throws ClassNotFoundException, SQLException, ParseException {

		int budget = 0;

		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);

		Stage s = new Stage();
		s.setResizable(false);
		int count = 3;

		if (ex.type.trim().equalsIgnoreCase("quiz")) {
			count = 5;
			budget = 20;
		} else if (ex.type.trim().equalsIgnoreCase("midterm")) {
			count = 10;
			budget = 50;

		} else if (ex.type.trim().equalsIgnoreCase("final")) {
			count = 15;
			budget = 70;
		}

		if (ar.size() + 1 > count) {
			Queries.insertData(ex);
			int examID = Queries.getMaxIdForExam();

			Queries.insertData(ar, examID);
			int newBudget = t.getBudget() + budget;
			t.setBudget(newBudget);
			Queries.updateBudget(t.getId(), newBudget);
			return;
		}

		RadioButton rb4 = new RadioButton("1)");
		RadioButton rb5 = new RadioButton("2)");
		RadioButton rb6 = new RadioButton("3)");
		RadioButton rb7 = new RadioButton("4)");

		ToggleGroup tg2 = new ToggleGroup();
		rb4.setToggleGroup(tg2);
		rb5.setToggleGroup(tg2);
		rb6.setToggleGroup(tg2);
		rb7.setToggleGroup(tg2);
		TextArea textArea = new TextArea();

		//TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		TextField tf3 = new TextField();
		TextField tf4 = new TextField();
		TextField tf5 = new TextField();

		Button b6 = new Button("Next");

		if (count == ar.size() + 1)
			b6.setText("Finish");

		b6.setPrefSize(150, 50);

		textArea.setPrefSize(600, 100);

		Label l3 = new Label("TEXT");

		HBox hb7 = new HBox(20, l3, textArea);

		HBox hb8 = new HBox(10, tf2, rb4);
		HBox hb9 = new HBox(10, tf3, rb5);
		HBox hb10 = new HBox(10, tf4, rb6);
		HBox hb11 = new HBox(10, tf5, rb7);

		VBox vb4 = new VBox(30, hb8, hb9, hb10, hb11);

		hb7.setAlignment(Pos.TOP_CENTER);
		hb8.setAlignment(Pos.TOP_CENTER);
		hb9.setAlignment(Pos.TOP_CENTER);
		hb10.setAlignment(Pos.TOP_CENTER);
		hb11.setAlignment(Pos.TOP_CENTER);

		vb4.setAlignment(Pos.TOP_CENTER);
		HBox hb14 = new HBox();
		HBox hb12 = new HBox(100, hb14, b6);
		HBox hb13 = new HBox();

		hb12.setAlignment(Pos.TOP_CENTER);

		VBox vb6 = new VBox(60, hb13, hb7, vb4, hb12);

		vb6.setAlignment(Pos.TOP_CENTER);
		StackPane sp3 = new StackPane(imageView, vb6);
		Scene scene4 = new Scene(sp3, 750, 550);

		s.setScene(scene4);

		s.show();

		b6.setOnAction(e -> {
			String text = "";
			int qid = 0;
			try {
				qid = Queries.getMaxQId() + 1 + ar.size() + 1;
			} catch (ClassNotFoundException | SQLException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int eid = ex.id;

			int ca = -1;

			if (textArea.getText() != null
					&& (rb4.isSelected() || rb5.isSelected() || rb6.isSelected() || rb7.isSelected())) {
				text = textArea.getText() + "_" + tf2.getText() + "#" + tf3.getText() + "#" + tf4.getText() + "#"
						+ tf5.getText();

				if (rb4.isSelected())
					ca = 1;
				else if (rb5.isSelected())
					ca = 2;
				else if (rb6.isSelected())
					ca = 3;
				else if (rb7.isSelected())
					ca = 4;

				ar.add(new Question(qid, text, ca, eid));

				s.close();
				try {
					collectQuestions(ar, ex, t);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			else
				AlertBox.display("please enter text of quastion or select correct answer");

		});

	}

	public static void showQuestion(Question question) {
		String questionText = question.getText();
		String[] ar = questionText.split("\\*");

		String answers[] = ar[1].split("#");
		Stage stage = new Stage();
		stage.setResizable(false);
		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);
		Pane pane = new Pane();
		Label text = new Label(ar[0]);
		text.setLayoutX(42.0);
		text.setLayoutY(48.0);
		VBox v = new VBox();
		v.setLayoutX(180);
		v.setLayoutY(130);
		v.setAlignment(Pos.CENTER);
		TextField tf1 = new TextField();
		tf1.setText(answers[0]);
		TextField tf2 = new TextField();
		tf2.setText(answers[1]);
		TextField tf3 = new TextField();
		tf3.setText(answers[2]);
		TextField tf4 = new TextField();
		tf4.setText(answers[3]);
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> {
			stage.close();
		});
		tf1.setEditable(false);
		tf2.setEditable(false);
		tf3.setEditable(false);
		tf4.setEditable(false);
		v.setSpacing(15);
		if (question.answer == 1) {
			tf1.setStyle("-fx-border-color: green ;");
		} else if (question.answer == 2) {
			tf2.setStyle("-fx-border-color: green ;");
		} else if (question.answer == 3) {
			tf3.setStyle("-fx-border-color: green ;");
		} else {
			tf4.setStyle("-fx-border-color: green ;");
		}
		v.getChildren().addAll(tf1, tf2, tf3, tf4, closeButton);
		pane.getChildren().addAll(imageView, text, v);
		Scene scene = new Scene(pane, 500, 400);
		stage.setScene(scene);
		stage.show();
	}

	public static Scene questionsScene(Stage primaryStage, Exam exam, Teacher t) {
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
		ObservableList<Question> questionsOb;
		try {
			questionsOb = Queries.getQuestions(exam);
			table.setItems(questionsOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}
		table.getColumns().addAll(questionIdColumn, examIdColumn);
		TableMethods.addButtonToQuestionsTable(primaryStage, table);
		Button refreshButton = new Button("Refresh");
		refreshButton.setLayoutX(668);
		refreshButton.setLayoutY(503);
		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getQuestions(exam));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Label label = new Label("Questions");
		label.setLayoutX(350);
		label.setLayoutY(35);
		label.setFont(new Font(20));
		Button backButton = new Button("back");
		backButton.setLayoutX(54);
		backButton.setLayoutY(503);
		backButton.setOnAction(e -> {
			primaryStage.setScene(Scenes.muathFirstTeacherScene(primaryStage, t));
		});

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, table, refreshButton, label, backButton);
		Scene scene = new Scene(pane, 800, 600);
		return scene;
	}

	public static Pane displayExamsInManager(Stage primaryStage, Teacher t) {
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
			examsOb = Queries.getExams(t);
			table.setItems(examsOb);
		} catch (ClassNotFoundException | SQLException e) {
			AlertBox.display("Error in dataBase while reading students");
			e.printStackTrace();
		}

		table.getColumns().addAll(idColumn, subjectColunmn, typeColumn, teacherIdColumn);

		TableMethods.addButtonToTable(primaryStage, table, t);
		Button refreshButton = new Button("Refresh");
		Button backButton = new Button("Back");

		backButton.setOnAction(e -> {

			primaryStage.setScene(Scenes.muathFirstTeacherScene(primaryStage, t));

		});

		refreshButton.setLayoutX(550);
		refreshButton.setLayoutY(503);

		backButton.setLayoutX(100);
		backButton.setLayoutY(503);

		backButton.setPrefSize(100, 30);
		refreshButton.setPrefSize(100, 30);

		refreshButton.setOnAction(e -> {
			try {
				table.setItems(Queries.getExams(t));
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

		pane.getChildren().addAll(imageView, table, backButton, refreshButton, label);
		return pane;
	}

	public static Scene teachersPersonalInfo(Teacher teacher, Stage primaryStage) {
		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(600);
		imageView.setFitWidth(800);

		Image sofoufLogo = new Image("file:SofoufLogo.png");
		ImageView sofoufLogoIV = new ImageView(sofoufLogo);
		sofoufLogoIV.setLayoutX(429);
		sofoufLogoIV.setLayoutY(151);
		sofoufLogoIV.setFitHeight(267);
		sofoufLogoIV.setFitWidth(289);

		Label welcomeLabel = new Label("Teachers personal Information");
		welcomeLabel.setFont(new Font(21));

		welcomeLabel.setLayoutX(259);
		welcomeLabel.setLayoutY(52);

		Label nameLabel = new Label("Teachers Name");
		nameLabel.setLayoutX(67);
		nameLabel.setLayoutY(161);
		TextField nameField = new TextField();
		nameField.setEditable(false);
		nameField.setText(teacher.getName());
		nameField.setLayoutX(174);
		nameField.setLayoutY(157);

		Label iDLabel = new Label("Teacher's ID");
		iDLabel.setLayoutX(67);
		iDLabel.setLayoutY(224);
		TextField iDField = new TextField();
		iDField.setEditable(false);
		iDField.setText(teacher.getId() + "");
		iDField.setLayoutX(174);
		iDField.setLayoutY(220);

		Label passwordLabel = new Label("Teacher's Password");
		passwordLabel.setLayoutX(67);
		passwordLabel.setLayoutY(284);
		TextField passwordField = new TextField();
		passwordField.setEditable(false);
		passwordField.setText(teacher.getPassword());
		passwordField.setLayoutX(174);
		passwordField.setLayoutY(280);

		Label emailLabel = new Label("Teacher's email");
		emailLabel.setLayoutX(67);
		emailLabel.setLayoutY(343);
		TextField emailField = new TextField();
		emailField.setEditable(false);
		emailField.setText(teacher.getEmail());
		emailField.setLayoutX(174);
		emailField.setLayoutY(339);

		Label budgetLabel = new Label("Teacher's budget");
		budgetLabel.setLayoutX(67);
		budgetLabel.setLayoutY(403);
		TextField budgetField = new TextField();
		budgetField.setLayoutX(174);
		budgetField.setLayoutY(399);

		budgetField.setEditable(false);
		budgetField.setText(teacher.budget + "");

		Button backButton = new Button("Back");
		backButton.setLayoutX(82);
		backButton.setLayoutY(501);
		backButton.setPrefSize(289, 71);
		Button signOutButton = new Button("Sign out");
		signOutButton.setLayoutX(432);
		signOutButton.setLayoutY(501);
		signOutButton.setPrefSize(289, 71);
		backButton.setOnAction(e -> {
			primaryStage.setScene(muathFirstTeacherScene(primaryStage, teacher));
		});
		signOutButton.setOnAction(e -> {
			primaryStage.setScene(programMainScene(primaryStage));
		});
		Pane pane = new Pane(imageView, welcomeLabel, backButton, signOutButton, sofoufLogoIV, nameField, nameLabel,
				passwordField, passwordLabel, emailField, emailLabel, budgetField, budgetLabel, iDField, iDLabel);
		Scene scene = new Scene(pane, 800, 600);
		return scene;

	}

}
