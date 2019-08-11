package cotuba;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

public class Main {

	//gerar pdf gerar epub
	//le e grava arquivo

	public static void main(String[] args) {

		LeitorOpcoesCLI leitorOpcoesCLI = new LeitorOpcoesCLI(args);

		Path diretorioDosMD = leitorOpcoesCLI.diretorioDosMD;
		String formato = leitorOpcoesCLI.formato;
		Path arquivoDeSaida = leitorOpcoesCLI.arquivoDeSaida;
		boolean modoVerboso = leitorOpcoesCLI.modoVerboso;
		RenderizadorMDParaHTML renderizadorMDParaHTML = new RenderizadorMDParaHTML();

		try {

			List<Capitulo> capituloList = renderizadorMDParaHTML.renderiza(diretorioDosMD);

			Ebook ebook = new Ebook();
			ebook.setArquivoDeSaida(arquivoDeSaida);
			ebook.setCapitulos(capituloList);
			ebook.setFormato(formato);

			if ("pdf".equals(formato)) {

				GeradorPDF gerador = new GeradorPDF();
				gerador.gerar(ebook);

			} else if ("epub".equals(formato)) {

				GeradorEpub gerador = new GeradorEpub();
				gerador.gerar(ebook);

			} else {
				throw new RuntimeException("Formato do ebook inválido: " + formato);
			}

			System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			if (modoVerboso) {
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}
