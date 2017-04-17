//import javafx.*;
package GraphicalUserInterface;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class GUI_Practice extends Application{


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("LBMS Client Window");

        Button button = new Button("New Connection");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 500, 300);
        primaryStage.setScene(scene);

        TabPane pane = new TabPane();

        Tab tab = new Tab(" + ");
        tab.setClosable(false);
        tab.setContent(button);
        pane.getTabs().add(tab);


        layout.getChildren().add(pane);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                newConnection(pane);
            }
        });

        primaryStage.show();
        newConnection(pane);
    }

    public void newConnection(TabPane pane){
        Tab newTab = new Tab("New");
        pane.getTabs().add(pane.getTabs().size()-1, newTab);
    }
}