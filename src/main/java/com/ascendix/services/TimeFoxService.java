package com.ascendix.services;

import com.ascendix.models.Option;
import com.ascendix.models.TimeFoxTask;
import com.ascendix.models.TimeFoxUser;
import com.ascendix.properties.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeFoxService {
    private static WebDriver WEB_DRIVER;
    private static TimeFoxUser USER;

    public TimeFoxService(TimeFoxUser userData) {
        USER = userData;
    }

    private WebDriver login(TimeFoxUser userData) {
        System.setProperty("webdriver.chrome.driver", Settings.getSettingValueByName("chrome_driver_path"));
        WebDriver driver = new ChromeDriver();

        driver.get(Settings.getSettingValueByName("time_fox_url"));
        setInputElementValue(driver, "user", userData.getUserName());
        setInputElementValue(driver, "passwd", userData.getPassword());
        setInputElementValue(driver, "org_cd", userData.getOrgId());
        WebElement loginForm = driver.findElement(By.id("lgf"));
        loginForm.submit();
        waitSeconds(1);

        return driver;
    }

    private void setInputElementValue(WebDriver driver, String elementName, String userName2) {
        WebElement userName = driver.findElement(By.name(elementName));
        userName.sendKeys(userName2);
    }

    private WebDriver getTimefoxDriver () {
        if (WEB_DRIVER == null) {
            WEB_DRIVER = login(USER);
        }

        return WEB_DRIVER;
    }

    public List<Option> getProjectsByClient(String clientId) {
        WebDriver driver = getTimefoxDriver();
        setSelectValue(clientId, driver, "cln_cd2");
        return getOptions(driver, "job_cd2");
    }

    public List<Option> getClients() {
        return getOptions(getTimefoxDriver(), "cln_cd2");
    }

    public List<Option> getTasksByProject(String projectId, String clientId) {
        WebDriver driver = getTimefoxDriver();
        setSelectValue(clientId, driver, "cln_cd2");
        setSelectValue(projectId, driver, "job_cd2");
        return getOptions(driver, "tsk_cd");
    }

    private List<Option> getOptions(WebDriver driver, String elementName) {
        List<Option> options = new ArrayList<>();
        Select clientDropdown = new Select(driver.findElement(By.name(elementName)));
        if (clientDropdown != null) {
            for (WebElement webElement : clientDropdown.getOptions()) {
                options.add(new Option(webElement.getText(), webElement.getAttribute("value")));
            }
        }
        return options;
    }

    private void setSelectValue(String value, WebDriver driver, String elementName) {
        Select client = new Select(driver.findElement(By.name(elementName)));
        client.selectByValue(value);
        waitSeconds(2);
    }

    public void addTask(TimeFoxTask task){
        WebDriver driver = getTimefoxDriver();
        setSelectValue(task.getMonth(), driver, "dtTS1");
        setSelectValue(task.getDay(), driver, "dtTS2");
        setSelectValue(task.getYear(), driver, "dtTS3");

        setInputElementValue(driver, "hrs", task.getTime());
        setInputElementValue(driver, "descript", task.getDescription());
        setSelectValue(task.getClientId(), driver, "cln_cd2");
        setSelectValue(task.getProjectId(), driver, "job_cd2");
        setSelectValue(task.getTaskId(), driver, "tsk_cd");
        WebElement saveNew = driver.findElement(By.xpath("//input[@type='button' and @value='Save New']"));
        saveNew.click();
    }

    private static void waitSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void destroy() {
        if (WEB_DRIVER != null) {
            WEB_DRIVER.quit();
            WEB_DRIVER = null;
        }
    }
}
