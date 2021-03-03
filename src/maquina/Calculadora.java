package maquina;

public class Calculadora extends MaquinaElectrica implements Recargable
{

    TipoPila tipoPila; // Indica el tipo de pila que usa la calculadora de entre los disponibles en el tipo enumerado TipoPila, que se proporciona ya implementado, listo para usar.
    private double horasDeUso; //indica cuántas horas de uso lleva la calculadora desde el último cambio de pilas.
    boolean pilaAgotada;// De tipo lógico. Toma el valor true cuando la calculadora no tiene pila, o la tiene agotada (necesita una nueva pila), o false en otro caso.
    static int HORAS_DE_USO=100; //Constante de clase. Toda calculadora debe tener las pilas adecuadas para que cuando se le repongan permitan su uso durante  la cantidad de horas indicada en esta constante. Valor: 100 horas.
    static public TipoPila DEFAULT_TIPO_PILA=TipoPila.AA_1_5V; // Será el tipo de pila que por defecto se asigna a la calculadora en el momento de su fabricación, en este caso serán pilas AA de 1.5 voltios. (AA_1_5V)
    boolean DEFAULT_PILA_AGOTADA=true; //Por defecto la calculadora estará sin pilas, así que se considera que la pila está agotada, ya que necesita que se le introduzca una nueva pila. Valor por defecto: true.

    public Calculadora(String marca, String modelo, TipoPila tipoPila) throws NullPointerException,IllegalArgumentException
    {
        super(marca, modelo);
        if(tipoPila!=TipoPila.AA_1_5V || tipoPila!=TipoPila.BOTON_CR1025_3V)
            throw new IllegalArgumentException("Error en tipo de pila: "+tipoPila+". Las calculadoras solo admiten pilas de tipo"
                    + TipoPila.AA_1_5V +" y "+ TipoPila.BOTON_CR1025_3V+".");
        if(tipoPila==null)
            throw new NullPointerException(" Error en tipo de pila:"+ tipoPila+". El tipo de pila no puede ser nulo.");

        this.tipoPila=tipoPila;
        pilaAgotada=DEFAULT_PILA_AGOTADA;
        horasDeUso=0;
        numeroDeSerie=cantidadDeMaquinasFabricadas;
        Maquina.cantidadDeMaquinasFabricadas++;

    }

    public Calculadora(String marca, String modelo, TipoPila tipoPila, boolean pilaAgotada)
    {
        this(marca, modelo,tipoPila);
        this.pilaAgotada = pilaAgotada;
    }

    @Override
    public void cargar()
    {
        pilaAgotada=false;
        horasDeUso=0;
    }

    @Override
    public double usarBateria(double cantidad)
    {
        horasDeUso+=cantidad;
        return HORAS_DE_USO-horasDeUso;
    }
    @Override
    public String toString()
    {
        String toStringSuper = super.toString();
        return String.format("%s; Tipo de Pila: %-10s ; Potencia: %-10f}",
                toStringSuper.substring(0, toStringSuper.length() - 2),
                tipoPila.toString(),HORAS_DE_USO-horasDeUso);
    }
}
