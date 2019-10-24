package com.wsj.wsj.tools.ide.web.template;

import com.wsj.wsj.tools.ide.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/toTemplate", method = RequestMethod.GET)
    public ModelAndView toTemplate() {
        ModelAndView mView = new ModelAndView("tool/template/template");
        return mView;
    }

    @RequestMapping(value = "/getFormat",method = RequestMethod.POST)
    @ResponseBody
    public String getFormat() {
        return templateService.getFormatTemplate("asdasdassdasds");
    }
}
