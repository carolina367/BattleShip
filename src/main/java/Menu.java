import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.util.Random;

public class Menu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label gameLabel = new Label("Battleship Game");
        gameLabel.setFont(new Font("Arial", 50));
        gameLabel.setTextFill(Color.BLACK);

        Button newGameButton = new Button("Start New Game");
        newGameButton.setOnAction(e -> {
        System.out.print("213");});

        Button savedGameButton = new Button("Continue Saved Game");
        savedGameButton.setOnAction(e -> {
            //Downloading data here
            //primaryStage.setScene(set proper game scene here);
            System.out.print("set proper game scene here");
        }
        );




        BorderPane container = new BorderPane();
        Scene mainScene = new Scene(container);
//        Scene ruleScene = new Scene(container);

//        Button rulesButton = new Button("Rules");
//        rulesButton.setOnAction(e -> {
//            primaryStage.setScene(ruleScene);});

        primaryStage.setScene(mainScene);
        VBox menuLayout = new VBox(20);
        menuLayout.setPadding(new Insets(50));
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: DARKCYAN;");
        menuLayout.getChildren().addAll(gameLabel, newGameButton, savedGameButton
//                ,rulesButton
        );
        mainScene.setRoot(menuLayout);

        primaryStage.setTitle("Battleship - v. 0.1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}