package com.example.payment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

public class LoginPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_entry;

    @FXML
    private TextField text_login;

    @FXML
    private TextField text_pass;


    @FXML
    void loginToAccount() {
        // Считывание логина и пароля из JSON-файла
        String login = text_login.getText();
        String password = text_pass.getText();

        if (validateCredentials(login, password)) {
            // Если логин и пароль верны, выполните необходимые действия
            System.out.println("Login successful");
            openMainPage();
        } else {
            // Иначе выведите сообщение об ошибке
            System.out.println("Invalid login or password");
        }
    }
    private boolean validateCredentials(String enteredLogin, String enteredPassword) {
        try {
            // Считывание данных из JSON-файла
            File jsonFile = new File("creds.json");
            String jsonString = new String(Files.readAllBytes(jsonFile.toPath()));

            // Преобразование JSON-строки в объект JSON
            JSONObject jsonObject = new JSONObject(jsonString);

            // Получение логина и пароля из JSON-объекта
            String storedLogin = jsonObject.getString("login");
            String storedPassword = jsonObject.getString("password");

            // Проверка логина и пароля
            return enteredLogin.equals(storedLogin) && enteredPassword.equals(storedPassword);
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Обработка ошибки при считывании данных из JSON-файла
        }
    }
    private void openMainPage() {
        try {
            // Загрузка main_page.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
            Parent root = loader.load();

            // Создание новой сцены
            Scene mainPageScene = new Scene(root);

            // Получение текущего Stage (окна)
            Stage stage = (Stage) btn_entry.getScene().getWindow();
            stage.setTitle("Меню");
            stage.setResizable(false);

            // Установка новой сцены в Stage
            stage.setScene(mainPageScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
