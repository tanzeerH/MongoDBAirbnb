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
import Util.MapReduceByRoomAndPrice;
import Util.MapReduceGroupByRating;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author hossa
 */
public class FXMLMapReduceDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    TextArea resq1; 
    
     @FXML
    TextField tvLimit;
     
    @FXML
    TextField tvPrice;
      
    @FXML
    TextField tvRoom;
    
    private  ObservableList<Listing> data;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
     @FXML
    private void handleQuery1(ActionEvent event) {
        String cost = tvLimit.getText().toString();
       String res= MapReduce.sampleMapReduce(cost);
       resq1.setText(res);
    }
      @FXML
    private void handleQuery2(ActionEvent event) {
        System.out.println("query2 called");
        String cost = tvPrice.getText().toString();
        String room = tvRoom.getText().toString();
       String res= MapReduceByRoomAndPrice.sampleMapReduce(cost, room);
     // String res= MapReduceGroupByRating.sampleMapReduce();
       resq1.setText(res);
    }
     @FXML
     private void handleQuery3(ActionEvent event) {
        System.out.println("query2 called");
       // String cost = tvPrice.getText().toString();
       // String room = tvRoom.getText().toString();
      // String res= MapReduceByRoomAndPrice.sampleMapReduce(cost, room);
      String res= MapReduceGroupByRating.sampleMapReduce();
       resq1.setText(res);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
     
    } 
    
     
    
}
