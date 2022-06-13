package com.example.usablesecurityproject;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelpingWindows {
	public static void insertTeacherWindow(TableView<Teacher> table) {
		Image image = new Image("file:image.jpg");

		ImageView imageView = new ImageView(image);

		Label label = new Label("Insert Teacher");
		label.setFont(new Font(20));
		label.setLayoutX(188);
		label.setLayoutY(56);

		Pane pane = new Pane();

		Label nameLabel = new Label("Enter Name");
		nameLabel.setLayoutX(111);
		nameLabel.setLayoutY(145);
		TextField nameField = new TextField();
		nameField.setLayoutX(67);
		nameField.setLayoutY(178);

		Label emailLabel = new Label("Enter Email");
		emailLabel.setLayoutX(323);
		emailLabel.setLayoutY(145);
		TextField emailField = new TextField();
		emailField.setLayoutX(278);
		emailField.setLayoutY(175);
		Label passwordLabel = new Label("Insert Password");
		passwordLabel.setLayoutX(208);
		passwordLabel.setLayoutY(222);

		TextField passwordField = new TextField();
		passwordField.setLayoutX(176);
		passwordField.setLayoutY(252);

		Button insertButton = new Button("Insert");
		insertButton.setLayoutX(227.0);
		insertButton.setLayoutY(300);
		insertButton.setOnAction(e -> {
			if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
				AlertBox.display("Please fill all the fields");

			} else {

				Teacher t = new Teacher(nameField.getText(), emailField.getText(), passwordField.getText());

				Queries.insertTeacher(t);
				try {
					table.setItems(Queries.getTeachers());
				} catch (ClassNotFoundException | SQLException e1) {
					AlertBox.display("Error in database");
				}

			}
		});
		pane.getChildren().addAll(imageView, nameField, nameLabel, passwordField, passwordLabel, emailField, emailLabel,
				insertButton, label);

		Scene scene = new Scene(pane, 500, 400);
		Stage stage = new Stage();
		stage.setScene(scene);

		stage.show();

	}

	public static void showQuestion(Question question) {
		String questionText = question.getText();
		String[] ar = questionText.split("_");
		

		String answers[] = ar[1].split("#");

		Stage stage = new Stage();
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

	public static void studentQuestions(Student student, ArrayList<Question> questions, int counter, int times,
                                        int mark, Exam exam) {
		Stage stage = new Stage();
		Image image = new Image("file:image.jpg");

		ImageView imageView = new ImageView(image);
		Button nextButton = new Button("Next");
		nextButton.setLayoutX(427);
		nextButton.setLayoutY(353);

		if (counter == times - 1) {
			nextButton.setText("Finish");
		}

		TextArea textArea = new TextArea();
		textArea.setLayoutX(28);
		textArea.setLayoutY(34);
		textArea.setPrefHeight(100);
		textArea.setPrefWidth(425);
		textArea.setEditable(false);

		Question q = questions.get(counter);
		String ar[] = q.getText().split("_");
		textArea.setText(ar[0]);
		String[] answers = ar[1].split("#");

		RadioButton r1 = new RadioButton();
		TextField tf1 = new TextField(answers[0]);
		tf1.setEditable(false);
		RadioButton r2 = new RadioButton();
		TextField tf2 = new TextField(answers[1]);
		tf2.setEditable(false);
		RadioButton r3 = new RadioButton();
		TextField tf3 = new TextField(answers[2]);
		tf3.setEditable(false);
		RadioButton r4 = new RadioButton();
		TextField tf4 = new TextField(answers[3]);
		tf4.setEditable(false);
		HBox h1 = new HBox(r1, tf1);
		HBox h2 = new HBox(r2, tf2);
		HBox h3 = new HBox(r3, tf3);
		HBox h4 = new HBox(r4, tf4);
		h1.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);
		h4.setAlignment(Pos.CENTER);

		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		r3.setToggleGroup(tg);
		r4.setToggleGroup(tg);

		VBox v = new VBox();
		v.setLayoutX(160);
		v.setLayoutY(179);
		v.setAlignment(Pos.CENTER);
		v.setSpacing(20);

		v.getChildren().addAll(h1, h2, h3, h4);

		nextButton.setOnAction(e -> {
			if (!r1.isSelected() && !r2.isSelected() && !r3.isSelected() && !r4.isSelected()) {
				AlertBox.display("Please Choose an answer");

			} else {

				if (counter == times - 1) {// need to update the database
					try {
						if ((q.answer == 1 && r1.isSelected()) || (q.answer == 2 && r2.isSelected())
								|| (q.answer == 3 && r3.isSelected()) || (q.answer == 4 && r4.isSelected())) {

							Queries.insertExamResult(mark + 1, student.getId(), questions.get(0).getExamId(), times);

							stage.close();

							HelpingWindows.resultWindow(mark + 1, times);
						} else {
							Queries.insertExamResult(mark, student.getId(), questions.get(0).getExamId(), times);

							stage.close();

							HelpingWindows.resultWindow(mark, times);
						}

					} catch (ClassNotFoundException | SQLException e1) {
						AlertBox.display("Error in database");
					}

					

				} else {
					stage.close();
					if ((q.answer == 1 && r1.isSelected()) || (q.answer == 2 && r2.isSelected())
							|| (q.answer == 3 && r3.isSelected()) || (q.answer == 4 && r4.isSelected())) {
						studentQuestions(student, questions, counter + 1, times, mark + 1, exam);

					} else {
						studentQuestions(student, questions, counter + 1, times, mark, exam);

					}
				}
			}

		});
		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, textArea, v, nextButton);
		Scene scene = new Scene(pane, 500, 400);
		stage.setScene(scene);
		stage.show();

	}

	public static void resultWindow(int mark, int times) {
		boolean b;
		if (mark < (times * 60 / 100)) {
			b = false;
		} else {
			b = true;
		}
		Stage stage = new Stage();
		Button closeButton = new Button("close");
		closeButton.setOnAction(e -> {
			stage.close();
		});
		closeButton.setLayoutX(441);
		closeButton.setLayoutY(353);

		Label statusLabel = new Label();
		statusLabel.setLayoutX(149);
		statusLabel.setLayoutY(104);
		statusLabel.setFont(new Font(34));
		Label resultLabel = new Label("Your result:" + mark + "/" + times);

		resultLabel.setLayoutX(140);
		resultLabel.setLayoutY(35);
		resultLabel.setFont(new Font(34));
		Image image = new Image("file:image.jpg");
		ImageView imageView = new ImageView(image);

		Image image1 = new Image("file:smile3.png");
		ImageView imageView1 = new ImageView(image1);

		imageView1.setLayoutX(160);
		imageView1.setLayoutY(180);
		imageView1.setFitHeight(150);
		imageView1.setFitWidth(180);

		Image image2 = new Image("file:mad.png");

		ImageView imageView2 = new ImageView(image2);
		imageView2.setLayoutX(160);
		imageView2.setLayoutY(180);
		imageView2.setFitHeight(150);
		imageView2.setFitWidth(180);

		Pane pane = new Pane();
		pane.getChildren().addAll(imageView, closeButton, statusLabel, resultLabel);
		if (b) {
			resultLabel.setTextFill(Color.web("green"));
			statusLabel.setText("Status:Passed");
			statusLabel.setTextFill(Color.web("green"));
			pane.getChildren().add(imageView1);

		} else {
			resultLabel.setTextFill(Color.web("red"));
			statusLabel.setText("Status:Failed");
			statusLabel.setTextFill(Color.web("red"));
			pane.getChildren().add(imageView2);

		}
		Scene scene = new Scene(pane, 500, 400);
		stage.setScene(scene);
		stage.show();

	}
}
