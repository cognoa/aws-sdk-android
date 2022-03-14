/*
 * Copyright 2010-2022 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package com.amazonaws.services.rekognition.model.transform;

import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.json.AwsJsonWriter;

/**
 * JSON marshaller for POJO DatasetSource
 */
class DatasetSourceJsonMarshaller {

    public void marshall(DatasetSource datasetSource, AwsJsonWriter jsonWriter) throws Exception {
        jsonWriter.beginObject();
        if (datasetSource.getGroundTruthManifest() != null) {
            GroundTruthManifest groundTruthManifest = datasetSource.getGroundTruthManifest();
            jsonWriter.name("GroundTruthManifest");
            GroundTruthManifestJsonMarshaller.getInstance().marshall(groundTruthManifest,
                    jsonWriter);
        }
        if (datasetSource.getDatasetArn() != null) {
            String datasetArn = datasetSource.getDatasetArn();
            jsonWriter.name("DatasetArn");
            jsonWriter.value(datasetArn);
        }
        jsonWriter.endObject();
    }

    private static DatasetSourceJsonMarshaller instance;

    public static DatasetSourceJsonMarshaller getInstance() {
        if (instance == null)
            instance = new DatasetSourceJsonMarshaller();
        return instance;
    }
}
