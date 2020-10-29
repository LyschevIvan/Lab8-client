package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.Web.RequestSender;

public class LoginRequest extends AbstractRequest {

    public LoginRequest(RequestSender requestSender, String login, String password) {
        super(requestSender);
        dataRequest = new Data("login", login, password);
    }
}
