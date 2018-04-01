package epamtasks.iostreams.t04;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Movie implements Serializable {
    private Set<Actor> actors;
    private final String title;
    private final int relize;

    public Movie(String title,int relize){
        this.title = title;
        this.relize = relize;
        actors = new HashSet<>();
    }

    public Movie addActor(String name,int age){
        actors.add(new Actor(name,age));
        return this;
    }

    public Set<Actor> getActors() {
        return actors;
    }
    public String getTitle() {
        return title;
    }
    public int getRelize() {
        return relize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return relize == movie.relize &&
                Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, relize);
    }

    @Override
    public String toString(){
        StringBuilder text = new StringBuilder()
                .append(String.format("%s realize at %d%n",title,relize));
        for (Actor ac:actors){
         text.append(String.format("%s  %d years old%n",ac.getFullName(),ac.getAge()));

        }
        return text.toString();
    }
}
