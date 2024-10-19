package com.dynamicpdf.pdf.service;
import com.dynamicpdf.pdf.model.InvoiceData;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class PDFService {

    private static final String OUTPUT_DIR = "D://javaassignment/new";

    public String generateInvoicePDF(InvoiceData data) {
        String pdfFilename = OUTPUT_DIR + "invoice_" + data.getSeller() + ".pdf";

        // Check if file already exists
        File file = new File(pdfFilename);
        if (file.exists()) {
            return pdfFilename;  // Return the existing file path
        }

        // If file doesn't exist, generate the PDF
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilename));
            document.open();

            // Add content to the PDF
            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("Seller: " + data.getSeller()));
            document.add(new Paragraph("Buyer: " + data.getBuyer()));

            // Loop through items and add them
            for (InvoiceData.Item item : data.getItems()) {
                document.add(new Paragraph("Item: " + item.getName() + ", Quantity: " + item.getQuantity() +
                        ", Rate: " + item.getRate() + ", Amount: " + item.getAmount()));
            }

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return pdfFilename;  // Return the path of the newly created PDF
    }
}
