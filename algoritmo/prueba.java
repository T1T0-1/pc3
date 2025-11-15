import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LaberintoGUI extends JFrame {

    // 1 = camino, 0 = pared, 9 = salida
    int[][] laberinto = {
        {1, 1, 0, 1},
        {0, 1, 1, 1},
        {0, 0, 1, 9}
    };

    boolean[][] visitado = new boolean[3][4];

    JPanel panelLaberinto = new JPanel();

    public LaberintoGUI() {
        setTitle("Algoritmo Recursivo con Backtracking");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelLaberinto.setLayout(new GridLayout(3, 4));
        dibujarLaberinto();

        JButton btnResolver = new JButton("Resolver Laberinto");
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resolverLaberinto(0, 0)) {
                    JOptionPane.showMessageDialog(null, "¡Salida encontrada!");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay salida");
                }
                dibujarLaberinto();
            }
        });

        add(panelLaberinto, BorderLayout.CENTER);
        add(btnResolver, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Dibuja el laberinto en pantalla
    public void dibujarLaberinto() {
        panelLaberinto.removeAll();

        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                JLabel celda = new JLabel("", SwingConstants.CENTER);
                celda.setOpaque(true);
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (laberinto[i][j] == 0) {
                    celda.setBackground(Color.BLACK); // pared
                } else if (laberinto[i][j] == 9) {
                    celda.setBackground(Color.RED); // salida
                } else if (visitado[i][j]) {
                    celda.setBackground(Color.GREEN); // camino explorado
                } else {
                    celda.setBackground(Color.WHITE); // camino libre
                }

                panelLaberinto.add(celda);
            }
        }

        panelLaberinto.revalidate();
        panelLaberinto.repaint();
    }

    // Algoritmo recursivo con backtracking
    public boolean resolverLaberinto(int fila, int col) {

        // Fuera de límites
        if (fila < 0 || col < 0 || fila >= laberinto.length || col >= laberinto[0].length) {
            return false;
        }

        // Caso base: salida encontrada
        if (laberinto[fila][col] == 9) {
            visitado[fila][col] = true;
            return true;
        }

        // Si es pared o ya visitado
        if (laberinto[fila][col] == 0 || visitado[fila][col]) {
            return false;
        }

        visitado[fila][col] = true;

        // Intentar moverse
        if (resolverLaberinto(fila - 1, col)) return true; // arriba
        if (resolverLaberinto(fila + 1, col)) return true; // abajo
        if (resolverLaberinto(fila, col - 1)) return true; // izquierda
        if (resolverLaberinto(fila, col + 1)) return true; // derecha

        // Retroceder
        visitado[fila][col] = false;
        return false;
    }

    public static void main(String[] args) {
        new LaberintoGUI();
    }
}
