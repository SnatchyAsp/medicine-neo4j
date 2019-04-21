package com.zangmz.hit.medicineneo4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zangmz.hit.medicineneo4j.utils.Webinfo;
import java.util.ArrayList;

@Controller
@RequestMapping("/show")
public class showController {

    @RequestMapping(value = "/input")
    public String input() {
//        test11 = new ArrayList<>();
//        all_returncode = -1;
//        all_returnmessage = "";
        return "newinput";
    }


    @RequestMapping(value = "/show",method = RequestMethod.POST)
    public ModelAndView showinfo(@ModelAttribute("SpringWeb")Webinfo webinfo, ModelMap model){
        String temp = com.alibaba.fastjson.JSON.toJSONString(webinfo);
        model.addAttribute("webinfo",temp);
        return new ModelAndView("newshow");
    }


}
