package com.company.Application.GUI;

import com.company.Application.Requests.LoginRequest;
import com.company.Application.Requests.RegRequest;
import com.company.Application.Requests.ShowRequest;
import com.company.Application.Web.RequestSender;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private final RequestSender requestSender;
    private String login = "";
    private String psw = "";
    private Locale locale;
    private ResourceBundle resourceBundle;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passField;
    @FXML
    private Button enterBtn;
    @FXML
    private Button regBtn;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label wrongLabel;


    private final StringProperty userName;
    private final StringProperty userPass;
    public LoginController(RequestSender requestSender, StringProperty userName, StringProperty userPass, Locale locale) {
        this.requestSender = requestSender;
        this.userName = userName;
        this.userPass = userPass;
        this.locale = locale;
        this.resourceBundle = ResourceBundle.getBundle("GuiLabels", locale);
    }
    @FXML
    private void enterBtn(){
        login = loginField.getText();
        psw = passField.getText();
        new LoginRequest(requestSender, login, psw).makeRequest();

    }
    public void correctPass(){
        userName.setValue(login);
        userPass.setValue(psw);
        loginField.setText("");
        loginField.setStyle("-fx-border-color: transparent");
        passField.setStyle("-fx-border-color: transparent");
        passField.setText("");
        wrongLabel.setText("");
        new ShowRequest(requestSender,login,psw).makeRequest();
        ((Stage)enterBtn.getScene().getWindow()).close();
    }
    public void incorrectPass() {

        passField.setText("");
        loginField.setStyle("-fx-border-color: red");
        passField.setStyle("-fx-border-color: red");
        wrongLabel.setText(resourceBundle.getString("incPass"));
    }
    @FXML
    private void regBtn(){
        login = loginField.getText();
        psw = passField.getText();
        new RegRequest(requestSender, login, psw).makeRequest();

    }
    public void successReg(){
        loginField.setText("");
        passField.setText("");
        loginField.setStyle("-fx-border-color: transparent");
        passField.setStyle("-fx-border-color: transparent");
        wrongLabel.setText(resourceBundle.getString("successReg"));
    }
    public void failedReg(){
        loginField.setText("");
        loginField.setStyle("-fx-border-color: red");
        passField.setStyle("-fx-border-color: transparent");
        passField.setText("");
        wrongLabel.setText(resourceBundle.getString("incLogin"));
    }
    public void changeLocale(Locale locale){
//        resourceBundle = ResourceBundle.getBundle("GuiLabels", locale);
        this.locale = locale;
        setGuiLabels();

    }
    private void setGuiLabels(){
        resourceBundle = ResourceBundle.getBundle("GuiLabels", locale);
        loginLabel.setText(resourceBundle.getString("enterLogin"));
        passLabel.setText(resourceBundle.getString("enterPass"));
        enterBtn.setText(resourceBundle.getString("login"));
        regBtn.setText(resourceBundle.getString("reg"));

    }

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        InputStream imageStream = getClass().getResourceAsStream("/Resource/image1.png");
        Image image = new Image(imageStream,282,100,true, false);

        imageView.setImage(image);
        setGuiLabels();
    }


}
