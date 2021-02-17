package maquina;

public class Molino extends MaquinaMecanica
{
    private TipoMolino tipoMolino;

    public Molino(String marca, String modelo, Fuerza fuerzaMotriz) throws IllegalArgumentException, NullPointerException
    {
        super(marca, modelo, fuerzaMotriz);
        switch (fuerzaMotriz)
        {
            case ANIMAL: tipoMolino=TipoMolino.FUERZA_ANIMAL;
                break;
            case ELECTRICIDAD: tipoMolino=TipoMolino.ELECTRICO;
                break;
            case COMBUSTIBLE: tipoMolino=TipoMolino.A_MOTOR_COMBUSTION;
                break;
            case VIENTO: tipoMolino=TipoMolino.DE_VIENTO;
                break;
            case CORRIENTE_AGUA: tipoMolino=TipoMolino.DE_AGUA;
                break;
            default:  throw new IllegalArgumentException("Error, Fuerza motriz no válida:"+ fuerzaMotriz);
        }

        Maquina.cantidadDeMaquinasFabricadas++;
    }

    public TipoMolino getTipoMolino()
    {
        return tipoMolino;
    }
    @Override
    public String toString()
    {
        String toStringSuper = super.toString();
        return String.format("%s; Molino de: %s }",
                toStringSuper.substring(0, toStringSuper.length() - 2), tipoMolino.toString());
    }
}
