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

import java.util.HashMap;
import java.util.Map;

/**
 * Metadata for an AWS region, including its name and what services are
 * available in it.
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 7/2/21: Compared with AWS SDK 2.26.0 and found nothing to modify.
 * - 11/2/21: Compared with AWS SDK 2.35.0 and found nothing to modify.
 */
public final class Region {

    private static final String DEFAULT_DOMAIN = "amazonaws.com";

    private final String name;
    private final String domain;

    private final Map<String, String> serviceEndpoints =
            new HashMap<String, String>();

    private final Map<String, Boolean> httpSupport =
            new HashMap<String, Boolean>();

    private final Map<String, Boolean> httpsSupport =
            new HashMap<String, Boolean>();

    Region(final String name, final String domain) {
        this.name = name;

        if (domain == null || domain.isEmpty()) {
            this.domain = DEFAULT_DOMAIN;
        } else {
            this.domain = domain;
        }
    }

    /**
     * @param regionString the region represented as a string. i.e. us-east-1
     * @return the region with the id given, or null if it cannot be found in
     * the current regions.xml file.
     */
    public static Region getRegion(String regionString) {
        return RegionUtils.getRegion(regionString);
    }

    /**
     * The unique system ID for this region; ex: &quot;us-east-1&quot;.
     *
     * @return The unique system ID for this region.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the domain for this region; ex: &quot;amazonaws.com&quot;.
     *
     * @return The domain for this region.
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @return a map of the available services in this region and their
     * endpoints. The keys of the map are service abbreviations, as defined in
     * {@link ServiceAbbreviations}, and the values are the endpoint URLs.
     *
     * @return A map of the available services in this region.
     */
    Map<String, String> getServiceEndpoints() {
        return serviceEndpoints;
    }

    Map<String, Boolean> getHttpSupport() {
        return httpSupport;
    }

    Map<String, Boolean> getHttpsSupport() {
        return httpsSupport;
    }

    /**
     * @param serviceName the service name.
     * @return the endpoint for the service given.
     *
     * @see ServiceAbbreviations
     */
    public String getServiceEndpoint(String serviceName) {
        return serviceEndpoints.get(serviceName);
    }

    /**
     * @param serviceName the service name.
     * @return whether the given service is supported in this region.
     *
     * @see ServiceAbbreviations
     */
    public boolean isServiceSupported(String serviceName) {
        return serviceEndpoints.containsKey(serviceName);
    }

    /**
     * @param serviceName the service name.
     * @return whether the given service support the https protocol in this
     * region.
     *
     * @see ServiceAbbreviations
     */
    public boolean hasHttpsEndpoint(String serviceName) {
        return httpsSupport.containsKey(serviceName) && httpsSupport.get(serviceName);
    }

    /**
     * @param serviceName the service name.
     * @return whether the given service support the http protocol in this
     * region.
     *
     * @see ServiceAbbreviations
     */
    public boolean hasHttpEndpoint(String serviceName) {
        return httpSupport.containsKey(serviceName) && httpSupport.get(serviceName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Region))
            return false;

        Region region = (Region) obj;
        return this.getName().equals(region.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }

}
