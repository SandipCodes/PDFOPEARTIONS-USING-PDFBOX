package com.app;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.model.Employee;
import com.app.service.EmployeeService;

@SpringBootApplication
@EnableJpaRepositories
public class Task4PdfOperationsUsingPdfboxApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx=null;
		EmployeeService service=null;
		
		ctx=SpringApplication.run(Task4PdfOperationsUsingPdfboxApplication.class, args);

		service=ctx.getBean("empService", EmployeeService.class);
		
		
		Employee emp=service.getEmployeeById(1);
		//System.out.println("Received EMp is:"+emp);
		
		try {
		     //create new pdf doc
			String fileName="PdfWithText.pdf";
			PDDocument document=new PDDocument();
			PDPage page=new PDPage();
			
			//adding this page to our document
			document.addPage(page);
		
			//get a stream to write into Doc page
			PDPageContentStream contentStream=new PDPageContentStream(document, page);
			
			contentStream.setLineWidth(150);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 30);
			contentStream.moveTextPositionByAmount(150, 750);
			contentStream.drawString("Registration Form");
			contentStream.endText();
			
			
            contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
			contentStream.moveTextPositionByAmount(100, 650);
			contentStream.drawString("First Name:");
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
			contentStream.moveTextPositionByAmount(100, 630);
			contentStream.drawString("Last Name:");
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
			contentStream.moveTextPositionByAmount(100, 610);
			contentStream.drawString("Current Address:");
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
			contentStream.moveTextPositionByAmount(100, 590);
			contentStream.drawString("Permanant Address:");
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(100, 570);
			contentStream.drawString("------------------------------------------------------------------");
			contentStream.endText();
		
			//adding image to pdf document
			PDImageXObject imageXObject=PDImageXObject.createFromFile("dimple.jpg", document);
			contentStream.drawImage(imageXObject, 100, 200);

			
			contentStream.close();
		
			
			document.save(fileName);
			
			System.out.println("Your file is created in:"+ System.getProperty("user.dir"));
			
			/*//savePdfAsImage
			service.savePdfAsImage(document);
			document.close();
			
			//merge two pdf files
			service.mergeTwoPdfs();
			System.out.println("Files merged successfully in."+ System.getProperty("user.dir"));
		
			
			//split pdf doc
			service.splitPdf();
			System.out.println("Files Splitted successfully."+ System.getProperty("user.dir"));
		
			//adding rectangle
			service.addRectangle();
			System.out.println("Added rectangle successfully. See file at"+ System.getProperty("user.dir"));
		
			//encrypting doc
			service.encryptPdf();*/
			
			service.getTextOfPdf();
			
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
		
	}//main

}//class
