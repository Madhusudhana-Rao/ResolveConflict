package com.user.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
sdvfd
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
trhthtyh
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.mulergfgfgfdgipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/hello")
public class UploadFile {
	
	@POST
	@Path("/pdf")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
	{
	    String UPLOAD_PATH = "d:/";
	    try
	    {
	    	
	        int read = 0;
	        byte[] bytes = new byte[1024];
	 
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
	        while ((read = fileInputStream.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	        
	    } catch (IOException e) 
	    {
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	    return Response.ok("File Data uploaded successfully !!").build();
	    
	  
	}
	
	@SuppressWarnings("unused")
	@POST
	@Path("/pdf2")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadPdfFile(@FormDataParam("file") FormDataBodyPart formDataBodyPart
			)
		
	 {
		System.out.println("hi");
		String identifier = null; // jsonMetadata.getString("identifier");
        String met = null; // jsonMetadata.getString("met");
        String height = null; // jsonMetadata.getString("height");
        String width = null; // jsonMetadata.getString("width");
        String profile = null; // jsonMetadata.getString("profile");
        InputStream dataStream = null;
        long fileSize = 0;
		
       
     
        
		List<BodyPart> bodyParts =formDataBodyPart.getParent().getBodyParts();
		
		System.out.println("bodyParts size "+bodyParts.size());
		for (BodyPart bodyPart : bodyParts) {
        	
            ContentDisposition contentDisposition = bodyPart.getContentDisposition();
            System.out.println("ContentDisposition:"+contentDisposition);
            
           String name = contentDisposition.getFileName();
          // System.out.println("Name:"+name);
           
          // System.out.println(contentDisposition.getFileName());
            
          //  System.out.println(bodyPart.getEntityAs(String.class));
            
            
            
            
           if (name.equals("profile")) {
                profile = bodyPart.getEntityAs(String.class);
                System.out.println("Profile:"+profile);
                
            }
        
           else if (name.equals("width")) {
                width = bodyPart.getEntityAs(String.class);
                System.out.println("Width:"+width);
            }
           else if (name.equals("height")) {
                height = bodyPart.getEntityAs(String.class);
                System.out.println("Height:"+height);
            }
           else if (name.equals("met")) {
                met = bodyPart.getEntityAs(String.class);
                System.out.println("Met:"+met);
            }
           else if (name.equals("identifier")) {
                identifier = bodyPart.getEntityAs(String.class);
                
                System.out.println("Identifier:"+identifier);
            }
           else if (name.equals("file")) {
                dataStream = bodyPart.getEntityAs(InputStream.class);
                fileSize = contentDisposition.getSize();
                System.out.println("File Size:"+fileSize);
            }            
		
		 
		}
		 return Response.ok("File Data uploaded successfully !!").build();
	 }
	
System.out.println("Ok");		
	
}
