package cotuba.md;

import cotuba.application.RenderizadorMDParaHTML;
import cotuba.domain.Capitulo;
import cotuba.tema.AplicadorTema;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RenderizadorMDParaHTMLComCommonMark implements RenderizadorMDParaHTML {

    @Override
    public List<Capitulo> renderiza(Path diretorioDosMD) {
        List<Capitulo> capituloList = new ArrayList<>();

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        try (Stream<Path> arquivosMD = Files.list(diretorioDosMD)) {
            arquivosMD
                    .filter(matcher::matches)
                    .sorted()
                    .forEach(arquivoMD -> {
                        Parser parser = Parser.builder().build();
                        Node document = null;
                        Capitulo.Builder builder = new Capitulo.Builder();

                        try {
                            document = parser.parseReader(Files.newBufferedReader(arquivoMD));
                            document.accept(new AbstractVisitor() {
                                @Override
                                public void visit(Heading heading) {
                                    if (heading.getLevel() == 1) {
                                        // capítulo
                                        builder.comTitulo(((Text) heading.getFirstChild()).getLiteral());
                                        // TODO: usar título do capítulo
                                    } else if (heading.getLevel() == 2) {
                                        // seção
                                    } else if (heading.getLevel() == 3) {
                                        // título
                                    }
                                }

                            });
                        } catch (Exception ex) {
                            throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
                        }

                        try {
                            HtmlRenderer renderer = HtmlRenderer.builder().build();
                            String html = renderer.render(document);
                            AplicadorTema aplicadorTema = new AplicadorTema();
                            html = aplicadorTema.aplica(html);
                            builder.comConteudoHTML(html);
                        } catch (Exception ex) {
                            throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
                        }

                        capituloList.add(builder.build());

                    });
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
        }

        return capituloList;
    }

}
