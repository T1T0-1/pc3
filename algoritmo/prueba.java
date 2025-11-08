import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QuickSortGUI extends JFrame {

    private final JTextField txtNumeros;
    private final JTextField txtBuscar;
    private final JTextArea txtResultado;
    private int[] arregloOrdenado;

    public QuickSortGUI() {
        setTitle("QuickSort y Búsqueda Binaria (Divide y Vencerás)");
        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // --- Panel superior: entrada de números ---
        JPanel panelEntrada = new JPanel(new GridLayout(3, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos de Entrada"));

        panelEntrada.add(new JLabel("Ingrese números separados por comas:"));
        txtNumeros = new JTextField();
        panelEntrada.add(txtNumeros);

        panelEntrada.add(new JLabel("Número a buscar:"));
        txtBuscar = new JTextField();
        panelEntrada.add(txtBuscar);

        JButton btnOrdenar = new JButton("Ordenar (QuickSort)");
        JButton btnBuscar = new JButton("Buscar (Binaria)");

        panelEntrada.add(btnOrdenar);
        panelEntrada.add(btnBuscar);

        // --- Panel central: resultados ---
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(txtResultado);

        add(panelEntrada, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // --- Eventos de botones ---
        btnOrdenar.addActionListener((ActionEvent e) -> ordenarArreglo());
        btnBuscar.addActionListener((ActionEvent e) -> buscarElemento());
    }

    // --- Método para ordenar el arreglo con QuickSort ---
    private void ordenarArreglo() {
        try {
            String[] partes = txtNumeros.getText().split(",");
            int[] arreglo = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                arreglo[i] = Integer.parseInt(partes[i].trim());
            }

            quickSort(arreglo, 0, arreglo.length - 1);
            arregloOrdenado = arreglo;

            txtResultado.setText("=== RESULTADO DEL ORDENAMIENTO (QUICK SORT) ===\n");
            txtResultado.append("Arreglo ordenado:\n");
            for (int num : arregloOrdenado) {
                txtResultado.append(num + " ");
            }
            txtResultado.append("\n\nTécnica utilizada: DIVIDE Y VENCERÁS\n");
            txtResultado.append("→ Divide: separa en subarreglos menores y mayores al pivote\n");
            txtResultado.append("→ Vence: ordena recursivamente cada subarreglo\n");
            txtResultado.append("→ Combina: une los resultados para obtener el arreglo final\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Usa números separados por comas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
// --- Algoritmo QuickSort ---
    public static void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(arr, inicio, fin);
            quickSort(arr, inicio, indicePivote - 1);
            quickSort(arr, indicePivote + 1, fin);
        }
    }
    // --- Método para buscar un elemento (Búsqueda Binaria) ---
    private void buscarElemento() {
        if (arregloOrdenado == null) {
            JOptionPane.showMessageDialog(this, "Primero ordena el arreglo antes de buscar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int valor = Integer.parseInt(txtBuscar.getText().trim());
            int resultado = busquedaBinaria(arregloOrdenado, valor);

            txtResultado.append("\n=== RESULTADO DE LA BÚSQUEDA BINARIA ===\n");
            if (resultado == -1) {
                txtResultado.append("El elemento " + valor + " NO se encuentra en el arreglo.\n");
            } else {
                txtResultado.append("El elemento " + valor + " se encuentra en la posición (índice): " + resultado + "\n");
            }
            txtResultado.append("Técnica utilizada: DIVIDE Y VENCERÁS\n");
            txtResultado.append("→ Divide: busca en la mitad izquierda o derecha del arreglo\n");
            txtResultado.append("→ Vence: reduce el problema hasta encontrar el valor\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static int particion(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (arr[j] <= pivote) {
                i++;
                intercambiar(arr, i, j);
            }
        }
        intercambiar(arr, i + 1, fin);
        return i + 1;
    }

    public static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // --- Búsqueda Binaria ---
    public static int busquedaBinaria(int[] arr, int valor) {
        int inicio = 0, fin = arr.length - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (arr[medio] == valor)
                return medio;
            else if (arr[medio] < valor)
                inicio = medio + 1;
            else
                fin = medio - 1;
        }
        return -1;
    }

    // --- Método principal ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuickSortGUI().setVisible(true);
        });
    }
}


/////////
// MÉTODOS DE BASE DE DATOS (.TXT)
    // ======================================================

    // Cargar datos desde archivo .txt
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
       txtResultado.setText("No se encontró el archivo de datos. Se creará automáticamente.\n");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            if (linea != null && !linea.isEmpty()) {
                txtNumeros.setText(linea.trim());
                txtResultado.setText("Datos cargados desde archivo TXT:\n" + linea + "\n");
            } else {
                