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

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
@AllArgsConstructor
public class Roles {
    @RelationshipId
    private final Long id;

    @Getter
    private final List<String> roles;

    @Getter
    @TargetNode
    private final PersonEntity person;

//    public Roles(PersonEntity person, List<String> roles) {
//        this.person = person;
//        this.roles = new ArrayList<>(roles);
//    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    @Getter
    @RelationshipProperties
    @AllArgsConstructor
    public static class RolesReverse {
        @RelationshipId
        private final Long id;

        private final List<String> roles;

        @TargetNode
        private final MovieEntity movie;
    }
}
