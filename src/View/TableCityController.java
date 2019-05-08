package View;

import View.Displayable.DisplayCity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class TableCityController implements Initializable {

    @FXML
    private TableView<DisplayCity> CityEdit;

    @FXML
    private  TableColumn<DisplayCity , String> city_col_id;
    @FXML
    private  TableColumn<DisplayCity , String> city_col_name;
    @FXML
    private  TableColumn<DisplayCity , String> city_col_x;
    @FXML
    private  TableColumn<DisplayCity , String> city_col_y;
    @FXML
    private  TableColumn<DisplayCity , String> city_col_size;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        city_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        city_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        city_col_x.setCellValueFactory(new PropertyValueFactory<>("x"));
        city_col_y.setCellValueFactory(new PropertyValueFactory<>("y"));
        city_col_size.setCellValueFactory(new PropertyValueFactory<>("size"));

    }


    public void refresh(Vector<DisplayCity> displayCities ){
        CityEdit.getItems().setAll();
    }

    @Override
    public String toString() {
        return "Con trolleur tab city";
    }
}
