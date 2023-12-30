package com.example.payment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.example.payment.DataItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainPage {

    public Label label_name;
    public Label label_tariff;
    public Label label_prev;
    public Label label_curr;
    public Label label_count;
    public Label label_edit;
    @FXML
    private Label text1;

    @FXML
    private Label text2;

    @FXML
    private Label text3;

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
    private Button btn_update;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private TableView<DataItem> table_list;

    @FXML
    private TableColumn<DataItem, String> columnCount;

    @FXML
    private TableColumn<DataItem, String> columnCurr;

    @FXML
    private TableColumn<DataItem, String> columnMinus;

    @FXML
    private TableColumn<DataItem, String> columnName;

    @FXML
    private TableColumn<DataItem, String> columnPay;

    @FXML
    private TableColumn<DataItem, String> columnPrev;

    @FXML
    private TableColumn<DataItem, String> columnTariff;

    private final ObservableList<DataItem> dataItems = FXCollections.observableArrayList();

    private DataItem selectedItem;

    @FXML
    void add_click(ActionEvent event) {
        try {
            // Загрузка addPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addPage.fxml"));
            Parent root = loader.load();

            // Создание нового Stage (окна)
            Stage addPageStage = new Stage();
            addPageStage.setTitle("Додавання квитанції");

            // Установка сцены в Stage
            addPageStage.setScene(new Scene(root));

            // Установка модальности (блокировка предыдущего окна)
            addPageStage.initModality(Modality.WINDOW_MODAL);
            addPageStage.initOwner(btn_add.getScene().getWindow());

            // Отображение нового окна
            addPageStage.showAndWait();

            updateTableList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateTableList() {
        dataItems.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String tariff = parts[1].trim();
                    String prev = parts[2].trim();
                    String curr = parts[3].trim();
                    String count = parts[4].trim();

                    DataItem dataItem = new DataItem(name, tariff, prev, curr, count);
                    dataItems.add(dataItem);
                }
            }

            // Обновление данных в таблице
            table_list.setItems(dataItems);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        text_count.setVisible(false);
        text_curr.setVisible(false);
        text_name.setVisible(false);
        text_prev.setVisible(false);
        text_tariff.setVisible(false);
        btn_update.setVisible(false);
        label_name.setVisible(false);
        label_tariff.setVisible(false);
        label_prev.setVisible(false);
        label_curr.setVisible(false);
        label_count.setVisible(false);
        label_edit.setVisible(false);

        // Вызов метода для обновления данных в таблице
        updateTableList();
    }
    @FXML
    void deleteHandler(ActionEvent event) {
        DataItem selectedItem = table_list.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Удаляем элемент из данных и из таблицы
            dataItems.remove(selectedItem);

            // Сохраняем обновленные данные в файл
            saveDataToFile();

            // Если нужно обновить таблицу, вызовите updateTableList();
        }
    }

    private void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter("data.txt")) {
            for (DataItem item : dataItems) {
                writer.printf("%s,%s,%s,%s,%s%n", item.nameProperty().get(), item.tariffProperty().get(), item.prevProperty().get(), item.currProperty().get(), String.valueOf(item.countProperty().get()));



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editHandler(ActionEvent event) {
        selectedItem = table_list.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            btn_add.setVisible(false);
            btn_delete.setVisible(false);
            btn_edit.setVisible(false);
            table_list.setVisible(false);
            columnCount.setVisible(false);
            columnCurr.setVisible(false);
            columnMinus.setVisible(false);
            columnName.setVisible(false);
            columnPay.setVisible(false);
            columnPrev.setVisible(false);
            columnTariff.setVisible(false);
            text1.setVisible(false);
            text2.setVisible(false);
            text3.setVisible(false);
            text_count.setVisible(true);
            text_curr.setVisible(true);
            text_name.setVisible(true);
            text_prev.setVisible(true);
            text_tariff.setVisible(true);
            btn_update.setVisible(true);
            label_name.setVisible(true);
            label_tariff.setVisible(true);
            label_prev.setVisible(true);
            label_curr.setVisible(true);
            label_count.setVisible(true);
            label_edit.setVisible(true);
            setInitialData(selectedItem);
        }
        else{
            System.out.println("Не нажали");
        }
    }



    public void setInitialData(DataItem dataItem) {
        text_name.setText(dataItem.nameProperty().getValue());
        text_tariff.setText(dataItem.tariffProperty().getValue());
        text_prev.setText(dataItem.prevProperty().getValue());
        text_curr.setText(dataItem.currProperty().getValue());
        text_count.setText(dataItem.countProperty().getValue());
    }

    public void UpdateHandler(ActionEvent actionEvent) {
        String name = text_name.getText();
        String tariff = text_tariff.getText();
        String prev = text_prev.getText();
        String curr = text_curr.getText();
        String count = text_count.getText();

        if (selectedItem != null) {
            // Обновите данные в выбранной строке
            selectedItem.nameProperty().set(name);
            selectedItem.tariffProperty().set(tariff);
            selectedItem.prevProperty().set(prev);
            selectedItem.currProperty().set(curr);
            selectedItem.countProperty().set(count);

            // Сохраните обновленные данные в файл
            saveDataToFile();

            // Если нужно обновить таблицу, вызовите updateTableList();
            updateTableList();

            // Покажите элементы управления после обновления
            btn_add.setVisible(true);
            btn_delete.setVisible(true);
            btn_edit.setVisible(true);
            table_list.setVisible(true);
            columnCount.setVisible(true);
            columnCurr.setVisible(true);
            columnMinus.setVisible(true);
            columnName.setVisible(true);
            columnPay.setVisible(true);
            columnPrev.setVisible(true);
            columnTariff.setVisible(true);
            text1.setVisible(true);
            text2.setVisible(true);
            text3.setVisible(true);
            text_count.setVisible(false);
            text_curr.setVisible(false);
            text_name.setVisible(false);
            text_prev.setVisible(false);
            text_tariff.setVisible(false);
            btn_update.setVisible(false);
            label_name.setVisible(false);
            label_tariff.setVisible(false);
            label_prev.setVisible(false);
            label_curr.setVisible(false);
            label_count.setVisible(false);
            label_edit.setVisible(false);
        }
    }
}
