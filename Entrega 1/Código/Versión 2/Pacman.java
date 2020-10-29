

/**
 * En esta clase se guarda la información acerca del Pacman
 * El Pacman extiende la clase Caracter, y por lo tanto hereda sus
 * atributos y métodos
 * @author Helmuth Trefftz
 */
public class Pacman extends Caracter {
    // Cuántos puntos de vida tiene el Pacman
    int puntosVida;
/**
       * Constructor para Pacman
       * @param tipo El parámetro tipo define el tipo de Pacman .
       * @param posicion El parametro posicion define el numero de columna.
       * @param representacion El parametro representacion reṕresenta al Pacman.
       * @param puntosVida El parametro puntosVida nos da la cantidad de vida del Pacman.
       */
    public Pacman(int tipo, Posicion posicion, char representacion, int puntosVida) {
        super(tipo, posicion, representacion);
        this.puntosVida = puntosVida;
    }

}
