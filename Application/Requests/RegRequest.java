package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.Web.RequestSender;

public class RegRequest extends AbstractRequest {

    public RegRequest(RequestSender requestSender, String login, String psw) {
        super(requestSender);
        dataRequest = new Data("reg", login, psw);
    }
}
