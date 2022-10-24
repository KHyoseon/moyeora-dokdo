package com.ssafy.dokdo.Repository;


import com.ssafy.dokdo.Entity.Terrain;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TerrainRepository extends MongoRepository<Terrain,String> {
    Optional<Terrain> findTerrainByName(String name);
}
