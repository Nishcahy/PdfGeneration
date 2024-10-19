package com.dynamicpdf.pdf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamicpdf.pdf.model.InvoiceData;
import com.dynamicpdf.pdf.service.PDFService;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {
	
	    private final PDFService pdfService;
	   
	    public PdfController(PDFService pdfService) {
	        this.pdfService = pdfService;
	    }

	    @PostMapping("/generate")
	    public ResponseEntity<String> generatePDF(@RequestBody InvoiceData invoiceData) {
	        String pdfPath = pdfService.generateInvoicePDF(invoiceData);
	        return ResponseEntity.ok("PDF generated at: " + pdfPath);
	    }
	

}
