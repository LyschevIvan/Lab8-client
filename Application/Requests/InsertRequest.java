package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.ProductClasses.Product;
import com.company.Application.Web.RequestSender;

public class InsertRequest extends AbstractRequest {
    public InsertRequest(RequestSender requestSender,String login, String pass, Integer id, Product product) {
        super(requestSender);
        dataRequest = new Data("insert",login,pass, id, product);
    }
}
