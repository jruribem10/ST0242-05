

/**
* En esta clase se mantiene la información del tablero
* El tablero es una matriz (arreglo de dos dimensiones) de Celdas
* Es necesario tener una referencia a juego para poder acceder 
* a la información del pacman
* @author User
*/
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class Tablero {

Juego juego;
Celda[][] tablero;
int numFilas;
int numCols;

int cantidadG = 15; 
public Galleta[] gll = new Galleta[cantidadG];

String[] archivo = {
    "15 17",
    "*****************",
    "*               *",
    "* ****** ****** *",
    "* *    * *    * *",
    "*               *",
    "* *    * *    * *",
    "* ****** ****** *",
    "*               *",
    "* ****** ****** *",
    "* *    * *    * *",
    "*               *",
    "* *    * *    * *",
    "* ****** ****** *",
    "*               *",
    "*****************",
    "P 1 1",
    "O 13 15"
};

/**
 * Constructor
 * Se recibe la referencia al juego, para poder acceder al pacman
 * Se lee el "archivo" con la información del laberinto, la posición
 * del pacman y la salida
 * @param juego 
 */
public Tablero(Juego juego) {
    this.juego = juego;
    leerArchivo();
    generarGalleta ();
 
}

/**
 * En este método se lee el laberinto.
 * Cuando aprendamos archivos, se leerá la información de un archivo
 * texto.
 */
private void leerArchivo() {
    int i = 0;
    String linea = archivo[i];
    i++;
    Scanner lineScan = new Scanner(linea);
    // Leer el tamaño del tablero en filas y columnas
    numFilas = lineScan.nextInt();
    numCols = lineScan.nextInt();
    // Definir el tamaño del tablero
    tablero = new Celda[numFilas][numCols];
    // Leer cada una de las filas que conforman el laberinto
    for (int fila = 0; fila < numFilas; fila++) {
        linea = archivo[i];
        i++;
        for (int col = 0; col < numCols; col++) {
            char c = linea.charAt(col);
            // esMuro, esSalida, tienearepita, caracter
            if (c == '*') {
                tablero[fila][col] = new Celda(true, false, false, null);
            }
            if (c == ' ') {
                tablero[fila][col] = new Celda(false, false, false, null);
            }
        }
    }
    // Leer la información adicional. Esto es:
    // (i) La posición del Pacman (empieza por P)
    // (ii) La posición de la salida (empieza por O)
    // En una versión futura se podrían leer las posiciones de los
    // fantasmas.
    while (i < archivo.length) {
        linea = archivo[i];
        i++;
        lineScan = new Scanner(linea.substring(1));
        if (linea.charAt(0) == 'P') {
            // La información del Pacman
            int fila = lineScan.nextInt();
            int col = lineScan.nextInt();
            Posicion posicion = new Posicion(fila, col);
            juego.pacman = new Pacman(Caracter.PACMAN, posicion, '^', Juego.PUNTOS_VIDA_INICIALES);
            tablero[posicion.fila][posicion.col].caracter = juego.pacman;
        } else if (linea.charAt(0) == 'O') {
            // La información de la salida del laberinto
            int fila = lineScan.nextInt();
            int col = lineScan.nextInt();
            tablero[fila][col].esSalida = true;
        }
    }

}

/**
 * En este método se dibuja el tablero.
 * A cada celda se le invoca el métoco "caracterCelda", que devuelve
 * un caracter que representa el contenido de la celda.
 */
public void dibujarTablero() {
    String s = "";
    for (int fila = 0; fila < numFilas; fila++) {
        for (int col = 0; col < numCols; col++) {
            s += tablero[fila][col].caracterCelda();
        }
        s += "\n";
    }
    System.out.println(s);
}
/**
 * En este método se generan las galletas
 * aleatoriamente
 
   */
public void generarGalleta (){

   
    cantidadG=0;
    boolean allG = true; 
    while(allG){
        int x = getRandom(0, numFilas-1);
        int y = getRandom(0, numCols-1);   
        int z = getRandom(0, 1);
        
            if(x<15 && y <15){
                Celda temp = tablero[x][y];
                if('*' ==temp.caracterCelda()|| 'O' ==temp.caracterCelda()||'^' ==temp.caracterCelda())  {
                //
                }  else{
                
                gll[cantidadG] = new Galleta(x,y,z);
               
                tablero[x][y] = new Celda(false,false,true,null);
                cantidadG++; 
                }
            
                if(cantidadG>=15){
                    allG = false;
                }  
            }
        }

}

     
    
    

    
 public static int getRandom(int min, int max) {
    if (min > max) {
        throw new IllegalArgumentException("Min " + min + " greater than max " + max);
    }      
    return (int) ( (long) min + Math.random() * ((long)max - min + 1));
}   
    
}
