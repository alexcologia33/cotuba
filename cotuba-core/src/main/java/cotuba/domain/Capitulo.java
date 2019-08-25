package cotuba.domain;

public class Capitulo {

    private final String titulo;
    private final String conteudoHTML;

    public Capitulo(String titulo, String conteudoHTML) {
        this.titulo = titulo;
        this.conteudoHTML = conteudoHTML;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudoHTML() {
        return conteudoHTML;
    }

    public static final class Builder {

        private String titulo;
        private String conteudoHTML;

        public Builder comConteudoHTML(String conteudoHTML) {
            this.conteudoHTML = conteudoHTML;
            return this;
        }

        public Builder comTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Capitulo build() {
            return new Capitulo(titulo, conteudoHTML);
        }

    }

}
