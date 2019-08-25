package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraEstatisticas implements AoFinalizarGeracao {

    @Override
    public void aposGeracao(Ebook ebook) {
        for (Capitulo capitulo : ebook.getCapitulos()) {
            String html = capitulo.getConteudoHTML();
            Document doc = Jsoup.parse(html);
            String textoDoCapitulo = doc.body().text().replaceAll("\\p{Punct}", " ");
            textoDoCapitulo = Normalizer.normalize(textoDoCapitulo, Normalizer.Form.NFD);
            textoDoCapitulo = textoDoCapitulo.replaceAll("[^\\p{ASCII}]", " ");

            ContagemPalavras palavraContagemMap = new ContagemPalavras();
            String[] palavras = textoDoCapitulo.split("\\s+");

            for (String palavra : palavras) {
                palavraContagemMap.adicionaPalavra(palavra.toUpperCase());
            }

            for (Map.Entry<String, Integer> stringIntegerEntry : palavraContagemMap.entrySet()) {
                System.out.println(String.format("%s: %d", stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
            }
        }
    }
}
