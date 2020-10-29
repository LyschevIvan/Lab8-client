package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.Web.RequestSender;

public class RemoveGrRequest extends AbstractRequest{
    public RemoveGrRequest(RequestSender requestSender, String login, String pass, Integer key) {
        super(requestSender);
        dataRequest = new Data("remove_greater_key", login, pass, key);
    }
}
