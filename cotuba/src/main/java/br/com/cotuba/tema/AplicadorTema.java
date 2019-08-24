package br.com.cotuba.tema;

import br.com.cotuba.domain.Capitulo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class AplicadorTema {

    public void aplica(Capitulo capitulo) {

        String html = capitulo.getConteudoHTML();

        Document document = Jsoup.parse(html);

        //String css = "h1 { border-bottom: 1px dashed black; }";
        List<String> listaDeTemas = Plugin.listaTemas();
        for (String css : listaDeTemas) {
            document.select("head").append("<style> " + css + " </style>");
        }

        capitulo.setConteudoHTML(document.html());
    }

}
