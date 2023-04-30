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
//        BackgroundImage backgroundImage = new BackgroundImage(
//                new Image("assets/Missouri_post_refit.jpg", 803, 578, false, true),
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                BackgroundSize.DEFAULT
//        );


        BorderPane container = new BorderPane();
        BorderPane containerRules = new BorderPane();
//        container.setBackground(new Background(backgroundImage));
//        containerRules.setBackground(new Background(backgroundImage));
        Scene mainScene = new Scene(container,800,600);
        Scene ruleScene = new Scene(containerRules,800,600);

        Label gameLabel = new Label("Battleship Game");
        gameLabel.setFont(new Font("Charles worth", 30));
        gameLabel.setTextFill(Color.BLACK);

        Label gameLabel3 = new Label("Battleship Game - rules");
        gameLabel3.setFont(new Font("Charles worth", 30));
        gameLabel3.setTextFill(Color.BLACK);

        //rules
        Label ruleLabel=new Label(
                "Rules of the game \n" +
                        "1. Each player places their ships on their own game board \n" +
                        "2. Take turns calling out coordinates to bomb enemy's ships\n" +
                        "3. You can hit ship, rock or water \n" +
                        "4. You win when enemy has all ships sank\n" +
                        "5. Remember to have fun!");
        ruleLabel.setFont(new Font("Charles worth", 15));
        ruleLabel.setTextFill(Color.BLACK);


        Button newGameButton = new Button("Multiplayer");
        newGameButton.setOnAction(e -> {
//            primaryStage.setScene(gameScene);
            System.out.print("213");
            }
        );

        Button AgainstAI = new Button("Against AI");
        AgainstAI.setOnAction(e -> {
//            primaryStage.setScene(gameScene);
//            setAI(true);
            System.out.print("set proper game scene here");
            }
        );
        Button rulesButton = new Button("Rules");
        rulesButton.setOnAction(e -> {
            System.out.print("rules ");
            primaryStage.setScene(ruleScene);
            }
        );
        Button backToMenu2 = new Button("Back to menu");
        backToMenu2.setOnAction(e -> {
            primaryStage.setScene(mainScene);});

        VBox menuLayout = new VBox(20);
        menuLayout.setPadding(new Insets(50));
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: DARKSLATEBLUE;");
        menuLayout.getChildren().addAll(gameLabel, newGameButton, AgainstAI, rulesButton);
        mainScene.setRoot(menuLayout);

        VBox ruleLayout = new VBox(20);
        ruleLayout.setPadding(new Insets(50));
        ruleLayout.setAlignment(Pos.CENTER);
        ruleLayout.setStyle("-fx-background-color: DARKSLATEBLUE;");
        ruleLayout.getChildren().addAll(gameLabel3, ruleLabel, backToMenu2);
        ruleScene.setRoot(ruleLayout);

        primaryStage.setTitle("Battleship - v. 0.1");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}