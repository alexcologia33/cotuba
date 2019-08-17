package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.pdf.GeradorPDFComItext;

public interface GeradorPDF {
    void gerar(Ebook ebook);

    static GeradorPDF cria() {
        return new GeradorPDFComItext();
    }
}
