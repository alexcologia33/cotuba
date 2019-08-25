package cotuba.plugin;

import cotuba.domain.Ebook;

import java.util.ServiceLoader;

public interface AoFinalizarGeracao {

    static void gerou(Ebook ebook) {

        ServiceLoader<AoFinalizarGeracao> loader = ServiceLoader.load(AoFinalizarGeracao.class);

        for (AoFinalizarGeracao aoFinalizarGeracao : loader) {
            aoFinalizarGeracao.aposGeracao(ebook);
        }

    }

    void aposGeracao(Ebook ebook);

}
