package cotuba.cli;

import cotuba.application.FormatoEbook;

import java.nio.file.Path;

public interface ParametrosOpcoesCLI {
    Path getDiretorioDosMD();

    FormatoEbook getFormato();

    Path getArquivoDeSaida();
}
