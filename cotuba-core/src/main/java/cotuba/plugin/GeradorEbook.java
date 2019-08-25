package cotuba.plugin;

import cotuba.application.FormatoEbook;
import cotuba.domain.Ebook;

import java.util.ServiceLoader;

public interface GeradorEbook {
    void gera(Ebook ebook);

    FormatoEbook formato();

    public static GeradorEbook criar(FormatoEbook formato) {

        for (GeradorEbook gerador : ServiceLoader.load(GeradorEbook.class)) {
            if (gerador.formato().equals(formato)) {
                return gerador;
            }
        }

        throw new RuntimeException("Formato de ebook inválido: "+ formato);
    }


}
