#coding=utf-8
__author__ = 'JennyHui'
from appium import webdriver
from selenium.webdriver.common.by import By
import BasePage

'''
好豆菜谱首页涉及的所有页面元素 - 操作方法 -> 封装
'''
driver = webdriver.Remote('http://localhost:4723/wd/hub', BasePage.Base.capabilities)

class Dash(BasePage.Base):

	#搜索框
	search_loc = (By.ID,"com.haodou.recipe:id/search_button")
	#输入框
	input_loc = (By.ID,"com.haodou.recipe:id/search_src_text")

	#菜谱集
	clloect_loc =(By.ID,"com.haodou.recipe:id/collect_item")

	#收藏按钮
	favorite_loc = (By.ID,"com.haodou.recipe:id/action_fav")
	fail_fav_loc = (By.NAME,"取消收藏成功")

	#菜名
	title_loc = (By.ID,"com.haodou.recipe:id/title")

	#定位点击搜索框，进入搜索页面
	def click_search_box(self):
		self.clickButton(self.search_loc)

	#定位输入框，输入菜谱名
	def input_recipe(self,recipe):
		self.send_keys(self.input_loc,recipe)

	#点击菜谱集（搜索后的第一项）
	def click_clloect_item(self):
		self.clickButton(self.clloect_loc)

	#点击收藏
	def click_favorite_clloect(self):
		self.clickButton(self.favorite_loc)

	#收藏的菜谱系列名
	def get_recipe_name(self):
		els = self.find_elements(self.title_loc)
		#recipe_list = json.dumps(list, encoding='UTF-8', ensure_ascii=False)
		print u'收藏的菜谱名:'+els[0].text

#搜索/收藏
def search(recipe):
	dash_page = Dash(driver)
	dash_page.click_search_box()
	print u'搜索关键字:'+recipe
	dash_page.input_recipe(recipe)
	driver.press_keycode(66)
	dash_page.click_clloect_item()
	dash_page.click_favorite_clloect()
	dash_page.get_recipe_name()
	dash_page.saveScreenshot('search_'+recipe)
	driver.quit()



