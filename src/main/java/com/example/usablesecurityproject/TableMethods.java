package com.example.usablesecurityproject;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableMethods {
	public static void addButtonToTable(Stage primaryStage, TableView<Exam> table) {
		TableColumn<Exam, Void> colBtn = new TableColumn("View Questions");
		colBtn.setMinWidth(156);
		Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>> cellFactory = new Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>>() {
			@Override
			public TableCell<Exam, Void> call(final TableColumn<Exam, Void> param) {
				final TableCell<Exam, Void> cell = new TableCell<Exam, Void>() {

					private final Button btn = new Button("View Questions");

					{
						btn.setOnAction((ActionEvent event) -> {

							primaryStage.setScene(
									Scenes.questionsScene(primaryStage, getTableView().getItems().get(getIndex())));

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);
		colBtn.setPrefWidth(110);
		table.getColumns().add(colBtn);

	}

	public static void addButtonToQuestionsTable(Stage primaryStage, TableView<Question> table) {
		TableColumn<Question, Void> colBtn = new TableColumn("View Questions' Text");
		colBtn.setMinWidth(156);
		Callback<TableColumn<Question, Void>, TableCell<Question, Void>> cellFactory = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
			@Override
			public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
				final TableCell<Question, Void> cell = new TableCell<Question, Void>() {

					private final Button btn = new Button("View Text");

					{
						btn.setOnAction((ActionEvent event) -> {
							
							HelpingWindows.showQuestion(getTableView().getItems().get(getIndex()));

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);
		colBtn.setPrefWidth(110);
		table.getColumns().add(colBtn);

	}

	public static void addButtonToTable(Stage primaryStage, TableView<Exam> table, Teacher t) {
		TableColumn<Exam, Void> colBtn = new TableColumn("View Questions");

		colBtn.setMinWidth(156);

		Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>> cellFactory = new Callback<TableColumn<Exam, Void>, TableCell<Exam, Void>>() {
			@Override
			public TableCell<Exam, Void> call(final TableColumn<Exam, Void> param) {
				final TableCell<Exam, Void> cell = new TableCell<Exam, Void>() {

					private final Button btn = new Button("View Questions");

					{
						btn.setOnAction((ActionEvent event) -> {

							primaryStage.setScene(
									Scenes.questionsScene(primaryStage, getTableView().getItems().get(getIndex()), t));

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);
		colBtn.setPrefWidth(110);
		table.getColumns().add(colBtn);
	}
}
