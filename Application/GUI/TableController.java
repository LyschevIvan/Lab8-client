package com.company.Application.GUI;

import com.company.Application.Data;
import com.company.Application.ProductClasses.ProductForRow;
import com.company.Application.Requests.*;
import com.company.Application.Web.RequestSender;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class TableController implements Initializable {
    private final FXMLLoader loginLoader;
    private final FXMLLoader productViewLoader;
    private Scene loginScene;
    private Scene showScene;
    private RequestSender requestSender;
    private Socket socket;
    private float scale = 1;
    @FXML
    private Menu userMenu;
    @FXML
    private MenuItem loginMenu;
    @FXML
    private MenuItem logoutMenu;
    @FXML
    private Menu viewMenu;
    @FXML
    private MenuItem tableView;
    @FXML
    private MenuItem visualView;
    @FXML
    private Menu languageMenu;
    @FXML
    private MenuItem ruLang;
    @FXML
    private MenuItem enLang;
    @FXML
    private MenuItem eeLang;
    @FXML
    private MenuItem lvLang;
    @FXML
    private Label userName;
    @FXML
    private Label productsLabel;
    @FXML
    private TableView<ProductForRow> productsTable;
    @FXML
    private TableColumn<ProductForRow,Integer> keyColumn;
    @FXML
    private TableColumn<ProductForRow,Long> idColumn;
    @FXML
    private TableColumn<ProductForRow,String> nameColumn;
    @FXML
    private TableColumn<ProductForRow,Integer> xColumn;
    @FXML
    private TableColumn<ProductForRow,String> yColumn;
    @FXML
    private TableColumn<ProductForRow,String> priceColumn;
    @FXML
    private TableColumn<ProductForRow,String> partNumberColumn;
    @FXML
    private TableColumn<ProductForRow, Long> manCostColumn;
    @FXML
    private TableColumn<ProductForRow,String> uomColumn;
    @FXML
    private TableColumn<ProductForRow,String> ownerNameColumn;
    @FXML
    private TableColumn<ProductForRow,Long> ownerHeightColumn;
    @FXML
    private TableColumn<ProductForRow,String> ownerWeightColumn;
    @FXML
    private TableColumn<ProductForRow, String> ownerHairColorColumn;
    @FXML
    private TableColumn<ProductForRow, String> creationDateColumn;
    @FXML
    private Pane canvasContainer;
    @FXML
    private Canvas canvas;
    @FXML
    private Button showButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button removeLwKeyButton;
    @FXML
    private Button removeGrKeyButton;
    @FXML
    private Button removeByUomButton;
    @FXML
    private Button scalePlus;
    @FXML
    private Button scaleMinus;
    @FXML
    private TextField keyFilter;
    @FXML
    private TextField idFilter;
    @FXML
    private Label colorLabel;
    @FXML
    private Label colorLB;
    @FXML
    private Label loginLabel;


    private final ObservableList<ProductForRow> products = FXCollections.observableArrayList();
    private final ObservableMap<String,Color> users = FXCollections.observableHashMap();
    private ResourceBundle resource;



    private final StringProperty login = new SimpleStringProperty();
    private final StringProperty pass = new SimpleStringProperty();
    private final LoginController loginController;
    private final ProductsViewController productsViewController;
    private final ObjectProperty<ProductForRow> selectedProduct = new SimpleObjectProperty<>();
    public TableController(Socket socket) throws IOException {
        Locale locale = new Locale("ru","RU");
        resource = ResourceBundle.getBundle("GuiLabels",locale);
        this.socket = socket;
        this.requestSender = new RequestSender(socket);
        loginLoader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        productViewLoader = new FXMLLoader(getClass().getResource("productView.fxml"));
        productsViewController = new ProductsViewController(requestSender, locale);
        loginController = new LoginController(requestSender, login, pass, locale);
        productViewLoader.setController(productsViewController);
        loginLoader.setController(loginController);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisable(true);
        setGuiLabels();
        initTable();
        productsTable.managedProperty().bind(productsTable.visibleProperty());
        keyColumn.setResizable(false);

        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        partNumberColumn.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        manCostColumn.setCellValueFactory(new PropertyValueFactory<>("manufactureCost"));
//        uomColumn.setCellValueFactory(new PropertyValueFactory<>("unitOfMeasure"));
        ownerNameColumn.setCellValueFactory(new PropertyValueFactory<>("personName"));
        ownerHeightColumn.setCellValueFactory(new PropertyValueFactory<>("personHeight"));
//        ownerHairColorColumn.setCellValueFactory(new PropertyValueFactory<>("personHairColor"));
        canvas.managedProperty().bind(canvas.visibleProperty());
        showButton.visibleProperty().bind(showButton.managedProperty());
        showButton.managedProperty().bind(canvas.visibleProperty());
        scaleMinus.visibleProperty().bind(scaleMinus.managedProperty());
        scaleMinus.managedProperty().bind(canvas.visibleProperty());
        scalePlus.visibleProperty().bind(scalePlus.managedProperty());
        scalePlus.managedProperty().bind(canvas.visibleProperty());
        canvas.setVisible(false);
        tuneCanvas();

        productsTable.setItems(products);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream inputObjectStream = new ObjectInputStream(inputStream);
                    while (true){
                        Data data = (Data) inputObjectStream.readObject();
                        executeResponse(data);
                    }
                } catch (Exception e) {
                    System.out.println("err");
                }
                return null;
            }
        };
        Thread taskThread = new Thread(task);
        taskThread.setDaemon(true);
        taskThread.start();
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::canvasClicked);
        login.addListener((observable, oldValue, newValue) -> userName.setText(newValue));
        TableView.TableViewSelectionModel<ProductForRow> selectionModel = productsTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedProduct.setValue(newValue));
        try {
            loginScene = new Scene(loginLoader.load(),300, 400);
            showScene = new Scene(productViewLoader.load(),500,600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTable() {

        creationDateColumn.setCellValueFactory(param -> {
            SimpleDateFormat ruFormat = new SimpleDateFormat();
            if (resource.getLocale().getLanguage().equals("ru")){

                ruFormat.applyPattern("dd.MM.yy");
            }else {
                ruFormat.applyPattern("dd/MM/yyyy");
            }
            if (param.getValue()!= null){

                return new SimpleStringProperty(ruFormat.format(param.getValue().getCreationDate()));
            }
            else{
                return new SimpleStringProperty("");
            }

        });
        yColumn.setCellValueFactory(this::floatRefactor);
        priceColumn.setCellValueFactory(this::floatRefactor);
        ownerWeightColumn.setCellValueFactory(this::floatRefactor);
        uomColumn.setCellValueFactory(param -> {
            SimpleStringProperty res = new SimpleStringProperty();
            switch (param.getValue().getUnitOfMeasure()){
                case "Gr": res.setValue(resource.getString("Gr"));break;
                case "Kg": res.setValue(resource.getString("Kg"));break;
                case "Mg": res.setValue(resource.getString("Mg"));break;
                case "Pcs": res.setValue(resource.getString("Pcs"));break;
                default: res.setValue(resource.getString("null"));
            }
            return res;
        });
        ownerHairColorColumn.setCellValueFactory(param -> {
            SimpleStringProperty res = new SimpleStringProperty();
            switch (param.getValue().getPersonHairColor()){
                case "Green": res.setValue(resource.getString("Green"));break;
                case "Black": res.setValue(resource.getString("Black"));break;
                case "Blue": res.setValue(resource.getString("Blue"));break;
                case "Orange": res.setValue(resource.getString("Orange"));break;
                case "Red": res.setValue(resource.getString("Red"));break;
                default: res.setValue(resource.getString("null"));
            }
            return res;
        });
    }
    private SimpleStringProperty floatRefactor(TableColumn.CellDataFeatures<ProductForRow, String> param){
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        if (resource.getLocale().getLanguage().equals("en")){
            ds.setDecimalSeparator(',');
        }
        else {
            ds.setDecimalSeparator('.');
        }
        df.setDecimalFormatSymbols(ds);
        return new SimpleStringProperty(df.format(param.getValue().getY()));
    }

    @FXML
    private void loginMenu(){
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setResizable(false);
        loginStage.setScene(loginScene);
        loginStage.showAndWait();
    }

    @FXML
    private void logoutMenu(){
        login.setValue("");
        products.clear();
        setDisable(true);
        products.clear();
        colorLabel.setStyle("-fx-background-color: transparent");
        clearCanvas();
    }
    @FXML
    private void setRuLang(){
        Locale locale = new Locale("ru", "RU");
        resource = ResourceBundle.getBundle("GuiLabels",locale);
        setGuiLabels();
        initTable();
        productsTable.refresh();
        loginController.changeLocale(locale);
        productsViewController.setLocale(locale);
    }
    @FXML
    private void setEnLang(){
        Locale locale = new Locale("en", "UK");
        resource = ResourceBundle.getBundle("GuiLabels",locale);
        setGuiLabels();
        initTable();
        productsTable.refresh();
        loginController.changeLocale(locale);
        productsViewController.setLocale(locale);
    }
    @FXML
    private void setVisualView(){
        productsTable.setVisible(false);
        canvas.setVisible(true);
        selectedProduct.setValue(null);
    }
    @FXML
    private void setTableView(){
        productsTable.setVisible(true);
        canvas.setVisible(false);
        selectedProduct.setValue(null);
    }
    @FXML
    private void insertButton(){
        Stage showStage = new Stage();
        showStage.initModality(Modality.APPLICATION_MODAL);
        showStage.setResizable(false);
        showStage.setScene(showScene);
        productsViewController.setInsert(login.getValue());
        showStage.showAndWait();
        ProductForRow product = productsViewController.getProductForRow();
        if (product!=null){
            new InsertRequest(requestSender,login.getValue(),pass.getValue(), product.getKey(),product.getProduct()).makeRequest();
        }

    }
    @FXML
    private void updateButton(){
        Stage showStage = new Stage();
        showStage.initModality(Modality.APPLICATION_MODAL);
        showStage.setResizable(false);
        showStage.setScene(showScene);
        if(selectedProduct.getValue() != null){
            if(selectedProduct.getValue().getPersonName().equals(login.getValue())){
                productsViewController.setUpdate(selectedProduct.getValue());
                showStage.showAndWait();
                ProductForRow product = productsViewController.getProductForRow();
                if (product!=null){
                    product.setId(selectedProduct.getValue().getId());
                    product.setCreationDate(selectedProduct.getValue().getCreationDate());
                    new UpdateRequest(requestSender,login.getValue(),pass.getValue(), product.getKey(),product.getProduct()).makeRequest();
                }
            }
            else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(resource.getString("attention"));
                alert.setHeaderText(null);
                alert.setContentText(resource.getString("noPermit"));
                alert.showAndWait();
            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resource.getString("attention"));
            alert.setHeaderText(null);
            alert.setContentText(resource.getString("choseProd"));
            alert.showAndWait();
        }

    }
    @FXML
    private void showButton(){
        Stage showStage = new Stage();
        showStage.initModality(Modality.APPLICATION_MODAL);
        showStage.setResizable(false);
        showStage.setScene(showScene);
        if(selectedProduct.getValue() != null){
            productsViewController.setShow(selectedProduct.getValue());
            showStage.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resource.getString("attention"));
            alert.setHeaderText(null);
            alert.setContentText(resource.getString("choseProd"));
            alert.showAndWait();
        }
    }
    @FXML
    private void removeButton(){
        if(selectedProduct.getValue() != null){
            if(selectedProduct.getValue().getPersonName().equals(login.getValue())) {
                new RemoveRequest(requestSender,login.getValue(), pass.getValue(),selectedProduct.getValue().getKey()).makeRequest();
                products.remove(selectedProduct.getValue().getKey());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(resource.getString("attention"));
                alert.setHeaderText(null);
                alert.setContentText(resource.getString("noPermit"));
                alert.showAndWait();
            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resource.getString("attention"));
            alert.setHeaderText(null);
            alert.setContentText(resource.getString("choseProd"));
            alert.showAndWait();
        }
    }
    @FXML
    private void removeGrButton(){
        TextInputDialog keyDialog = new TextInputDialog();
        keyDialog.setHeaderText(resource.getString("enterKey"));
        keyDialog.setContentText(resource.getString("key")+":");
        Optional<String> result = keyDialog.showAndWait();
        String resultString = result.orElse("");
        try{
            Integer key = Integer.valueOf(resultString.replaceAll(" ", ""));
            new RemoveGrRequest(requestSender,login.getValue(),pass.getValue(),key).makeRequest();
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resource.getString("attention"));
            alert.setHeaderText(null);
            alert.setContentText(resource.getString("keyFormat"));
            alert.showAndWait();
        }
    }
    @FXML
    private void removeLwButton(){
        TextInputDialog keyDialog = new TextInputDialog();
        keyDialog.setHeaderText(resource.getString("enterKey"));
        keyDialog.setContentText(resource.getString("key")+":");
        Optional<String> result = keyDialog.showAndWait();
        String resultString = result.orElse("");
        try{
            Integer key = Integer.valueOf(resultString.replaceAll(" ", ""));
            new RemoveLwRequest(requestSender,login.getValue(),pass.getValue(),key).makeRequest();
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(resource.getString("attention"));
            alert.setHeaderText(null);
            alert.setContentText(resource.getString("keyFormat"));
            alert.showAndWait();
        }
    }
    @FXML
    private void scalePlus(){
        scale*=2;
        drawProducts();
    }
    @FXML
    private void scaleMinus(){
        scale/=2;
        drawProducts();
    }
    @FXML
    private void filter(){
        String keyString = keyFilter.getText();
        String idString = idFilter.getText();
        ObservableList<ProductForRow> filtered = FXCollections.observableArrayList(products.stream()
                .filter(p -> p.getKey().toString().contains(keyString))
                .filter(p -> p.getId().toString().contains(idString))
                .collect(Collectors.toList()));
        productsTable.setItems(filtered);
        productsTable.refresh();

    }
    private void setGuiLabels(){
        userMenu.setText(resource.getString("user"));
        loginMenu.setText(resource.getString("login"));
        logoutMenu.setText(resource.getString("logout"));
        viewMenu.setText(resource.getString("viewMenu"));
        tableView.setText(resource.getString("table"));
        visualView.setText(resource.getString("visual"));
        productsLabel.setText(resource.getString("products"));
        keyColumn.setText(resource.getString("key"));
        idColumn.setText(resource.getString("id"));
        nameColumn.setText(resource.getString("name"));
        languageMenu.setText(resource.getString("language"));
        ruLang.setText(resource.getString("ru"));
        enLang.setText(resource.getString("en"));
        eeLang.setText(resource.getString("ee"));
        lvLang.setText(resource.getString("lv"));
        xColumn.setText(resource.getString("x"));
        yColumn.setText(resource.getString("y"));
        priceColumn.setText(resource.getString("price"));
        partNumberColumn.setText(resource.getString("partNumber"));
        manCostColumn.setText(resource.getString("manufactureCost"));
        uomColumn.setText(resource.getString("uom"));
        ownerNameColumn.setText(resource.getString("ownerName"));
        ownerHeightColumn.setText(resource.getString("ownerHeight"));
        ownerWeightColumn.setText(resource.getString("ownerWeight"));
        ownerHairColorColumn.setText(resource.getString("ownerHairColor"));
        creationDateColumn.setText(resource.getString("creationDate"));
        showButton.setText(resource.getString("show"));
        insertButton.setText(resource.getString("insert"));
        updateButton.setText(resource.getString("update"));
        removeButton.setText(resource.getString("remove"));
        removeGrKeyButton.setText(resource.getString("removeGrKey"));
        removeLwKeyButton.setText(resource.getString("removeLwKey"));
        removeByUomButton.setText(resource.getString("removeUom"));
        colorLB.setText(resource.getString("color"));
        loginLabel.setText(resource.getString("loginName"));
    }
    private void tuneCanvas(){
        canvasContainer.setStyle("-fx-background-color: white");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        gc.moveTo(width/2, 0);// Y line drawing
        gc.lineTo(width/2,height);
        gc.moveTo(width/2, 0);
        gc.lineTo(width/2-7,10);
        gc.moveTo(width/2, 0);
        gc.lineTo(width/2+7,10);
        gc.moveTo(0,height/2); // X line drawing
        gc.lineTo(width,height/2);
        gc.moveTo(width,height/2);
        gc.lineTo(width-10,height/2+7);
        gc.moveTo(width,height/2);
        gc.lineTo(width-10,height/2-7);
        gc.stroke();

    }
    private void clearCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    private void drawProducts(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        clearCanvas();
        float rad = 10;
        for(ProductForRow product : products){

            int x = (int) (canvas.getWidth()/2+ product.getX()*scale);
            float y = (float) (canvas.getHeight()/2 -  product.getY()*scale);
            Color fillColor = users.get(product.getPersonName());
            gc.setFill(fillColor);
            gc.fillOval(x-rad,y-rad,rad*2,rad*2);
            gc.stroke();

        }
        if (!users.containsKey(login.getValue())){
            users.put(login.getValue(),ColorManager.nextColor());
        }
        colorLabel.setStyle("-fx-background-color: "+ColorManager.toRGBCode(users.get(login.getValue())));

    }

    private void canvasClicked(MouseEvent event){
        double x = event.getX()-canvas.getWidth()/2;
        double y = canvas.getHeight()/2 - event.getY();
        int rad = 10;
        for (ProductForRow product : products){
            int productX = product.getX();
            float productY = product.getY();
            if(Math.pow((productX*scale-x),2)+(Math.pow((productY*scale-y),2))<=(Math.pow(rad,2))){
                selectedProduct.setValue(product);
            }
        }
    }

    private void incorrectPass(){
        loginController.incorrectPass();
    }
    private void correctPass() {
        loginController.correctPass();
        setDisable(false);


    }
    private void successReg(){
        loginController.successReg();
    }
    private void failedReg(){
        loginController.failedReg();
    }
    private void setDisable(boolean flag){
        insertButton.setDisable(flag);
        showButton.setDisable(flag);
        updateButton.setDisable(flag);
        removeButton.setDisable(flag);
        removeLwKeyButton.setDisable(flag);
        removeGrKeyButton.setDisable(flag);
        removeByUomButton.setDisable(flag);
        keyFilter.setDisable(flag);
        idFilter.setDisable(flag);
    }
    private void executeResponse(Data data) {
        switch (data.getCommand()) {
            case "login":
                if (data.isSuccess()) {

                    Platform.runLater(() -> correctPass());

                } else {
                    Platform.runLater(() -> incorrectPass());
                }
                break;
            case "reg":
                if (data.isSuccess()) {
                    Platform.runLater(() -> successReg());
                    break;
                } else {
                    Platform.runLater(() -> failedReg());
                }
                break;
            case "show":

                if (data.isSuccess()) {
                    products.clear();
                } else {
                    if (data.getValue() != null) {
                        drawProducts();
                    } else {
                        if (!users.containsKey(data.getProduct().getOwner().getName())) {
                            users.put(data.getProduct().getOwner().getName(), ColorManager.nextColor());
                        }
                        products.add(ProductForRow.getProductForRow(data.getProductId(), data.getProduct()));

                    }

                }
                break;
            case "insert":
                if (!data.isSuccess()) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Внимание");
                        alert.setHeaderText(null);
                        alert.setContentText("Объект с таким ключем уже есть!");
                        alert.showAndWait();
                    });

                }
            default:
                System.out.println(3);
        }

    }
}
