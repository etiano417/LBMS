//import javafx.*;
package GraphicalUserInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application{

    int clients = 0;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("LBMS Client Window");
        TabPane pane = new TabPane();

        Tab newTab = new Tab();
        Label label = new Label("+");
        label.setOnMouseClicked(event -> newConnection(pane));
        newTab.setGraphic(label);
        newTab.setClosable(false);
        pane.getTabs().add(newTab);
        newTab.setDisable(true);
        newTab.setStyle("-fx-opacity: 1;");

        Scene scene = new Scene(pane, 600, 400);
        stage.setScene(scene);

        stage.show();
        newConnection(pane);
    }

    public void newConnection(TabPane pane){
        clients+=1;
        Tab newTab = new UserTab(""+clients);
        pane.getTabs().add(pane.getTabs().size()-1, newTab);
        pane.getSelectionModel().select(newTab);
    }
}