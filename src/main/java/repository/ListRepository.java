package repository;

import model.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends CrudRepository<List, Long> {
    List getById(Long id);
    void deleteById(Long id);
}
