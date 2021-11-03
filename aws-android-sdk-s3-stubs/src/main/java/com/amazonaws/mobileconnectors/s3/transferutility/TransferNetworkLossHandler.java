/**
 * Copyright 2015-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * <p>
 * http://aws.amazon.com/apache2.0
 * <p>
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.mobileconnectors.s3.transferutility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * A Broadcast receiver to receive network connection change events.
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 7/2/21: Compared with AWS SDK 2.26.0 and removed blocks of commented out code now that we have our own fork of
 * the SDK.
 * - 11/1/21: Compared with AWS SDK 2.35.0 and found nothing to modify other than a few notes.
 */
@SuppressWarnings("checkstyle:finalclass")
public class TransferNetworkLossHandler extends BroadcastReceiver {

    /**
     * An Android Networking utility that gives network specific information.
     */
    final ConnectivityManager connManager;

    /**
     * The Singleton instance.
     */
    private static TransferNetworkLossHandler transferNetworkLossHandler;

    /**
     * Constructs a TransferNetworkLossHandler.
     *
     * @param context the application context
     */
    private TransferNetworkLossHandler(Context context) {
        connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Real constructor contains more, but not of Cognoa's concern.
    }

    /**
     * Return the singleton instance of the receiver.
     * - Used by Cognoa in thee following classes:
     * -- NewUploadManager.kt
     *
     * @param context the application context
     * @return handler object
     */
    public static synchronized TransferNetworkLossHandler getInstance(Context context) {
        if (transferNetworkLossHandler == null) {
            transferNetworkLossHandler = new TransferNetworkLossHandler(context);
        }
        return transferNetworkLossHandler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // See the actual implementation when we wish to mock something here for handling network loss in a test.
    }
}
