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

package com.amazonaws.retry;

import com.amazonaws.ClientConfiguration;

import java.util.Random;

/**
 * This class includes a set of pre-defined retry policies, including default
 * policies used by SDK.
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 7/2/21: Compared with AWS SDK 2.26.0 and found nothing to modify.
 * - 11/2/21: Compared with AWS SDK 2.35.0 and found nothing to modify.
 * - 3/15/22: Deprecated and merged with main core module.
 */
public class PredefinedRetryPolicies {

    /** No retry policy **/
    public static final RetryPolicy NO_RETRY_POLICY = new RetryPolicy(
            RetryPolicy.RetryCondition.NO_RETRY_CONDITION,
            RetryPolicy.BackoffStrategy.NO_DELAY,
            0,      // maxErrorRetry
            false); // honorMaxErrorRetryInClientConfig


    /* SDK default */
    /** Base sleep time (milliseconds) for throttling exceptions. **/
    private static final int BASE_DELAY_IN_MILLISECONDS = 100;

    /** Maximum exponential back-off time before retrying a request */
    private static final int MAX_BACKOFF_IN_MILLISECONDS = 20 * 1000;

    /** SDK default max retry count **/
    public static final int DEFAULT_MAX_ERROR_RETRY = 3;

    /** SDK default retry policy **/
    public static final RetryPolicy DEFAULT;

    /* Default for DynamoDB client */

    /** Default max retry count for DynamoDB client **/
    public static final int DYNAMODB_DEFAULT_MAX_ERROR_RETRY = 10;

    /** Default policy for DynamoDB client **/
    public static final RetryPolicy DYNAMODB_DEFAULT;

    /* Reusable retry policy components */

    /**
     * The SDK default retry condition, which checks for various conditions in
     * the following order:
     * <ul>
     * <li>Never retry on requests with non-repeatable content;
     * <li>Retry on client exceptions caused by IOException;
     * <li>Retry on service exceptions that are either 500 internal server
     * errors, 503 service unavailable errors, service throttling errors or
     * clock skew errors.
     * </ul>
     */
    public static final RetryPolicy.RetryCondition DEFAULT_RETRY_CONDITION = new SDKDefaultRetryCondition();

    /**
     * The SDK default back-off strategy, which increases exponentially up to a
     * max amount of delay. It also applies a larger scale factor upon service
     * throttling exception.
     */
    public static final RetryPolicy.BackoffStrategy DEFAULT_BACKOFF_STRATEGY = new SDKDefaultBackoffStrategy(
            BASE_DELAY_IN_MILLISECONDS, MAX_BACKOFF_IN_MILLISECONDS);

    static {
        DEFAULT = getDefaultRetryPolicy();
        DYNAMODB_DEFAULT = getDynamoDBDefaultRetryPolicy();
    }

    /**
     * @return the SDK default retry policy. This policy will honor the
     * maxErrorRetry set in ClientConfiguration.
     *
     * @see ClientConfiguration#setMaxErrorRetry(int)
     */
    public static RetryPolicy getDefaultRetryPolicy() {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION,
                DEFAULT_BACKOFF_STRATEGY,
                DEFAULT_MAX_ERROR_RETRY,
                true);
    }

    /**
     * @return the default retry policy for DynamoDB client. This policy will
     * honor the maxErrorRetry set in ClientConfiguration.
     *
     * @see ClientConfiguration#setMaxErrorRetry(int)
     */
    public static RetryPolicy getDynamoDBDefaultRetryPolicy() {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION,
                DEFAULT_BACKOFF_STRATEGY,
                DYNAMODB_DEFAULT_MAX_ERROR_RETRY,
                true);
    }

    /**
     * @param maxErrorRetry the max error retry count.
     * @return the SDK default retry policy with the specified max retry count.
     */
    public static RetryPolicy getDefaultRetryPolicyWithCustomMaxRetries(int maxErrorRetry) {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION,
                DEFAULT_BACKOFF_STRATEGY,
                maxErrorRetry,
                false);
    }

    /**
     * @param maxErrorRetry the max error retry count.
     * @return the default retry policy for DynamoDB client with the specified
     * max retry count.
     */
    public static RetryPolicy getDynamoDBDefaultRetryPolicyWithCustomMaxRetries(int maxErrorRetry) {
        return new RetryPolicy(DEFAULT_RETRY_CONDITION,
                DEFAULT_BACKOFF_STRATEGY,
                maxErrorRetry,
                false);
    }

    /**
     * The default implementation of RetryCondition used by the SDK. User could
     * extend this class to provide additional custom conditions. The default
     * implementation checks for various conditions in the following order:
     * <ul>
     * <li>Retry on client exceptions caused by IOException;
     * <li>Retry on service exceptions that are either 500 internal server
     * errors, 503 service unavailable errors, service throttling errors or
     * clock skew errors.
     * </ul>
     */
    public static class SDKDefaultRetryCondition implements RetryPolicy.RetryCondition {

        @Override
        public boolean shouldRetry(int retriesAttempted) {
            // TODO: Mock something here if needed.
            return false;
        }

    }

    /** A private class that implements the default back-off strategy. **/
    private static final class SDKDefaultBackoffStrategy implements RetryPolicy.BackoffStrategy {

        /** For generating a random scale factor **/
        private final Random random = new Random();

        private final int baseDelayMs;
        private final int maxDelayMs;

        private SDKDefaultBackoffStrategy(int baseDelayMs, int maxDelayMs) {
            this.baseDelayMs = baseDelayMs;
            this.maxDelayMs = maxDelayMs;
        }

        /** {@inheritDoc} */
        @Override
        public final long delayBeforeNextRetry(int retries) {
            if (retries <= 0)
                return 0;

            // Full jitter
            // https://www.awsarchitectureblog.com/2015/03/backoff.html
            return random.nextInt(Math.min(maxDelayMs, (1 << retries) * baseDelayMs));
        }
    }
}
