package com.zangmz.hit.medicineneo4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/test")

public class mvcController {

    @RequestMapping("/hello")
    public String hello() {
        return "test1";
    }
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("input", "command", new Relation());
    }

    @RequestMapping(value = "/getRelation", method = RequestMethod.POST)
    /*public String addUser(@ModelAttribute("SpringWeb")Student stu,
                          ModelMap model) {
        model.addAttribute("name1", stu.getname1());
        model.addAttribute("name2", stu.getname2());
        return "getRelation";
    }*/
    public ModelAndView addUser(@ModelAttribute("SpringWeb")Relation rel,
                                ModelMap model) {
        model.addAttribute("head_entity", rel.getHead_entity());
        model.addAttribute("tail_entity", rel.getTail_entity());
        model.addAttribute("umls_pcnn", rel.getUmls_pcnn());
        model.addAttribute("umls_rel", rel.getUmls_rel());
        model.addAttribute("pmoz_rel", rel.getPmoz_rel());
        model.addAttribute("subclass_of", rel.getSubclass_of());
        return new ModelAndView("getRelation", "command", new Relation());
    }

}