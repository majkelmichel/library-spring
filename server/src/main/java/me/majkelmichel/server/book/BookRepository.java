package me.majkelmichel.server.book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

    @Override
    <S extends Book> S save(S entity);

    @Override
    Optional<Book> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}