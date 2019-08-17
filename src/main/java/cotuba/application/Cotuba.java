package cotuba.application;

import cotuba.cli.ParametrosOpcoesCLI;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public void executar(ParametrosOpcoesCLI parametrosOpcoesCLI) {

        String formato = parametrosOpcoesCLI.getFormato();
        Path diretorioDosMD = parametrosOpcoesCLI.getDiretorioDosMD();
        Path arquivoDeSaida = parametrosOpcoesCLI.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizadorMDParaHTML = RenderizadorMDParaHTML.cria();
        List<Capitulo> capituloList = renderizadorMDParaHTML.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capituloList);
        ebook.setFormato(formato);

        if ("pdf".equals(formato)) {

            GeradorPDF gerador = GeradorPDF.cria();
            gerador.gerar(ebook);

        } else if ("epub".equals(formato)) {

            GeradorEpub gerador = GeradorEpub.cria();
            gerador.gerar(ebook);

        } else {
            throw new RuntimeException("Formato do ebook inválido: " + formato);
        }
    }

}
