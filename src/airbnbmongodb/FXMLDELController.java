/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airbnbmongodb;

import Util.Connection;
import Util.Constant;
import Util.Listing;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author hossa
 */
public class FXMLDELController implements Initializable{
    
     @FXML
    TableView tbAll;
    
    private  ObservableList<Listing> data;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
         Stage stage;
        Parent root;
        stage = (Stage) tbAll.getScene().getWindow();
        //load up OTHER FXML document
        try {
            System.out.println("insert clicked");
             root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
             Scene scene = new Scene(root,Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT);
            stage.setTitle("Insert Document");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleInsertAction(ActionEvent event) {
        Stage stage;
        Parent root;
        stage = (Stage) tbAll.getScene().getWindow();
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
    private void handleUpdate(ActionEvent event) {
        Stage stage;
        Parent root;
        System.out.println("update clicked");
        stage = (Stage) tbAll.getScene().getWindow();
        //load up OTHER FXML document
        try {
            System.out.println("insert clicked");
             root = FXMLLoader.load(getClass().getResource("FXMLDocumentDelete.fxml"));
             Scene scene = new Scene(root,Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT);
            stage.setTitle("Delete Document");
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
        stage = (Stage) tbAll.getScene().getWindow();
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
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      refresh();
    } 
    private void refresh()
    {
        ArrayList<DBObject> objctList = readAll();
      ArrayList<Listing> docs = Listing.getListingFromDocObject(objctList);
      data = FXCollections.observableArrayList(docs);
      initTableView();
    }
    public ArrayList<DBObject> readAll()
    {
        ArrayList<DBObject> objctList = new ArrayList<>();
        DB db = Connection.getDataBaseInstance();
        DBCursor cursor = db.getCollection(Constant.LISTING).find();
        if(cursor.hasNext())
            cursor.next();
	while(cursor.hasNext()) {
            
            objctList.add( cursor.next());
	    
	}
        return objctList;
       
    }
     private void initTableView()
        {
        int column_maxWidth = 400;
        tbAll.setEditable(true);
       
    
        //setting name
     TableColumn Name = new TableColumn("Name"); 
     Name.setPrefWidth(column_maxWidth);
        Name.setCellValueFactory(
                new PropertyValueFactory<Listing, String>("name"));  
       Name.setCellFactory(TextFieldTableCell.forTableColumn());
       
        
      //setting url
     TableColumn Url = new TableColumn("Url"); 
     Url.setPrefWidth(column_maxWidth);
        Url.setCellValueFactory(
                new PropertyValueFactory<Listing, String>("url"));  
       Url.setCellFactory(TextFieldTableCell.forTableColumn());     
         tbAll.setItems(data);
        // tbAll.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
         TableColumn Price = new TableColumn("Price"); 
     Price.setPrefWidth(column_maxWidth);
     Price.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("price"));  
     Price.setCellFactory(TextFieldTableCell.forTableColumn());
     
     //Latitude
      TableColumn Lat = new TableColumn("latitude"); 
     Lat.setPrefWidth(column_maxWidth);
     Lat.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("latitude"));  
     Lat.setCellFactory(TextFieldTableCell.forTableColumn());
     
     //Longitude
     TableColumn Longitude = new TableColumn("longitude"); 
     Longitude.setPrefWidth(column_maxWidth);
     Longitude.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("longitude"));  
     Longitude.setCellFactory(TextFieldTableCell.forTableColumn());
     
     //review
      TableColumn Review = new TableColumn("review"); 
     Review.setPrefWidth(column_maxWidth);
     Review.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("review"));  
     Review.setCellFactory(TextFieldTableCell.forTableColumn());
     
     
     //city
      TableColumn City = new TableColumn("city"); 
     City.setPrefWidth(column_maxWidth);
     City.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("city"));  
     City.setCellFactory(TextFieldTableCell.forTableColumn());
         
         
         TableColumn Remove = new TableColumn("Remove");
         Remove.setCellValueFactory(new PropertyValueFactory<Listing, String>("remove"));
         Remove.setPrefWidth(200);
         
         tbAll.getColumns().addAll(Remove);
         tbAll.getColumns().addAll(Name,Url,Review,Price,City,Lat,Longitude);
        tbAll.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = tbAll.getSelectionModel().getSelectedCells();

        try{
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                int row = tbAll.getSelectionModel().getSelectedIndex();
                int col = tablePosition.getColumn();
                if (val.toString().equals("Delete")) {
                   // String s = ((Drug) tableDrug.getItems().get(i)).getSsn();
                   // System.out.println(s);
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialog_result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if (dialog_result == 0) {
                       // String id = data.get(row)._id;
                        BasicDBObject basicDBObject = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );
                        Connection.getDataBaseInstance().getCollection(Constant.LISTING).remove(basicDBObject);
                        JOptionPane.showMessageDialog(null, "Document Deleted.");
                        refresh();
                        System.out.println("Yes option");
                    } else {
                        System.out.println("No Option");
                    }
                }
            }
        });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        

   
        }
}
