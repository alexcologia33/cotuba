package cotuba.cli;

import cotuba.application.Cotuba;

import java.nio.file.Path;

public class Main {

	//gerar pdf gerar epub
	//le e grava arquivo

	public static void main(String[] args) {

		LeitorOpcoesCLI leitorOpcoesCLI = new LeitorOpcoesCLI(args);

		Path diretorioDosMD = leitorOpcoesCLI.diretorioDosMD;
		String formato = leitorOpcoesCLI.formato;
		Path arquivoDeSaida = leitorOpcoesCLI.arquivoDeSaida;
		boolean modoVerboso = leitorOpcoesCLI.modoVerboso;

		try {

			Cotuba cotuba = new Cotuba();
			cotuba.executar(formato, diretorioDosMD, arquivoDeSaida);

			System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			if (modoVerboso) {
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}
