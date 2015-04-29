#coding=utf-8
__author__ = 'JennyHui'
import unittest
from Public import HTMLTestRunner
import time
import os

case_path = ".\\TestCase"
result = ".\\Result\\"

def Creatsuite():
    #定义单元测试容器
    testunit = unittest.TestSuite()

    #定搜索用例文件的方法
    discover = unittest.defaultTestLoader.discover(case_path, pattern='Test_*.py', top_level_dir=None)

    #将测试用例加入测试容器中
    for test_suite in discover:
        for casename in test_suite:
            testunit.addTest(casename)
        print testunit
    return testunit

test_case = Creatsuite()

#获取系统当前时间
now = time.strftime('%Y-%m-%d-%H_%M_%S', time.localtime(time.time()))
day = time.strftime('%Y-%m-%d', time.localtime(time.time()))

#定义个报告存放路径，支持相对路径
tdresult = result + day
if os.path.exists(tdresult):
    filename = tdresult + "\\" + now + "_result.html"
    fp = file(filename, 'wb')
    #定义测试报告
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp, title=u'Appium测试报告', description=u'用例详情：')

    #运行测试用例
    runner.run(test_case)
    fp.close()  #关闭报告文件
else:
    os.mkdir(tdresult)
    filename = tdresult + "\\" + now + "_result.html"
    fp = file(filename, 'wb')
    #定义测试报告
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp, title=u'Appium测试报告', description=u'用例详情：')

    #运行测试用例
    runner.run(test_case)
    fp.close()  #关闭报告文件
