package com.ariba.procurment.mgmt.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ariba.procurment.mgmt.service.FileUploadService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component 
public class FileUploadListener 
{
	@Autowired private FileUploadService service;
	
	public void processRequest(String filePath) throws Exception 
	{
		try 
		{
			log.info(" ######### Request :" + filePath);
			service.uploadFile(filePath, "");
		} 
		catch (Exception e) 
		{
			log.error("Exception occured in processRequest() "+e.getMessage());
		}
	}
}

