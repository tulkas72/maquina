package maquina;

abstract public class MaquinaMecanica extends Maquina
{
    private Fuerza fuerzaMotriz;
    protected final static Fuerza DEFAULT_FUERZA_MOTRIZ=Fuerza.COMBUSTIBLE;

    public MaquinaMecanica(String marca, String modelo, Fuerza fuerzaMotriz) throws NullPointerException
    {
        super(marca, modelo);
        this.fuerzaMotriz=fuerzaMotriz;
        throw new NullPointerException("Error: null. La fuerza motriz no puede ser nula, debe indicarse una fuerza motriz válida.");
    }

    public MaquinaMecanica(String marca, String modelo )
    {
        this(marca,modelo, DEFAULT_FUERZA_MOTRIZ);
    }

    public Fuerza getFuerzaMotriz()
    {
        return fuerzaMotriz;
    }
    @Override
    public String toString() {
        String toStringSuper = super.toString();
        return String.format("%s; Fuerza Motriz: %-10s }",
                toStringSuper.substring(0, toStringSuper.length() - 2),
                this.getFuerzaMotriz());
    }

}
