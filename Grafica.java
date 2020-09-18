
import java.util.LinkedList;
//import org.graphstream.graph.*;
//import org.graphstream.graph.implementations.SingleGraph;

public class Grafica {
    LinkedList<Vertice> vertices; //Lista de vértices.
    boolean dirigida; //Verdadero si y solo si la gráfica es dirigida.

    LinkedList<Vertice> componente = new LinkedList<Vertice>(); //Generamos una lista de los vécinos para que al imprimir no se tengan que hacer malabares.
    int ordenFinal = 0;
 
    /**
     * Constructor de la clase gráfica.
     * @param dir: decide si la gráfica es dirigida o no.
     **/
    public Grafica(boolean dir){
        dirigida = dir;
        vertices = new LinkedList<Vertice>();
    }

    /**
    * Función que recibe un id y devuelve el vértice que tiene dicho id. Si no existe devuelve null.
    * @param ident: identificador del vértice.
    * @return vertice con ese id.
    */
    public Vertice getVerticeId(int ident){
        Vertice ret = null;
        for(Vertice v:vertices){
            if(ident == v.id){
                ret = v;
            }
        }
        return ret;
    }
 
    /**
    * Función que agrega una arista.
    * @param i id del vértice de origen de la arista.
    * @param j id del vértice de destino de la arista.
    */
    public void agregaArista(int i, int j){
        Vertice vert_i = getVerticeId(i);
        Vertice vert_j = getVerticeId(j);
        vert_i.agregaVecino(vert_j);
    
        if(!dirigida){
            vert_j.agregaVecino(vert_i);
        }
    }
  
    /**
    * Agrega un vértice a la gráfica
    * @param ident: identificador del vértice nuevo.
    */
    public void agregaVertice(int ident){
        Vertice nuevo = new Vertice(ident);
        vertices.add(nuevo);
    }

    /**
    * Obtiene el orden(número de vértices) de la gráfica.
    */
    public int getOrden(){
        return vertices.size();
    }

    @Override
    public String toString(){
        String salida = "";
        for(Vertice v:vertices){
            salida += v.toString()+"->"+v.vecinos.toString()+"\n";
        }
        return salida;
    }

    /**
    * Método que agrega un vértice a la lista componente.
    * @param vec el vértice a agregar en la lista componente.
    */    
    public void agrega(Vertice vec){
        if(!componente.contains(vec)){
            componente.add(vec);
        }
    }

    /**
    * Método dfs que mediante el algoritmo dfs recorre una gráfica.
    * @param g: la gráfica ha recorrer.
    * @param v: el vértice inicial-
    */
    public void dfs(Grafica g, Vertice v){
        v.visita();                         // Marcamos al vértice como visitado.
        agrega(v);                          // Agrega el vértice a nuestra lista.
        ordenFinal ++;                      // Sumamos uno a ordenFinal, para calcular si al final son los mismos que getOrden()

        for(Vertice u: v.vecinos){          
            if(!u.visitado()){
                agrega(u);
                dfs(g, u);
            }
        }
    }

    /**
    * Método esBosque que, si el orden final (dados los vértices de la componente en BFS) es igual a la cantidad de vértices de toda la gráfica.
    */
    public boolean esBosque(){
        return !(ordenFinal == getOrden());
    }

    /**
    * Método que imprime nuestra lista "componente" que son los vértices de la componente conexa.
    */
    public void imprime(){
        System.out.println(componente.toString());
        ordenFinal = 0;
        componente = new LinkedList<Vertice>(); //Una vez hemos impreso los elementos de la componente conexa, lo reseteamos para que sea la lista
    }                                           //de los vértices de la siguiente componente.
}