package com.myworks.mywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class HomeController {
    @RequestMapping(value = {"","/home"})
    public  String displayHome(Model model){
        model.addAttribute("username","Yasin Dalkılı123231ç");
        model.addAttribute("list", Arrays.asList("Yasin","Dalkılıç","Efem"));
        return "home.html";
    }

  //  @PostMapping("/data/{dataId}")
  /*  public  void postMapping(@RequestParam(required = false,name = "custom",value = "1",defaultValue = "2") boolean isFav , @PathVariable String dataId){
            // data?custom=true
        // pathvariable abc/userid
    } */

}
