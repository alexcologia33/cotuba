package br.com.cognitio.estatisticas;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContagemPalavras  {

    Map<String, Integer> map = new TreeMap<>();

    private static final long serialVersionUID = 1L;

    public void adicionaPalavra(String palavra) {
        Integer quantidade = map.getOrDefault(palavra, 0);
        quantidade++;
        map.put(palavra, quantidade);
    }


    public Set<Map.Entry<String, Integer>> entrySet() {
        return map.entrySet();
    }
}
