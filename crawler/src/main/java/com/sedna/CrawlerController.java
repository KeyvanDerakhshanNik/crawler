package com.sedna;

import java.util.HashSet;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrawlerController {
    @GetMapping("/")
    public String home(){
        return "homepage.html";
    }

    @RequestMapping("/getResult")
    @ResponseBody
    public String prob(@RequestParam String URL){
       // RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
    	//1. Pick a URL from the frontier
    	HashSet<String> res= new CrawlerService().getPageLinks(URL);    	
    	StringBuffer result =new StringBuffer();    	
    	for (String item:res){
    		result.append(item+"<br>");
    	}    	
        return result.toString();

    }
}