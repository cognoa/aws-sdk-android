/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package com.amazonaws.auth;

/**
 * Basic implementation of the AWSCredentials interface that allows callers to
 * pass in the AWS access key and secret access in the constructor.
 *
 * It is not recommended to use this class. Instead, use short term credentials
 * from Cognito with the AWSMobileClient.
 * @see <a href="https://aws-amplify.github.io/aws-sdk-android/docs/reference/com/amazonaws/mobile/client/AWSMobileClient.html">AWSMobileClient</a>
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8 which elimiated the base class.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found no differences.
 * - 7/2/21: Compared with AWS SDK 2.26.0 and found no differences.
 * - 11/1/21: Compared with AWS SDK 2.35.0 and found no differences.
 */
public class BasicAWSCredentials {

    private final String accessKey;
    private final String secretKey;

    /**
     * Constructs a new BasicAWSCredentials object, with the specified AWS
     * access key and AWS secret key.
     *
     * - Used by Cognoa in the following classes:
     * -- NewUploadManagerTests.java
     * -- NewVideoScenariosViewModel.kt
     * -- S3Utils.java
     *
     * @param accessKey The AWS access key.
     * @param secretKey The AWS secret access key.
     */
    public BasicAWSCredentials(String accessKey, String secretKey) {
        if (accessKey == null) {
            throw new IllegalArgumentException("Access key cannot be null.");
        }
        if (secretKey == null) {
            throw new IllegalArgumentException("Secret key cannot be null.");
        }

        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /*
     * (non-Javadoc)
     * @see com.amazonaws.auth.AWSCredentials#getAWSAccessKeyId()
     *
     * - Used by Cognoa in S3UtilsTest.java
     */
    public String getAWSAccessKeyId() {
        return accessKey;
    }

    /*
     * (non-Javadoc)
     * @see com.amazonaws.auth.AWSCredentials#getAWSSecretKey()
     *
     * - Used by Cognoa in S3UtilsTest.java
     */
    public String getAWSSecretKey() {
        return secretKey;
    }

}
