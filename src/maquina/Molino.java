package maquina;

public class Molino extends MaquinaMecanica
{
    private TipoMolino tipoMolino;

    public Molino(String marca, String modelo, Fuerza fuerzaMotriz) throws IllegalArgumentException
    {
        //if(fuerzaMotriz)
        super(marca, modelo, fuerzaMotriz);

    }
}
