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

package com.amazonaws;

import com.amazonaws.retry.PredefinedRetryPolicies;
import com.amazonaws.retry.RetryPolicy;

/**
 * Client configuration options such as proxy settings, user agent string, max
 * retry attempts, etc.
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 6/18/21: Restored 3 getters for Kotlin compiler to map the properties to getters/setters.
 * - 7/7/21: Compared with AWS SDK 2.26.0 and just updated some usage notes.
 * - 11/3/21: Compared with AWS SDK 2.35.0 and just updated some usage notes.
 */
public class ClientConfiguration {

    /** The default timeout for creating new connections. */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 15 * 1000;

    /** The default timeout for reading from a connected socket. */
    public static final int DEFAULT_SOCKET_TIMEOUT = 15 * 1000;

    /**
     * Default request retry policy, including the maximum retry count of 3, the
     * default retry condition and the default back-off strategy.
     * <p>
     * Note this default policy might be overridden by a service-specific
     * default policy, if the user doesn't provide a custom RetryPolicy
     * implementation by {@link #setRetryPolicy(RetryPolicy)}. For example,
     * AmazonDynamoDBClient by default uses a different retry policy
     * {@link PredefinedRetryPolicies#DYNAMODB_DEFAULT}.
     *
     * @see PredefinedRetryPolicies#DEFAULT
     * @see PredefinedRetryPolicies#DYNAMODB_DEFAULT
     */
    public static final RetryPolicy DEFAULT_RETRY_POLICY = PredefinedRetryPolicies.DEFAULT;

    /**
     * The maximum number of times that a retryable failed request (ex: a 5xx
     * response from a service) will be retried. Or -1 if the user has not
     * explicitly set this value, in which case the configured RetryPolicy will
     * be used to control the retry count.
     */
    private int maxErrorRetry = -1;

    /** The retry policy upon failed requests. **/
    private RetryPolicy retryPolicy = DEFAULT_RETRY_POLICY;

    /**
     * The amount of time to wait (in milliseconds) for data to be transfered
     * over an established, open connection before the connection is timed out.
     * A value of 0 means infinity, and is not recommended.
     */
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;

    /**
     * The amount of time to wait (in milliseconds) when initially establishing
     * a connection before giving up and timing out. A value of 0 means
     * infinity, and is not recommended.
     */
    private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;

    /**
     * Optional override to control which signature algorithm should be used to
     * sign requests to the service. If not explicitly set, the client will
     * determine the algorithm to use by inspecting a configuration file baked
     * in to the SDK.
     */
    private String signerOverride;

    /**
     * Constructor.
     * - Used by Cognoa in NewUploadManager.kt
     */
    public ClientConfiguration() {
    }

    /**
     * Returns the retry policy upon failed requests.
     *
     * @return The retry policy upon failed requests.
     */
    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    /**
     * Sets the retry policy upon failed requests. User could specify whether
     * the RetryPolicy should honor maxErrorRetry set by
     * {@link #setMaxErrorRetry(int)}.
     *
     * - Used by Cognoa in NewUploadManager.kt
     *
     * @param retryPolicy The retry policy upon failed requests.
     */
    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    /**
     * Returns the maximum number of retry attempts for failed retryable
     * requests (ex: 5xx error responses from a service). This method returns -1
     * before a maxErrorRetry value is explicitly set by
     * {@link #setMaxErrorRetry(int)}, in which case the configured RetryPolicy
     * will be used to control the retry count.
     *
     * @return The maximum number of retry attempts for failed retryable
     *         requests, or -1 if maxErrorRetry has not been set by
     *         {@link #setMaxErrorRetry(int)}.
     */
    public int getMaxErrorRetry() {
        return maxErrorRetry;
    }

    /**
     * Sets the maximum number of retry attempts for failed retryable requests
     * (ex: 5xx error responses from services).
     *
     * - Used by Cognoa in NewUploadManager.kt
     *
     * @param maxErrorRetry The maximum number of retry attempts for failed
     *            retryable requests. This value should not be negative.
     */
    public void setMaxErrorRetry(int maxErrorRetry) {
        if (maxErrorRetry < 0) {
            throw new IllegalArgumentException("maxErrorRetry shoud be non-negative");
        }
        this.maxErrorRetry = maxErrorRetry;
    }

    /**
     * Returns the amount of time to wait (in milliseconds) for data to be
     * transfered over an established, open connection before the connection
     * times out and is closed. A value of 0 means infinity, and isn't
     * recommended.
     *
     * @return The amount of time to wait (in milliseconds) for data to be
     *         transfered over an established, open connection before the
     *         connection times out and is closed.
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * Sets the amount of time to wait (in milliseconds) for data to be
     * transfered over an established, open connection before the connection
     * times out and is closed. A value of 0 means infinity, and isn't
     * recommended.
     *
     * - Used by Cognoa in NewUploadManager.kt
     *
     * @param socketTimeout The amount of time to wait (in milliseconds) for
     *            data to be transfered over an established, open connection
     *            before the connection is times out and is closed.
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * Returns the amount of time to wait (in milliseconds) when initially
     * establishing a connection before giving up and timing out. A value of 0
     * means infinity, and is not recommended.
     *
     * @return The amount of time to wait (in milliseconds) when initially
     *         establishing a connection before giving up and timing out.
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the amount of time to wait (in milliseconds) when initially
     * establishing a connection before giving up and timing out. A value of 0
     * means infinity, and is not recommended.
     *
     * - Used by Cognoa in NewUploadManager.kt
     *
     * @param connectionTimeout The amount of time to wait (in milliseconds)
     *            when initially establishing a connection before giving up and
     *            timing out.
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Returns the name of the signature algorithm to use for signing requests
     * made by this client. If not set or explicitly set to null, the client
     * will choose a signature algorithm to use based on a configuration file of
     * supported signature algorithms for the service and region.
     * <p>
     * Most users do not need to concern themselves with which signature
     * algorithm is being used, as the defaults will be sufficient. This setting
     * exists only so advanced users can opt in to newer signature protocols
     * which have not yet been made the default for a particular service/region.
     * <p>
     * Not all services support all signature algorithms, and configuring an
     * unsupported signature algorithm will lead to authentication failures. Use
     * me at your own risk, and only after consulting the documentation for the
     * service to ensure it actually does supports your chosen algorithm.
     * <p>
     * If non-null, the name returned from this method is used to look up a
     * {@code Signer} class implementing the chosen algorithm by the
     * {@code com.amazonaws.auth.SignerFactory} class.
     *
     * @return The signature algorithm to use for this client, or null to use
     *         the default.
     */
    public String getSignerOverride() {
        return signerOverride;
    }
}
