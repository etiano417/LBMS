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

        Button button = new Button("New Connection");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Tab newtab = new Tab(" + ");
        newtab.setClosable(false);
        newtab.setContent(button);
        pane.getTabs().add(newtab);

        Scene scene = new Scene(pane, 600, 400);
        stage.setScene(scene);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newConnection(pane);
            }
        });


        stage.show();
        newConnection(pane);
    }

    public void newConnection(TabPane pane){
        clients+=1;
        Tab newTab = new UserTab(""+clients);
        pane.getTabs().add(pane.getTabs().size()-1, newTab);
    }
}