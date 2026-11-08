public class JuegoMecanico extends Atraccion {
    private final int cantidadJuegos;
    private final double duracionPromedio;

    public JuegoMecanico(String codigo, String nombre, int añoDeInstalacion, double mantenimientoBase, boolean funcionando, int cantidadJuegos, double duracionPromedio) {

        super(codigo,nombre,añoDeInstalacion,mantenimientoBase,funcionando);
        if (cantidadJuegos <= 0) {
            throw new IllegalArgumentException("La cantidad de juegos debe ser mayor a cero");
        }
        if (!Double.isFinite(duracionPromedio) || duracionPromedio <= 0) {
            throw new IllegalArgumentException("La duracion debe ser mayor a cero");
        }

        this.cantidadJuegos = cantidadJuegos;
        this.duracionPromedio = duracionPromedio;
    }

    @Override
    public double calcularMantenimiento() {
        if (duracionPromedio > 10) {
            return getMantenimientoBase() * 1.10;
        }
        return getMantenimientoBase();
    }
    @Override
    public String condicionesMantenimiento() {
        if (duracionPromedio > 10) {
            return "Debido a la duración el precio aumenta";
        }
        return "Precio normal";
    }
    @Override
    public String toString() {
        return "Codigo: " + getCodigo() + " \nNombre: " + getNombre() + " \nAño: " + getAñoDeInstalacion() + " \nMantenimiento base: S/ " + getMantenimientoBase() + " \nFuncionando: " + isFuncionando() + " \nDuracion: " + duracionPromedio + "\n Cantidad de juegos: " + cantidadJuegos + " \nMantenimiento final: s/ " + calcularMantenimiento();
    }
}