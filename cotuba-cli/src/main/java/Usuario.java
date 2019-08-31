public final class Usuario implements Cloneable {
    private final Long id;
    private final String nome;
    private final String email;

    public Usuario(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario atualizar(Builder builder) {
        Usuario clone = this.clone();
        return clone;
    }

    @Override
    public Usuario clone() {
        try {
            return (Usuario) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public final class Builder {

        private String nome;
        private String email;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
