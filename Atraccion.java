import java.time.Year;

public abstract class Atraccion {
    private String codigo;
    private String nombre;
    private int añoDeInstalacion;
    private double mantenimientoBase;
    private boolean funcionando;
    public Atraccion(String codigo, String nombre, int añoDeInstalacion, double mantenimientoBase, boolean funcionando) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo esta vacio");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre esta vacio");
        }

        if (añoDeInstalacion < 1900 ||
                añoDeInstalacion > Year.now().getValue()) {
            throw new IllegalArgumentException("El año es menor a 1900");
        }

        if (!Double.isFinite(mantenimientoBase) ||
                mantenimientoBase <= 0) {
            throw new IllegalArgumentException("El costo de mantenimiento debe ser mayor a 0");
        }

        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.añoDeInstalacion = añoDeInstalacion;
        this.mantenimientoBase = mantenimientoBase;
        this.funcionando = funcionando;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAñoDeInstalacion() {
        return añoDeInstalacion;
    }

    public double getMantenimientoBase() {
        return mantenimientoBase;
    }

    public boolean isFuncionando() {
        return funcionando;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }
    public abstract double calcularMantenimiento();

    public abstract String condicionesMantenimiento();
}
