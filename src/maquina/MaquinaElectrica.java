package maquina;

abstract public class MaquinaElectrica extends Maquina
{
    private int voltaje;
    private double potencia;
    private static int MIN_VOLTAJE=10;
    private static int MAX_VOLTAJE=400;
    private static int DEFAULT_VOLTAJE=MIN_VOLTAJE;
    private static double MIN_POTENCIA_ELECTRICA=700.0;
    private static double MAX_POTENCIA_ELECTRICA=200_000.0;
    private static double DEFAULT_POTENCIA_ELECTRICA=MIN_POTENCIA_ELECTRICA;

    public MaquinaElectrica(String marca, String modelo, int voltaje, double potencia) throws IllegalArgumentException
    {
        super(marca, modelo);
        if(voltaje < MIN_VOLTAJE || voltaje>MAX_VOLTAJE)
        {
            throw new IllegalArgumentException(" Error de voltaje:" + voltaje + ". Mínimo " +
                    MIN_VOLTAJE + "v y máximo" + MAX_VOLTAJE + " v.");
        }

        if(potencia < MIN_POTENCIA_ELECTRICA || potencia > MAX_POTENCIA_ELECTRICA)
        {
            throw new IllegalArgumentException(" Error de potencia:" + potencia + ". Mínimo " +
                    MIN_POTENCIA_ELECTRICA + "w y máximo" + MAX_POTENCIA_ELECTRICA + "  kw.");
        }

        this.voltaje = voltaje;
        this.potencia = potencia;
    }

    public MaquinaElectrica(String marca, String modelo)
    {
        this(marca, modelo,MIN_VOLTAJE,MIN_POTENCIA_ELECTRICA);
    }

    public int getVoltaje()
    {
        return voltaje;
    }

    public double getPotencia()
    {
        return potencia;
    }

    @Override
    public String toString()
    {
        String toStringSuper = super.toString();
        return String.format("%s; Voltaje: %-10d ; Potencia: %-10d}",
                toStringSuper.substring(0, toStringSuper.length() - 2),
                this.getVoltaje(),this.getPotencia());
    }


}
