package com.ariba.procurment.mgmt.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import com.ariba.procurment.mgmt.dto.PurchaseRequisitionDTO;
import com.ariba.procurment.mgmt.event.CreatePurchaseRequisitionEvent;

import lombok.extern.slf4j.Slf4j;

public interface FileUploadService 
{
	public void uploadFile(String filePath,String logedinUser);
	
	@Service
	@Slf4j
	public class impl implements FileUploadService
	{
		@Autowired private PurchaseRequisitionService purchaseRequisitionService;
		public void uploadFile(String filePath,String logedinUser) 
		{
			try (FileInputStream inputStream = new FileInputStream(new File(filePath));Workbook wb = WorkbookFactory.create(inputStream))
			{
				
		        int numberOfSheet = wb.getNumberOfSheets();
		        if (numberOfSheet > 0)
		        {
		        	Sheet sheet = wb.getSheetAt(1);
		        	
		        	Row row  = sheet.getRow(12);
		        	row.getCell(0).setCellType(CellType.STRING);
		        	row.getCell(2).setCellType(CellType.STRING);
		        	row.getCell(3).setCellType(CellType.STRING);
		        	
		        	String title = row.getCell(0).getStringCellValue();
		        	String onBehalfOf = row.getCell(2).getStringCellValue();
		        	String companyCode = row.getCell(3).getStringCellValue();
		        	log.info("title  " + title + " "+onBehalfOf + " "+ companyCode);
		        	
		        	List<PurchaseRequisitionDTO> purchaseRequisitionDTOs = new ArrayList<>();
		        	
		        	for (int j = 15; j < sheet.getLastRowNum(); j++)
					{
						row  = sheet.getRow(j);
						row.getCell(0).setCellType(CellType.STRING);
						row.getCell(1).setCellType(CellType.STRING);
						row.getCell(2).setCellType(CellType.STRING);
						row.getCell(3).setCellType(CellType.STRING);
						row.getCell(4).setCellType(CellType.STRING);
						row.getCell(5).setCellType(CellType.STRING);
						row.getCell(6).setCellType(CellType.STRING);
						row.getCell(7).setCellType(CellType.STRING);
						row.getCell(8).setCellType(CellType.STRING);
						row.getCell(9).setCellType(CellType.STRING);
						row.getCell(10).setCellType(CellType.STRING);
						row.getCell(11).setCellType(CellType.STRING);
						if (row.getCell(12) != null) row.getCell(12).setCellType(CellType.STRING);
						if (row.getCell(13) != null) row.getCell(13).setCellType(CellType.STRING);
						if (row.getCell(15) != null) row.getCell(15).setCellType(CellType.STRING);

						if (row.getCell(0) != null && !"".equals(row.getCell(0).getStringCellValue()) && row.getCell(0).getStringCellValue() != null)
						{
							PurchaseRequisitionDTO requisitionDTO = PurchaseRequisitionDTO.builder()
									.vendor(row.getCell(0).getStringCellValue())
									.itemDescription(row.getCell(1).getStringCellValue())
									.commodity(row.getCell(2).getStringCellValue())
									.quantity(row.getCell(3).getStringCellValue())
									.price(row.getCell(4).getStringCellValue())
									.uom(row.getCell(5).getStringCellValue())
									.needByDate(row.getCell(6).getStringCellValue())
									.accountType(row.getCell(7).getStringCellValue())
									.costCenter(row.getCell(8).getStringCellValue())
									.glAccount(row.getCell(9).getStringCellValue())
									.shippingAddress(row.getCell(10).getStringCellValue())
									.comments(row.getCell(11).getStringCellValue())
									.supplierPartNumber(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : "")
									.eccPlant(row.getCell(13) != null ? row.getCell(13).getStringCellValue() : "")
									//.purchaseRequisitionNumber(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : "")
									.title(title)
									.onBehalfOf(onBehalfOf)
									.companyCode(companyCode)
									.createdBy(logedinUser)
									.build();
							purchaseRequisitionDTOs.add(requisitionDTO);
						}
					}
		        	CreatePurchaseRequisitionEvent event = new CreatePurchaseRequisitionEvent();
		        	event.setPurchaseRequisitionDTOs(purchaseRequisitionDTOs);
		        	purchaseRequisitionService.save(event);
		        }
			}
			catch (UnexpectedRollbackException e)
		    {
				log.error("Exception " + e.getCause(),e);
		    }
		    catch (Exception e)
		    {
		    	log.error("Exception " + e.getCause(),e);
		    }
		}
	}
}
