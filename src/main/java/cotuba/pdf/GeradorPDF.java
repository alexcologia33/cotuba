package cotuba.pdf;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

public class GeradorPDF {

    public GeradorPDF() {

    }

    public void gerar(Ebook ebook) {
        try(
            PdfWriter writer = new PdfWriter(Files.newOutputStream(ebook.getArquivoDeSaida()));
            PdfDocument pdf = new PdfDocument(writer);
            Document pdfDocument = new Document(pdf)
        ) {

            Iterator<Capitulo> iterator = ebook.getCapitulos().iterator();

            while (iterator.hasNext()) {

                Capitulo capitulo = iterator.next();

                List<IElement> convertToElements = HtmlConverter.convertToElements(capitulo.getConteudoHTML());
                for (IElement element : convertToElements) {
                    pdfDocument.add((IBlockElement) element);
                }

                if (iterator.hasNext()) {
                    pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao criar arquivo PDF: " + ebook.getArquivoDeSaida().toAbsolutePath(), ex);
        }
    }
}
