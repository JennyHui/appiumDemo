package me.jenny.TestCase;

import me.jenny.PO.*;
import me.jenny.Public.DataExcel;
import me.jenny.Public.LoggerControler;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestSearch{

	LoggerControler caseLog;
	DashPage DashPage;
	BasePage BasePage;
	int failTime = 0;
	int passTime = 0;

	@BeforeClass
	public void startUp() throws IOException, InvalidFormatException {
		DashPage = new DashPage();
		BasePage = new BasePage();
		DataExcel.filepath = "./src/me/jenny/Data/";
		DataExcel.inputfile = "caseData.xls";
		DataExcel.sheetname = "Data";
		caseLog = BasePage.MyLogger(TestSearch.class);
	}

	@AfterClass
	public void tearDown(){

	}

	@Test
	public void testSearch() throws Exception {
		caseLog.info("测试好豆菜谱首页搜索功能");
		List recipe = DataExcel.getColData(3);
		int runTime = recipe.size()-1;
		for(int i=1;i<=runTime;i++){
			String recipeName = String.valueOf(recipe.get(i));
			try {
				DashPage.Search(recipeName,TestSearch.class);
				passTime = passTime+1;
				caseLog.info("case成功；已有"+passTime+"个case成功");
				DataExcel.returnResult("Pass", 4,i);
				DashPage.driver.quit();
			}catch (Exception e){
				failTime = failTime+1;
				caseLog.info("case失败；已有"+failTime+"个case失败");
				DataExcel.returnResult("Fail", 4,i);
				DashPage.driver.quit();
			}
		}
		caseLog.info("测试完成。本次测试共有"+runTime+"个case;其中成功的有"+passTime+"个;失败的有"+failTime+"个。具体结果请看"+DataExcel.filepath+"目录下的Result文件");
		/**测试完成后修改Excel名*/
		File sourceFile = new File(DataExcel.filepath+DataExcel.inputfile);
		File btFile=new File(DataExcel.filepath+"Result_"+DataExcel.inputfile);
		sourceFile.renameTo(btFile);
	}
}


