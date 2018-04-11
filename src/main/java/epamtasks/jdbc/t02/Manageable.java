package epamtasks.jdbc.t02;

import java.util.List;

public interface Manageable<T> {
    List<T> findAll(String sql);
    T findById(int id);
    void insert(T entity);
    void update(String sql);
    void batchSQL(String... sqls);
    void deleteById(int id);

}
