//Clase vértice

import java.util.LinkedList;

public class Vertice{
  LinkedList<Vertice> vecinos; //Lista de vecinos
  Vertice p = null; //Padre del vértice
  int id; //Identificador.
  String nombre; //Nombre del vértice.
  boolean revisado = false; //bandera que indica si el vértice ha sido revisado.

  
  /**
   * Constructor de la clase vértice.
   * @param ident: identificador del vértice, el cual es un número.
   */
  public Vertice(int ident){
    id = ident;
    vecinos = new LinkedList<Vertice>();
    nombre = "v"+ident;
  }


  /**
   * Método que nos dice si el vértice tiene vécinos.
   * @return true si tiene vécinos, false en otro caso.
   **/
  public boolean tieneVecinos(){
    if(vecinos.size() == 0)
      return true;
    return false;
  }

  /**
   * Método que marca como visitado un vértice.
   **/
  public void visita(){
    revisado = true;
  }
  /**
   * Método que nos dice si el vértice ha sido visitado.
   * @return la etiqueta del vértice.
   **/
  public boolean visitado(){
    return revisado;
  }

  //Comprueba si 2 vértices son iguales.
  @Override
  public boolean equals(Object o){
    boolean ret = false;
    if(o instanceof Vertice){
      Vertice comp = (Vertice)o;
      if(comp.id == this.id){
        ret = true;
      }
    }
    return ret;
  }
  
  /**
   * Agrega un vecino a este vértice
   * @param vec: nuevo vecino.
   */
  public void agregaVecino(Vertice vec){
    if(!vecinos.contains(vec)){
      vecinos.add(vec);
    }
  }
  
  @Override
  public String toString(){
    return nombre;
  }
}
