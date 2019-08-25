package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AcaoPosGeracao;
import cotuba.plugin.AoFinalizarGeracao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class CalculadoraEstatisticas implements AoFinalizarGeracao {

    @Override
    public void aposGeracao(Ebook ebook, Consumer<String> acaoPosGeracao) {
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

            for (ContagemPalavras.Contagem contagem : palavraContagemMap) {
                acaoPosGeracao.accept(contagem.toString());
            }
        }
    }
}
