package maquina;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class CocheElectrico extends Coche implements Recargable, Enchufable
{

    private int voltajeBateria;
    private int capacidadMaximaBateria; //35, 50, 75, 100, 125, 150, 200
    private double cargaEfectiva;
    private int autonomia;
    private int DEFAULT_CAPACIDAD_MAXIMA_BATERIA=100; // Capacidad máxima por defecto de la batería de un Coche Eléctrico. Valor: 100 kWh.
    private int DEFAULT_POTENCIA=100_000; //Potencia por defecto de un Coche Eléctrico. Valor: 100.000 W.
    private double DEFAULT_CARGA_EFECTIVA; // Carga efectiva por defecto para un Coche Eléctrico. Se entiende que la  carga efectiva va a ser siempre la mitad de la capacidad máxima de la batería, por lo que los coches saldrán de fábrica siempre con la batería a media carga. Por tanto, su valor se calculará siempre a partir de la capacidad máxima de la batería indicada anteriormente. ¿Por qué lo almacenamos entonces aparte, si puede calcularse? Porque estas son las reglas de fabricación actuales, pero en el futuro se puede decidir que el coche salga de fábrica completamente cargado, o completamente descargado, o con una cantidad concreta de carga... Así que mejor dejar el código preparado para actualizar ese valor por defecto de manera cómoda.
    private int MIN_AUTONOMIA=300; // La autonomía mínima permitida que se podrá asignar a un Coche Eléctrico. Valor: 300 kilómetros sin repostar/recargar.
    private int MAX_AUTONOMIA=600; // La autonomía máxima permitida que se podrá asignar a un Coche Eléctrico. Valor: 600 kilómetros sin repostar/recargar.
    private int DEFAULT_VOLTAJE=24;
    private HashSet<Integer> voltajes_permitidos = new HashSet<>() {{
        add(12);
        add(24);
        add(48);
    }};
    private HashSet<Integer> capacidades_permitidas = new HashSet<>()
    {{
        add(35); add(50);
        add(75); add(100);
        add(125); add(150);
        add(200);
    }};
   //HashSet<Integer> prueba= (HashSet<Integer>) Stream.of(12,24,28).collect(Collectors.toSet());
    /**
     * Array que en cada fila establece los países que comparten un determinado
     * voltaje estándar. Cada fila por tanto, corresponderá a un voltaje
     * estándar diferente. Este listado se usa internamente por la clase, por lo
     * que no es necesario que sea público.
     *
     */
    private final static String[][] LISTA_PAISES_COMPATIBLES = {
            {"España", "Portugal"}, // 12 v.
            {"Francia", "Bélgica"}, // 24 v.
            {"Inglaterra"} // 48 v.
    };
    public CocheElectrico(String marca, String modelo, Fuerza fuerzaMotriz) throws NullPointerException, IllegalArgumentException
    {
        super(marca, modelo, fuerzaMotriz);
        if(fuerzaMotriz!=Fuerza.ELECTRICIDAD)
            throw new IllegalArgumentException(
              String.format("Error en fuerza Motriz:%s. Para un coche eléctrico debe ser necesariamente%s.",
                             fuerzaMotriz, Fuerza.ELECTRICIDAD.name()));

        capacidadMaximaBateria=DEFAULT_CAPACIDAD_MAXIMA_BATERIA;
        voltajeBateria=DEFAULT_VOLTAJE;
        Maquina.cantidadDeMaquinasFabricadas++;
    }

    public CocheElectrico(String marca, String modelo, Fuerza fuerzaMotriz, int voltajeBateria, int capacidadMaximaBateria, int autonomia) throws NullPointerException, IllegalArgumentException
    {
        this(marca, modelo, fuerzaMotriz);
        boolean excepcion=false;

        if(!voltajes_permitidos.contains(voltajeBateria)) { excepcion=true;  }
        else
            if(!capacidades_permitidas.contains(capacidadMaximaBateria)){  excepcion=true;}
            else
                if(autonomia<MIN_AUTONOMIA || autonomia>MAX_AUTONOMIA)
                  { excepcion=true;  }

        if(excepcion)
        {
            Maquina.cantidadDeMaquinasFabricadas--;
            throw new IllegalArgumentException(
              String.format("Error: Voltaje%d, capacidad máxima %d o autonomia %d, fuera de valores permitidos",
                             voltajeBateria,capacidadMaximaBateria,autonomia));
        }

        this.voltajeBateria = voltajeBateria;
        this.capacidadMaximaBateria = capacidadMaximaBateria;
        this.autonomia = autonomia;
    }
    @Override
    public int getVoltaje()
    {
        return voltajeBateria;
    }

    @Override
    public String[] getPaisesCompatibles()
    {
        String paisesCompat[]=LISTA_PAISES_COMPATIBLES[0];
        switch (voltajeBateria)
        {
            case 12:
                paisesCompat=LISTA_PAISES_COMPATIBLES[0];
                break;
            case 24:
                paisesCompat=LISTA_PAISES_COMPATIBLES[1];
                break;
            case 48:
                paisesCompat=LISTA_PAISES_COMPATIBLES[2];
                break;
        }
        return paisesCompat;
    }

    @Override
    public TipoCombustible getTipoCombustible()
    {
        return TipoCombustible.ELECTRICIDAD;
    }

    private double kms_con_carga_actual()
    {
        return (cargaEfectiva*autonomia)/capacidadMaximaBateria;
    }
    @Override
    public void cargar()
    {
        cargaEfectiva=capacidadMaximaBateria;
        super.repostar();
    }
    @Override
    public double usarBateria(double cantidad)
    {
        double capUsada=(cantidad*capacidadMaximaBateria)/autonomia;
        cargaEfectiva-=capUsada;
        return cargaEfectiva;
    }


    @Override
    public void desplazar(double Kilometros)
    {
        if(kms_con_carga_actual()<Kilometros)
        {
          throw new IllegalArgumentException(
                  String.format("Error: Porcentaje de carga:%s. No es suficiente para recorrer%s kilómetros.",
                          cargaEfectiva, Kilometros));
        }
        super.desplazar(Kilometros);
        usarBateria(Kilometros);
    }

    @Override
    public String toString()
    {
        String toStringSuper = super.toString();
        String paisesCompat="";
        switch (voltajeBateria)
        {
            case 12:
                paisesCompat+=new String(LISTA_PAISES_COMPATIBLES[0][0]+" "+LISTA_PAISES_COMPATIBLES[0][1]);
                break;
            case 24:
                paisesCompat+=new String(LISTA_PAISES_COMPATIBLES[1][0]+" "+LISTA_PAISES_COMPATIBLES[1][1]);
                break;
            case 48:
                paisesCompat+=new String(LISTA_PAISES_COMPATIBLES[2][0]+" "+LISTA_PAISES_COMPATIBLES[2][1]);
                break;
        }
        return String.format("%s; Kilometraje: %10d; Voltaje: %3d; Capacidad Batería %3d; Carga Efectiva: %3d" +
                        "Países compatibles: %15s }",
                toStringSuper.substring(0, toStringSuper.length() - 2),
                getTotalKilometrosRecorridos(),voltajeBateria,capacidadMaximaBateria,cargaEfectiva,paisesCompat);
    }


}
