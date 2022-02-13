package service;


import model.List;

public interface ListService {

    void create (List product);
    java.util.List<List> readAll();
    List read (long id);
    void delete(long id);
    boolean addToList(Long productId, Long listId);
}
