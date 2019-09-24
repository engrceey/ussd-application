package com.etranzact.service;

import com.etranzact.model.request.UssdRequest;
import com.etranzact.model.response.UssdResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface UssdHandler {

    UssdResponse processRequest(UssdRequest request, HttpServletRequest servletRequest);
}
