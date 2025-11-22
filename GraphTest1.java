import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GraphTest1 {
    
    private MapaAutores autores;
    private MapaPubli publicaciones;
    private Graph grafo;
    
    @Before
    public void setUp() {
        autores = new MapaAutores();
        publicaciones = new MapaPubli();
        grafo = new Graph();
        
        autores.cargarAutores("Datuak/autores.txt");
        publicaciones.cargarPublicaciones("Datuak/publicaciones.txt");
        publicaciones.cargarPublisAutor("Datuak/publisyautores.txt");
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
        assertTrue(grafo.estanConectados1("Albert Einstein", "Albert Einstein"));
        //2 autores conectados
        assertTrue(grafo.estanConectados1("Marie Curie","Rosalind Franklin"));
        //2 autores NO conectados
        assertFalse(grafo.estanConectados1("Albert Einstein", "Ada Lovelace"));
        //2 autores no existentes
        assertFalse(grafo.estanConectados1("Nikola Tesla", "Nikola Tesla"));
    }
    
    @Test
    public void testEstanConectados2() {
        grafo.crearGrafo(autores, publicaciones);
        //Mismo autor
        ArrayList<String> camino1 = grafo.estanConectados2("Albert Einstein", "Albert Einstein");
        assertEquals(1, camino1.size());
        assertEquals("Albert Einstein", camino1.get(0));
        //2 autores conectados
        ArrayList<String> camino2 = grafo.estanConectados2("Albert Einstein", "Marie Curie");
        assertEquals(3, camino2.size());
        assertEquals("Marie Curie", camino2.get(0));
        assertEquals("Isaac Newton", camino2.get(1));
        assertEquals("Albert Einstein", camino2.get(2));
        //2 autores NO conectados
        ArrayList<String> camino3 = grafo.estanConectados2("Albert Einstein", "Ada Lovelace");
        assertTrue(camino3.isEmpty());
        //2 auotres no existentes
        ArrayList<String> camino4 = grafo.estanConectados2("Nikola Tesla", "Nikola Tesla");
        assertTrue(camino4.isEmpty());
    }
}