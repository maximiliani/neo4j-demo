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

import edu.kit.datamanager.neo4jdemo.model.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons"
//        , excerptProjection = PersonRepository.MinimalPersonView.class
)
public interface PersonRepository extends Neo4jRepository<PersonEntity, Long>, CrudRepository<PersonEntity, Long> {
    PersonEntity findOneByName(String name);

    @Query("MATCH (p:Person)-[a:ACTED_IN]->(m:Movie) where any(role IN a.roles WHERE role CONTAINS $name) RETURN p,a,m\n")
    List<PersonEntity> findByRoleName(String name);

    void deleteByName(String name);

    @Query("match p=shortestPath((p1:Person{name:$name1})-[*]-(p2:Person{name:$name2})) return p")
    List<PersonEntity> findSingleConnectionBetweenPersons(@Param("name1") String name1, @Param("name2") String name2);

    @Query("match p=allShortestPaths((p1:Person{name:$name1})-[*]-(p2:Person{name:$name2})) return p")
    List<PersonEntity> findAllConnectionsBetweenPersons(@Param("name1") String name1, @Param("name2") String name2);

//    @Projection(name = "fullPersonView", types = {PersonEntity.class})
//    interface PersonView extends MinimalPersonView {
//        List<PersonEntity> getFollowedBy();
//
//        List<PersonEntity> getFollows();
//
//        List<Roles> getActedIn();
//
//        List<MovieEntity> getDirected();
//
//        List<MovieEntity> getProduced();
//
//        List<MovieEntity> getWrote();
//
//        List<Reviews> getReviewed();
//    }

//    @Projection(name = "minimalPersonView", types = {PersonEntity.class})
//    interface MinimalPersonView {
//        String getName();
//
//        Integer getBorn();
//    }
}