package View;

import View.Displayable.DisplayCity;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;


import java.awt.image.DataBufferDouble;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import static javafx.scene.control.cell.ChoiceBoxTableCell.forTableColumn;

public class TableCityController implements Initializable {

    private View view;

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

        CityEdit.setEditable(true);

        city_col_name.setCellFactory(TextFieldTableCell.forTableColumn());
        city_col_name.setOnEditCommit(displayCityStringCellEditEvent -> {
            System.out.println(displayCityStringCellEditEvent.getNewValue());
            System.out.println(displayCityStringCellEditEvent.getRowValue().getId());
            view.EditCity(displayCityStringCellEditEvent.getRowValue().getId(),
                    displayCityStringCellEditEvent.getRowValue().getX(),
                    displayCityStringCellEditEvent.getRowValue().getY(),
                    displayCityStringCellEditEvent.getRowValue().getSize(),
                    displayCityStringCellEditEvent.getNewValue() );}
            );



        //city_col_x.setCellFactory(TextFieldTableCell.forTableColumn());

        //city_col_y.setCellFactory(TextFieldTableCell.forTableColumn());

        //city_col_size.setCellFactory(TextFieldTableCell.forTableColumn());

    }



    // clear and redraw the table
    public void refresh(Vector<DisplayCity> displayCities ){
        CityEdit.getItems().setAll(displayCities);
    }

    @Override
    public String toString() {
        return "Controller tab city";
    }

    public void setMaman(View view) {
        this.view = view;

    }
}
