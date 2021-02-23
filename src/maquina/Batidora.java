package maquina;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Batidora extends MaquinaElectrica implements Enchufable
{

    private int DEFAULT_VOLTAJE_BATIDORA=230;// Establece un valor para el voltaje por defecto de una Batidorade 230 voltios.
    private int DEFAULT_POTENCIA_BATIDORA=700;// Establece un valor para la potencia por defecto de una Batidora de 700 watios.
    HashSet<Integer> voltajes_permitidos= (HashSet<Integer>) Stream.of(110,120,220,230).collect(Collectors.toSet());
    HashSet<Integer> potencias_permitidas= (HashSet<Integer>) Stream.of( 500,600,700,800,1000,1200,1500).collect(Collectors.toSet());

    /**
     * Array que en cada fila establece los países que comparten un determinado
     * voltaje estándar. Cada fila por tanto, corresponderá a un voltaje
     * estándar diferente. Este listado se usa internamente por la clase, por lo
     * que no es necesario que sea público.
     *
     */
    private final static String[][] LISTA_PAISES_COMPATIBLES =
    {
            {"Japón", "Corea"}, //para 110 v.
            {"USA"}, //para 120 v.
            {"China"},//para 220 v.
            {"España", "Alemania", "Francia", "Bélgica"}//para 230 v.
    };

    public Batidora(String marca, String modelo, int voltaje, double potencia) throws IllegalArgumentException
    {
        super(marca, modelo, voltaje, potencia);

    }

    @Override
    public String[] getPaisesCompatibles()
    {   //sabemos que si llegamos a esta función, la batidora se ha construido correctamente,
        //con un voltaje de los permitidos-> sólo hace falta comprobar los compatibles
        //por defecto ponemos los países compatibles con 110, si es otro voltaje se cambia en el switch
        String[] paisesCompat=LISTA_PAISES_COMPATIBLES[0];
        switch (getVoltaje())
        { //no
            case 120: paisesCompat=
        }
        return new String[0];
    }
}
