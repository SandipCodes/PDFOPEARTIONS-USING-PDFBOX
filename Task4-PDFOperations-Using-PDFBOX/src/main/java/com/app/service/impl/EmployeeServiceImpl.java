package com.app.service.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import com.app.service.EmployeeService;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	@Transactional
	public void saveEmployee(Employee e) {
	    repository.save(e);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee e) {
       repository.save(e);
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer id) {
        repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Employee getEmployeeById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public void savePdfAsImage(PDDocument document) {
		PDFRenderer renderer=new PDFRenderer(document);
		try {
			BufferedImage image=renderer.renderImage(0);
			
			ImageIO.write(image, "JPEG",new File("pdfimage.jpg"));
			//document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mergeTwoPdfs() {
			
		File file1=null;
		File file2=null;
		PDDocument document1=null;
		PDDocument document2=null;
		//declaring PDFMergerUtility
		PDFMergerUtility mergerUtility=null;
		
		//locating the existing pdf files
		file1=new File("E:\\UPSC\\MPSC\\Solved Papers\\MPSC-Prelims-Paper-1-Solved-2014.pdf");
	    file2=new File("E:\\UPSC\\MPSC\\Solved Papers\\MPSC-Prelims-Paper-2-Solved-2014.pdf");
	
	   try {
			//loading the pdf files
			
			document1=PDDocument.load(file1);
			document2=PDDocument.load(file2);

			 //instantiating the PDFMergerUtility
		    mergerUtility=new PDFMergerUtility();
		    
		    //setting the destination file
			mergerUtility.setDestinationFileName("merged-mpsc-papers-2014.pdf");
			
			//setting the source file
			mergerUtility.addSource(file1);
			mergerUtility.addSource(file2);
			
			//merge the documents using mergeDocuments()
			mergerUtility.mergeDocuments();
			
			
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   finally {
		   
		   if(document1!=null) {
			   try {
				   document1.close();   
			   }
			   catch(IOException ie) {
				   ie.printStackTrace();
			   }
			 
		   }
		   if(document2!=null) {
			   try {
				   document2.close();   
			   }
			   catch(IOException ie) {
				   ie.printStackTrace();
			   }
		   
		   }
	   }//finally
		
	}//method

	@Override
	public void splitPdf() {
		
		File file1=null;
		PDDocument document=null;
		Splitter splitter=null;
		file1=new File("E:\\UPSC\\mpscht.pdf");
		List<PDDocument> pages=null;
		try {
			document=PDDocument.load(file1);
			
			//intantiate the Splitter obj
			splitter=new Splitter();
			
			//splitting doc
			pages=splitter.split(document);
			
			//creating iterator
			Iterator<PDDocument> iterator=pages.listIterator();
			
			//saving every page as separate doc
			
			int i=1;
			
			while(iterator.hasNext()) {
				PDDocument pd=iterator.next();
				pd.save("splitted file"+ (i++) + ".pdf");
			}
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		
	}

	@Override
	public void addRectangle() {
		
		File file1=null;
		PDDocument document=null;
		PDPageContentStream content=null;
		//load existing pdf file
		file1=new File("E:\\UPSC\\mpscht.pdf");
		
		try {
		     document=PDDocument.load(file1);
		 	   //get page
				PDPage page=document.getPage(0);
			 
		     //instantiating pdpage content stream
		     content=new PDPageContentStream(document, page);
		     
		     //setting a non stroking color
		     
		     content.setNonStrokingColor(Color.GREEN);
		     
		     //drwa rect
		     content.addRect(200, 650, 100, 100);
		     content.fill();
		
		     //closing the content stream
		     content.close();
		     //saving the doc
		     document.save(new File("rectangle.pdf"));
		     //closing the doc
		     document.close();
		     
		     
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}//addRectangle()

	@Override
	public void encryptPdf() {
		
		File file1=null;
		PDDocument document=null;
		
		AccessPermission ap=null;
		StandardProtectionPolicy spp=null;
		//load existing pdf file
		file1=new File("E:\\UPSC\\mpscht.pdf");
		
		try {
		     document=PDDocument.load(file1);
		 	   
		     //create access permission object
		     ap=new AccessPermission();
		     
		     //create standard protection object
		     spp=new StandardProtectionPolicy("1234", "5678", ap);
		     
		     //setting the lenth of the protection key
		     spp.setEncryptionKeyLength(128);
		     
		     //setting the access permission
		     spp.setPermissions(ap);
		  
		     //protecting the doc
		     document.protect(spp);
		     
		     System.out.println("Document encrypted successfully");
		     
		     //saving the doc
		     document.save(new File("protected.pdf"));
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		finally {
			
			if(document !=null) {
				
				try {
					document.close();
				}
				catch(IOException ie) {
					ie.printStackTrace();
				}
			}
		}//finally
	}//method

	@Override
	public void getTextOfPdf() {
	
		File file1=null;
		PDDocument document=null;
	
		PDFTextStripper stripper=null;
		String text=null;
		//load existing pdf file
		file1=new File("E:\\UPSC\\mpscht.pdf");
		
		try {
		     document=PDDocument.load(file1);
		     
		     //instantiating PDFTextStripper obj
		     stripper=new PDFTextStripper();
		     
		     //getting text from pdf
		     text=stripper.getText(document);
		     
		     System.out.println(text);
	
	   }
		catch(IOException ie) {
			ie.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if(document !=null) {
				
				try {
					document.close();
				}
				catch(IOException ie) {
					ie.printStackTrace();
				}
			}
		}//finally
	
	}//getTextOfPdf()
		
}//EmployeeServiceImpl class
