package com.example.payment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_add;

    @FXML
    private TextField text_count;

    @FXML
    private TextField text_curr;

    @FXML
    private TextField text_name;

    @FXML
    private TextField text_prev;

    @FXML
    private TextField text_tariff;

    @FXML
    void AddHandler(ActionEvent event) {
        // Получение данных из TextField
        String count = text_count.getText();
        String curr = text_curr.getText();
        String name = text_name.getText();
        String prev = text_prev.getText();
        String tariff = text_tariff.getText();

        // Соберем строку данных
        String data = String.format("%s,%s,%s,%s,%s%n", name, tariff, prev, curr, count);


        // Определим путь к файлу
        Path filePath = Path.of("data.txt");

        try {
            // Записываем данные в файл
            Files.write(filePath, data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Данные успешно сохранены в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {


    }

}
