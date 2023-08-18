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
    public String goShirt(Model model) {

        String url = "https://www.musinsa.com/categories/item/001";
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless"); //실행하는 장면을 숨긴다
        options.addArguments("-remote-allow-origins=*"); //모든 원격 서버에서 온 요청을 수락
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(url);
            String originalWindowHandle = driver.getWindowHandle();
            List<WebElement> base = driver.findElements(By.id("searchList"));
            List<WebElement> elements = base.get(0).findElements(By.className("li_box"));

            for (int i = 0; i < 5; i++) {
                Shirt shirt = new Shirt();
                shirt.setShirtName(elements.get(i).findElement(By.className("list_info")).getText());
                shirt.setShirtPrice(elements.get(i).findElement(By.className("price")).getText());
                shirt.setShirtImage(elements.get(i).findElement(By.tagName("img")).getAttribute("data-original"));
                store.add(shirt);
            }

            for(int i = 0; i < 2; i++) {
                List<WebElement> elements1 = base.get(0).findElements(By.className("li_box"));
                elements1.get(i).findElement(By.className("img-block")).click();
                driver.navigate().back();
            }

            model.addAttribute("store", store);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }

        return "shirt/shirtMain";
    }
}
