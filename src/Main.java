import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static DiscountManager dm = DiscountManager.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = new ArrayList<>();

        System.out.println("Bienvenido a la tienda de Ropa de Guiselle y Jennifer");
        System.out.println("=== Ingreso de Productos ===");
        while (true) {
            System.out.print("Ingrese Pieza de Ropa (o 'fin' para terminar): ");
            String nombre = scanner.nextLine().trim();
            if (nombre.equalsIgnoreCase("fin")) {
                break;
            }
            if (nombre.isBlank()) {
                System.out.println("no ingresaste nombre de pieza de ropa, intenta de nuevo.");
                continue;
            }
            System.out.print("Precio: ");
            String precioStr = scanner.nextLine().trim();
            double precio;
            try {
                precio = Double.parseDouble(precioStr);
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido.");
                continue;
            }
            items.add(new Item(nombre, precio));
            System.out.println("Añadido: " + nombre + " ($" + precio + ")\n");
        }

        if (items.isEmpty()) {
            System.out.println("No se ingresaron piezas de ropa.");
            scanner.close();
            return;
        }

        double total = items.stream().mapToDouble(Item::getPrecio).sum();
        System.out.println("Total sin descuento: $" + total);

        System.out.print("Porcentaje de descuento a aplicar al total (ej 15): ");
        double percent = 0;
        try {
            percent = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, se asume 0%.");
        }
        double totalConDescuento = dm.aplicarDescuento(total, percent);
        System.out.println("Total con " + percent + "% descuento: $" + totalConDescuento);

        System.out.print("Descuento fijo adicional (ej 20): ");
        double fijo = 0;
        try {
            fijo = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida, se asume 0.");
        }
        double totalFinal = dm.aplicarDescuentoFijo(totalConDescuento, fijo);
        System.out.println("Total final tras descuento fijo: $" + totalFinal);

        System.out.println("=== Resumen compra ===");
        items.forEach(i -> System.out.println(" - " + i));

        scanner.close();
    }
}
