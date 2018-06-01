package com.ariba.procurment.mgmt.command.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ariba.procurment.mgmt.util.MQConstants;
import com.ariba.procurment.mgmt.util.SystemUtils;


@RestController
@RequestMapping("v1/uploadFile")
public class FileUploadCommandController 
{
	
	@Value("${metering.unixPath}")
    private String linuxDirectoryPath;
	
    @Value("${metering.windowsPath}")
    private String windowsDirectoryPath;

	@Autowired private RabbitTemplate rabbitTemplate;
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public String acceptOrRejectGroupRequest(@RequestParam("filess") java.util.List<MultipartFile> files,HttpServletRequest httpServletRequest) throws FileNotFoundException, IOException
	{
		String destPath = SystemUtils.IS_OS_UNIX ? linuxDirectoryPath : windowsDirectoryPath;
		
		if (files != null && files.size() > 0)
		{
			for (MultipartFile file  : files)
			{
				Path path = Paths.get(destPath + File.separator + file.getOriginalFilename());
				Files.write(path, file.getBytes());
				rabbitTemplate.convertAndSend(MQConstants.ARIBA_EXCHANGE_NAME, MQConstants.FILE_UPLOAD_ROUTING_KEY, path.toString());
			}
		}
	    return "{\"success\":true,\"message\":\"Your file has been uploaded.\"}";
	}
}
