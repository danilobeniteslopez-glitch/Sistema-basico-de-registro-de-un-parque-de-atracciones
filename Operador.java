public class Operador extends Trabajador {
    private final Atraccion atraccionAsignada;
    private final double horasTrabajadas;

    public Operador(String codigo, String nombreCompleto, String dni, double sueldoBase, int añoIngreso, Atraccion atraccionAsignada, double horasTrabajadas) {
        super(codigo, nombreCompleto, dni, sueldoBase, añoIngreso);
        if (atraccionAsignada == null) {
            throw new IllegalArgumentException("Debe asignar una atraccion existente");
        }
        if (!Double.isFinite(horasTrabajadas) || horasTrabajadas < 0) {
            throw new IllegalArgumentException("Horas trabajadas invalidas");
        }

        this.atraccionAsignada = atraccionAsignada;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Atraccion getAtraccionAsignada() {
        return atraccionAsignada;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        if (horasTrabajadas > 160) {
            return getSueldoBase() * 1.10;
        }
        return getSueldoBase();
    }

    @Override
    public String toString() {
        return super.toString() + "\n Atraccion asignada: " + atraccionAsignada.getNombre() + "\n Codigo de atraccion: " + atraccionAsignada.getCodigo() + "\n Horas trabajadas: " + horasTrabajadas;
    }
}
