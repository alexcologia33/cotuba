package cotuba.cli;

import java.nio.file.Path;

public interface ParametrosOpcoesCLI {
    Path getDiretorioDosMD();

    String getFormato();

    Path getArquivoDeSaida();
}
