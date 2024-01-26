/*
 * Copyright (c) 2024 Karlsruhe Institute of Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.kit.datamanager.neo4jdemo.dao;

import edu.kit.datamanager.neo4jdemo.model.MovieEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies"
//        , excerptProjection = MovieRepository.MinimalMovieView.class
)
public interface MovieRepository extends Neo4jRepository<MovieEntity, Long>, CrudRepository<MovieEntity, Long> {
    MovieEntity findOneByTitle(String title);

    @Query("MATCH (m:Movie)<-[a:ACTED_IN]-(p:Person) WHERE p.name = $name MATCH (m:Movie)<-[d:DIRECTED]-(p2:Person) RETURN m")
    List<MovieEntity> findAllByActorName(String name);

    @Query("MATCH p=shortestPath(\n"
            + "(bacon:Person {name: $person1})-[*]-(meg:Person {name: $person2}))\n"
            + "WITH p, [n IN nodes(p) WHERE n:Movie] AS x\n"
            + "UNWIND x AS m\n"
            + "MATCH (m) <-[r:DIRECTED]-(d:Person)\n"
            + "RETURN p, collect(r), collect(d)"
    )
    List<MovieEntity> findAllOnShortestPathBetween(@Param("person1") String person1, @Param("person2") String person2);

    @Query("MATCH p=allShortestPaths(\n"
            + "(p1:Person {name: $person1})-[*]-(p2:Person {name: $person2}))\n"
            + "WITH p, [n IN nodes(p) WHERE n:Movie] AS x\n"
            + "UNWIND x AS m\n"
            + "MATCH (m) <-[r:DIRECTED]-(d:Person)\n"
            + "RETURN p, collect(r), collect(d)"
    )
    List<MovieEntity> findAllOnAllShortestPathBetween(@Param("person1") String person1, @Param("person2") String person2);

//    @Projection(name = "fullMovieView", types = {MovieEntity.class})
//    interface MovieView extends MinimalMovieView {
//        List<Roles> getActors();
//
//        List<PersonEntity> getDirectors();
//
//        List<PersonEntity> getProducers();
//
//        List<PersonEntity> getWriters();
//
//        List<Reviews> getReviewers();
//    }
//
//    @Projection(name = "minimalMovieView", types = {MovieEntity.class})
//    interface MinimalMovieView {
//        String getTitle();
//
//        String getTagline();
//    }
}
