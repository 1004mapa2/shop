package com.shop.main.shirt.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShirtController {

    @GetMapping("/shirt")
    public String goShirt() {

        String url = "https://www.musinsa.com/categories/item/001";
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");

        options.addArguments("-remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        try{
            driver.get(url);
//            System.out.println(driver.getPageSource());
            List<WebElement> el = driver.findElements(By.className("list_info"));

            for (WebElement element : el) {
                System.out.println(element.getText());
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            driver.close();
        }

//        Thread.sleep(10000);
        return "shirt/shirtMain";
    }
}
