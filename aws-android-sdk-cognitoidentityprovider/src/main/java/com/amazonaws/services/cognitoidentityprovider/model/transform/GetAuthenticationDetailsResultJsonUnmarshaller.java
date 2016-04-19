/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package com.amazonaws.services.cognitoidentityprovider.model.transform;

import java.util.Map;
import java.util.Map.Entry;

import com.amazonaws.services.cognitoidentityprovider.model.*;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.*;
import com.amazonaws.transform.*;
import com.amazonaws.util.json.AwsJsonReader;

/**
 * Get Authentication Details Result JSON Unmarshaller
 */
public class GetAuthenticationDetailsResultJsonUnmarshaller implements Unmarshaller<GetAuthenticationDetailsResult, JsonUnmarshallerContext> {

    public GetAuthenticationDetailsResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        GetAuthenticationDetailsResult getAuthenticationDetailsResult = new GetAuthenticationDetailsResult();

        AwsJsonReader reader = context.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            
            if (name.equals("Salt")) {
                getAuthenticationDetailsResult.setSalt(StringJsonUnmarshaller.getInstance().unmarshall(context));
            } else 
            if (name.equals("SrpB")) {
                getAuthenticationDetailsResult.setSrpB(StringJsonUnmarshaller.getInstance().unmarshall(context));
            } else 
            if (name.equals("SecretBlock")) {
                getAuthenticationDetailsResult.setSecretBlock(ByteBufferJsonUnmarshaller.getInstance().unmarshall(context));
            } else 
            if (name.equals("Username")) {
                getAuthenticationDetailsResult.setUsername(StringJsonUnmarshaller.getInstance().unmarshall(context));
            } else 
            {
                reader.skipValue();
            }
            
        }
        reader.endObject();
        
        return getAuthenticationDetailsResult;
    }

    private static GetAuthenticationDetailsResultJsonUnmarshaller instance;
    public static GetAuthenticationDetailsResultJsonUnmarshaller getInstance() {
        if (instance == null) instance = new GetAuthenticationDetailsResultJsonUnmarshaller();
        return instance;
    }
}
    