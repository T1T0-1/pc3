import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuickSortGUI extends JFrame implements ActionListener {

    private JTextField inputField;
    private JTextArea outputArea;
    private JButton sortButton, clearButton;

    public QuickSortGUI() {
        // Configuración de la ventana
        setTitle("Ordenamiento QuickSort - Divide y Vencerás");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel superior: entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Ingrese números separados por comas: "));
        inputField = new JTextField(25);
        inputPanel.add(inputField);

        // Panel central: salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Panel inferior: botones
        JPanel buttonPanel = new JPanel();
        sortButton = new JButton("Ordenar");
        clearButton = new JButton("Limpiar");
        sortButton.addActionListener(this);
        clearButton.addActionListener(this);
        buttonPanel.add(sortButton);
        buttonPanel.add(clearButton);

        // Agregar componentes a la ventana
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Centrar ventana
    }

    // Acción de botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {
            String texto = inputField.getText().trim();

            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese algunos números.");
                return;
            }

            try {
                // Convertir texto en arreglo
                String[] partes = texto.split(",");
                int[] arreglo = new int[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    arreglo[i] = Integer.parseInt(partes[i].trim());
                }

                // Ordenar con QuickSort
                quickSort(arreglo, 0, arreglo.length - 1);

                // Mostrar resultado
                outputArea.setText("Arreglo ordenado:\n");
                for (int num : arreglo) {
                    outputArea.append(num + " ");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: asegúrate de ingresar solo números separados por comas.");
            }

        } else if (e.getSource() == clearButton) {
            inputField.setText("");
            outputArea.setText("");
        }
    }

    // Método QuickSort (Divide y Vencerás)
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

    // Método principal para ejecutar la interfaz
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuickSortGUI().setVisible(true);
        });
    }
}



//mejorando con base de datos 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuickSortGUI extends JFrame implements ActionListener {

      private final JTextField inputField;
    private final JTextArea outputArea;
    private JButton sortButton, clearButton;
  

    public QuickSortGUI() {
        // Configuración de la ventana
        setTitle("Ordenamiento QuickSort - Divide y Vencerás");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel superior: entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Ingrese números separados por comas: "));
        inputField = new JTextField(25);
        inputPanel.add(inputField);

        // Panel central: salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Panel inferior: botones
        JPanel buttonPanel = new JPanel();
        sortButton = new JButton("Ordenar");
        clearButton = new JButton("Limpiar");
        sortButton.addActionListener(this);
        clearButton.addActionListener(this);
        buttonPanel.add(sortButton);
        buttonPanel.add(clearButton);

        // Agregar componentes a la ventana
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Centrar ventana
    }

    // Acción de botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {
            String texto = inputField.getText().trim();

            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese algunos números.");
                return;
            }

            try {
                // Convertir texto en arreglo
                String[] partes = texto.split(",");
                int[] arreglo = new int[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    arreglo[i] = Integer.parseInt(partes[i].trim());
                }

                // Ordenar con QuickSort
                quickSort(arreglo, 0, arreglo.length - 1);

                // Mostrar resultado
                outputArea.setText("Arreglo ordenado:\n");
                for (int num : arreglo) {
                    outputArea.append(num + " ");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: asegúrate de ingresar solo números separados por comas.");
            }

        } else if (e.getSource() == clearButton) {
            inputField.setText("");
            outputArea.setText("");
        }
    }

    // Método QuickSort (Divide y Vencerás)
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

    // Método principal para ejecutar la interfaz
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuickSortGUI().setVisible(true);
        });
    }
}
// Datos de conexión
    private final String URL = "jdbc:mysql://localhost:3306/quicksortdb";
    private final String USER = "root";      // Cambia si tu usuario es distinto
    private final String PASS = "";          // Agrega contraseña si tienes una

    public QuickSortDB() {
        setTitle("QuickSort con Base de Datos - Divide y Vencerás");
        setSize(550, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

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

