package tema11;

public class FabricaDeDados {
    public Dado criaDado(String tipoDeDado) {
        switch (tipoDeDado.toLowerCase()) {
            case "d4": return new D4();
            case "d6": return new D6();
            case "d8": return new D8();
            case "d10": return new D10();
            case "d12": return new D12();
            case "d20": return new D20();
            case "d100": return new D100(new D10());
            default: throw new RuntimeException("Tipo de dado inválido.");
        }
    }
}
