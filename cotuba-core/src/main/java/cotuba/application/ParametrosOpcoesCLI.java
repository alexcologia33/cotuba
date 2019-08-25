package cotuba.application;

import cotuba.application.FormatoEbook;

import java.nio.file.Path;

public interface ParametrosOpcoesCLI {
    Path getDiretorioDosMD();

    FormatoEbook getFormato();

    Path getArquivoDeSaida();
}
