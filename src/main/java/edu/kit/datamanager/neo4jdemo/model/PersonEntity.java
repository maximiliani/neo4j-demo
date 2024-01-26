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
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Person")
@Getter
public class PersonEntity {
    @Property("name")
    private final String name;

    @Property("born")
    @Setter
    private Integer born;

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.INCOMING)
    @Setter
    private List<PersonEntity> followedBy;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    @Setter
    private List<PersonEntity> follows;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.OUTGOING)
    @Setter
    private List<Roles.RolesReverse> actedIn;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.OUTGOING)
    @Setter
    private List<MovieEntity> directed;

    @Relationship(type = "PRODUCED", direction = Relationship.Direction.OUTGOING)
    @Setter
    private List<MovieEntity> produced;

    @Relationship(type = "WROTE", direction = Relationship.Direction.OUTGOING)
    @Setter
    private List<MovieEntity> wrote;

    @Relationship(type = "REVIEWED", direction = Relationship.Direction.OUTGOING)
    @Setter
    private List<Reviews.ReviewsReverse> reviewed;

    @PersistenceCreator
    public PersonEntity(String name) {
        this.name = name;
    }

    public PersonEntity(String name, Integer born) {
        this.name = name;
        this.born = born;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                ", name='" + name + '\'' +
                '}';
    }
}
