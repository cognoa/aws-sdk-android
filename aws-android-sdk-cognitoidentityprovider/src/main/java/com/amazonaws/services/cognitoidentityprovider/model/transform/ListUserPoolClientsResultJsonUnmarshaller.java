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
 * List User Pool Clients Result JSON Unmarshaller
 */
public class ListUserPoolClientsResultJsonUnmarshaller implements Unmarshaller<ListUserPoolClientsResult, JsonUnmarshallerContext> {

    public ListUserPoolClientsResult unmarshall(JsonUnmarshallerContext context) throws Exception {
        ListUserPoolClientsResult listUserPoolClientsResult = new ListUserPoolClientsResult();

        AwsJsonReader reader = context.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            
            if (name.equals("UserPoolClients")) {
                listUserPoolClientsResult.setUserPoolClients(new ListUnmarshaller<UserPoolClientDescription>(UserPoolClientDescriptionJsonUnmarshaller.getInstance()).unmarshall(context));
            } else 
            if (name.equals("NextToken")) {
                listUserPoolClientsResult.setNextToken(StringJsonUnmarshaller.getInstance().unmarshall(context));
            } else 
            {
                reader.skipValue();
            }
            
        }
        reader.endObject();
        
        return listUserPoolClientsResult;
    }

    private static ListUserPoolClientsResultJsonUnmarshaller instance;
    public static ListUserPoolClientsResultJsonUnmarshaller getInstance() {
        if (instance == null) instance = new ListUserPoolClientsResultJsonUnmarshaller();
        return instance;
    }
}
    