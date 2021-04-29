package fr.univrouen.cv21rest.repositories;

//import fr.univrouen.cv21rest.model.CVCollection;
import fr.univrouen.cv21rest.model.CV;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CVRepository extends MongoRepository<CV, Long> {
    List<CV> findAll();
}