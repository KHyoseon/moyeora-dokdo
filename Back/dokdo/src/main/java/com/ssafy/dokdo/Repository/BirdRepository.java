package com.ssafy.dokdo.Repository;

import com.ssafy.dokdo.Entity.Bird;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BirdRepository extends MongoRepository<Bird,String> {
    Optional<Bird> findBirdByName(String name);
}
