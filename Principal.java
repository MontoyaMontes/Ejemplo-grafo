import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class Principal{
    public static boolean esBosque; //Variable que nos dice si una gráfica es un bosque.
    /**
    * Método que recibe un objeto tipo Grafica y la dibuja.
    * @param g: gráfica que recibe.
    */
    public void dibujaGrafica(Grafica g){
        Graph graph = new SingleGraph("Ventana"); //Se crea una gráfica de graphstream
        
        //En esta parte se agregan todos los vértices a "graph".
        for(Vertice v : g.vertices){
            graph.addNode(v.nombre);
            graph.getNode(v.nombre).addAttribute("ui.size", 10);
            if(esBosque) //Revisamos si nuestra gráfica es un bosque, en caso de serlo, pintamos sus vértices de rojo.
                graph.getNode(v.nombre).addAttribute("ui.style", "fill-color: rgb(255,000,00);");
            graph.getNode(v.nombre).addAttribute("ui.label", v.nombre); //Se agrega una etiqueta con el nombre del vértice.

            
        }

        //En esta parte se agregan las aristas.
        if(g.dirigida){ //Si es dirigida
            for(Vertice v : g.vertices){
                for(Vertice u : v.vecinos){
                    String nombreArista = v.nombre+","+u.nombre;
                    graph.addEdge(nombreArista, v.nombre, u.nombre,true);
                }
            }
        }else{ //Si no es dirigida
            for(Vertice v : g.vertices){
                for(Vertice u : v.vecinos){
                    String nombreArista = v.nombre+","+u.nombre;
                    String nombreArista2 = u.nombre+","+v.nombre;
                    if(graph.getEdge(nombreArista2) == null){ //Si no se ha agregado la arista.
                        graph.addEdge(nombreArista, v.nombre, u.nombre);
                    }
                }
            }
        }
        graph.display(); //Se pone la gráfica en pantalla.
    }

    public static void main(String[] args){
        Lector l = new Lector();
        l.lee(args[0]); //Se lee el archivo.
        Grafica g1 = l.creaGrafica(); //Se crea una gráfica con el archivo leído.
        Principal pr = new Principal();

        /**
        * En esta sección deben ejecutar su algoritmo para calcular las componentes conexas.
        * Después deben imprimir cada componente separada por un salto de línea.
        */
 
        int orden = g1.getOrden();//hacemos esto por si comienza en un vértice con etiqueta mayor a 0, modificar el índice.

        for(int i = 0; i < orden ; i++){
            if(g1.getVerticeId(i) == null){                                         //Revisamos si el vértice vi existe, si no, sumamos 1 a i y al orden.
                i++;
                orden++;
            }

            if(!g1.getVerticeId(i).visitado() && g1.getVerticeId(i).tieneVecinos()){//Vemos si el vértice no visitado tiene vecinos, si no los tiene
                g1.getVerticeId(i).visita();                                        //Lo marcamos como visitadoe imprimimos [vi] ya que es una componente sola.
                if(orden > 1)
                    esBosque = true;
                System.out.println("[" + g1.getVerticeId(i) + "]");
            }

            if(!g1.getVerticeId(i).visitado()){                                     //Aplicamos dfs sobre todos los índices.
                for (Vertice v : g1.getVerticeId(i).vecinos) {
                    if(!v.visitado())
                        g1.dfs(g1, v);
                }
                if(g1.esBosque())
                    esBosque = true;
                g1.imprime();                                                       //Se imprime la componente.
            }
        }

        pr.dibujaGrafica(g1);                                                       //Se dibuja la gráfica en pantalla
    }
}




