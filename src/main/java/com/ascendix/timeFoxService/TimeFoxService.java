package com.ascendix.timeFoxService;

import com.ascendix.models.TimeFoxUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TimeFoxService {
    public WebDriver login(TimeFoxUser userData) throws IOException {
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
        loginForm.submit(); //TODO: add timeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement month = driver.findElement(By.name("dtTS1"));
        WebElement date = driver.findElement(By.name("dtTS2"));
        WebElement year = driver.findElement(By.name("dtTS3"));
        //add more info xpath
        WebElement getdate = driver.findElement(By.className("btn"));
        month.sendKeys("March");
        date.sendKeys("21");
        year.sendKeys("19");
        getdate.click();
        WebElement time = driver.findElement(By.name("hrs"));
        WebElement descript = driver.findElement(By.name("descript"));
        WebElement client = driver.findElement(By.name("cln_cd2"));
        WebElement project = driver.findElement(By.name("job_cd2"));
        WebElement task = driver.findElement(By.name("tsk_cd"));
        //WebElement save = driver.findElement(By.name("btnSaveEdit"));
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        time.sendKeys("8");
        descript.sendKeys("Working");
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        WebElement saveNew = driver.findElement(By.xpath("//input[@type='button' and @value='Save New']"));
        saveNew.click();
        Select clientDropdown = new Select(driver.findElement(By.name("cln_cd2")));
        for (WebElement w : clientDropdown.getOptions()) {
            System.out.println(w.getText());
        }
        return driver;

    }
}
