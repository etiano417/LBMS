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
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("LBMS Client Window");
        TabPane pane = new TabPane();

        Tab tab = new Tab(" + ");
        tab.setClosable(false);
        pane.getTabs().add(tab);

        Button button = new Button("New Connection");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        //TabPane pane = new TabPane();

        Tab newtab = new Tab(" + ");
        tab.setClosable(false);
        tab.setContent(button);
        pane.getTabs().add(newtab);

        Scene scene = new Scene(pane, 500, 300);
        stage.setScene(scene);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                newConnection(pane);
            }
        });


        stage.show();
        newConnection(pane);
    }

    public void newConnection(TabPane pane){
        Tab newTab = new UserTab("New");
        pane.getTabs().add(pane.getTabs().size()-1, newTab);
    }
}