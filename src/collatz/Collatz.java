package collatz;

/**
 * Clase cuyo método permite ver los pasos de la conjetura de Collatz de un
 * número natural mayor que cero.
 * @author Alberto Serrano Ibaibarriaga
 */
public class Collatz {

    public static String comprobarCollatz(int n) {
        String pasos;

        if (n > 0) {

            if (n == 1) {
                pasos = "1 alcanzado. Se para aquí para evitar el bucle 4-2-1.";
            } else {

                if ((n % 2) == 0) {
                    n /= 2;
                } else {
                    n = n * 3 + 1;
                }

                pasos = n + "\n" + comprobarCollatz(n);
            }

        } else {
            pasos = "ERROR: debe pasarse un entero positivo mayor que cero.";
        }

        return pasos;
    }

}
