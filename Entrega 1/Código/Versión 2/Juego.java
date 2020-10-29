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
    int vidas = PUNTOS_VIDA_INICIALES ; 

    /**
     * Constructor
     * Se crea un tablero
     */
    public Juego() {
        tablero = new Tablero(this);
        
    }

    /**
     * Interacción con el usuario
     */
    public void jugar() {
        boolean ganaElJuego = false;
        tablero.dibujarTablero();
        Scanner in = new Scanner(System.in);
        String linea = in.nextLine();
        while (!linea.equals("q") && !ganaElJuego) {
            int fila = pacman.posicion.fila;
            int col = pacman.posicion.col;
            int nuevaFila = fila;
            int nuevaCol = col;
            switch (linea) {

                case "a":
                nuevaCol = col - 1;
                break;
                case "d":
                nuevaCol = col + 1;
                break;
                case "w":
                nuevaFila = fila - 1;
                break;
                case "s":
                nuevaFila = fila + 1;
                break;
                default:
                nuevaFila=fila;
                nuevaCol=col;
            }

            Celda anterior = tablero.tablero[fila][col];
            Celda nueva = tablero.tablero[nuevaFila][nuevaCol];                      
            if  (validarCasilla(nuevaFila, nuevaCol)==true)  { 
                nueva.caracter = pacman;
                anterior.caracter = null;
                pacman.posicion = new Posicion(nuevaFila, nuevaCol);
            }else {
                
                if (nueva.caracterCelda()=='.'){
                     
                    // si llego y paso por la calleta se inactive y cambie de caracter 
                     nueva.caracter = pacman;
                     anterior.caracter = null;
                     pacman.posicion = new Posicion(nuevaFila, nuevaCol);
                    for (int i = 0 ; i<tablero.gll.length ; i ++){
                     int x =tablero.gll[i].getPos_x(); 
                     int y =tablero.gll[i].getPos_y(); 
                   
                     if(x ==nuevaFila && y == nuevaCol){
                         
                        if(tablero.gll[i].getGalletin()){
                            vidas--;
                            System.out.println("Se te ha restado una vida");
                        }else{
                            vidas++;
                            System.out.println("Se te ha sumado una vida");
                            }
                        }
                     System.out.println("Total de vidas: " + vidas);
                    }
                
                   // Aquí hay que verificar si el jugador ganó el juego
                // Esto es, si llega a una parte del laberinto
                // que es una salida 
                   
                }
                else if(nueva.caracterCelda()=='O' ){
                System.out.println("Has ganado el juego, ¡felicitaciones!");
                }else{
                
                System.out.println("Movimiento no autorizado");
            
                   
            }
                
                
                
            }


            tablero.dibujarTablero();
            linea = in.nextLine();
        }
        if(ganaElJuego) {
            System.out.println();
        }
    }

    /**
     * En este metodo se debe chequear las siguientes condiciones:
     * (i) Que el usuario no se salga de las filas del tablero
     * (ii) Que el usuario no se salga de las columnas del tablero
     * (iii) Que la posición no sea un muro
     * (iv) Que no haya un caracter en esa posición
     * 
     * @param nuevaFila Fila hacia donde se quiere mover el usuario
     * @param nuevaCol Columna hacia donde se quiere mover el usuario
     * @return true si es una jugada válida, false de lo contrario
     */
    private boolean validarCasilla(int nuevaFila, int nuevaCol) {
        Celda nueva = tablero.tablero[nuevaFila][nuevaCol];   
        if  (nueva.caracterCelda() ==' '){           
            return true ;
        }else {
            return false;
        }
    }
}
