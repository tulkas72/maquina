package maquina;

/**
 *
 */
public abstract class Coche extends MaquinaMecanica implements Desplazable
{
    /**
     *
     */
    protected double kilometrosSinRepostar;
    protected double kilometrosTotalesRecorridos;
    protected TipoCombustible combustible;
    protected static double MAX_DESPLAZAMIENTO=1_500.0;

    /**
     *
     * @param marca
     * @param modelo
     * @param fuerzaMotriz
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Coche(String marca, String modelo, Fuerza fuerzaMotriz) throws NullPointerException, IllegalArgumentException
    {
        super(marca, modelo, fuerzaMotriz);
        switch (fuerzaMotriz)
        {
            case ELECTRICIDAD: this.combustible=TipoCombustible.ELECTRICIDAD;
            case COMBUSTIBLE: this.fuerzaMotriz=fuerzaMotriz;
                break;
            case VIENTO:
            case CORRIENTE_AGUA:
            case ANIMAL: throw new IllegalArgumentException("Error, Fuerza motriz no válida:"+ fuerzaMotriz);
        }
        kilometrosSinRepostar=0.0;
        kilometrosTotalesRecorridos=0.0;

    }
    public void repostar(){kilometrosSinRepostar=0;}

    @Override
    public double getKilometrosSinRepostar()
    {
        return kilometrosSinRepostar;
    }

    @Override
    public double getTotalKilometrosRecorridos()
    {
        return kilometrosTotalesRecorridos;
    }

    @Override
    public void desplazar(double Kilometros)
    {
        if(Kilometros<0 || Kilometros>MAX_DESPLAZAMIENTO)
            throw new IllegalArgumentException(
                    String.format(" Cantidad de kilómetros negativa o excesiva (Máx: %s km): %s km.,",
                            MAX_DESPLAZAMIENTO, Kilometros));
        kilometrosTotalesRecorridos+=Kilometros;
        kilometrosSinRepostar+=Kilometros;
    }
    public abstract TipoCombustible getTipoCombustible();

    @Override
    public String toString()
    {
        String toStringSuper = super.toString();
        return String.format("%s; Combustible: %s; Kilómetros: %10d }",
                toStringSuper.substring(0, toStringSuper.length() - 2),
                combustible.toString(),kilometrosSinRepostar);
    }
}
