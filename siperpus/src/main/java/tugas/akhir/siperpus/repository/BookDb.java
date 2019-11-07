package tugas.akhir.siperpus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugas.akhir.siperpus.model.BookModel;

@Repository
public interface BookDb extends JpaRepository<BookModel, Long>{
    
}