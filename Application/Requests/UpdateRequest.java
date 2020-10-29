package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.ProductClasses.Product;
import com.company.Application.Web.RequestSender;

public class UpdateRequest extends AbstractRequest{
    public UpdateRequest(RequestSender requestSender, String login, String pass, Integer key, Product product) {
        super(requestSender);
        dataRequest = new Data("update",login,pass,key, product );
    }
}
