# -*- coding: utf-8 -*-
__author__ = 'JennyHui'
import unittest
from PO import DashPage

class Search(unittest.TestCase):

	def setUp(self):
		recipe_list = [u'苦瓜',u'黄瓜']
		self.recipe = recipe_list[-1]

	def test_Search(self):
		u'''测试好豆菜谱搜索框功能'''
		DashPage.search(self.recipe)

	def tearDown(self):
		pass

if __name__ == '__main__':
	unittest.main()

