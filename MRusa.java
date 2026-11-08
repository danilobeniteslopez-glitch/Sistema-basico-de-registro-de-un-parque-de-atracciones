public class MRusa extends Atraccion {
    private final double alturaMaxima;
    private final int pasajerosMaximos;

    public MRusa(
            String codigo,
            String nombre,
            int añoDeInstalacion,
            double mantenimientoBase,
            boolean funcionando,
            double alturaMaxima,
            int pasajerosMaximos) {

        super(codigo, nombre, añoDeInstalacion,
                mantenimientoBase, funcionando);

        if (!Double.isFinite(alturaMaxima) || alturaMaxima <= 0) {
            throw new IllegalArgumentException("La altura tiene que ser mayor a cero");
        }

        if (pasajerosMaximos <= 0) {
            throw new IllegalArgumentException("Los pasajeros deben ser mayores a cero");
        }
        this.alturaMaxima = alturaMaxima;
        this.pasajerosMaximos = pasajerosMaximos;
    }

    @Override
    public double calcularMantenimiento() {
        if (alturaMaxima > 30) {
            return getMantenimientoBase() * 1.20;
        }
        return getMantenimientoBase();
    }

    @Override
    public String condicionesMantenimiento() {
        if (alturaMaxima > 30) {return "Debido a la altura habra un incremento del 20%";}
        return "El mantenimiento es normal";
    }
    @Override
    public String toString() {
        return "Codigo: " + getCodigo() + " \nNombre: " + getNombre() + " \nAño: " + getAñoDeInstalacion() + "\n Mantenimiento base: S/ " + getMantenimientoBase() + "\n Funcionando: " + isFuncionando() + "\n Altura maxima: " + alturaMaxima + " m" + " \nPasajeros maximos: " + pasajerosMaximos + "\n Mantenimiento final: s/ " + calcularMantenimiento();
    }

}