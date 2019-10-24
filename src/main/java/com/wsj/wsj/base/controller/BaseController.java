package com.wsj.wsj.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {
    @Autowired(required = false)
    protected HttpServletResponse response;
    @Autowired
    protected HttpServletRequest request;

    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public ModelAndView toIndex(){
        ModelAndView mView = new ModelAndView("index");
        return mView;
    }
}
