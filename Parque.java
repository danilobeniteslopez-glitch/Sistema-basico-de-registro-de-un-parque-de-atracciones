import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale;

public class Parque {
    private final Map<String, Atraccion> atracciones = new HashMap<>();
    private final Map<String, Trabajador> trabajadores = new HashMap<>();
    private String clave(String codigo) {
        return codigo == null ? "" : codigo.trim().toUpperCase(Locale.ROOT);
    }

    public boolean registrarAtraccion(Atraccion atraccion) {
        if (atraccion == null) return false;
        return atracciones.putIfAbsent(clave(atraccion.getCodigo()), atraccion) == null;
    }

    public boolean registrarTrabajador(Trabajador trabajador) {
        if (trabajador == null) return false;
        if (trabajador instanceof Operador) {
            Operador operador = (Operador) trabajador;
            Atraccion asignada = operador.getAtraccionAsignada();
            if (atracciones.get(clave(asignada.getCodigo())) != asignada) {
                return false;
            }
        }
        return trabajadores.putIfAbsent(clave(trabajador.getCodigo()), trabajador) == null;
    }

    public Atraccion buscarAtraccion(String codigo) {
        return atracciones.get(clave(codigo));
    }

    public Trabajador buscarTrabajador(String codigo) {
        return trabajadores.get(clave(codigo));
    }

    public List<Atraccion> listarAtracciones() {
        return new ArrayList<>(atracciones.values());
    }

    public List<Trabajador> listarTrabajadores() {
        return new ArrayList<>(trabajadores.values());
    }

    public boolean tieneOperadoresAsignados(Atraccion atraccion) {
        for (Trabajador trabajador : trabajadores.values()) {
            if (trabajador instanceof Operador) {
                Operador operador = (Operador) trabajador;
                if (operador.getAtraccionAsignada() == atraccion) return true;
            }
        }
        return false;
    }

    public boolean eliminarAtraccion(String codigo) {
        Atraccion atraccion = atracciones.get(clave(codigo));
        if (atraccion == null || tieneOperadoresAsignados(atraccion)) return false;
        return atracciones.remove(clave(codigo)) != null;
    }

    public boolean eliminarTrabajador(String codigo) {
        return trabajadores.remove(clave(codigo)) != null;
    }
}
