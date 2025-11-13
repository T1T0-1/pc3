import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class QuickSortDB extends JFrame implements ActionListener {

    private JTextArea outputArea;
    private JButton loadButton, sortButton, saveButton;
    private ArrayList<Integer> numeros = new ArrayList<>();

    // Datos de conexión
    private final String URL = "jdbc:mysql://localhost:3306/quicksortdb";
    private final String USER = "root";      // Cambia si tu usuario es distinto
    private final String PASS = "";          // Agrega contraseña si tienes una

    public QuickSortDB() {
        setTitle("QuickSort con Base de Datos - Divide y Vencerás");
        setSize(550, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Área de salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Botones
        JPanel buttonPanel = new JPanel();
        loadButton = new JButton("Cargar desde BD");
        sortButton = new JButton("Ordenar con QuickSort");
        saveButton = new JButton("Guardar en BD");

        loadButton.addActionListener(this);
        sortButton.addActionListener(this);
        saveButton.addActionListener(this);

        buttonPanel.add(loadButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(saveButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            cargarDesdeBD();
        } else if (e.getSource() == sortButton) {
            ordenarNumeros();
        } else if (e.getSource() == saveButton) {
            guardarEnBD();
        }
    }

    // Conectar y leer datos desde la base de datos
    private void cargarDesdeBD() {
        numeros.clear();
        outputArea.setText("Cargando datos desde la base de datos...\n");

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT valor FROM numeros")) {

            while (rs.next()) {
                numeros.add(rs.getInt("valor"));
            }

            outputArea.append("Datos cargados: " + numeros + "\n");

        } catch (SQLException ex) {
            outputArea.append("Error al conectar con la base de datos: " + ex.getMessage());
        }
    }

    // Aplicar QuickSort
    private void ordenarNumeros() {
        if (numeros.isEmpty()) {
            outputArea.append("Primero carga los datos desde la base de datos.\n");
            return;
        }

        int[] arr = numeros.stream().mapToInt(Integer::intValue).toArray();
        quickSort(arr, 0, arr.length - 1);

        outputArea.append("\nDatos ordenados: ");
        for (int num : arr) outputArea.append(num + " ");
        outputArea.append("\n");

        // Actualizar lista
        numeros.clear();
        for (int num : arr) numeros.add(num);
    }

    // Guardar el resultado en la tabla "numeros_ordenados"
    private void guardarEnBD() {
        if (numeros.isEmpty()) {
            outputArea.append("No hay datos para guardar.\n");
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement()) {

            st.executeUpdate("DELETE FROM numeros_ordenados");

            for (int num : numeros) {
                st.executeUpdate("INSERT INTO numeros_ordenados (valor) VALUES (" + num + ")");
            }

            outputArea.append("\nDatos ordenados guardados correctamente en la tabla 'numeros_ordenados'.\n");

        } catch (SQLException ex) {
            outputArea.append("Error al guardar en la base de datos: " + ex.getMessage());
        }
    }

    // QuickSort
    public static void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(arr, inicio, fin);
            quickSort(arr, inicio, indicePivote - 1);
            quickSort(arr, indicePivote + 1, fin);
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

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuickSortDB().setVisible(true);
        });
    }
}     
public class LaberintoBacktracking {

    static int[][] laberinto = {
        {1, 1, 0, 1},
        {0, 1, 1, 1},
        {0, 0, 1, 9} // 9 representa la salida
    };
    static boolean[][] visitado = new boolean[3][4];

    public static void main(String[] args) {
        if (resolverLaberinto(0, 0))
            System.out.println("¡Salida encontrada!");
        else
            System.out.println("No existe una salida.");
    }
