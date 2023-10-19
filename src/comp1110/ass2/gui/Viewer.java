package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     */

    void displayState(String state) {
        GameState gameState = new GameState(state);
        BoardOther board = gameState.getBoard();
        Assam assam = gameState.getAssam();
        ArrayList<Player> players = gameState.getPlayers();
        GridPane grid = new GridPane();
        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 7; row++) {
                if (gameState.getBoard().getRugs()[column][row] != null) {
                    Rectangle square = new Rectangle(50, 50);
                    switch (board.getRug(column, row).getColour()) {
                        case 'c':
                            square.setFill(Color.CYAN);
                            square.setStroke(Color.BLACK);
                            grid.add(square, column, row);
                        case 'y':
                            square.setFill(Color.YELLOW);
                            square.setStroke(Color.BLACK);
                            grid.add(square, column, row);
                        case 'r':
                            square.setFill(Color.RED);
                            square.setStroke(Color.BLACK);
                            grid.add(square, column, row);
                        case 'p':
                            square.setFill(Color.PURPLE);
                            square.setStroke(Color.BLACK);
                            grid.add(square, column, row);
                    }
                }
                else {
                Rectangle square = new Rectangle(50, 50);
                square.setFill(Color.WHITE);
                square.setStroke(Color.BLACK);
                grid.add(square, column, row);}
            }
        }
        Polygon triangle = new Polygon();
        double centerX = 0;
        double centerY = 0;
        double baseLength = 20;
        double height = 40;
        double x1 = centerX - baseLength / 2;
        double y1 = centerY + height / 2;
        double x2 = centerX + baseLength / 2;
        double y2 = centerY + height / 2;
        double x3 = centerX;
        double y3 = centerY - height / 2;
        triangle.getPoints().addAll(x1, y1, x2, y2, x3, y3);
        triangle.setFill(Color.BLACK);
        int assamDirection = assam.getDirection();
        IntPair assamPosition = assam.getPosition();
        triangle.setRotate(assamDirection);
        grid.add(triangle, assamPosition.getX(), assamPosition.getY());
        GridPane.setHalignment(triangle, HPos.CENTER);
        GridPane.setValignment(triangle, VPos.CENTER);

        TextField cyanTextField = new TextField(players.get(0).toString());
        TextField yellowTextField = new TextField(players.get(1).toString());
        TextField redTextField = new TextField(players.get(2).toString());
        TextField purpleTextField = new TextField(players.get(3).toString());
        VBox vbox = new VBox(10); // 10 pixels spacing
        vbox.getChildren().addAll(cyanTextField, yellowTextField, redTextField, purpleTextField);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(grid, vbox);
        root.getChildren().add(hBox);
    }

    /**
     * Create a basic text field for input and a refresh button.
     */

    private void makeControls() {
        Label boardLabel = new Label("Game State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(800);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Marrakech Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
