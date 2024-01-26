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

package edu.kit.datamanager.neo4jdemo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.List;

@Node("Movie")
@Getter
public class MovieEntity {
    @Property("title")
    private final String title;
    @Id
    @GeneratedValue
    private Long id;
    @Property("tagline")
    @Setter
    private String tagline;

    @Property("released")
    @Setter
    private Integer released;

    @Relationship(type = "ACTED_IN", direction = Direction.INCOMING)
    @Setter
    private List<Roles> actors;

    @Relationship(type = "DIRECTED", direction = Direction.INCOMING)
    @Setter
    private List<PersonEntity> directors;

    @Relationship(type = "PRODUCED", direction = Direction.INCOMING)
    @Setter
    private List<PersonEntity> producers;

    @Relationship(type = "WROTE", direction = Direction.INCOMING)
    @Setter
    private List<PersonEntity> writers;

    @Relationship(type = "REVIEWED", direction = Direction.INCOMING)
    @Setter
    private List<Reviews> reviewers;

    public MovieEntity(String title) {
        this.title = title;
    }
}
