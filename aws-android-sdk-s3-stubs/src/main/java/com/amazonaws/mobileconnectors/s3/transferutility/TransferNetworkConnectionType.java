/**
 * Copyright 2019-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package com.amazonaws.mobileconnectors.s3.transferutility;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
// TODO: Figure out the right thing here to restore these logs.
// import com.amazonaws.logging.Log;
// import com.amazonaws.logging.LogFactory;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the type of connection a transfer is allowed to use
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8 which removed types not being used and a related getter method.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 7/2/21: Compared with AWS SDK 2.26.0 and restored the isConnected() method used by the TransferRecord class.
 * - 11/1/21: Compared with AWS SDK 2.35.0 and restored non-logging portions removed on 2/14/20 in anticipation of supporting the different types.
 */
public enum TransferNetworkConnectionType {
    /**
     * Any connection
     * - Used by Cognoa in the following classes:
     * -- NewUploadManager.kt
     */
    @SerializedName("ANY")
    ANY() {
        @Override
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected();
        }
    },

    /**
     * Wifi only
     */
    @SerializedName("WIFI")
    WIFI() {
        @Override
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected()
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
    },

    /**
     * Mobile only
     */
    @SerializedName("MOBILE")
    MOBILE() {
        @Override
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected()
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
    };

    private static final Map<String, TransferNetworkConnectionType> MAP;
    static {
        MAP = new HashMap<String, TransferNetworkConnectionType>();
        for (final TransferNetworkConnectionType type : TransferNetworkConnectionType.values()) {
            MAP.put(type.toString(), type);
        }
    }

    // TODO: Figure out the right thing here to restore these logs.
    // private static final Log LOGGER = LogFactory.getLog(TransferNetworkConnectionType.class);

    /**
     * Gets the status of network connectivity.
     *
     * @return true if network is connected, false otherwise.
     */
    boolean isConnected(final ConnectivityManager connectivityManager) {
        return verify(connectivityManager.getActiveNetworkInfo());
    }

    protected abstract boolean verify(final NetworkInfo networkInfo);

    /**
     * Returns the connection type from string
     *
     * @param typeAsString connection type of the transfer as string.
     * @return the {@link TransferNetworkConnectionType}
     */
    public static TransferNetworkConnectionType getConnectionType(String typeAsString) {
        if (MAP.containsKey(typeAsString)) {
            return MAP.get(typeAsString);
        }

        // TODO: Figure out the right thing here to restore these logs.
        // LOGGER.error("Unknown connection type " + typeAsString
        //         + " transfer will have connection type set to ANY.");
        return ANY;
    }
}
