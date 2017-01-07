package graficos;

import collatz.Collatz;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Clase que dispondrá los componentes gráficos de la aplicación para el uso de
 * la misma.
 * @author Alberto Serrano Ibaibarriaga
 */
public class Ventana extends JFrame {
    private JLabel etiquetaNumero;
    private JTextField textoNumero;
    private JButton botonEjecutar;
    private JTextArea textoConsola;
    private JScrollPane panelConsola;
    private JPanel panelControl, panelVentana;

    public Ventana() {
        configurarComponentes();
        configurarVentana();
    }

    private void configurarComponentes() {
        /* Instanciazión de algunos componentes gráficos. */
        etiquetaNumero = new JLabel(" Número:  ");
        textoNumero = new JTextField("1");
        botonEjecutar = new JButton("Ejecutar");
        textoConsola = new JTextArea();
        panelControl = new JPanel();
        panelVentana = new JPanel();

        /*
         * Configuración de las dimensiones y distribuciones de algunos
         * componentes gráficos.
         */
        textoNumero.setPreferredSize(new Dimension(
            128,
            textoNumero.getHeight()
        ));
        textoConsola.setPreferredSize(new Dimension(512, 2048));
        panelConsola = new JScrollPane(textoConsola);
        panelConsola.setPreferredSize(new Dimension(525, 256));
        panelControl.setLayout(new BorderLayout());
        panelVentana.setLayout(new BorderLayout());

        /* Distribución dentro de los componentes gráficos contenedores. */
        panelControl.add(etiquetaNumero, BorderLayout.WEST);
        panelControl.add(textoNumero, BorderLayout.CENTER);
        panelControl.add(botonEjecutar, BorderLayout.EAST);
        panelVentana.add(panelControl, BorderLayout.NORTH);
        panelVentana.add(panelConsola, BorderLayout.CENTER);

        /* Últimos retoques de presentación. */
        textoConsola.setText(
                "¡Bienvenid@!\nPara ver los pasos de la conjetura "
                + "puede escribir un número natural superior a cero y clicar el"
                + "\nbotón de arriba a la derecha.\nPara limpiar la consola "
                + "use el mismo botón pero escriba \"clean\"."
        );
        textoConsola.setEditable(false);

        /* Configuración de la acción a realizar tras un evento de clic. */
        botonEjecutar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int n;
                String texto = textoNumero.getText();

                if (texto.matches("[1-9][0-9]*")) {
                    n = Integer.parseInt(texto);
                    textoConsola.append("\nProcesando...");
                    textoConsola.append("\n" + Collatz.comprobarCollatz(n));
                } else if (texto.equals("clean")) {
                    textoConsola.setText("¡Impoluto!");
                } else {
                    textoConsola.append(
                        "\nERROR. Restricciones:\n"
                        + "    · N > 0, siendo N un entero.\n"
                        + "    · No vale poner ceros a la izquierda."
                    );
                }
            }

        });

        /* Establecimiento del panel principal de la ventana. */
        setContentPane(panelVentana);
    }

    private void configurarVentana() {
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Conjetura de Collatz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new Ventana().setVisible(true);
    }

}
