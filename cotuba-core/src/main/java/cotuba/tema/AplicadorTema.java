package cotuba.tema;

import cotuba.domain.Capitulo;
import cotuba.plugin.Tema;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class AplicadorTema {

    public String aplica(String html) {

        Document document = Jsoup.parse(html);

        //String css = "h1 { border-bottom: 1px dashed black; }";
        List<String> listaDeTemas = Tema.listaTemas();
        for (String css : listaDeTemas) {
            document.select("head").append("<style> " + css + " </style>");
        }

        return document.html();
    }

}
