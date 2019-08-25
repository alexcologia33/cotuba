package cotuba.cli;

import cotuba.plugin.AcaoPosGeracao;

public class ImprimeNoConsole implements AcaoPosGeracao {

    @Override
    public void executa(String mensagem) {
        System.out.println(mensagem);
    }

}
