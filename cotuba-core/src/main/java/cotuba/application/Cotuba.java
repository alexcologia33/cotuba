package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;
import cotuba.plugin.GeradorEbook;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public final class Cotuba {

    public void executar(ParametrosOpcoesCLI parametrosOpcoesCLI, Consumer<String> acaoPosGeracao) {

        FormatoEbook formato = parametrosOpcoesCLI.getFormato();
        Path diretorioDosMD = parametrosOpcoesCLI.getDiretorioDosMD();
        Path arquivoDeSaida = parametrosOpcoesCLI.getArquivoDeSaida();

        RenderizadorMDParaHTML renderizadorMDParaHTML = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizadorMDParaHTML.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook(formato, arquivoDeSaida, capitulos);

        GeradorEbook.criar(formato).gera(ebook);

        AoFinalizarGeracao.gerou(ebook, acaoPosGeracao);
    }

}
