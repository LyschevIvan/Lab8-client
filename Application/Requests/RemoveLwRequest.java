package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.Web.RequestSender;

public class RemoveLwRequest  extends AbstractRequest{
    public RemoveLwRequest(RequestSender requestSender, String login, String pass, Integer key) {
        super(requestSender);
        dataRequest = new Data("remove_lower_key",login,pass,key);
    }
}
