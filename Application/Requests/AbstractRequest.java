package com.company.Application.Requests;

import com.company.Application.Data;
import com.company.Application.Web.RequestSender;

public abstract class AbstractRequest {
    RequestSender requestSender;
    Data dataRequest;

    public AbstractRequest(RequestSender requestSender) {
        this.requestSender = requestSender;
    }
    public void makeRequest()  {
        requestSender.sendRequest(dataRequest);
    }
}
