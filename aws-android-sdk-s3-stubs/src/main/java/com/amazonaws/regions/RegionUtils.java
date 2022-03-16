/*
 * Copyright 2013-2019 Amazon Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazonaws.regions;

import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilities for working with regions.
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 * Uses Cognoa's Logging Module.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 7/2/21: Compared with AWS SDK 2.26.0 and found nothing to modify.
 * - 11/2/21: Compared with AWS SDK 2.35.0 and restored Amazon's logging module, but commented out.
 * - 3/15/22: Deprecated and merged with main core module.
 */
public class RegionUtils {

    private static List<Region> regions;

    // Use the same logger as the http client
    // private static final Log log = LogFactory.getLog("com.amazonaws.request");

    /**
     * @return a list of the available AWS regions.
     */
    public static synchronized List<Region> getRegions() {
        if (regions == null) {
            init();
        }

        return regions;
    }

    /**
     * @param serviceAbbreviation the service abbreviation.
     * @return a list of the regions that support the service given.
     *
     * @see ServiceAbbreviations
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public static synchronized List<Region> getRegionsForService(String serviceAbbreviation) {
        List<Region> regions = new LinkedList<Region>();
        for (Region r : getRegions()) {
            if (r.isServiceSupported(serviceAbbreviation)) {
                regions.add(r);
            }
        }
        return regions;
    }

    /**
     * @param regionName the region name.
     * @return the region with the id given, if it exists. Otherwise, returns
     * null.
     */
    public static Region getRegion(String regionName) {
        for (Region r : getRegions()) {
            if (r.getName().equals(regionName)) {
                return r;
            }
        }

        return null;
    }

    /**
     * Searches through all known regions to find one with any service at the
     * specified endpoint. If no region is found with a service at that
     * endpoint, an exception is thrown.
     *
     * @param endpoint The endpoint for any service residing in the desired
     *            region.
     * @return The region containing any service running at the specified
     *         endpoint, otherwise an exception is thrown if no region is found
     *         with a service at the specified endpoint.
     * @throws MalformedURLException If the given URL is malformed, or if the
     *             one of the service URLs on record is malformed.
     */
    public static Region getRegionByEndpoint(String endpoint) {
        URI targetEndpointUri = getUriByEndpoint(endpoint);
        String targetHost = targetEndpointUri.getHost();

        for (Region region : getRegions()) {
            for (String serviceEndpoint : region.getServiceEndpoints().values()) {
                URI serviceEndpointUrl = getUriByEndpoint(serviceEndpoint);

                if (serviceEndpointUrl.getHost().equals(targetHost))
                    return region;
            }
        }

        throw new IllegalArgumentException("No region found with any service for endpoint "
                + endpoint);
    }

    /**
     * There is no need to call this method, it is public only for
     * Compatibility. Every regions method makes sure this method has already
     * been called. This method will load the regions (specified by the
     * developer if the system property
     * SDKGlobalConfiguraiton.REGIONS_FILE_OVERRIDE_SYSTEM_PROPERTY is set, or
     * the defaults shipped with the SDK) into memory.
     */
    public static synchronized void init() {

        // TODO: Mock this constant if useful.
//        if (System.getProperty(REGIONS_FILE_OVERRIDE_SYSTEM_PROPERTY) != null) {
//            try {
//                loadRegionsFromOverrideFile();
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException("Couldn't find regions override file specified", e);
//            }
//        }

        // Fall back onto the version we ship with the SDK
        if (regions == null) {
            initSDKRegions();
        }
        // Throw out RuntimeException explicitly
        if (regions == null) {
            throw new RuntimeException("Failed to initialize the regions.");
        }
    }

    /**
     * Failsafe method to initialize the regions list from the list bundled with
     * the SDK, in case it cannot be fetched from the remote source.
     */
    private static void initSDKRegions() {
        // if (log.isDebugEnabled()) {
        //     log.debug("Initializing the regions with default regions");
        // }
        regions = RegionDefaults.getRegions();
    }

    /**
     * Get the URI object for the given endpoint. URI class cannot correctly
     * parse the endpoint if it doesn't include protocol. This method will add
     * the protocol if this happens.
     */
    private static URI getUriByEndpoint(String endpoint) {
        URI targetEndpointUri = null;
        try {
            targetEndpointUri = new URI(endpoint);
            if (targetEndpointUri.getHost() == null) {
                targetEndpointUri = new URI("http://" + endpoint);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Unable to parse service endpoint: " + e.getMessage());
        }
        return targetEndpointUri;
    }
}
