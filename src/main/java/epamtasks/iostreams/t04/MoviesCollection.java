package epamtasks.iostreams.t04;



import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static epamtasks.iostreams.t04.Serializator.deserialiseObject;
import static epamtasks.iostreams.t04.Serializator.serialiseObject;

public class MoviesCollection implements Serializable {

    private HashSet<Movie> collection;
    private String collectionName;

    public MoviesCollection(){
        collection = new HashSet<>();
        collectionName = "SIMPLE_MOVIEs_COLLECTION";
    }

    public MoviesCollection addMovie(Movie movie){
       collection.add(movie);
       return this;
    }

    public MoviesCollection setCollectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    public static MoviesCollection getCollectionFromFile(String fileName){
        List<Object> objects = new ArrayList<>();
        if(deserialiseObject(fileName,objects)) {
            return (MoviesCollection) objects.get(0);
        }else return new MoviesCollection().setCollectionName("EMPTY");

    }

    public boolean saveCollectionToFile(String fileName){

        return serialiseObject(fileName,this);
    }


    public StringBuilder getMovies(){
        StringBuilder sb = new StringBuilder();
        for (Movie mv: collection
             ) {
            sb.append(mv.toString()).append("\n");
        }
        return sb;
    }
    public Set<Movie> getCollection() {
        return collection;
    }
    public String getCollectionName() {
        return collectionName;
    }
    public Movie getMovieByTitle(String title){
        for (Movie mv:collection) {
           if( mv.getTitle().equalsIgnoreCase(title)) return mv;
        }
        return new Movie("NOT_FOUND",0);
    }


    public boolean isPresentMovie(String title) {
        for (Movie m: collection)  {
            if(m.getTitle().equalsIgnoreCase(title)) return true;
        }
        return false;
    }
}
