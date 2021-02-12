package maquina;

public class Bicicleta extends MaquinaMecanica implements Desplazable
{
    public Bicicleta(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void desplazar(double Kilometros) {

    }

    @Override
    public double getTotalKilometrosRecorridos() {
        return 0;
    }

    @Override
    public double getKilometrosSinRepostar() {
        return 0;
    }
}
