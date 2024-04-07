package jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class JsoupBetman {

    public static void main(String[] args) {
        // Chrome 웹드라이버 경로 설정
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36");
        options.addArguments("--headless");

        // Chrome 웹드라이버 인스턴스 생성
        WebDriver driver = new ChromeDriver();

        // 웹페이지 열기
        driver.get("https://www.betman.co.kr/main/mainPage/gamebuy/gameSlip.do?gmId=G101&gmTs=240042");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 데이터 가져오기
//        List<WebElement> cells = driver.findElements(By.cssSelector("#tbl_gmBuySlipList tr"));
        List<WebElement> cells = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#tbl_gmBuySlipList tr")));


        // 셀 값 출력
        for (WebElement cell : cells) {
            System.out.println(cell.getText());
        }

        // 웹드라이버 종료
        driver.quit();
    }
}
