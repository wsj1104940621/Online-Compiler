package com.wsj.wsj.tools.ide.web.ide;

import com.wsj.wsj.base.controller.BaseController;
import com.wsj.wsj.base.entity.JsonResult;
import com.wsj.wsj.tools.ide.entity.JavaEntity;
import com.wsj.wsj.tools.ide.service.ide.IDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(value = "/ide")
public class IDEController extends BaseController {
    @Autowired
    private IDEService ideService;

    @RequestMapping(value = "/toDemo",method = RequestMethod.GET)
    public ModelAndView toDemo() {
        ModelAndView mView = new ModelAndView("tool/ide/ide_demo");
        return mView;
    }

    @RequestMapping(value = "/getSample",method = RequestMethod.GET)
    @ResponseBody
    public String getSample() throws IOException {
        return ideService.getDemoText();
    }

    @RequestMapping(value = "/codeExec",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult codeExec(JavaEntity javaEntity) throws IOException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(ideService.codeExec(javaEntity));
        return jsonResult;
    }
}
