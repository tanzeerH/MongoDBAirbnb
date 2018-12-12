/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airbnbmongodb;

import Util.Connection;
import Util.Constant;
import Util.Listing;
import Util.MapReduce;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author hossa
 */
public class FXMLInsertDocumentController implements Initializable {
    @FXML
    private TextField tvName;
     @FXML
    private TextField tvSummary;
    @FXML
    private TextField tvGuest;
    @FXML
    private TextField tvReview;
    @FXML
    private TextField tvZip;
    @FXML
    private TextField tvlat;
    @FXML
    private TextField tvURL;
    @FXML
    private TextField tvSTreet;
    @FXML
    private TextField tvCity;
    @FXML
    private TextField tvCountry;
     @FXML
    private TextField tvlon;
      @FXML
    private TextField tvBed;
    @FXML
    private TextField tvPrice;
    @FXML
    private TextField tvAmn;
    @FXML
    private Label label;
    
    @FXML
    Button btninsert;
     @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void handleInsertAction(ActionEvent event) {
        Stage stage;
        Parent root;
        stage = (Stage) btninsert.getScene().getWindow();
        //load up OTHER FXML document
        try {
            System.out.println("insert clicked");
             root = FXMLLoader.load(getClass().getResource("Insert.fxml"));
             Scene scene = new Scene(root,Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT);
            stage.setTitle("Insert Document");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleMap(ActionEvent event) {
        Stage stage;
        Parent root;
        stage = (Stage) btninsert.getScene().getWindow();
        //load up OTHER FXML document
        try {
             root = FXMLLoader.load(getClass().getResource("MapReduce.fxml"));
             Scene scene = new Scene(root,Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT);
            stage.setTitle("MapReduce");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    @FXML
    private void handleInsert(ActionEvent event) {
        System.out.println("insert button clicked");
        BasicDBObject basicDBObject = new BasicDBObject();
        
        String name = tvName.getText().toString();
        basicDBObject.put("name", name);
        
        //url
        String url = tvURL.getText().toString();
        basicDBObject.put("url", url);
        
        String summary = tvSummary.getText().toString();
        basicDBObject.put("summary", summary);
        
         String guest = tvGuest.getText().toString();
        basicDBObject.put("guest", guest);
        
        String review = tvReview.getText().toString();
        basicDBObject.put("review", review);
        
        //zip
         String zip = tvZip.getText().toString();
        basicDBObject.put("zip", zip);
        
          String lat = tvlat.getText().toString();
        basicDBObject.put("latitude", lat);
        
        String street = tvSTreet.getText().toString();
        basicDBObject.put("street", street);
        
         String city = tvCity.getText().toString();
        basicDBObject.put("city", city);
        
        String country = tvCountry.getText().toString();
        basicDBObject.put("country", country);
        
        //longitude
         String lon = tvlon.getText().toString();
        basicDBObject.put("longitude", lon);
        
         String bed = tvBed.getText().toString();
        basicDBObject.put("bed", bed);
        
        String price = tvPrice.getText().toString();
        basicDBObject.put("price", price);
        
         String amn = tvAmn.getText().toString();
        basicDBObject.put("amenities", lon);
        
        Connection.getDataBaseInstance().getCollection(Constant.LISTING).insert(basicDBObject);
        JOptionPane.showMessageDialog(null, "Document Inserted");
        
        //
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
     
    } 
    
     
    
}
