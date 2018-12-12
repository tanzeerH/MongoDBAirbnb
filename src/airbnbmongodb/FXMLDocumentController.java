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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author hossa
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    TableView tbAll;
    
    private  ObservableList<Listing> data;
    
    private  ArrayList<DBObject> objctList;
   
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
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
      objctList = readAll();
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
       
       Name.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Listing, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Listing, String> t) {
                  String val= t.getNewValue();
                  int row = t.getTablePosition().getRow();
                  int col = t.getTablePosition().getColumn();
                    BasicDBObject updateDocument = new BasicDBObject();
                    updateDocument.append("$set", new BasicDBObject().append("name", val.toString()));
                    
                    System.out.println("ObjectId("+data.get(row)._id+")");
                    BasicDBObject searchQuery2 = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );

                    Connection.getDataBaseInstance().getCollection(Constant.LISTING).update(searchQuery2, updateDocument);
                    JOptionPane.showMessageDialog(null, "update Successful");
                }
            }
    );
       
        
      //setting url
     TableColumn Url = new TableColumn("Url"); 
     Url.setPrefWidth(column_maxWidth);
     Url.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("url"));  
     Url.setCellFactory(TextFieldTableCell.forTableColumn());  
     
     TableColumn Summary = new TableColumn("Summary"); 
     Summary.setPrefWidth(column_maxWidth);
     Summary.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("summary"));  
     Summary.setCellFactory(TextFieldTableCell.forTableColumn()); 
     
     //street
      TableColumn Street = new TableColumn("Street"); 
     Street.setPrefWidth(column_maxWidth);
     Street.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("street"));  
     Street.setCellFactory(TextFieldTableCell.forTableColumn()); 
     
     //guest
      TableColumn Guest = new TableColumn("Guest"); 
     Guest.setPrefWidth(column_maxWidth);
     Guest.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("guest"));  
     Guest.setCellFactory(TextFieldTableCell.forTableColumn());
     
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
       
     
      //zip
     TableColumn Zip = new TableColumn("Zip"); 
     Zip.setPrefWidth(column_maxWidth);
     Zip.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("guest"));  
     Zip.setCellFactory(TextFieldTableCell.forTableColumn());
     
     //country
      TableColumn Country = new TableColumn("Country"); 
     Country.setPrefWidth(column_maxWidth);
     Country.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("country"));  
     Country.setCellFactory(TextFieldTableCell.forTableColumn());
     
     
     //Latitudetude
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
     
     //bathrooms
      TableColumn Bathrooms = new TableColumn("Bathrooms"); 
     Bathrooms.setPrefWidth(column_maxWidth);
     Bathrooms.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("bathrooms"));  
     Bathrooms.setCellFactory(TextFieldTableCell.forTableColumn());
     
     
     //bedroom
      TableColumn Bedroom = new TableColumn("Bedroom"); 
     Bedroom.setPrefWidth(column_maxWidth);
     Bedroom.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("bedroom"));  
     Bedroom.setCellFactory(TextFieldTableCell.forTableColumn());
     
      TableColumn Price = new TableColumn("Price"); 
     Price.setPrefWidth(column_maxWidth);
     Price.setCellValueFactory(
            new PropertyValueFactory<Listing, String>("price"));  
     Price.setCellFactory(TextFieldTableCell.forTableColumn());
       
       
       tbAll.setItems(data);
        // tbAll.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        
       tbAll.getColumns().addAll(Name,Url,Summary,Street,City, Zip,Bedroom,Bathrooms,Lat,Longitude,Price);
 

       
       //to integrate update
       
         
      //  tbAll.getColumns().addAll(Update);
        tbAll.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = tbAll.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                 TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                int row = tbAll.getSelectionModel().getSelectedIndex();
                int col = tablePosition.getColumn();
                System.err.println(""+ data.get(row).name + " table position "+ tablePosition.getColumn());
                if( col == 0)
                {
                    BasicDBObject updateDocument = new BasicDBObject();
                    updateDocument.append("$set", new BasicDBObject().append("name", val.toString()));
                    
                    BasicDBObject searchQuery2 = new BasicDBObject().append("_id", data.get(row)._id);

                    Connection.getDataBaseInstance().getCollection(Constant.LISTING).update(searchQuery2, updateDocument);
                    JOptionPane.showMessageDialog(null, "update Successful");
                }
                // System.out.println("Selected Value" + val + "ssn "+ tableDrug.getSelectionModel().getSelectedIndex());
                
            }
        });
   
        }
    
}
