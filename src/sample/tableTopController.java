package sample;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.animation.Shake;

public class tableTopController {

    //private ObservableList<TopUser> nickNameData;// = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backToStartWindow;


    @FXML
    private TableView<TopUser> leaderList;

    @FXML
    private TableColumn<TopUser, String> TableColumnNickname;


    @FXML
    private TableView<Progress> progressList;

    @FXML
    private TableColumn<Progress, String> TableColumnProgress;



    @FXML
    void initialize() {
        //ArrayList<TopUser> al;

        DatabaseHandler dbHandler = new DatabaseHandler();
        /*al=dbHandler.showAll();
       al.forEach((u)->{System.out.println("R"+u.getNickName());});*/

        leaderList.setItems(FXCollections.observableArrayList((  dbHandler.showAll())));
        progressList.setItems(FXCollections.observableArrayList((  dbHandler.showAllProgress())));
        TableColumnNickname.setCellValueFactory(new PropertyValueFactory<>("nickName"));
        TableColumnProgress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        //String nickName = TableColumnNickname.getText().trim();


        backToStartWindow.setOnAction(event -> {
            openNewScene("/sample/startWindow.fxml");
        });

    }

    public void openNewScene (String window){
        backToStartWindow.getScene().getWindow().hide();
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


