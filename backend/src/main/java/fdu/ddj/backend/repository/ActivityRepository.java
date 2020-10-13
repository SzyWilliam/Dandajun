package fdu.ddj.backend.repository;

import fdu.ddj.backend.domain.Activity;
import fdu.ddj.backend.domain.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

}
