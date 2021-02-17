package maquina;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class CocheElectrico extends Coche
{

    private int voltajeBateria;
    private int capacidadMaximaBateria; //35, 50, 75, 100, 125, 150, 200
    private double cargaEfectiva;
    private int autonomia;
    private int DEFAULT_CAPACIDAD_MAXIMA_BATERIA=100; // Capacidad máxima por defecto de la batería de un Coche Eléctrico. Valor: 100 kWh.
    private int DEFAULT_POTENCIA=100_000; //Potencia por defecto de un Coche Eléctrico. Valor: 100.000 W.
    private double DEFAULT_CARGA_EFECTIVA; // Carga efectiva por defecto para un Coche Eléctrico. Se entiende que la  carga efectiva va a ser siempre la mitad de la capacidad máxima de la batería, por lo que los coches saldrán de fábrica siempre con la batería a media carga. Por tanto, su valor se calculará siempre a partir de la capacidad máxima de la batería indicada anteriormente. ¿Por qué lo almacenamos entonces aparte, si puede calcularse? Porque estas son las reglas de fabricación actuales, pero en el futuro se puede decidir que el coche salga de fábrica completamente cargado, o completamente descargado, o con una cantidad concreta de carga... Así que mejor dejar el código preparado para actualizar ese valor por defecto de manera cómoda.
    private int MIN_AUTONOMIA; // La autonomía mínima permitida que se podrá asignar a un Coche Eléctrico. Valor: 300 kilómetros sin repostar/recargar.
    private int MAX_AUTONOMIA; // La autonomía máxima permitida que se podrá asignar a un Coche Eléctrico. Valor: 600 kilómetros sin repostar/recargar.
    private int DEFAULT_VOLTAJE=24;
    private HashSet<Integer> voltajes_permitidos=new HashSet<Integer>()
    {{
        add(12);
        add(24);
        add(48);
    }};
    private HashSet<Integer> potencias_permitidas= new HashSet<Integer>()
    {{
        add(35); add(50);
        add(75); add(100);
        add(125); add(150);
        add(200);
    }};
   HashSet<Integer> pp= (HashSet<Integer>) Stream.of(12,24,28).collect(Collectors.toSet());

    public CocheElectrico(String marca, String modelo, Fuerza fuerzaMotriz) throws NullPointerException, IllegalArgumentException
    {
        super(marca, modelo, fuerzaMotriz);
        capacidadMaximaBateria=DEFAULT_CAPACIDAD_MAXIMA_BATERIA;
        voltajeBateria=12;
        int a=49;
        voltajes_permitidos.contains(a);
    }

    @Override
    public TipoCombustible getTipoCombustible()
    {
        return null;
    }
}
