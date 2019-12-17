package sample;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort +
                "/" + dbName + "?serverTimezone=Europe/Moscow&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    public void enterNickName (TopUser topUser){
        String insert = "INSERT INTO " + Const.TOPUSER_TABLE + "(" +
                Const.TOPUSER_NAME + ")" + "VALUES(?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, topUser.getNickName());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void enterProgress (Progress progress){
        String insert = "INSERT INTO " + Const.PROGRESS_TABLE + "(" +
                Const.PROGRESS_PROGRESS + ")" + "VALUES(?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, progress.getProgress());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<TopUser> showAll (){
        ArrayList<TopUser> al=new ArrayList<>();
        ResultSet resSet = null;
        String query = "SELECT * FROM " + Const.TOPUSER_TABLE;

        try {
            PreparedStatement prStAll = getDbConnection().prepareStatement(query);
            resSet = prStAll.executeQuery();

            while (resSet.next()) {


                String name = resSet.getString("nickname");
               TopUser tu=new TopUser(name);
                //System.out.println("- "+name);
               al.add(tu);
            }


        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<Progress> showAllProgress (){
        ArrayList<Progress> al=new ArrayList<>();
        ResultSet resSet = null;
        String query = "SELECT * FROM " + Const.PROGRESS_TABLE;

        try {
            PreparedStatement prStAll = getDbConnection().prepareStatement(query);
            resSet = prStAll.executeQuery();

            while (resSet.next()) {

                int progress = resSet.getInt("progress");
                Progress tu=new Progress(progress);
                //System.out.println("- "+name);
                al.add(tu);
            }


        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return al;
    }


    /*public ResultSet showAllTopUser (TopUser topUser){
        ResultSet resSet = null;
        String query = "SELECT * FROM " + Const.TOPUSER_TABLE;

        try {
            PreparedStatement prStAll = getDbConnection().prepareStatement(query);
            resSet = prStAll.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return resSet;
    }*/



}
