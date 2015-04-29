package me.jenny.PO;

import me.jenny.Public.LoggerControler;
import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class DashPage extends BasePage {

	/**
	 * 页面元素
	 */

	//首页搜索框
	public static final By search_loc = By.id(BaseId + "search_button");

	//点击搜索框弹出的输入框
	public static final By input_loc = By.id(BaseId + "search_src_text");

	//搜索结果中的第一个结果：菜谱集
	public static final By clloection_loc = By.id(BaseId+"collect_item");

	//收藏菜谱集按钮
	public static final By favorite_loc = By.id(BaseId + "action_fav");

	//fail_fav_loc = (By.NAME,"取消收藏成功")

	//收藏菜谱当前页面的菜名
	public static final By title_loc = By.id(BaseId+"title");

	/**
	 * 操作方法
	 */
	//定位点击搜索框，进入搜索页面
	public void ClickSearchBox(){
		this.clickEle(search_loc);
	}

	//定位输入框，输入菜谱名
	public void InputRecipe(String recipe){
		this.sendKeys(input_loc,recipe);
	}

	//点击菜谱集（搜索结果中的的第一项）
	public void ClickCollectionItem(){
		this.clickEle(clloection_loc);
	}

	//点击收藏
	public void ClickFavorite(){
		this.clickEle(favorite_loc);
	}

	//收藏菜谱系列名
	public String GetRecipeName(){
		WebElement ele = this.findEle(title_loc);
		return ele.getText();
	}

	/**
	 * 业务封装
	 */

	//搜索 - 收藏
	public void Search(String recipe,Class name) throws IOException, InvalidFormatException {
		LoggerControler log = this.MyLogger(name);
		AndroidDriver driver = super.SetDriver();
		this.ClickSearchBox();
		this.InputRecipe(recipe);
		log.info("本次搜索的食材是："+recipe);
		driver.sendKeyEvent(66);
		this.ClickCollectionItem();
		//this.ClickFavorite();
		log.info("收藏的菜谱名为："+this.GetRecipeName());
		super.captureScreen("Search_"+recipe);
		Assert.assertTrue(GetRecipeName().contains(recipe));
		log.info("该Case测试通过");
	}
}
