package com.company.Application;

import com.company.Application.ProductClasses.Product;

import java.io.Serializable;

public class Data implements Serializable {
    private String command;
    private String value;
    private int productId;
    private Product product;

    private String login;
    private String password;
    boolean success = false;

    public Data(String command, String login, String password) {
        this.command = command;
        this.login = login;
        this.password = password;
    }

    public Data(String command, boolean success) {
        this.command = command;
        this.success = success;
    }

    public Data(String command, String value) {
        this.command = command;
        this.value = value;
    }

    public Data(String command, int productId, Product product) {
        this.command = command;
        this.productId = productId;
        this.product = product;
    }

    public Data(String command, String login, String pass, Integer id, Product product) {
        this.command = command;
        this.login = login;
        this.password = pass;
        this.productId = id;
        this.product = product;
    }

    public Data(String command, String login, String pass, Integer productId) {
        this.command = command;
        this.login = login;
        this.password = pass;
        this.productId = productId;
    }

    public String getCommand() {
        return command;
    }

    public String getValue() {
        return value;
    }

    public int getProductId() {
        return productId;
    }

    public Product getProduct() {
        return product;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuccess() {
        return success;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("command= " + command + "\n");
        stringBuilder.append("value= " + value + "\n");
        stringBuilder.append("productId= " + productId+ "\n");
        if (product!=null){
            stringBuilder.append(product.toString());
        }
        stringBuilder.append("login= "+ login + "\n");
        stringBuilder.append("password= "+ password + "\n");
        stringBuilder.append("success= " + success+"\n");

        return stringBuilder.toString();
    }
}
