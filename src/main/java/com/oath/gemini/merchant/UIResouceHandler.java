package com.oath.gemini.merchant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * @author tong on 10/1/2017
 */
public class UIResouceHandler extends ResourceHandler implements Handler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        super.handle(target, baseRequest, request, response);
    }
}
