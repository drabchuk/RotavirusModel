package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Rotavirus ");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("parameters.fxml"));
        AnchorPane load = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Parameters");
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();

        Plotter.main("plot");

    }

    public static void main(String[] args) {
        launch(args);
    }



}
