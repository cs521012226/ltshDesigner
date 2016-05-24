package org.ltsh.test.framework.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.ltsh.framework.util.ExcelUtil;

public class ExcelTest {
	@Test
	public void testExcel(){
		ExcelUtil excelUtil = new ExcelUtil();
		List list = new ArrayList();
		list.add(new Object[]{1,"name1"});
		list.add(new Object[]{2,"name2"});
		try {
			excelUtil.derivedExcel("d://","测试导出", new String[]{"id","姓名"}, new String[]{"site_通过:1,审批中:2,不通过:0"}, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
