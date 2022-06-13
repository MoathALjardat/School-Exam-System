package com.example.usablesecurityproject;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setScene(Scenes.programMainScene(primaryStage));
		Queries.getManager();
		primaryStage.setResizable(false);

		primaryStage.show();

	}
}
