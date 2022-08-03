package uabc.videoclubs.controllers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfWriter;

import uabc.videoclubs.entities.Customer;
import uabc.videoclubs.entities.Inventory;
import uabc.videoclubs.entities.InventoryIndex;
import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.entities.Ticket;

public class RentalPdfExporter {
	public void generateRegisterRental(List<Rental> renta,String customer, List<String> listTitle, HttpServletResponse response) throws DocumentException, IOException{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font fonTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fonTiltle.setSize(20);
		Paragraph paragraph1 = new Paragraph("Detalles de renta", fonTiltle);
		document.open();
		document.add(paragraph1);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.BLACK);
		//Integer j = 0;
		for(Integer i = 0; i < renta.size(); i++) {
			Paragraph paragraph7 = new Paragraph(" ", font);
			Paragraph paragraph2 = new Paragraph("Titulo a rentar: " + listTitle.get(i));
			Paragraph paragraph3 = new Paragraph("Rental id: " + renta.get(i).getRentalId(), font);
			Paragraph paragraph4 = new Paragraph("Fecha y hora de renta: " + renta.get(i).getRentalDate().toString(), font);
			Paragraph paragraph5 = new Paragraph("No. de inventario: " + renta.get(i).getInventoryId().toString());
			
			document.add(paragraph7);
			document.add(paragraph2);
			document.add(paragraph3);
			document.add(paragraph4);
			document.add(paragraph5);
			
		}
		Paragraph paragraph6 = new Paragraph("No. de empleado que atendio: " + renta.get(0).getStaffId().toString());
		document.add(paragraph6);
		Paragraph paragraph8 = new Paragraph("nombre del cliente: " + customer, font);
		document.add(paragraph8);
		/*Paragraph paragraph2 = new Paragraph("Rental id: " + renta.getRentalId(), font);
		Paragraph paragraph3 = new Paragraph("Fecha y hora de renta: " + renta.getRentalDate(), font);
		//Paragraph paragraph4 = new Paragraph("Id de inventario: " + renta.getInventoryId(), font);
		Paragraph paragraph4 = new Paragraph("Titulo: " + inventoryIndex.getTitle(), font);
		//Paragraph paragraph4 = new Paragraph("Monto a pagar: " + renta.getA, font);
		//Paragraph paragraph5 = new Paragraph("Id de cliente: " + renta.getCustomerId(), font);
		Paragraph paragraph5 = new Paragraph("Cliente: " + customer , font);
		Paragraph paragraph6 = new Paragraph("id de trabajador: " + renta.getStaffId(), font);
		document.add(paragraph2);
		document.add(paragraph3);
		document.add(paragraph4);
		document.add(paragraph5);
		document.add(paragraph6);*/
		document.close();
	}
	public void ticketPdfCreator(String customerName, List<String> titleName, Float amount, HttpServletResponse response) throws DocumentException, IOException{
		final DecimalFormat df = new DecimalFormat("0.00");
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font fonTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fonTiltle.setSize(20);
		Paragraph paragraph1 = new Paragraph("Detalles de multa", fonTiltle);
		document.open();
		document.add(paragraph1);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.BLACK);
		Paragraph paragraph2 = new Paragraph("Cliente: "+ customerName, font);
		document.add(paragraph2);
		for(Integer i = 0; i < titleName.size(); i++) {
			Paragraph paragraph3 = new Paragraph("Pelicula multada: " + titleName.get(i), font);
			document.add(paragraph3);
		}
		Paragraph paragraph4 = new Paragraph("Total a pagar: " + df.format(amount), font);
		document.add(paragraph4);
		document.close();
		//
		//response.sendRedirect("/multa");
	}
}
