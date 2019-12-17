package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.animation.Shake;



public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField writeNick;

    @FXML
    private Button startGameBtn;

    @FXML
    private Button showAllResult;


    @FXML
    void initialize() {
        startGameBtn.setOnAction(event -> {

            String nickNameString = writeNick.getText().trim();

            Shake writeNickAnim = new Shake(writeNick);

            if (writeNick.getText().equals(""))
                writeNickAnim.playAnim();
            else {

            Stage closeWindow = (Stage) startGameBtn.getScene().getWindow();
            closeWindow.close();

                DatabaseHandler dbHandler = new DatabaseHandler();
                TopUser topUser = new TopUser(nickNameString);
                dbHandler.enterNickName(topUser);

                try {
                    gameVosmashki();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            /*String nickNameString = writeNick.getText().trim();
            if (!nickNameString.equals("")){


                Stage closeWindow = (Stage) startGameBtn.getScene().getWindow();
                closeWindow.close();

                try {
                    gameVosmashki();
                    DatabaseHandler dbHandler = new DatabaseHandler();
                    TopUser topUser = new TopUser(nickNameString);
                    dbHandler.enterNickName(topUser);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            } else {
                Shake writeNickAnim = new Shake(writeNick);
                writeNickAnim.playAnim();
            }*/

        });

        showAllResult.setOnAction(event -> {

            openNewScene("/sample/tableTop.fxml");
        });


    }

    public static void gameVosmashki () throws IOException, InterruptedException {
        MainFrame mainFrame = new MainFrame();
        Thread tuning = new Thread(mainFrame, "MainFrame");
        tuning.start();
        tuning.join();

        Puzzle puzzleField = new Puzzle(mainFrame.width, mainFrame.height);
        Thread game = new Thread(puzzleField, "PuzzleField");
        game.start();
        game.join();


        System.exit(0);
    }

    public void openNewScene (String window){
        showAllResult.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }


}
