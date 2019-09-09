package com.app.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.PDFRenderer;
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

}//EmployeeServiceImpl class
