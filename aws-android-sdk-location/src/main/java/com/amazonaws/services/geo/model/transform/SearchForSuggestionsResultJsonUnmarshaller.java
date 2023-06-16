/*
 * Copyright 2010-2023 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.services.geo.model.transform;

import com.amazonaws.services.geo.model.*;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.*;
import com.amazonaws.transform.*;
import com.amazonaws.util.json.AwsJsonReader;

/**
 * JSON unmarshaller for POJO SearchForSuggestionsResult
 */
class SearchForSuggestionsResultJsonUnmarshaller implements
        Unmarshaller<SearchForSuggestionsResult, JsonUnmarshallerContext> {

    public SearchForSuggestionsResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        AwsJsonReader reader = context.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SearchForSuggestionsResult searchForSuggestionsResult = new SearchForSuggestionsResult();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("Categories")) {
                searchForSuggestionsResult.setCategories(new ListUnmarshaller<String>(
                        StringJsonUnmarshaller.getInstance()
                        )
                                .unmarshall(context));
            } else if (name.equals("PlaceId")) {
                searchForSuggestionsResult.setPlaceId(StringJsonUnmarshaller.getInstance()
                        .unmarshall(context));
            } else if (name.equals("SupplementalCategories")) {
                searchForSuggestionsResult.setSupplementalCategories(new ListUnmarshaller<String>(
                        StringJsonUnmarshaller.getInstance()
                        )
                                .unmarshall(context));
            } else if (name.equals("Text")) {
                searchForSuggestionsResult.setText(StringJsonUnmarshaller.getInstance()
                        .unmarshall(context));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return searchForSuggestionsResult;
    }

    private static SearchForSuggestionsResultJsonUnmarshaller instance;

    public static SearchForSuggestionsResultJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new SearchForSuggestionsResultJsonUnmarshaller();
        return instance;
    }
}
