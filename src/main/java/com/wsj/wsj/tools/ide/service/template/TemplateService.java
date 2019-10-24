package com.wsj.wsj.tools.ide.service.template;

import org.springframework.stereotype.Service;
import testTool.template.argsEnum.FunctionSpeedArgs;
import testTool.template.config.TemplateArgs;
import testTool.template.core.Template;

import java.util.EnumMap;

@Service
public class TemplateService {
    public String getFormatTemplate(String template) {
        try {
            Template.setTemplateArg(TemplateArgs.FUNCTIONSPEED);
            EnumMap map = new EnumMap(FunctionSpeedArgs.class);
            map.put(FunctionSpeedArgs.NAME, "myName");
            map.put(FunctionSpeedArgs.TIME, "myTime");
            map.put(FunctionSpeedArgs.UNIT, "myUnit");
            Template.setMap(map);
            return Template.format("asdasdasd{name}{unit}");
        } catch (Exception e) {
            e.printStackTrace();
            return template;
        }
    }
}
