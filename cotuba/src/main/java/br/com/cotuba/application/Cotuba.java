package br.com.cotuba.application;

import br.com.cotuba.cli.ParametrosOpcoesCLI;
import br.com.cotuba.domain.Capitulo;
import br.com.cotuba.domain.Ebook;
import br.com.cotuba.tema.Plugin;

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
