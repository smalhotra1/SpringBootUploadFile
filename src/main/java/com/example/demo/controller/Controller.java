package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class Controller {

	@Path("/file")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@DefaultValue("") @FormDataParam("tags") String tags, 
				@FormDataParam("file") InputStream file,
				@FormDataParam("file") FormDataContentDisposition fileDisposition) {

		String fileName = fileDisposition.getFileName();
		
		saveFile(file, fileName);
		
		String fileDetails = "File saved at C:\\Users\\ACERP\\Documents\\My new uploads" + fileName + " with tags "+ tags;

		System.out.println(fileDetails);

		return Response.ok(fileDetails).build();
	}
	
	private void saveFile(InputStream file, String name) {
		try {
			/* Change directory path */
			java.nio.file.Path path = FileSystems.getDefault().getPath("C:\\Users\\ACERP\\Documents\\My new uploads" + name); 
			/* Save InputStream as file */
			Files.copy(file, path);
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

}