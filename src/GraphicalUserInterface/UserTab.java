//import javafx.*;
package GraphicalUserInterface;
import UserInterface.*;

import javafx.fxml.FXML;
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


public class UserTab extends Tab{

    private String clientid;
    private TextField inputArea = new TextField();
    private TextArea outputArea = new TextArea();
    private Button button = new Button("Enter");
    private Label inputLabel = new Label("Request: ");
    private Label outputLabel = new Label("Terminal: ");
    private Label clockLabel = new Label();
    private String commandText = "";
    private BorderPane borderPane;
    private UserInterface UI;

    public UserTab(String id){
        super("Client "+ id);

        UI = new UserInterface();
        UI.connect();
        inputArea.setEditable(true);
        outputArea.setEditable(false);
        pushToTerminal("Client: " + UI.getClientId());

        borderPane = new BorderPane();
        setBorderPane(borderPane);
        setContent(borderPane);


        UI.setup();
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
        outputArea.setWrapText(true);

        // Bottom
        inputArea.setPrefSize(300, 15);
        HBox bottomBox = new HBox(inputLabel, inputArea, button);
        button.setOnAction(event -> submitCommand());
        //inputArea.getOnKeyPressed(KeyCode.ENTER submitCommand(););

        bottomBox.setPadding(new Insets(10));
        bottomBox.setSpacing(10);
        borderPane.setBottom(bottomBox);
        BorderPane.setAlignment(bottomBox, Pos.BOTTOM_CENTER);

        borderPane.setStyle("-fx-padding: 10;");
    }

    public void pushToTerminal(String text){
        outputArea.appendText(text + "\n");
    }

    private void submitCommand(){
        commandText += "" + inputArea.getText();
        commandText.replaceAll("\n", "");
        if (commandText.trim().length() > 0)
            pushToTerminal(inputArea.getText());

        while(commandText.contains(";")) {
            //UI.submitGUICommand(this, commandText.substring(1, commandText.indexOf(';')));
            //pushToTerminal("Command: " + commandText.substring(0, commandText.indexOf(';')+1));
            UI.submitGUICommand(this, commandText.substring(0, commandText.indexOf(';')));
            if(commandText.length() == commandText.indexOf(";"))
                commandText="";
            else
                commandText = commandText.substring(commandText.indexOf(';')+1).trim();
        }

        //pushToTerminal();
        inputArea.setText("");
    }
}