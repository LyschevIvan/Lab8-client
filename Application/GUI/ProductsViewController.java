package com.company.Application.GUI;

import com.company.Application.ProductClasses.*;
import com.company.Application.Exceptions.InfiniteCoordinateException;
import com.company.Application.Exceptions.WrongArgumentException;
import com.company.Application.Web.RequestSender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductsViewController implements Initializable {

    @FXML
    private Label keyLabel;
    @FXML
    private TextField keyField;
    @FXML
    private Label keyRest;
    @FXML
    private Label idLabel;
    @FXML
    private TextField idField;
    @FXML
    private Label idRest;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Label nameRest;
    @FXML
    private Label xLabel;
    @FXML
    private TextField xField;
    @FXML
    private Label xRest;
    @FXML
    private Label yLabel;
    @FXML
    private TextField yField;
    @FXML
    private Label yRest;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField priceField;
    @FXML
    private Label priceRest;
    @FXML
    private Label partNumberLabel;
    @FXML
    private TextField partNumberField;
    @FXML
    private Label pNumberRest;
    @FXML
    private Label manCostLabel;
    @FXML
    private TextField manCostField;
    @FXML
    private Label manCostRest;
    @FXML
    private Label uomLabel;
    @FXML
    private TextField uomField;
    @FXML
    private Label uomRest;
    @FXML
    private Label ownerNameLabel;
    @FXML
    private TextField ownerNameField;
    @FXML
    private Label ownerNameRest;
    @FXML
    private Label ownerHeightLabel;
    @FXML
    private TextField ownerHeightField;
    @FXML
    private Label heightRest;
    @FXML
    private Label ownerWeightLabel;
    @FXML
    private TextField ownerWeightField;
    @FXML
    private Label weightRest;
    @FXML
    private Label ownerHairColorLabel;
    @FXML
    private TextField ownerHairColorField;
    @FXML
    private Label hairColorRest;
    @FXML
    private Label dateLabel;
    @FXML
    private TextField dateField;
    @FXML
    private Label dateRest;
    @FXML
    private Button submitButton;
    private ProductForRow productForRow;
    private final RequestSender requestSender;
    private ResourceBundle resource;

    public ProductsViewController(RequestSender requestSender, Locale locale) {
        this.requestSender = requestSender;
        resource = ResourceBundle.getBundle("GuiLabels",locale);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGuiLabels();


    }
    @FXML
    private void submitButton(){
        boolean success = true;

        Product product = new Product();
        Integer key = 0;
        try{
            key = Integer.valueOf(keyField.getText().replaceAll(" ",""));
            keyField.setStyle("-fx-border-color: transparent");
        }
        catch (NumberFormatException e){
            success = false;
            keyField.setStyle("-fx-border-color: red");
        }
        try {
            product.setName(nameField.getText().replaceAll(" ",""));
            nameField.setStyle("-fx-border-color: transparent");
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
            nameField.setStyle("-fx-border-color: red");
        }
        Coordinates coordinates = new Coordinates();
        try{
            Integer x = Integer.valueOf(xField.getText().replaceAll(" ",""));
            coordinates.setX(x);
            xField.setStyle("-fx-border-color: transparent");
        }catch (NumberFormatException e){
            success = false;
            xField.setStyle("-fx-border-color: red");
        }
        try{
            Float y = Float.valueOf(yField.getText().replaceAll(" ","").replaceAll(",","."));
            coordinates.setY(y);
            yField.setStyle("-fx-border-color: transparent");
        }catch (NumberFormatException | InfiniteCoordinateException | WrongArgumentException e){
            success = false;
            yField.setStyle("-fx-border-color: red");
        }
        product.setCoordinates(coordinates);
        try{
            product.setPrice(Float.parseFloat(priceField.getText().replaceAll(" ","").replaceAll(",",".")));
            priceField.setStyle("-fx-border-color: transparent");
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
            priceField.setStyle("-fx-border-color: red");
        }
        try {
            product.setPartNumber(partNumberField.getText().replaceAll(" ",""));
            partNumberField.setStyle("-fx-border-color: transparent");
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
            partNumberField.setStyle("-fx-border-color: red");
        }
        try{
            product.setManufactureCost(Long.parseLong(manCostField.getText().replaceAll(" ","")));
            manCostField.setStyle("-fx-border-color: transparent");
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
           manCostField.setStyle("-fx-border-color: red");
        }
        uomField.setStyle("-fx-border-color: transparent");

        switch (uomField.getText().toLowerCase().replaceAll(" ","")){
            case "mg": product.setUnitOfMeasure(UnitOfMeasure.MILLIGRAMS); break;
            case "gr": product.setUnitOfMeasure(UnitOfMeasure.GRAMS); break;
            case "pcs": product.setUnitOfMeasure(UnitOfMeasure.PCS); break;
            case "kg": product.setUnitOfMeasure(UnitOfMeasure.KILOGRAMS); break;
            case "": product.setUnitOfMeasure(null); break;
            default: success = false; uomField.setStyle("-fx-border-color: red");
        }

        Person owner = new Person();
        try{
            owner.setName(ownerNameField.getText().replaceAll(" ",""));
            ownerNameField.setStyle("-fx-border-color: transparent");
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
            ownerNameField.setStyle("-fx-border-color: red");
        }
        try {
            owner.setHeight(Long.valueOf(ownerHeightField.getText().replaceAll(" ","")));
            ownerHeightField.setStyle("-fx-border-color: transparent");
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
            ownerHeightField.setStyle("-fx-border-color: red");
        }
        try {
            owner.setWeight(Float.valueOf(ownerWeightField.getText().replaceAll(" ","").replaceAll(",",".")));
            ownerWeightField.setStyle("-fx-border-color: transparent");
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
            ownerWeightField.setStyle("-fx-border-color: red");
        }
        ownerHairColorField.setStyle("-fx-border-color: transparent");
        switch (ownerHairColorField.getText().toLowerCase().replaceAll(" ","")){
            case "black": owner.setHairColor(Color.BLACK);break;
            case "blue": owner.setHairColor(Color.BLUE);break;
            case "red": owner.setHairColor(Color.RED);break;
            case "green": owner.setHairColor(Color.GREEN);break;
            case "orange": owner.setHairColor(Color.ORANGE);break;
            case "":owner.setHairColor(null);break;
            default:success = false; ownerHairColorField.setStyle("-fx-border-color: red");
        }
        try {
            product.setOwner(owner);
        } catch (WrongArgumentException | NumberFormatException e) {
            success = false;
            e.printStackTrace();
        }
        if (success){
            productForRow = ProductForRow.getProductForRow(key,product);
            ((Stage)(submitButton.getScene().getWindow())).close();
        }
        else {
            productForRow = null;
        }
    }
    private void setGuiLabels(){
        keyLabel.setText(resource.getString("key"));
        keyRest.setText(resource.getString("keyRest"));
        idLabel.setText(resource.getString("id"));
        idRest.setText(resource.getString("idRest"));
        nameLabel.setText(resource.getString("name"));
        nameRest.setText(resource.getString("nameRest"));
        xLabel.setText(resource.getString("x"));
        xRest.setText(resource.getString("xRest"));
        yLabel.setText(resource.getString("y"));
        yRest.setText(resource.getString("yRest"));
        priceLabel.setText(resource.getString("price"));
        priceRest.setText(resource.getString("priceRest"));
        partNumberLabel.setText(resource.getString("partNumber"));
        pNumberRest.setText(resource.getString("pNumRest"));
        manCostLabel.setText(resource.getString("manufactureCost"));
        manCostRest.setText(resource.getString("manCostRest"));
        uomLabel.setText(resource.getString("uom"));
        uomRest.setText(resource.getString("uomRest"));
        ownerNameLabel.setText(resource.getString("ownerName"));
        ownerNameRest.setText(resource.getString("ownerNameRest"));
        ownerHeightLabel.setText(resource.getString("ownerHeight"));
        heightRest.setText(resource.getString("heightRest"));
        ownerWeightLabel.setText(resource.getString("ownerWeight"));
        weightRest.setText(resource.getString("weightRest"));
        ownerHairColorLabel.setText(resource.getString("ownerHairColor"));
        hairColorRest.setText(resource.getString("colorRest"));
        dateLabel.setText(resource.getString("creationDate"));
        dateRest.setText(resource.getString("dateRest"));;
        submitButton.setText(resource.getString("submit"));


    }
    public void setInsert(String login){
        setRestrictions(true);
        keyField.setText("");
        keyField.setEditable(true);
        idField.setText("");
        nameField.setText("");
        nameField.setEditable(true);
        xField.setText("");
        xField.setEditable(true);
        yField.setText("");
        yField.setEditable(true);
        priceField.setText("");
        priceField.setEditable(true);
        partNumberField.setText("");
        partNumberField.setEditable(true);
        manCostField.setText("");
        manCostField.setEditable(true);
        uomField.setText("");
        uomField.setEditable(true);
        ownerNameField.setText(login);
        ownerNameField.setEditable(false);
        ownerHeightField.setText("");
        ownerHeightField.setEditable(true);
        ownerWeightField.setText("");
        ownerWeightField.setEditable(true);
        ownerHairColorField.setText("");
        ownerHairColorField.setEditable(true);
        dateField.setText("");
        submitButton.setVisible(true);
    }
    public void setUpdate(ProductForRow product){
        setRestrictions(true);
        keyField.setText(String.valueOf(product.getKey()));
        keyField.setEditable(true);
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        nameField.setEditable(true);
        xField.setText(String.valueOf(product.getX()));
        xField.setEditable(true);
        yField.setText(String.valueOf(product.getY()));
        yField.setEditable(true);
        priceField.setText(String.valueOf(product.getPrice()));
        priceField.setEditable(true);
        partNumberField.setText(product.getPartNumber());
        partNumberField.setEditable(true);
        manCostField.setText(String.valueOf(product.getManufactureCost()));
        manCostField.setEditable(true);
        uomField.setText(product.getUnitOfMeasure());
        uomField.setEditable(true);
        ownerNameField.setText(product.getPersonName());
        ownerNameField.setEditable(false);
        ownerHeightField.setText(String.valueOf(product.getPersonHeight()));
        ownerHeightField.setEditable(true);
        ownerWeightField.setText(String.valueOf(product.getPersonWeight()));
        ownerWeightField.setEditable(true);
        ownerHairColorField.setText(product.getPersonHairColor());
        ownerHairColorField.setEditable(true);
        dateField.setText(String.valueOf(product.getCreationDate()));
        submitButton.setVisible(true);

    }
    public void setShow(ProductForRow product){
        setRestrictions(false);
        keyField.setText(String.valueOf(product.getKey()));
        keyField.setEditable(false);
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        nameField.setEditable(false);
        xField.setText(String.valueOf(product.getX()));
        xField.setEditable(false);
        yField.setText(String.valueOf(product.getY()));
        yField.setEditable(false);
        priceField.setText(String.valueOf(product.getPrice()));
        priceField.setEditable(false);
        partNumberField.setText(product.getPartNumber());
        partNumberField.setEditable(false);
        manCostField.setText(String.valueOf(product.getManufactureCost()));
        manCostField.setEditable(false);
        uomField.setText(resource.getString(product.getUnitOfMeasure()));
        uomField.setEditable(false);
        ownerNameField.setText(product.getPersonName());
        ownerNameField.setEditable(false);
        ownerHeightField.setText(String.valueOf(product.getPersonHeight()));
        ownerHeightField.setEditable(false);
        ownerWeightField.setText(String.valueOf(product.getPersonWeight()));
        ownerWeightField.setEditable(false);
        ownerHairColorField.setText(resource.getString(product.getPersonHairColor()));
        ownerHairColorField.setEditable(false);
        dateField.setText(String.valueOf(product.getCreationDate()));
        submitButton.setVisible(false);

    }
    private void setRestrictions(boolean flag){
        keyRest.setVisible(flag);
        idRest.setVisible(flag);
        nameRest.setVisible(flag);
        xRest.setVisible(flag);
        yRest.setVisible(flag);
        priceRest.setVisible(flag);
        manCostRest.setVisible(flag);
        pNumberRest.setVisible(flag);
        uomRest.setVisible(flag);
        ownerNameRest.setVisible(flag);
        heightRest.setVisible(flag);
        weightRest.setVisible(flag);
        hairColorRest.setVisible(flag);
        dateRest.setVisible(flag);
    }
    public ProductForRow getProductForRow(){
        return productForRow;
    }

    public void setLocale(Locale locale) {
        resource = ResourceBundle.getBundle("GuiLabels", locale);
        setGuiLabels();
    }
}
