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

@RelationshipProperties
@Getter
@AllArgsConstructor
public class Reviews {
    private final String summary;

    private final Integer rating;

    @RelationshipId
    private Long id;

    @TargetNode
    private PersonEntity person;

    @AllArgsConstructor
    @Getter
    @RelationshipProperties
    public static class ReviewsReverse {
        @RelationshipId
        private final Long id;

        private final String summary;

        private final Integer rating;

        @TargetNode
        private final MovieEntity movie;
    }
}
