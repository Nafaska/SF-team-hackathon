package com.ascendix.services;

import com.ascendix.models.TimeFoxUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class TimeFoxService {
    private WebDriver login(TimeFoxUser userData) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver73.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://fox1.functionfox.com/timefox/"; //TODO: add config file
        driver.get(baseUrl);
        WebElement userName = driver.findElement(By.name("user"));
        WebElement password = driver.findElement(By.name("passwd"));
        WebElement org_cd = driver.findElement(By.name("org_cd"));
        userName.sendKeys(userData.getUserName());
        password.sendKeys(userData.getPassword());
        org_cd.sendKeys(userData.getOrgId());
        WebElement loginForm = driver.findElement(By.id("lgf"));
        loginForm.submit();
        return driver;
    }
}
