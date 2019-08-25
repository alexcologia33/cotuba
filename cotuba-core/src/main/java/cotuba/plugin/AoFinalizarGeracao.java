package cotuba.plugin;

import cotuba.domain.Ebook;

import java.util.ServiceLoader;
import java.util.function.Consumer;

public interface AoFinalizarGeracao {

    void aposGeracao(Ebook ebook, Consumer<String> acaoPosGeracao);

    static void gerou(Ebook ebook, Consumer<String> acaoPosGeracao) {

        ServiceLoader<AoFinalizarGeracao> loader = ServiceLoader.load(AoFinalizarGeracao.class);

        for (AoFinalizarGeracao aoFinalizarGeracao : loader) {
            aoFinalizarGeracao.aposGeracao(ebook, acaoPosGeracao);
        }

    }

}
