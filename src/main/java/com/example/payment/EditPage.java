package com.example.payment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class EditPage {
    public Button btn_update;
    @FXML
    private TextField text_name;

    @FXML
    private TextField text_tariff;

    @FXML
    private TextField text_prev;

    @FXML
    private TextField text_curr;

    @FXML
    private TextField text_count;
    @FXML
    private TableView<DataItem> table_list;

    @FXML
    private TableColumn<?, ?> columnName;

    // Добавьте этот метод для установки данных в текстовые поля
    public void setInitialData(DataItem dataItem) {
        text_name.setText(dataItem.nameProperty().getValue());
        text_tariff.setText(dataItem.tariffProperty().getValue());
        text_prev.setText(dataItem.prevProperty().getValue());
        text_curr.setText(dataItem.currProperty().getValue());
        text_count.setText(dataItem.countProperty().getValue());
    }

    // Добавьте этот метод для обработки обновления данных
    @FXML
    void UpdateHandler(ActionEvent event) {
        String name = text_name.getText();
        String tariff = text_tariff.getText();
        String prev = text_prev.getText();
        String curr = text_curr.getText();
        String count = text_count.getText();

        // Получите выбранную строку из TableView
        DataItem selectedItem = table_list.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Обновите данные в выбранной строке
            selectedItem.nameProperty().set(name);
            selectedItem.tariffProperty().set(tariff);
            selectedItem.prevProperty().set(prev);
            selectedItem.currProperty().set(curr);
            selectedItem.countProperty().set(count);

            // Закройте окно после обновления
            ((Stage) text_name.getScene().getWindow()).close();
        }
    }
}



