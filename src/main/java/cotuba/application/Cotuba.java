package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEpub;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.pdf.GeradorPDF;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public void executar(String formato, Path diretorioDosMD, Path arquivoDeSaida) {
        RenderizadorMDParaHTML renderizadorMDParaHTML = new RenderizadorMDParaHTML();
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
    }

}
