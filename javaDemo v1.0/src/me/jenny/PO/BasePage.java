package me.jenny.PO;

import me.jenny.Public.LoggerControler;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * 封装webdriver的底层方法
 */

public class BasePage {

	public AndroidDriver driver;
	public static final String BaseId = "com.haodou.recipe:id/";

	//初始化apk配置
	public DesiredCapabilities SetCa() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Sony");
		capabilities.setCapability("platformVersion", "5.0.2");
		capabilities.setCapability("appPackage", "com.haodou.recipe");
		capabilities.setCapability("appActivity", "com.haodou.recipe.Main");
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		return capabilities;
	}

	//初始化driver
	public AndroidDriver SetDriver() throws MalformedURLException {
		try{
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), this.SetCa());

		}catch (MalformedURLException e){
			e.printStackTrace();
		}
		return driver;
	}

	//封装智能等待方法
	private boolean waitToDisplayed(final By loc) {
		boolean waitDisplayed=false;
		waitDisplayed = new WebDriverWait(this.driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(loc).isDisplayed();
			}
		});
		return waitDisplayed;
	}

	//定位单个元素方法
	protected WebElement findEle(By loc){
		WebElement ele = null;
		if(this.waitToDisplayed(loc)){
			ele = this.driver.findElement(loc);
		}
		return ele;
	}

	//定位一组元素方法
	public List<WebElement> findEles(By loc){
		List<WebElement> eles = null;
		if (this.waitToDisplayed(loc)) {
			eles = this.driver.findElements(loc);
		}
		return eles;
	}

	//点击一个元素
	public void clickEle(By loc){
		WebElement ele = this.findEle(loc);
		ele.click();
	}

	//点击一组元素中的任一个方法
	public void clickEles(By loc,int i){
		List<WebElement> eles = this.findEles(loc);
		eles.get(i).click();
	}

	//输入方法
	public void sendKeys(By loc,String value){
		try{
		this.findEle(loc).clear();
		this.findEle(loc).click();
		this.findEle(loc).sendKeys(value);
		}catch(NoSuchElementException e){
			System.out.println("元素"+loc.toString()+"找不到");
		}
	}

	//获取当前日期
	public static String getCurrentDateTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		return df.format(new Date());
	}

	//获取脚本名字
	public String getName(String name){
		String date = getCurrentDateTime();
		String fp = "./src/me/jenny/Result/" + date + "/image/";
		String type = ".png";
		String filename = fp+"\\"+name+type;
		File file = new File(fp);
		if(!file.exists()){
			file.mkdir();
		}else{
			file = new File("./src/me/jenny/Result/"+filename);
			if(!file.exists()){
				file.mkdir();
			}
		}
		return filename;
	}

	//截图方法
	public void captureScreen(String name){
		File screenShotFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE );
		String filename = this.getName(name);
		try {
			FileUtils.copyFile (screenShotFile, new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//打Log方法
	public LoggerControler MyLogger(Class name){
		return LoggerControler.getLogger(name);
		}
}

