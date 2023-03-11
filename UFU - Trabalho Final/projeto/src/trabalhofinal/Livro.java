package trabalhofinal;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private boolean fgEmprestado = false;

    public Livro() {
        fgEmprestado = false;
    }

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        fgEmprestado = false;
    }

    public Livro(String titulo) {
        this.titulo = titulo;
        this.autor = "desconhecido";
        fgEmprestado = false;
    }
    
    private Livro(String titulo, String autor, boolean fgEmprestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.fgEmprestado = fgEmprestado;
    }
    
    Livro(int id, String titulo, String autor, boolean fgEmprestado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fgEmprestado = fgEmprestado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return this.autor;
    }

    public boolean isFgEmprestado () {
        return this.fgEmprestado;
    }

    public void emprestar() {
        this.fgEmprestado = true;
    }

    public void devolver() {
        this.fgEmprestado = false;
    }

    public Livro retornaLivro() {
        return this;
    }

    public void printLivro() {
        System.out.println("Livro\nID: " + this.id + "\nTítulo: " + this.titulo + "\nAutor: " + this.autor);
        if (this.fgEmprestado == true) {
            System.out.println("Status: emprestado");
        } else {
            System.out.println("Status: disponível e existente");
        }
    }
}
