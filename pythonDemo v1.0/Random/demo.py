#coding=utf-8
import time
from selenium import webdriver

capabilities = { 'browserName': 'Safari',
				 'platformName':'Android',
				 'platformVersion':'5.0.2',
				 'diviceNaem':'Xperia S',
				 'appPackage':'',
				 'appActivity':'',}
driver = webdriver.Remote('http://localhost:4723/wd/hub', capabilities)
time.sleep(10)
driver.quit()


'''
a_caps = {}
#automationName：设备使用哪种系统
a_caps['platformName']='Android'
#platformVersion：系统的版本
a_caps['platformVersion']='4.4.2'
#deviceName：设备名
a_caps['deviceName']='Xperia S'
#app：应用的绝对路径，注意一定是绝对路径。如果指定了appPackage和appActivity的话，这个属性是可以不设置的。
#appPackage这个值可以通过UIAutomator查到
a_caps['appPackage']='com.haodou.recipe'
#appActivity：待测试的app的Activity名字 //原生的activity要在前面加个'.'
a_caps['appActivity']='com.haodou.recipe.Main'

app属性和browserName属性是冲突的。
browserName：移动浏览器的名称。比如Safari' for iOS and 'Chrome', 'Chromium', or 'Browser' for Android；与app属性互斥。
udid：物理机的id。比如1ae203187fc012g。
'''
