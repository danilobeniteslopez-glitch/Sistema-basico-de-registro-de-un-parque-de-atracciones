import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Parque parque = new Parque();
    public static void main(String[] args) {
        int opcion;
        do {
            menu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                switch (opcion) {
                    case 1: case 2: case 3:
                        registrarAtraccion(opcion);
                        break;
                    case 4: case 5:
                        registrarTrabajador(opcion);
                        break;
                    case 6:
                        mostrarAtracciones();
                        break;
                    case 7:
                        mostrarTrabajadores();
                        break;
                    case 8:
                        buscarAtraccion();
                        break;
                    case 9:
                        buscarTrabajador();
                        break;
                    case 10:
                        modificarEstado();
                        break;
                    case 11:
                        eliminarAtraccion();
                        break;
                    case 12:
                        eliminarTrabajador();
                        break;
                    case 21:
                        System.out.println("Cerrando");
                        break;
                    default:
                        System.out.println("Input invalido");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Dato invalido: " + e.getMessage());
            }
        } while (opcion != 21);
        sc.close();
    }

    private static void menu() {
        System.out.println("Sistema de registro de parque de atracciones");
        System.out.println("1. Registrar montana rusa");
        System.out.println("2. Registrar juego mecanico");
        System.out.println("3. Registrar atraccion infantil");
        System.out.println("4. Registrar operador");
        System.out.println("5. Registrar tecnico");
        System.out.println("6. Mostrar atracciones");
        System.out.println("7. Mostrar trabajadores");
        System.out.println("8. Buscar atraccion");
        System.out.println("9. Buscar trabajador");
        System.out.println("10. Modificar estado de atraccion");
        System.out.println("11. Eliminar atraccion");
        System.out.println("12. Eliminar trabajador");
        System.out.println("21. Salir");
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("El texto esta vacio");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(leerTexto(mensaje).replace(',', '.'));
                if (Double.isFinite(valor)) return valor;
            } catch (NumberFormatException e) {
            }
            System.out.println("Decimal invalido");
        }
    }

    private static boolean leerEstado() {
        while (true) {
            String valor = leerTexto("Esta funcionando? (si/no): ");
            if (valor.equalsIgnoreCase("si")) return true;
            if (valor.equalsIgnoreCase("no")) return false;
            System.out.println("Escriba si o no");
        }
    }

    private static void registrarAtraccion(int tipo) {
        System.out.println("\nRegistro de atracciones");
        String codigo = leerTexto("Codigo: ");
        if (parque.buscarAtraccion(codigo) != null) {
            System.out.println("Ya existe una atraccion con ese codigo");
            return;
        }
        String nombre = leerTexto("Nombre: ");
        int Año = leerEntero("Año de instalacion: ");
        double precio = leerDecimal("Mantenimiento mensual base: ");
        boolean funcionando = leerEstado();

        Atraccion atraccion;
        switch (tipo) {
            case 1:
                double altura = leerDecimal("Altura maxima en metros: ");
                int pasajeros = leerEntero("Pasajeros maximos: ");
                atraccion = new MRusa(codigo, nombre, Año, precio, funcionando, altura, pasajeros);
                break;
            case 2:
                int juegos = leerEntero("Cantidad de juegos: ");
                double duracion = leerDecimal("Duracion promedio en minutos: ");
                atraccion = new JuegoMecanico(codigo, nombre, Año, precio, funcionando, juegos, duracion);
                break;
            case 3:
                int edadMin = leerEntero("Edad minima: ");
                int edadMax = leerEntero("Edad maxima: ");
                atraccion = new AtraccionInf(codigo, nombre, Año, precio, funcionando, edadMin, edadMax);
                break;
            default:
                return;
        }
        if (parque.registrarAtraccion(atraccion)) {
            System.out.println("Atraccion registrada");
        } else {
            System.out.println("El codigo ya existe");
        }
    }

    private static void registrarTrabajador(int tipo) {
        System.out.println("Registro de trabajadores");
        if (tipo == 4 && parque.listarAtracciones().isEmpty()) {
            System.out.println("Falta registrar una atraccion para asignar trabajadores");
            return;
        }
        String codigo = leerTexto("Codigo: ");
        if (parque.buscarTrabajador(codigo) != null) {
            System.out.println("Ya existe ese codigo con otro trabajador");
            return;
        }
        String nombre = leerTexto("Nombre completo: ");
        String dni = leerTexto("DNI: ");
        double sueldo = leerDecimal("Sueldo base: ");
        int Año = leerEntero("Año de ingreso: ");

        Trabajador trabajador;
        if (tipo == 4) {
            System.out.println("Atracciones disponibles: ");
            for (Atraccion a : parque.listarAtracciones()) {
                System.out.println(" - " + a.getCodigo() + " - " + a.getNombre());
            }
            String codigoAsignado = leerTexto("Codigo de atraccion asignada: ");
            Atraccion asignada = parque.buscarAtraccion(codigoAsignado);
            if (asignada == null) {
                System.out.println("La atraccion no existe");
                return;
            }
            double horas = leerDecimal("Horas trabajadas este mes: ");
            trabajador = new Operador(codigo, nombre, dni, sueldo,
                    Año, asignada, horas);
        } else {
            String especialidad = leerTexto("Especialidad: ");
            int mantenimientos = leerEntero("Mantenimientos realizados: ");
            trabajador = new Tecnico(codigo, nombre, dni, sueldo,
                    Año, especialidad, mantenimientos);
        }

        if (parque.registrarTrabajador(trabajador)) {
            System.out.println("Trabajador registrado correctamente");
        } else {
            System.out.println("codigo duplicado o atraccion no registrada.");
        }
    }

    private static void mostrarAtracciones() {
        List<Atraccion> lista = parque.listarAtracciones();
        if (lista.isEmpty()) {
            System.out.println("No hay atracciones registradas");
            return;
        }
        for (Atraccion a : lista) {
            System.out.println("\n ");
            System.out.println(a);
        }
    }

    private static void mostrarTrabajadores() {
        List<Trabajador> lista = parque.listarTrabajadores();
        if (lista.isEmpty()) {
            System.out.println("No hay trabajadores registrados");
            return;
        }
        for (Trabajador t : lista) {
            System.out.println("\n ");
            System.out.println(t);
        }
    }

    private static void buscarAtraccion() {
        String codigo = leerTexto("Codigo a buscar: ");
        Atraccion a = parque.buscarAtraccion(codigo);
        if (a == null) System.out.println("Atraccion no encontrada");
        else System.out.println(a);
    }

    private static void buscarTrabajador() {
        String codigo = leerTexto("Codigo a buscar: ");
        Trabajador t = parque.buscarTrabajador(codigo);
        if (t == null) System.out.println("Trabajador no encontrado");
        else System.out.println(t);
    }

    private static void modificarEstado() {
        String codigo = leerTexto("Codigo de atraccion: ");
        Atraccion a = parque.buscarAtraccion(codigo);
        if (a == null) {
            System.out.println("Atraccion no encontrada");
            return;
        }
        a.setFuncionando(leerEstado());
        System.out.println("Estado actualizado.");
    }

    private static void eliminarAtraccion() {
        String codigo = leerTexto("Codigo de atraccion a eliminar: ");
        Atraccion a = parque.buscarAtraccion(codigo);
        if (a == null) {
            System.out.println("Atraccion no encontrada");
        } else if (parque.tieneOperadoresAsignados(a)) {
            System.out.println("No se puede eliminar porque hay operadores asignados");
            System.out.println("Elimine esos operadores primero");
        } else if (parque.eliminarAtraccion(codigo)) {
            System.out.println("Atraccion eliminada");
        }
    }

    private static void eliminarTrabajador() {
        String codigo = leerTexto("Codigo de trabajador a eliminar: ");
        if (parque.eliminarTrabajador(codigo)) {
            System.out.println("Trabajador eliminado");
        } else {
            System.out.println("Trabajador no encontrado");
        }
    }
}
