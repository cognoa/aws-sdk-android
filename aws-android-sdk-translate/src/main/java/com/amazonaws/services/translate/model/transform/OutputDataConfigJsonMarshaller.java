/*
 * Copyright 2010-2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package com.amazonaws.services.translate.model.transform;

import com.amazonaws.services.translate.model.*;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.json.AwsJsonWriter;

/**
 * JSON marshaller for POJO OutputDataConfig
 */
class OutputDataConfigJsonMarshaller {

    public void marshall(OutputDataConfig outputDataConfig, AwsJsonWriter jsonWriter)
            throws Exception {
        jsonWriter.beginObject();
        if (outputDataConfig.getS3Uri() != null) {
            String s3Uri = outputDataConfig.getS3Uri();
            jsonWriter.name("S3Uri");
            jsonWriter.value(s3Uri);
        }
        if (outputDataConfig.getEncryptionKey() != null) {
            EncryptionKey encryptionKey = outputDataConfig.getEncryptionKey();
            jsonWriter.name("EncryptionKey");
            EncryptionKeyJsonMarshaller.getInstance().marshall(encryptionKey, jsonWriter);
        }
        jsonWriter.endObject();
    }

    private static OutputDataConfigJsonMarshaller instance;

    public static OutputDataConfigJsonMarshaller getInstance() {
        if (instance == null)
            instance = new OutputDataConfigJsonMarshaller();
        return instance;
    }
}
