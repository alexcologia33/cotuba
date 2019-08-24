package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.epub.GeradorEpub;
import cotuba.pdf.GeradorPDF;

import java.util.HashMap;
import java.util.Map;

public interface GeradorEbook {

    Map<String, GeradorEbook> GERADORES = new HashMap<String, GeradorEbook>() {{
       put("pdf", new GeradorPDF());
       put("epub", new GeradorEpub());
    }};

    void gera(Ebook ebook);

    static GeradorEbook cria(FormatoEbook formato) {

        GeradorEbook geradorEbook = GERADORES.get(formato);

        if (geradorEbook == null) {
            throw new RuntimeException(String.join("Formato do ebook inválido: ", formato.toString()));
        }

        return geradorEbook;
    }
}
