/**
 * Esta clase permite guardar la fila y columna de una posición
 * @author Helmuth Trefftz
 */
public class Posicion {
    int fila;
    int col;
    
    /**
       * Constructor para la Posicion
       * @param fila El parámetro fila define el número de fila.
       * @param col El parametro col define el numero de columna.
       */
    public Posicion(int fila, int col) {
        this.fila = fila;
        this.col = col;
    }
    // public int Fantasma(int filaF, int colF){
        // this.filaF = filaF;
        // this.colF = colF;
       // return 0;
    // }

    public int getfila(){
        return this.fila;
    }

    public int getcol(){
        return this.col;
    }
    public void setfila(int fila){
    this.fila=fila;
    }
    public void setcol(int col){
    this.col=col;
    }

}
    
    
