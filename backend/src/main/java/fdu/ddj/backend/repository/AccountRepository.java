package fdu.ddj.backend.repository;

import fdu.ddj.backend.domain.Account;
import fdu.ddj.backend.domain.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
