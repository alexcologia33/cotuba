package br.com.cotuba.epub;

import br.com.cotuba.application.GeradorEbook;
import br.com.cotuba.domain.Capitulo;
import br.com.cotuba.domain.Ebook;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeradorEpub implements GeradorEbook {

    @Override
    public void gera(Ebook ebook) {
        Book epub = new Book();

        Path arquivoDeSaida = ebook.getArquivoDeSaida();

        for (Capitulo capitulo : ebook.getCapitulos()) {
            String html = capitulo.getConteudoHTML();
            String titulo = capitulo.getTitulo();
            epub.addSection(titulo, new Resource(html.getBytes(), MediatypeService.XHTML));
        }

        EpubWriter epubWriter = new EpubWriter();

        try {
            epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }
}
