public class AtraccionInf extends Atraccion {
    private final int edadMinima;
    private final int edadMaxima;
    public AtraccionInf(
            String codigo,
            String nombre,
            int añoDeInstalacion,
            double mantenimientoBase,
            boolean funcionando,
            int edadMinima,
            int edadMaxima) {

        super(codigo,nombre,añoDeInstalacion, mantenimientoBase, funcionando);
        if (edadMinima < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (edadMaxima < edadMinima) {
            throw new IllegalArgumentException("Las edades no pueden ser invalidas");
        }
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    @Override
    public double calcularMantenimiento() {
        if (edadMaxima <= 10) {
            return getMantenimientoBase() * 0.95;
        }
        return getMantenimientoBase();
    }

    @Override
    public String condicionesMantenimiento() {
        String edades = "Edades permitidas: " + edadMinima + " a " + edadMaxima + " años";

        if (edadMaxima <= 10) {
            return edades + "Descuento del 5%";
        }

        return edades + "Mantenimiento normal";
    }

    @Override
    public String toString() {
        return "Codigo: " + getCodigo() + " \nNombre: " + getNombre() + " \nAño: " + getAñoDeInstalacion() + " \nMantenimiento base: S/ " + getMantenimientoBase() + " \nFuncionando: " + isFuncionando() + " \nedad Minima: " + edadMinima + " m" + " \nedad Maxima: " + edadMaxima + " \nMantenimiento final: s/ " + calcularMantenimiento();
    }
}
