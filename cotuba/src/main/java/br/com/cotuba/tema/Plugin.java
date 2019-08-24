package br.com.cotuba.tema;

import br.com.cotuba.domain.Ebook;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public interface Plugin {

    String cssDoTema();

    static List<String> listaTemas() {
        List<String> temas = new ArrayList<>();

        ServiceLoader<Plugin> loader = ServiceLoader.load(Plugin.class);
        for (Plugin plugin : loader) {
            String css = plugin.cssDoTema();
            temas.add(css);
        }

        return temas;
    }

    static void gerou(Ebook ebook) {

        ServiceLoader<Plugin> loader = ServiceLoader.load(Plugin.class);

        for (Plugin plugin : loader) {
            plugin.aposGeracao(ebook);
        }

    }

    void aposGeracao(Ebook ebook);

}
