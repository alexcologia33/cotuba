package br.com.cotuba.application;

import br.com.cotuba.domain.Capitulo;
import br.com.cotuba.md.RenderizadorMDParaHTMLComCommonMark;

import java.nio.file.Path;
import java.util.List;

public interface RenderizadorMDParaHTML {
    List<Capitulo> renderiza(Path diretorioDosMD);

    static RenderizadorMDParaHTML cria() {
        return new RenderizadorMDParaHTMLComCommonMark();
    }
}
