package oop.cw.guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class MainFrontend extends Application {
    @Override
    public void start(Stage primaryStage) {
        ConsoleApplication.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        GuiElements guiElements = new GuiElements();
        allClubs.sort(Collections.reverseOrder());
        primaryStage.getIcons().add(new Image("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png"));
        displayTable(allClubs, primaryStage, guiElements);
    }

    public static void displayTable(List<FootballClub> allClubs, Stage window, GuiElements guiElements) {
        ImageView eplLion = GuiElements.imageViewLay("" +
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png", 0, 500,
                220, 290);
        ImageView eplText = GuiElements.imageViewLay("" +
                        "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-text.png", 200, 555,
                100, 180);

        TableView<FootballClub> tableView = new TableView<>();
        List<TableColumn<FootballClub, String>> allColumns = GuiElements.generatePointsTableColumns(tableView);

        for (TableColumn<FootballClub, String> eachColumn : allColumns) {
            tableView.getColumns().add(eachColumn);
        }

        for (FootballClub footballClub : allClubs) {
            tableView.getItems().add(footballClub);
        }

        AnchorPane anchorPane = GuiElements.anchor();
        StackPane stackPane = GuiElements.stackPane(1366, 500);
        stackPane.getChildren().add(tableView);
        anchorPane.getChildren().addAll(stackPane, eplLion, eplText);
        Scene scene = guiElements.scene(anchorPane, 1366, 700, "style.css");
        window.setScene(scene);
        window.setTitle("PREMIER LEAGUE MANAGER");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
