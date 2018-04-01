package epamtasks.iostreams.t04;

import org.junit.jupiter.api.Test;

import java.io.File;

import static epamtasks.iostreams.t04.MoviesCollection.getCollectionFromFile;
import static org.junit.jupiter.api.Assertions.*;

class MoviesCollectionTest {

    @Test
    void getCollectionFromFileTest() {
        MoviesCollection mc = new MoviesCollection().setCollectionName("test");
        Movie m1 = new Movie("Thing",1983)
                .addActor("Kurt Rassel",32)
                .addActor("Wilford Brimley",35)
                .addActor("Richard Dysart",46);
        Movie m2 = new Movie("Inception",2010)
                .addActor("Leonardo DiCaprio",30)
                .addActor("Tom Hardy",33)
                .addActor("Ellen Page",24);

        mc.addMovie(m1)
                .addMovie(m2);
        String serFile = "movies1.ser";
        if(! new File(serFile).exists()) {
            assertTrue(mc.saveCollectionToFile(serFile));
            System.out.println("create new file");
        }
        MoviesCollection mc1 = getCollectionFromFile(serFile);
        System.out.println(mc1.getCollectionName());
        System.out.println( mc1.getMovies());
        assertTrue(mc.isPresentMovie("Inception"));

    }

    @Test
    void getMovieByTitleTest(){
        MoviesCollection mc = new MoviesCollection().setCollectionName("test");
        Movie m1 = new Movie("Thing",1983)
                .addActor("Kurt Rassel",32)
                .addActor("Wilford Brimley",35)
                .addActor("Richard Dysart",46);
        mc.addMovie(m1);
        String serFile = "movies1.ser";
        if(! new File(serFile).exists()) {
            assertTrue(mc.saveCollectionToFile(serFile));
            System.out.println("create new file");
        }
        MoviesCollection mc1 = getCollectionFromFile(serFile);
        Movie myMovie = mc1.getMovieByTitle("Thing");
        assertEquals(m1,myMovie);
    }

}