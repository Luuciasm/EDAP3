
public class PPal {
	
	public static void main(String[] args) {
		MapaAutores ma = new MapaAutores();
		MapaPubli mp = new MapaPubli();
		Graph grafo = new Graph();
		mp.cargarPublicaciones("Datuak/publications-titles-all.txt");
        mp.cargarPublisAutor("Datuak/publications-authors-all-final.txt");
        ma.cargarAutores("Datuak/authors-name-all.txt");
        //Tiempo de crearGrafo
        //long milisInicio = System.currentTimeMillis();
        //grafo.crearGrafo(ma, mp);
        //long milisFin = System.currentTimeMillis();
		//System.out.println("Tiempo de ejecución: "+(milisFin-milisInicio)+" milisegundos");
		
		//Tiempo de estanConectados1
        grafo.crearGrafo(ma, mp);
        long milisInicio = System.currentTimeMillis();
        grafo.estanConectados1("Nick C. Ryder", "Jaime Modiano");
        //grafo.estanConectados1("Encarnación Capilla", "Mónica Diaz");
        long milisFin = System.currentTimeMillis();
		System.out.println("Tiempo de ejecución: "+(milisFin-milisInicio)+" milisegundos");
		
		//Tiempo.estanConectados2
		grafo.crearGrafo(ma, mp);
        //long milisInicio = System.currentTimeMillis();
        //grafo.estanConectados1("David H. Fremlin", "Saharon Shelah");
        //grafo.estanConectados2("Nick C. Ryder", "Jaime Modiano");
        //long milisFin = System.currentTimeMillis();
		//System.out.println("Tiempo de ejecución: "+(milisFin-milisInicio)+" milisegundos");
		//NO CONECTADOS:
		//-> ID: Q39051034, Nombre: Dominik Bettenworth
		//-> ID: Q59668849, Nombre: Si Xie
		
		//CONECTADOS:
		//-> ID: Q113845717, Nombre: Nick C. Ryder
		//-> ID: Q42696815, Nombre: Jaime Modiano 
	}
}

