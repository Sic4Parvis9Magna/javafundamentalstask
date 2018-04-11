package epamtasks.jdbc.t02;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String massage,Exception ex){
        super(massage,ex);
    }
}
