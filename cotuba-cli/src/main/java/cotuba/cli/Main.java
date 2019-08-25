package cotuba.cli;

import cotuba.application.Cotuba;

public class Main {

	//gerar pdf gerar epub
	//le e grava arquivo

	public static void main(String[] args) {

		LeitorOpcoesCLI opcoesCLI = new LeitorOpcoesCLI(args);

		try {

			Cotuba cotuba = new Cotuba();
			cotuba.executar(opcoesCLI, System.out::println);

			System.out.println("Arquivo gerado com sucesso: " + opcoesCLI.arquivoDeSaida);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}
	}

}
