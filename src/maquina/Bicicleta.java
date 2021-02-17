package maquina;

public class Bicicleta extends MaquinaMecanica implements Desplazable
{
    private double radioRueda;
    private double totalKilometros;


    static double DEFAULT_RADIO_RUEDA=33.0;
    static double MIN_RADIO_RUEDA= 17.75;
    static double MAX_RADIO_RUEDA=36.85;
    static double MAX_DESPLAZAMIENTO=200; //sin paradas


    public Bicicleta(String marca, String modelo)
    {
        super(marca, modelo,Fuerza.ANIMAL);
        this.radioRueda=DEFAULT_RADIO_RUEDA;
        Maquina.cantidadDeMaquinasFabricadas++;
    }

    public Bicicleta(String marca, String modelo, double radioRueda) throws IllegalArgumentException
    {
        super(marca, modelo, Fuerza.ANIMAL);
        if(radioRueda<MIN_RADIO_RUEDA || radioRueda > MAX_RADIO_RUEDA)
            throw new
                    IllegalArgumentException(
                    String.format(" Error en valor del radio:%scm. Debe estar comprendido entre%scm. y %sc",
                                   radioRueda, MIN_RADIO_RUEDA, MAX_RADIO_RUEDA));

        this.radioRueda = radioRueda;
        Maquina.cantidadDeMaquinasFabricadas++;
    }

    @Override
    public void desplazar(double Kilometros) throws IllegalArgumentException
    {
        if(Kilometros<0 || Kilometros>MAX_DESPLAZAMIENTO)
            throw new IllegalArgumentException(
                    String.format(" Cantidad de kilómetros negativa o excesiva (Máx: %s km): %s km.,",
                                    MAX_DESPLAZAMIENTO, Kilometros));
        totalKilometros+=Kilometros;
    }

    @Override
    public double getTotalKilometrosRecorridos() {
        return totalKilometros;
    }

    @Override
    public double getKilometrosSinRepostar() {
        return totalKilometros;
    }

    @Override
    public String toString()
    {
        String toStringSuper = super.toString();
        return String.format("%s; Radio: %10f; Kilómetros: %10f }",
                toStringSuper.substring(0, toStringSuper.length() - 2),
                radioRueda,totalKilometros);
    }
}
