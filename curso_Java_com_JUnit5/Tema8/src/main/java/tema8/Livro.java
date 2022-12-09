package tema8;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private boolean fgEmprestado = false;
    private boolean fgAtivo = true;

    private static int cont=0;

    public Livro() {
        this.id = ++cont;
        fgEmprestado = false;
        fgAtivo = true;
    }

    public Livro(String titulo, String autor) {
        this.id = ++cont;
        this.titulo = titulo;
        this.autor = autor;
        fgEmprestado = false;
        fgAtivo = true;
    }

    public Livro(String titulo) {
        this.id = ++cont;
        this.titulo = titulo;
        this.autor = "desconhecido";
        fgEmprestado = false;
        fgAtivo = true;
    }
    private Livro(int id, String titulo, String autor, boolean fgEmprestado, boolean fgAtivo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fgEmprestado = fgEmprestado;
        this.fgAtivo = fgAtivo;
    }

    public static void setCont(int cont) {
        Livro.cont = cont;
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

    public boolean isFgAtivo () {
        return this.fgAtivo;
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

    public void excluiLivro() {
        this.fgAtivo = false;
    }

    public void printLivro() {
        System.out.println("ID: " + this.id + "\nTítulo: " + this.titulo + "\nAutor: " + this.autor);
        if (this.fgAtivo == true) {
            if (this.fgEmprestado == true) {
                System.out.println("Status: emprestado");
            } else {
                System.out.println("Status: disponível e existente");
            }
        } else {
            System.out.println("Status: sem estoque");
        }
    }

    public String geraString() {
        String string = this.getId() + ";" + this.getTitulo() + ";" + this.getAutor();
        string += ";" + this.isFgEmprestado() + ";" + this.isFgAtivo();
        return string;
    }

    public static Livro carregaPorString(String entrada) {
        String[] arrFields = entrada.split(";");
        int id = Integer.parseInt(arrFields[0]);
        String titulo = arrFields[1];
        String autor = arrFields[2];
        boolean fgEmprestado = Boolean.parseBoolean(arrFields[3]);
        boolean fgAtivo = Boolean.parseBoolean(arrFields[4]);
        Livro livroBaixado = new Livro(id, titulo, autor, fgEmprestado, fgAtivo);
        return livroBaixado;
    }
}
