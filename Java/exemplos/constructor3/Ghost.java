public class Ghost {
    private String color;
    private boolean isScary;

    public Ghost(String color, boolean isScary) {   //obs embaixo
        //super();
        this.color = color;
        this.isScary = isScary;
    }
    //parâmteros e variáveis de mesmo nome:
    //Se n especificarmos a qual estamos nos referindo, assum que
    //estamos falando ou da variável local, ou do parâmtero, ao invés
    //do campo.
    
    //comando "this"[constructor/non-static method] para especificar
    //que estamos falando do campo.


    public String getColor() {
        return color;
    }

    public boolean getIsScary() {
        return isScary;
    }
}