package fdu.ddj.backend.repository;
import fdu.ddj.backend.domain.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    List<Keyword> findByKey(String key);
}
