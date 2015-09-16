package com.sh.manage.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.VisitRecordDao;
import com.sh.manage.entity.VisitRecord;
import com.sh.manage.module.page.Page;
import com.sh.manage.utils.ExcelUtil;

@Service
public class VisitRecordService extends BaseService{
	
	@Autowired
	private VisitRecordDao dao;
	
	public Page findAllVisitRecord(String visitorName, String startDate, String endDate,int pageNo, int pageSize) {
		return dao.getAllVisitRecord( visitorName, startDate, endDate, pageNo, pageSize);
	}
	
	public VisitRecord getById(Integer id){
		return dao.get(VisitRecord.class, id);
	}
	
	public void delVisitRecord(VisitRecord visitRecord){
		dao.delete(visitRecord);
	}
	
	public void addVisitRecord(VisitRecord visitRecord){
		visitRecord.setCreatedTime(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		dao.save(visitRecord);
	}
	
	public void updateVisitRecord(VisitRecord visitRecord){
		VisitRecord old = dao.getObject(visitRecord);
		if(old != null){
			old.setVisitedName(visitRecord.getVisitedName());
			old.setVisitorName(visitRecord.getVisitorName());
			old.setLeaveTime(visitRecord.getLeaveTime());
			old.setVisitTime(visitRecord.getVisitTime());
			old.setReason(visitRecord.getReason());
			old.setMobile(visitRecord.getMobile());
			old.setIdcard(visitRecord.getIdcard());
			dao.update(old);
		}
	}
	
	public void exportExcel(String startDate, String endDate, ServletOutputStream outputStream) {
		List<VisitRecord> list = dao.findByDate(startDate, endDate);
		String[] titles = new String[] { "来访日期", "来访人", "手机号码", "证件号码", "被访人", "来访事由", "离开时间" };
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workBook.createSheet("导出excel例子");
		ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		for (int i = 0; i < titles.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		// 构建表体数据
		if (list != null && list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				XSSFRow bodyRow = sheet.createRow(j + 1);
				VisitRecord visit = list.get(j);

				cell = bodyRow.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(visit.getVisitTime());

				cell = bodyRow.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(visit.getVisitorName());

				cell = bodyRow.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(visit.getMobile());

				cell = bodyRow.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(visit.getIdcard());

				cell = bodyRow.createCell(4);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(visit.getVisitedName());

				cell = bodyRow.createCell(5);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(visit.getReason());

				cell = bodyRow.createCell(6);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(visit.getLeaveTime());
			}
		}
		try {
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
  

}
