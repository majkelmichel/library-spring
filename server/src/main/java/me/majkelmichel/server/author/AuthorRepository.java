package me.majkelmichel.server.author;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Override
    Optional<Author> findById(Long aLong);

    @Override
    List<Author> findAll();
}