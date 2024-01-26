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

package edu.kit.datamanager.neo4jdemo.graphql;

import edu.kit.datamanager.neo4jdemo.dao.MovieRepository;
import edu.kit.datamanager.neo4jdemo.model.MovieEntity;
import edu.kit.datamanager.neo4jdemo.model.PersonEntity;
import edu.kit.datamanager.neo4jdemo.model.Reviews;
import edu.kit.datamanager.neo4jdemo.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @QueryMapping
    public MovieEntity movieById(@Argument Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public MovieEntity movieByTitle(@Argument String title) {
        return movieRepository.findOneByTitle(title).orElse(null);
    }

    @QueryMapping
    public Iterable<MovieEntity> allMovies() {
        return movieRepository.findAll();
    }

    @QueryMapping
    public Iterable<MovieEntity> moviesByYear(@Argument Integer year) {
        return movieRepository.findAllByReleased(year);
    }

    @QueryMapping
    public Iterable<MovieEntity> moviesByActor(@Argument String name) {
        return movieRepository.findAllByActorName(name);
    }

    @SchemaMapping
    public Iterable<Roles> actors(MovieEntity movie) {
        return movie.getActors();
    }

    @SchemaMapping
    public PersonEntity actor(Roles roles) {
        return roles.getPerson();
    }

    @SchemaMapping
    public Iterable<PersonEntity> director(MovieEntity movie) {
        return movie.getDirectors();
    }

    @SchemaMapping
    public Iterable<PersonEntity> producer(MovieEntity movie) {
        return movie.getProducers();
    }

    @SchemaMapping
    public Iterable<PersonEntity> writer(MovieEntity movie) {
        return movie.getWriters();
    }

    @SchemaMapping
    public Iterable<Reviews> reviewer(MovieEntity movie) {
        return movie.getReviewers();
    }

    @SchemaMapping
    public PersonEntity reviewer(Reviews reviews) {
        return reviews.getPerson();
    }
}
