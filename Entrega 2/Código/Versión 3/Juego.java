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
    Fantasma fantasma;
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
        boolean perdiste=false;
        tablero.dibujarTablero();
        Scanner in = new Scanner(System.in);
        String linea = in.nextLine();
        while (!linea.equals("q") && !ganaElJuego) {
            int fila = pacman.posicion.fila;
            int col = pacman.posicion.col;
            int filaFantasma=fantasma.posicion.fila;
            int colFantasma=fantasma.posicion.col;
            int nuevaFila = fila;
            int nuevaCol = col;
            int nuevaFilaFantasma=filaFantasma;
            int nuevaColFantasma=colFantasma;
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
            // /**
            // * se valida y se mueve el pacman y el fantasma
            // * 
            // */
            Celda anterior = tablero.tablero[fila][col];
            Celda nueva = tablero.tablero[nuevaFila][nuevaCol]; 
            //se valida posicion actual y anterior del fantasma
            Celda nuevaFantasma=tablero.tablero[nuevaFilaFantasma][nuevaColFantasma];
            Celda anteriorFantasma=tablero.tablero[filaFantasma][colFantasma];
            if  (validarCasilla(nuevaFila, nuevaCol)==true)  { 
                nueva.caracter = pacman;
                anterior.caracter = null;
                // anteriorFantasma.caracter=null;
                //Se compara posicion del pacman con el fantasma para saber si perdio 
                if(nuevaFila==filaFantasma && nuevaCol==colFantasma){
                    System.out.println("Perdiste");
                    break;
                }
                //mueve el fantasma cuando ve al pacman
                if(nuevaFila==filaFantasma){
                    if(nuevaCol<colFantasma){
                        System.out.println("Fantasma dice :Te veo en mi fila");
                        nuevaColFantasma=nuevaColFantasma-2;
                        nuevaFantasma.caracter = fantasma;

                        anteriorFantasma.caracter=null;
                        fantasma.posicion=new Posicion(filaFantasma,nuevaColFantasma);
                    }else {nuevaColFantasma=nuevaColFantasma+2;
                        nuevaFantasma.caracter = fantasma;
                        
                        fantasma.posicion=new Posicion(filaFantasma,nuevaColFantasma);
                    }} 
                else if (nuevaCol==colFantasma){
                    if(nuevaFila<filaFantasma){
                        System.out.println( "Fantasma dice :Te veo en mi columna");
                        nuevaFilaFantasma=nuevaFilaFantasma-2;

                        nuevaFantasma.caracter = fantasma;
                        anteriorFantasma.caracter=null;
                        fantasma.posicion=new Posicion(nuevaFilaFantasma,colFantasma);
                    }else { nuevaFilaFantasma=nuevaFilaFantasma+2;

                        nuevaFantasma.caracter = fantasma;
                        fantasma.posicion=new Posicion(nuevaFilaFantasma,colFantasma);
                    }} else{
                    System.out.println("Fantasma dice: no te veo");
                    nuevaFantasma.caracter = fantasma;

                }
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

                    }
                    System.out.println("Total de vidas: " + vidas);

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
            //Celda anteriorFantasma=tablero.tablero[filaFantasma][colFantasma];
            // anteriorFantasma.caracter=null;
            // if(teVeoFila(nuevaFila)){
            // if(colFantasma<nuevaCol){

            // colFantasma+=2;
            // }else{
            // colFantasma-=2;
            // }
            // }
            // else if(teVeoCol(nuevaCol)){
            // if(filaFantasma<nuevaFila){
            // System.out.println("Te veo");
            // filaFantasma+=2;
            // } else
            // filaFantasma-=2;
            // }
            // else if((teVeoFila(nuevaFila))&&(teVeoCol(nuevaCol))){
            // perdiste=true;
            // }

            tablero.dibujarTablero();
            linea = in.nextLine();
            if (nueva.caracterCelda()=='Ə'){
                System.out.println("El fantasma te ha comido");
            }

        }
        if(ganaElJuego) {
            System.out.println();
        }
    }

    // public Posicion moverFantasma(int filaF, int colF){
    // boolean arriba=false;
    // boolean abajo=false;
    // boolean derecha=false;
    // boolean izquierda=false;
    // double valorRandom=Math.random();
    // if(valorRandom<0.25){
    // arriba=true;
    // filaF+=2;
    // }else if(valorRandom<=0.25&&valorRandom<0.5){
    // abajo=true;
    // filaF-=2;
    // }else if(valorRandom<=0.5&&valorRandom<0.75){
    // izquierda=true;
    // colF-=2;
    // }else if(valorRandom<=0.5&&valorRandom<0.75){
    // derecha=true;
    // colF-=2;
    // }
    // return new Posicion(filaF,colF);
    // }

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

    private boolean teVeoFila(int nuevaFila ){
        // Celda nueva = tablero.tablero[nuevaFila][null];  

        if  (fantasma.posicion.fila==nuevaFila){         
            return true ;

        }else {
            return false;
        }

    }

    private boolean teVeoCol(int nuevaCol){
        //Celda nueva = tablero.tablero[0][nuevaCol];   
        if  (fantasma.posicion.col==nuevaCol){           
            return true ;
        }else {
            return false;
        }
    }
}