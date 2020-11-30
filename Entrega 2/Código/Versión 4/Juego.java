
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
/**
 * Esta clase maneja el juego
 * Se tiene una referencia al tablero y al pacman
 * En esta clase se hace la interacción con el usuario
 * @author Helmuth Trefftz
 */
import java.util.Scanner;

public class Juego {

    /**
     * El número de puntos iniciales de vida del pacman
     */
    public static final int PUNTOS_VIDA_INICIALES = 10;
    Tablero tablero; 
    Pacman pacman;
    ArrayList<Celda> visitadas = new ArrayList<>();

    /**

     *  Aqui Se crea un tablero
     */
    public Juego() {
        tablero = new Tablero(this);
    }

    public void jugar() {
        ArrayList<Celda> visitadas = new ArrayList<>();

        tablero.dibujarTablero();
        int fila = pacman.posicion.fila;
        int col = pacman.posicion.col;

        if(encontrarRuta(fila,col)) {
            System.out.println("Has encontrado la mejor ruta");
        }
        tablero.dibujarTablero();
    }

    /**
     * En este metodo se debe chequear las siguientes condiciones:
     * (i) Que el usuario no se salga de las filas del tablero
     * (ii) Que el usuario no se salga de las columnas del tablero
     * (iii) Que la posición no sea un muro
     * ( Que no haya un caracter en esa posición
     */
    private boolean validarCasilla(int nuevaFila, int nuevaCol) {

        Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
        if(nueva.caracterCelda() =='*'){
            return false;
        }
        return true;
    }

    public boolean encontrarRuta(int fila, int col){
        try{
            Thread.sleep(500);
        }       
        catch(InterruptedException excepcion){
            Thread.currentThread().interrupt();
        } boolean llegaste;
        Celda actual = tablero.tablero[fila][col];
        if(((validarCasilla(fila, col-1))||(validarCasilla(fila+1, col))||(validarCasilla(fila-1, col))||(validarCasilla(fila, col+1)))==false){
            visitadas.remove(tablero.tablero[fila-1][col]);
            visitadas.remove(tablero.tablero[fila+1][col]);
            visitadas.remove(tablero.tablero[fila][col+1]);
            visitadas.remove(tablero.tablero[fila][col-1]);

            tablero.tablero[fila][col-1].caracter= null;
            tablero.tablero[fila][col+1].caracter= null;
            tablero.tablero[fila-1][col].caracter= null;
            tablero.tablero[fila+1][col].caracter= null;
        }
        if(tablero.tablero[fila][col].esSalida) return true;
        if(visitadas.contains(actual)){
            return false;
        }else visitadas.add(actual);
        //Derecha
        if(validarCasilla(fila, col+1)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;
            llegaste = encontrarRuta(fila, col+1);
            tablero.dibujarTablero();
            if(llegaste){
                visitadas.add(actual);
                return true;
            }
        }

        //Izquierda
        if(validarCasilla(fila, col-1)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;

            llegaste = encontrarRuta(fila, col-1);
            tablero.dibujarTablero();
            if(llegaste){
                visitadas.add(actual);
                return true;
            }
        }

        //Arriba
        if(validarCasilla(fila-1, col)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;
            llegaste = encontrarRuta(fila-1, col);
            tablero.dibujarTablero();
            if(llegaste){
                visitadas.add(actual);
                return true;
            }
        }
        // Abajo
        if(validarCasilla(fila+1, col)){
            pacman.posicion = new Posicion(fila,col);
            tablero.tablero[fila][col].caracter = pacman;

            llegaste = encontrarRuta(fila+1, col);
            tablero.dibujarTablero();
            if(llegaste){
                visitadas.add(actual);
                return true;
            }
        }

        return false;
    }
}