package com.harghar.library.bookcatalogservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harghar.library.bookcatalogservice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    List<Book> findAllByOwnerId(UUID ownerId);
}
