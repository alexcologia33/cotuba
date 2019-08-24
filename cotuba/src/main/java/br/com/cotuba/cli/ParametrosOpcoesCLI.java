package br.com.cotuba.cli;

import br.com.cotuba.application.FormatoEbook;

import java.nio.file.Path;

public interface ParametrosOpcoesCLI {
    Path getDiretorioDosMD();

    FormatoEbook getFormato();

    Path getArquivoDeSaida();
}
