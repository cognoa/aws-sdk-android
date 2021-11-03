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

package com.amazonaws.services.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Provides the client for accessing the Amazon S3 web service.
 * </p>
 * <p>
 * Amazon S3 provides storage for the Internet, and is designed to make
 * web-scale computing easier for developers.
 * </p>
 * <p>
 * The Amazon S3 Android Client provides a simple interface that can be used to
 * store and retrieve any amount of data, at any time, from anywhere on the web.
 * It gives any developer access to the same highly scalable, reliable, secure,
 * fast, inexpensive infrastructure that Amazon uses to run its own global
 * network of web sites. The service aims to maximize benefits of scale and to
 * pass those benefits on to developers.
 * </p>
 * <p>
 * For more information about Amazon S3, please see <a
 * href="http://aws.amazon.com/s3"> http://aws.amazon.com/s3</a>
 * </p>
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 5/6/21: Added setS3ClientOptions() method from AWS SDK 2.19.0.
 * - 7/7/21: Compared with AWS SDK 2.26.0 and there's enough differences that things would only be pulled in if the
 * build fails.
 * - 11/3/21: Compared with AWS SDK 2.35.0 and there's still enough differences that things will only be pulled in if the
 * build fails.
 */
@SuppressWarnings("deprecation")
public class AmazonS3Client {

    public static final String S3_SERVICE_NAME = "s3";

    private static final String S3_SIGNER = "S3SignerType";
    private static final String S3_V4_SIGNER = "AWSS3V4SignerType";

    /** S3 specific client configuration options */
    protected S3ClientOptions clientOptions = new S3ClientOptions();

    /**
     * The S3 client region that is set by either (a) calling
     * setRegion/configureRegion OR (b) calling setEndpoint with a
     * region-specific S3 endpoint. This region string will be used for signing
     * requests sent by this client.
     */
    volatile String clientRegion;

    /**
     * Number of Kbytes that needs to be written before status updates are called.
     */
    private int notificationThreshold = 1024;

    private static final int BUCKET_REGION_CACHE_SIZE = 300;

    private static final Map<String, String> bucketRegionCache = Collections.synchronizedMap(
            new LinkedHashMap<String, String>(BUCKET_REGION_CACHE_SIZE, 1.1f, true) {
                private static final long serialVersionUID = 23453L;

                @Override
                protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                    return size() > BUCKET_REGION_CACHE_SIZE;
                }
            });

    static Map<String, String> getBucketRegionCache() {
        return bucketRegionCache;
    }

    /**
     *
     * @param awsCredentials The AWS credentials to use when making requests to
     *            Amazon S3 with this client.
     * @param region the AWS region
     * @param clientConfiguration The client configuration options controlling
     *            how this client connects to Amazon S3 (e.g. proxy settings,
     *            retry counts, etc).
     */
    public AmazonS3Client(BasicAWSCredentials awsCredentials,
                          com.amazonaws.regions.Region region,
                          ClientConfiguration clientConfiguration) {
        // TODO: The credentials are cast to a credentials provider later... should do something with that.
        // Inject the part called from a constructor later down the chain.
        init(region, clientConfiguration);
    }


    /**
     *
     * @param awsCredentialsProvider The AWS credentials provider which will
     *            provide credentials to authenticate requests with AWS
     *            services.
     * @param region the AWS region
     * @param clientConfiguration The AWS credentials provider which will
     *            provide credentials to authenticate requests with AWS
     *            services.
     */
    private void init(com.amazonaws.regions.Region region,
                      ClientConfiguration clientConfiguration) {
        // TODO: Something that makes sense for mocking purposes.
//        if (awsCredentialsProvider == null) {
//            throw new IllegalArgumentException("Credentials cannot be null. Credentials is required to sign the request");
//        }
//
//        if (region == null) {
//            throw new IllegalArgumentException("Region cannot be null. Region is required to sign the request");
//        }
//
//        this.clientConfiguration = clientConfiguration;
//        this.endpointPrefix = Constants.S3_ENDPOINT_PREFIX;
//
//        // calling this.setEndpoint(...) will also modify the signer accordingly
//        setEndpoint(Constants.S3_HOSTNAME);
//        setRegion(region);
//
//        final HandlerChainFactory chainFactory = new HandlerChainFactory();
//        requestHandler2s.addAll(chainFactory.newRequestHandlerChain(
//                "/com/amazonaws/services/s3/request.handlers"));
//        requestHandler2s.addAll(chainFactory.newRequestHandler2Chain(
//                "/com/amazonaws/services/s3/request.handler2s"));
//
//        log.debug("initialized with endpoint = " + this.endpoint);
    }

    /**
     * <p>
     * Override the default S3 client options for this client. Also set the
     * endpoint to s3-accelerate if such is specified in the S3 client options.
     * </p>
     *
     * @param clientOptions The S3 client options to use.
     */
    public void setS3ClientOptions(S3ClientOptions clientOptions) {
        this.clientOptions = new S3ClientOptions(clientOptions);
    }


    /**
     * Sets the number of Kbytes that need to be written before updates to the
     * listener occur.
     *
     * @param threshold Number of Kbytes that needs to be written before
     *            write update notification occurs. Minimum value supported
     *            is 128 KB due to the limitations imposed by the HttpClient
     *            while reading the data from the stream.
     */
    public void setNotificationThreshold(final int threshold) {
        this.notificationThreshold = threshold;
    }

    /**
     * Converts the current endpoint set for this client into virtual addressing
     * style, by placing the name of the specified bucket before the S3 service
     * endpoint.
     *
     * @param bucketName The name of the bucket to use in the virtual addressing
     *            style of the returned URI.
     * @return A new URI, creating from the current service endpoint URI and the
     *         specified bucket.
     */
    private URI convertToVirtualHostEndpoint(URI endpoint, String bucketName) {
        try {
            return new URI(endpoint.getScheme() + "://" + bucketName + "."
                    + endpoint.getAuthority());
        } catch (final URISyntaxException e) {
            throw new IllegalArgumentException("Invalid bucket name: " + bucketName, e);
        }
    }

    private String getHostStyleResourcePath(String key) {
        String resourcePath = key;
        /*
         * If the key name starts with a slash character, in order to prevent it
         * being treated as a path delimiter, we need to add another slash
         * before the key name. {@see
         * com.amazonaws.http.HttpRequestFactory#createHttpRequest}
         */
        if (key != null && key.startsWith("/")) {
            resourcePath = "/" + key;
        }
        return resourcePath;
    }

    private String getPathStyleResourcePath(String bucketName, String key) {
        return bucketName + "/" + (key != null ? key : "");
    }

    static boolean isValidIpV4Address(String ipAddr) {
        if (ipAddr == null) {
            return false;
        }
        final String[] tokens = ipAddr.split("\\.");
        if (tokens.length != 4) {
            return false;
        }
        for (final String token : tokens) {
            try {
                final int tokenInt = Integer.parseInt(token);
                if (tokenInt < 0 || tokenInt > 255) {
                    return false;
                }
            } catch (final NumberFormatException ase) {
                return false;
            }
        }
        return true;
    }

}

