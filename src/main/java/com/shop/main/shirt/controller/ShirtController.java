package com.shop.main.shirt.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Iterator;

@Controller
public class ShirtController {

    @GetMapping("/shirt")
    public String goShirt() throws IOException {
//        String URL = "https://1004mapa2.tistory.com/";
        String musinUrl = "https://www.musinsa.com/categories/item/001";

        Document doc = Jsoup.connect(musinUrl).get();
        String text = doc.text();
        System.out.println(text);
//        Iterator<Element> iterator = doc.select("strong.tit_post").iterator();
//        Iterator<Element> iterator = doc.select("p.price").iterator();

//        while(iterator.hasNext()){
//            System.out.println(iterator.next().text());
//        }

        return "shirt/shirtMain";
    }
}
