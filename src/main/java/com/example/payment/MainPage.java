package com.example.payment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private TableView<DataItem> table_list;

    @FXML
    private TableColumn<?, ?> columnCount;

    @FXML
    private TableColumn<?, ?> columnCurr;

    @FXML
    private TableColumn<?, ?> columnMinus;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnPay;

    @FXML
    private TableColumn<?, ?> columnPrev;

    @FXML
    private TableColumn<?, ?> columnTariff;

    private final ObservableList<DataItem> dataItems = FXCollections.observableArrayList();



    @FXML
    void add_click(ActionEvent event) {
        try {
            // Загрузка addPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addPage.fxml"));
            Parent root = loader.load();

            // Создание нового Stage (окна)
            Stage addPageStage = new Stage();
            addPageStage.setTitle("Добавление");

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
                writer.printf("%s,%s,%s,%s,%s%n", item.nameProperty(), item.tariffProperty(), item.prevProperty(), item.currProperty(), String.valueOf(item.countProperty()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editHandler(ActionEvent event) {
        try {
            // Загрузка editPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editPage.fxml"));
            Parent root = loader.load();

            // Получение контроллера
            EditPage editPageController = loader.getController();

            // Получение выбранной строки из TableView
            DataItem selectedItem = table_list.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Установка начальных данных в editPageController
                editPageController.setInitialData(selectedItem);

                // Создание нового Stage (окна)
                Stage editPageStage = new Stage();
                editPageStage.setTitle("Редагування");

                // Установка сцены в Stage
                editPageStage.setScene(new Scene(root));

                // Установка модальности (блокировка предыдущего окна)
                editPageStage.initModality(Modality.WINDOW_MODAL);
                editPageStage.initOwner(btn_edit.getScene().getWindow());

                // Отображение нового окна
                editPageStage.showAndWait();

                // Обновление данных в TableView после редактирования
                updateTableList();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {


        // Вызов метода для обновления данных в таблице
        updateTableList();
    }

}
