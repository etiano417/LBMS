//import javafx.*;
package GraphicalUserInterface;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class UserTab extends Tab{

    private TextField input;
    private TextArea output;

    private String clientid;
    private TextField inputArea = new TextField();
    private TextArea outputArea = new TextArea();
    private Button okButton = new Button("Enter");
    private Label inputLabel = new Label("Request: ");
    private Label outputLabel = new Label("Terminal: ");
    private Label clockLabel = new Label();
    private String buildText = "";
    private BorderPane borderPane;
    public UserTab(){
        super();
        instantiate(new BorderPane());
    }

    public UserTab(String s){
        super(s);
        inputArea.setEditable(true);
        outputArea.setEditable(false);

        borderPane = new BorderPane();
        setBorderPane(borderPane);
        setContent(borderPane);
    }

    public void instantiate(BorderPane borderPane){
        input = new TextField();
        output = new TextArea("TEXT");
        input.setVisible(true);
        input.setEditable(true);
        output.setVisible(true);
        output.setEditable(false);

        //Shared
        borderPane.setPadding(new Insets(10));

        //Top
        Region region = new Region();
        region.setPrefSize(500, 300);
        HBox.setHgrow(region, Priority.ALWAYS);
        HBox topBox = new HBox(region);
        borderPane.setTop(topBox);

        //Center
        borderPane.setCenter(output);

        Label inputLabel = new Label("input");

        //Bottom
        HBox box = new HBox(inputLabel, input);
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        borderPane.setBottom(box);
        BorderPane.setAlignment(box, Pos.BOTTOM_CENTER);
    }

    private void setBorderPane(BorderPane borderPane) {
        // Top
        clockLabel.setPadding(new Insets(5, 20, 5, 20));
        Region region1 = new Region();
        Region region2 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        HBox.setHgrow(region2, Priority.ALWAYS);
        HBox topBox = new HBox(region1, region2, clockLabel);
        borderPane.setTop(topBox);
        BorderPane.setAlignment(topBox, Pos.TOP_CENTER);
        // Center
        borderPane.setCenter(outputArea);
        // Bottom
        HBox box = new HBox(inputLabel, inputArea, okButton);
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        borderPane.setBottom(box);
        BorderPane.setAlignment(box, Pos.BOTTOM_CENTER);

        borderPane.setStyle("-fx-padding: 10;");
    }
}