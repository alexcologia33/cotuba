package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.epub.GeradorEpubComEpubLib;

public interface GeradorEpub {
    void gerar(Ebook ebook);

    static GeradorEpub cria() {
        return new GeradorEpubComEpubLib();
    }
}
