package epamtasks.iostreams.t04;


import java.io.Serializable;
import java.util.Objects;


public class Actor implements Serializable {
    private final String fullName;
    private final int age;

    Actor(String fullName,int age){
        this.fullName = fullName;
        this.age = age;

    }

    public String getFullName() {
        return fullName;
    }
    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return age == actor.age &&
                Objects.equals(fullName, actor.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fullName, age);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }
}
