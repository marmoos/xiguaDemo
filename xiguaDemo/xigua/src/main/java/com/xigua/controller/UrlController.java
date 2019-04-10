package com.xigua.controller;

import com.xigua.server.UrlServer;
import com.xigua.vo.urlVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UrlController {

    @Autowired
    private UrlServer server;
    @Value("${shortUrl.code}")
    private String code;
    @Value("${shortUrl.length}")
    private String length;
    @Value("${shortUrl.context}")
    private String context;
    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("infos",server.queryAll());
        return "index";
    }
    @PostMapping("/getShortURL")
    public String getShortURL(String url,String shorturl){
        urlVo urlResult=null;
        if(shorturl!=null&&!shorturl.trim().equals("")){
           urlResult=server.queryByshortUrl(shorturl);
            if(urlResult==null){
                urlResult=new urlVo();
                urlResult.setShortUrl(shorturl);
                urlResult.setLongUrl(url);
                urlResult.setTimes(0);
                server.insertUrl(urlResult);
            }
        }else {
            urlResult=server.addShortURL(url,code,Integer.valueOf(length),context);
        }
       if(urlResult!=null)
        return "redirect:http://localhost:8080/shorturl/"+urlResult.getShortUrl();
       else
           return "error";
    }
    @RequestMapping("/shorturl/{shorturl}")
    public String shortUrl(@PathVariable("shorturl") String shorturl ,Model model){
        model.addAttribute("info",server.queryByshortUrl(shorturl));
        return "shortUrl";
    }
    @RequestMapping("/url/{shorturl}")
    public String goUrl(@PathVariable("shorturl") String shorturl){
        urlVo vo=server.queryByshortUrl(shorturl);
        server.incTimes(shorturl);
        return "redirect:http://"+vo.getLongUrl();
    }
    @RequestMapping("/goUrl")
    public String gotoUrl(String shorturl){
        urlVo vo=server.queryByshortUrl(shorturl);
        server.incTimes(shorturl);
        return "redirect:http://"+vo.getLongUrl();
    }

}
