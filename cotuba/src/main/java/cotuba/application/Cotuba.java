package cotuba.application;

import cotuba.cli.ParametrosOpcoesCLI;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.tema.Plugin;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public void executar(ParametrosOpcoesCLI parametrosOpcoesCLI) {

        FormatoEbook formato = parametrosOpcoesCLI.getFormato();
        Path diretorioDosMD = parametrosOpcoesCLI.getDiretorioDosMD();
        Path arquivoDeSaida = parametrosOpcoesCLI.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizadorMDParaHTML = RenderizadorMDParaHTML.cria();
        List<Capitulo> capituloList = renderizadorMDParaHTML.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capituloList);
        ebook.setFormato(formato);

        GeradorEbook gerador = GeradorEbook.cria(formato);
        gerador.gera(ebook);

        Plugin.gerou(ebook);
    }

}
