public class Tecnico extends Trabajador {
    private final String especialidad;
    private final int mantenimientosRealizados;

    public Tecnico(String codigo, String nombreCompleto, String dni, double sueldoBase, int AñoIngreso, String especialidad, int mantenimientosRealizados) {
        super(codigo, nombreCompleto, dni, sueldoBase, AñoIngreso);

        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacia");
        }
        if (mantenimientosRealizados < 0) {
            throw new IllegalArgumentException("Los mantenimientos no pueden ser negativos");
        }
        this.especialidad = especialidad.trim();
        this.mantenimientosRealizados = mantenimientosRealizados;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public int getMantenimientosRealizados() {
        return mantenimientosRealizados;
    }

    @Override
    public double calcularSueldo() {
        if (mantenimientosRealizados > 5) {
            return getSueldoBase() * 1.15;
        }
        return getSueldoBase();
    }

    @Override
    public String toString() {
        return super.toString() + "\n Especialidad: " + especialidad + "\n Mantenimientos realizados: " + mantenimientosRealizados;
    }
}
