package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.epub.GeradorEpub;
import cotuba.pdf.GeradorPDF;

public interface GeradorEbook {
    void gera(Ebook ebook);

    static GeradorEbook cria(String formato) {
        if ("pdf".equals(formato)) {
            return new GeradorPDF();
        } else if ("epub".equals(formato)) {
            return new GeradorEpub();
        } else {
            throw new RuntimeException(String.join("Formato do ebook inválido: ", formato));
        }
    }
}
