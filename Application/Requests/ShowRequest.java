package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.Web.RequestSender;

public class ShowRequest extends AbstractRequest {
    public ShowRequest(RequestSender requestSender, String login, String psw) {
        super(requestSender);
        dataRequest = new Data("show",login,psw);
    }
}
