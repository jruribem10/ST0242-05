
/**
 * Esta clase es para crear la arepa
 * 
 * @author Juan Esteban Amaya 
 * @version 26/10/2020
 */
public class Galleta
{
    private int pos_x;
    private int pos_y;
    private boolean galletin; //true exlosivo
     /**

     * Constructor para la galleta

     * @param posx El parámetro posx define el número de la poscion en cuanto a la columna.
     * @param posy El parametro posy define el numero de la posicion en cuanto a la fila.
     * @param cond El parametro cond define un numero que caracteriza la galleta.

     */
   public Galleta(int posx , int posy , int cond){
    this.pos_x = posx;
    this.pos_y = posy;
    if(cond == 0){
    galletin = false;
    }else {
    galletin = true ;
    }
    
    }
     /**
     * Metodo para regresar la posicion en x.
     * @return Regresa el numero de x.
     */
    public int getPos_x(){
    return this.pos_x;
    }
     /**
     * Metodo para regresar la posicion de y.
     * @return Regresa la posicion de y
     */
    public int getPos_y(){
    return this.pos_y;
    }
     /**
     * Metodo para regresar el tipo de galleta 
     * @return Regresa el numero del tipo de galleta
     */
    public boolean getGalletin(){
    return this.galletin;
    }
    
    
}
