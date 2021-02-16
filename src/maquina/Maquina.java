package maquina;

public abstract class Maquina
{

    private String marca;
    private String modelo;
    protected int numeroDeSerie;
    static int cantidadDeMaquinasFabricadas=0;

    public Maquina(String marca, String modelo)
    {
        this.marca = marca;
        this.modelo = modelo;
        numeroDeSerie=Maquina.cantidadDeMaquinasFabricadas;
        Maquina.cantidadDeMaquinasFabricadas++;
    }

    public String getMarca()
    {
        return marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public int getNumeroDeSerie()
    {
        return numeroDeSerie;
    }

    public static int getCantidadDeMaquinasFabricadas()
    {
        return cantidadDeMaquinasFabricadas;
    }

    @Override
    public String toString()
    {
        return String.format("{ Marca: %-10s; modelo: %-10s; NS: %-4d }",
                this.getMarca(),
                this.getModelo(),
                this.getNumeroDeSerie());
    }


}
