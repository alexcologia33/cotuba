package br.com.paradizo.tema;


import cotuba.plugin.Tema;

public class TemaParadizo implements Tema {
    @Override
    public String cssDoTema() {
        System.out.printf("TemaParadizo.cssDoTema");
        return FileUtils.getResourceContents("/tema.css");
    }
}
