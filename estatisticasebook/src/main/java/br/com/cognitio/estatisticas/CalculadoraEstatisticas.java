package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.tema.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CalculadoraEstatisticas implements Plugin {
    @Override
    public String cssDoTema() {
        return null;
    }

    @Override
    public void aposGeracao(Ebook ebook) {
        for (Capitulo capitulo : ebook.getCapitulos()) {
            String html = capitulo.getConteudoHTML();
            Document doc = Jsoup.parse(html);
            String textoDoCapitulo = doc.body().text();
            String[] palavras = textoDoCapitulo.split("\\s+");
            for (String palavra : palavras) {
                System.out.printf(palavra);
            }
        }
    }
}
