package uabc.videoclubs.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;



import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import uabc.videoclubs.entities.Film;

public class FilmPdfGeneretor {
	public void generate(Film film, List<String> category, List<String> actores, HttpServletResponse reponse) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, reponse.getOutputStream());
		document.open();
		Font fonTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fonTiltle.setSize(20);
		Paragraph paragraph1 = new Paragraph("Detalles de pelicula", fonTiltle);
		document.add(paragraph1);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.BLACK);
		if(film.getTitle() != null) {
		Paragraph paragraph2 = new Paragraph("Titulo: " + film.getTitle(), font);
		document.add(paragraph2);}
		if( film.getFilmId() != null) {
		Paragraph paragraph3 = new Paragraph("id: " + film.getFilmId().toString(), font);
		document.add(paragraph3);}
		if(film.getRentalRate() != null) {
		Paragraph paragraph4 = new Paragraph("Precio: " + film.getRentalRate().toString(), font);
		document.add(paragraph4);}
		if(film.getReleaseYear() != null) {
		Paragraph paragraph5 = new Paragraph("Lanzamiento: " + film.getReleaseYear().toString(), font);
		document.add(paragraph5);}
		if(film.getLength() != null) {
		Paragraph paragraph6 = new Paragraph("Duracion: " + film.getLength().toString() + " minutos", font);
		document.add(paragraph6);
		}
		PdfPTable tableCategory = new PdfPTable(1);
		tableCategory.setWidthPercentage(100);
		tableCategory.setWidths(new int[] {3});
		tableCategory.setSpacingBefore(5);
		PdfPCell cellCat = new PdfPCell();
		cellCat.setBackgroundColor(CMYKColor.GRAY);
		cellCat.setPadding(5);
	    Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);

	    font2.setColor(CMYKColor.WHITE);
	    
	    cellCat.setPhrase(new Phrase("Categorias", font2));
	    tableCategory.addCell(cellCat);
	    for(String cate: category) {
	    	tableCategory.addCell(cate);
	    }
	    document.add(tableCategory);
	    if(film.getLanguage() != null) {
		Paragraph paragraph7 = new Paragraph("Lenguaje: " + film.getLanguage().getName(), font);
		document.add(paragraph7);}
	    if(film.getDescription() != null) {
		Paragraph paragraph8 = new Paragraph("Descripcion: " + film.getDescription(), font);
		document.add(paragraph8);}
		
		PdfPTable tableActor = new PdfPTable(1);
		tableCategory.setWidthPercentage(100);
		tableCategory.setWidths(new int[] {3});
		tableCategory.setSpacingBefore(5);
		PdfPCell cellActor = new PdfPCell();
		cellActor.setBackgroundColor(CMYKColor.GRAY);
		cellActor.setPadding(5);
		cellActor.setPhrase(new Phrase("Actores", font2));
		tableActor.addCell(cellActor);
		for(String act: actores) {
			tableActor.addCell(act);
		}
		document.add(tableActor);
		document.close();
		/*PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100 );
		table.setWidths(new int[] {3,3,3,3,3,3,3,3,3});
		table.setSpacingBefore(5);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.blue);
		cell.setPadding(5);
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);

	    font.setColor(CMYKColor.WHITE);
		cell.setPhrase(new Phrase("Titulo", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Id", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Precio de renta", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Lanzamiento", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Duracion", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Categoria", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Lenguaje", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Calificacion", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Desctripcion", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Actores", font));
		table.addCell(cell);
		
		table.addCell(film.getTitle());
		table.addCell(film.getFilmId().toString());
		table.addCell(film.getRentalRate().toString());
		table.addCell(film.getLength().toString());
		table.addCell()*/
	}
}
