import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
	
      HashMap<String, Integer> th;
      String[] keys;
      ArrayList<Integer>[] adjList;
	
      
      public void crearGrafo(MapaAutores autores, MapaPubli publicaciones) {
	  	// Post: crea el grafo desde la lista de autores
		//       Los nodos son nombres de autores
    	  
   	    // Paso 1: llenar th�
   	    th = new HashMap<String, Integer>();
   	    int cont = 0;
   	    for (Autor a : autores.getMapaAutores().values()) {
   	    	if (!th.containsKey(a.getNombre())) {
   	    		th.put(a.getNombre(), cont);
   	    		cont++;
        	}
	    }

   	    // Paso 2: llenar keys�
   	    keys = new String[th.size()];
   	    for (String nombreAutor : th.keySet()) {
   	    	keys[th.get(nombreAutor)] = nombreAutor;
    	}

   	    // Paso 3: llenar adjList�
   	    adjList = new ArrayList[th.size()];
   	    for (int i = 0; i < adjList.length; i++) {
   	    	adjList[i] = new ArrayList<Integer>();
    	}

	    // Recorremos cada publicación y conectamos a todos sus autores entre sí
	    HashMap<String, ArrayList<String>> mapaPublisAutor = publicaciones.getMapaPublisAutor();
	    for (String idPubli : mapaPublisAutor.keySet()) {
	        ArrayList<String> autoresPubli = mapaPublisAutor.get(idPubli);
	        for (int i = 0; i < autoresPubli.size(); i++) {
	            Autor autor1 = autores.buscarAutor(autoresPubli.get(i));  
	            if (autor1 != null && th.containsKey(autor1.getNombre())) {
	                int pos1 = th.get(autor1.getNombre());
	                for (int j = i + 1; j < autoresPubli.size(); j++) {
	                    Autor autor2 = autores.buscarAutor(autoresPubli.get(j));
	                    if (autor2 != null && th.containsKey(autor2.getNombre())) {
	                        int pos2 = th.get(autor2.getNombre());
	                        if (!adjList[pos1].contains(pos2)) {
	                            adjList[pos1].add(pos2);
	                        }
	                        if (!adjList[pos2].contains(pos1)) {
	                            adjList[pos2].add(pos1);
	                        }
	                    }
	                }
	            }
	        }
	    }
	}



	
	public void print(){
	   for (int i = 0; i < adjList.length; i++){
		System.out.print("Element: " + i + " " + keys[i] + " --> ");
		for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
		
		System.out.println();
	   }
	}
	
	public boolean estanConectados1(String a1, String a2){
	    if(!th.containsKey(a1) || !th.containsKey(a2)) {
	        return false;
	    }
	    if(a1.equals(a2)) {
	        return true;
	    }  
	    Queue<Integer> porExaminar = new LinkedList<Integer>();
	    int pos1 = th.get(a1);
	    int pos2 = th.get(a2);
	    boolean[] examinados = new boolean[th.size()];
	    porExaminar.add(pos1);
	    examinados[pos1] = true;
	    while(!porExaminar.isEmpty()) {
	        int actual = porExaminar.remove();
	        if(actual == pos2) {
	            return true;
	        }
	        for(int vecino : adjList[actual]) {
	            if(!examinados[vecino]) {
	                porExaminar.add(vecino);
	                examinados[vecino] = true;
	            }
	        }
	    }
	    return false;
	}

	public ArrayList<String> estanConectados2(String a1, String a2){
		ArrayList<String> rdo = new ArrayList<String>();
		if(!th.containsKey(a1) || !th.containsKey(a2)) {
			return rdo;
		}
		if(a1.equals(a2)) {
			rdo.add(a1);
			return rdo;
		}
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put(a1, a2);
		Queue<Integer> porExaminar = new LinkedList<Integer>();
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];
		porExaminar.add(pos1);
		while(!enc && !porExaminar.isEmpty()) {
			int actual = porExaminar.remove();
			if(actual == pos2) {
				enc = true;
			}else {
				examinados[actual] = true;
				for (int j: adjList[actual]) {
					if(!examinados[j]) {
						porExaminar.add(j);
						mapa.put(keys[j], keys[actual]);
					}
				}
			}
		}
		if(enc) {
			rdo.add(a2);
			String nom1 = "";
			String nom2 = a2;
			while(!nom1.equals(a1)) {
				nom1 = mapa.get(nom2);
				rdo.add(nom1);
				nom2 = nom1;
			}
		}
		return rdo;

	}

}








