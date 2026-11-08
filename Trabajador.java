public abstract class Trabajador {
    private final String codigo;
    private final String nombreCompleto;
    private final String dni;
    private final double sueldoBase;
    private final int añoIngreso;

    public Trabajador(String codigo, String nombreCompleto, String dni, double sueldoBase, int añoIngreso) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }

        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El dni no puede estar vacio");
        }

        if (!Double.isFinite(sueldoBase) || sueldoBase <= 0) {
            throw new IllegalArgumentException("El sueldo debe ser mayor a cero");
        }

        if (añoIngreso < 1900 ||
                añoIngreso > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("el año no puede ser menor a 1900");
        }

        this.codigo = codigo.trim();
        this.nombreCompleto = nombreCompleto.trim();
        this.dni = dni.trim();
        this.sueldoBase = sueldoBase;
        this.añoIngreso = añoIngreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public int getAñoIngreso() {
        return añoIngreso;
    }

    public abstract double calcularSueldo();

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\nNombre: " + nombreCompleto + "\nDNI: " + dni + "\nSueldo base: S/ " + sueldoBase + "\nAnio de ingreso: " + añoIngreso + "\nSueldo final: S/ " + calcularSueldo();
    }
}
