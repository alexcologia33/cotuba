package aula2;

@Component // e nao service, pois service nao e dominio
public class Departamento {

    private SERASA serasa;
    private SPC spc;
    private TitulosDao titulosDao;
    private Secretaria secretaria;

    public void negativar() {
        List<Titulo> titulos = titulosDao.obterVencidos();
        spc.negativar(titulos);
        serasa.negativar(titulos);
        secretaria.informarNegativacao(titulos);
    }
}



------
Solucao

public class SERASA implements OPC {

}

public interface OPC {
    public void negativar();
}


@Component // e nao service, pois service nao e dominio
public class Departamento {

    private List<OPC> opcList;
    private TitulosRepository titulosRepository;
    private Secretaria secretaria;

    public void negativar() {
        List<Titulo> titulos = titulosRepository.obterVencidos();
        for (Opc opc : opcList) {
            opc.negativar(titulos);
        }
        secretaria.informarNegativacao(titulos);
    }
}
