package epamtasks.iostreams.t04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SerializatorTest {

    @Test
    void serialiseObjectTest() {
        MoviesCollection mc = new MoviesCollection();
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
        String serFile = "movies.ser";
        assertTrue(Serializator.serialiseObject(serFile,mc));

        List<Object> deser = new ArrayList<>();

        assertTrue(Serializator.deserialiseObject(serFile,deser));
        assertTrue(!deser.isEmpty());
        MoviesCollection mc2 = (MoviesCollection)deser.get(0);
        System.out.println(mc2.getMovies());
    }
}