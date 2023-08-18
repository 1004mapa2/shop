package com.shop.main.shirt.controller;

import com.shop.main.shirt.domain.Shirt;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShirtController {

    private static List<Shirt> store = new ArrayList<>();
    @GetMapping("/shirt")
    public String goShirt(Shirt shirt, Model model) {

        String url = "https://www.musinsa.com/categories/item/001";
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless"); //실행하는 장면을 숨긴다
        options.addArguments("-remote-allow-origins=*"); //모든 원격 서버에서 온 요청을 수락
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(url);
            List<WebElement> base = driver.findElements(By.id("searchList"));
            List<WebElement> elements = base.get(0).findElements(By.className("li_box"));

            for (int i = 0; i < 5; i++) {
                shirt.setShirtName(elements.get(i).findElement(By.className("list_info")).getText());
                shirt.setShirtPrice(elements.get(i).findElement(By.className("price")).getText());
                store.add(shirt);
            }

            for (Shirt a : store) {
                System.out.println(a.getShirtPrice());
                System.out.println(a.getShirtName());
            }
//            for (String q : a) {
//                System.out.println(q);
//            }
//            for (Object w : b) {
//                System.out.println(w);
//            }


//            model.addAttribute("elements", elements);

//            for (int i = 0; i < 5; i++) {
//                WebElement element = elements.get(i).findElement(By.className("list_info"));
//                List<Shirt> asd = (List<Shirt>) element;
//                for(Shirt a : asd){
//                    System.out.println(a);
//                }
//                System.out.println(element.getText());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }

        return "shirt/shirtMain";
    }
}
