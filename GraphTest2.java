import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphTest2 {
    private MapaAutores autores;
    private MapaPubli publicaciones;
    private Graph grafo;

	@Before
	public void setUp() throws Exception {
		autores = new MapaAutores();
        publicaciones = new MapaPubli();
        grafo = new Graph();
        
        autores.cargarAutores("Datuak/authors-name-all.txt");
        publicaciones.cargarPublicaciones("Datuak/publications-titles-all.txt");
        publicaciones.cargarPublisAutor("Datuak/publications-authors-all-final.txt");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrearGrafo() {
		grafo.crearGrafo(autores, publicaciones);
		assertNotNull(grafo);
	}

	@Test
	public void testEstanConectados1() {
		 grafo.crearGrafo(autores, publicaciones);
		 //Mismo autor
		 String autor1 = grafo.keys[0];
         assertTrue(grafo.estanConectados1(autor1, autor1));
         //2 autores conectados
		 String autor2 = grafo.keys[0];
         int pos3 = grafo.adjList[0].get(0);
         String autor3 = grafo.keys[pos3];
         assertTrue(grafo.estanConectados1(autor2, autor3));
         assertTrue(grafo.estanConectados1("Nick C. Ryder", "Jaime Modiano"));
         //2 autores no conectados
         assertFalse(grafo.estanConectados1("Dominik Bettenworth", "Si Xie"));
         //2 autores no existentes
         assertFalse(grafo.estanConectados1("abcd", "dcba"));
	}

	@Test
	public void testEstanConectados2() {
		grafo.crearGrafo(autores, publicaciones);
		//Mismo autor
		String autor = grafo.keys[0];
        ArrayList<String> camino = grafo.estanConectados2(autor, autor);
        assertEquals(1, camino.size());
        assertEquals(autor, camino.get(0));
        //2 autores conectados
        String autor1 = grafo.keys[0];
        int pos = grafo.adjList[0].get(0);
        String autor2 = grafo.keys[pos];
        ArrayList<String> camino1 = grafo.estanConectados2(autor1, autor2);
        for (int i=0; i<camino1.size()-1; i++) {
            String actual = camino1.get(i);
            String siguiente = camino1.get(i + 1);
            int posActual = grafo.th.get(actual);
            int posSiguiente = grafo.th.get(siguiente);
            assertTrue(grafo.adjList[posActual].contains(posSiguiente));
        }
        //2 autores no conectados
        ArrayList<String> camino3 = grafo.estanConectados2("Dominik Bettenworth", "Si Xie");
        assertTrue(camino3.isEmpty());
        //2 autores no existentes
        ArrayList<String> camino4 = grafo.estanConectados2("No existe", "No existe");
        assertTrue(camino4.isEmpty());
	
	}

}
