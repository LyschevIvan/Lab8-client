package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.Web.RequestSender;

public class RemoveRequest  extends AbstractRequest{
    public RemoveRequest(RequestSender requestSender, String login, String pass, Integer key) {
        super(requestSender);
        dataRequest = new Data("remove", login,pass, key);
    }
}
