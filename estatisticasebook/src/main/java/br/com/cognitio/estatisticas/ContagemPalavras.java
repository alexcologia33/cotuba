package br.com.cognitio.estatisticas;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContagemPalavras implements Iterable<ContagemPalavras.Contagem> {

    private Map<String, Integer> map = new TreeMap<>();

    private static final long serialVersionUID = 1L;

    public void adicionaPalavra(String palavra) {
        Integer quantidade = map.getOrDefault(palavra, 0);
        quantidade++;
        map.put(palavra, quantidade);
    }


    public Set<Map.Entry<String, Integer>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Iterator<ContagemPalavras.Contagem> iterator() {

        Iterator<Map.Entry<String, Integer>> iterator = this.map.entrySet().iterator();

       return new Iterator<Contagem>() {
           @Override
           public boolean hasNext() {
               return iterator.hasNext();
           }

           @Override
           public Contagem next() {
               Map.Entry<String, Integer> next = iterator.next();
               String palavra = next.getKey();
               int qtd = next.getValue();
               return new ContagemPalavras.Contagem(palavra, qtd);
           }
       };
    }

    public static final class Contagem {
        private final String palavra;
        private final int quantiade;

        public Contagem(String palavra, int quantiade) {
            this.palavra = palavra;
            this.quantiade = quantiade;
        }

        public String getPalavra() {
            return palavra;
        }

        public int getQuantiade() {
            return quantiade;
        }

        @Override
        public String toString() {
            return "Contagem{" +
                    "palavra='" + palavra + '\'' +
                    ", quantiade=" + quantiade +
                    '}';
        }
    }
}
