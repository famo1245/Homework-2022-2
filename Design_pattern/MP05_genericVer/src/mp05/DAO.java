package mp05;

import java.util.List;

public interface DAO<P, K> {
    public void insert(P p);
    public List<P> findAll();
    public P findByKey(K key);
    public void update(P p);
    public void delete(K key);
    //public void delete(PasswordInfo p);
}
