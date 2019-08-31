package cotuba.web.controller;

import cotuba.web.utils.SpringFileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class GeracaoDeLivrosController {

    public ResponseEntity<ByteArrayResource> gerarPDF(@PathVariable("id") Long id, Model model) {
        Path pdf = Paths.get("/home/");
        return SpringFileUtils.montaResponseOArquivo(pdf, "application/pdf");
    }

}
