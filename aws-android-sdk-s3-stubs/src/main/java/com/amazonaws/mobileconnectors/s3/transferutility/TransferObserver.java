/**
 * Copyright 2015-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

import android.database.Cursor;

import java.io.File;

/**
 * TransferObserver is used to track state and progress of a transfer.
 * Applications can set a listener and will get notified when progress or state
 * changes.
 * <p>
 * For example, you can track the progress of an upload as the following:
 * </p>
 *
 * <pre>
 * TransferObserver transfer = transferUtility.upload(bucket, key, file);
 * transfer.setListener(new TransferListener() {
 *     public onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
 *         // update progress bar
 *         progressBar.setProgress(bytesCurrent);
 *     }
 *
 *     public void onStateChanged(int id, TransferState state) {
 *     }
 *
 *     public void onError(int id, Exception ex) {
 *     }
 * });
 * </pre>
 * <p>
 * Note that callbacks of a listener will be invoked on the main thread.
 * </p>
 *
 * Mock implementation used by Cognoa - originally forked from AWS SDK 2.16.8
 * Portions not used by Cognoa have been removed. All others are stubbed.
 * This mostly includes TransferDbUtil + anything operating on it.
 *
 * Fork History:
 * - 2/14/20: Original fork from AWS SDK 2.16.8.
 * - 10/13/20: Compared with AWS SDK 2.19.0 and found nothing to modify.
 * - 7/2/21: Compared with AWS SDK 2.26.0 and found nothing to modify.
 * - 11/1/21: Compared with AWS SDK 2.35.0 and found nothing to modify.
 */
public class TransferObserver {

    private final int id;

    private String bucket;
    private String key;
    private long bytesTotal;
    private long bytesTransferred;
    private TransferState transferState;
    private String filePath;

    private TransferListener transferListener;
    private TransferStatusListener statusListener;

    /**
     * Constructs a TransferObserver and initializes fields with the given
     * arguments.
     *
     * @param id The transfer id of the transfer to be observed.
     * @param dbUtil an instance of database utility
     * @param bucket bucket of the S3 object
     * @param key key of the S3 object
     * @param file a file associated with this transfer
     */
    TransferObserver(int id, String bucket, String key, File file) {
        this.id = id;
        this.bucket = bucket;
        this.key = key;
        filePath = file.getAbsolutePath();
        bytesTotal = file.length();
        transferState = TransferState.WAITING;
    }

    /**
     * Constructs a TransferObserver and initializes fields with the given
     * arguments.
     *
     * @param id The transfer id of the transfer to be observed.
     * @param dbUtil an instance of database utility
     * @param bucket bucket of the S3 object
     * @param key key of the S3 object
     * @param file a file associated with this transfer
     * @param listener the listener for the transfer
     */
    TransferObserver(int id, String bucket, String key, File file,
            TransferListener listener) {
        this(id, bucket, key, file);
        this.setTransferListener(listener);
    }

    /**
     * Constructs a TransferObserver and initializes fields with the given
     * arguments.
     *
     * @param id The transfer id of the transfer to be observed.
     * @param dbUtil an instance of database utility
     */
    TransferObserver(int id) {
        this.id = id;
    }

    /**
     * Update transfer state from the given cursor.
     *
     * @param c a cursor to read the state of the transfer from
     */
    protected void updateFromDB(Cursor c) {
        bucket = c.getString(c.getColumnIndexOrThrow(TransferTable.COLUMN_BUCKET_NAME));
        key = c.getString(c.getColumnIndexOrThrow(TransferTable.COLUMN_KEY));
        bytesTotal = c.getLong(c.getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_TOTAL));
        bytesTransferred = c.getLong(c
                .getColumnIndexOrThrow(TransferTable.COLUMN_BYTES_CURRENT));
        transferState = TransferState.getState(c.getString(c
                .getColumnIndexOrThrow(TransferTable.COLUMN_STATE)));
        filePath = c.getString(c.getColumnIndexOrThrow(TransferTable.COLUMN_FILE));
    }

    /**
     * Sets a listener used to receive notification when state or progress
     * changes.
     * <p>
     * Note that callbacks of the listener will be invoked on the main thread.
     * </p>
     *
     * @param listener A TransferListener used to receive notification.
     */
    public void setTransferListener(TransferListener listener) {
        if (listener != null) {
            synchronized (this) {
                // Remove previous listener.
                cleanTransferListener();

                // One additional listener is attached so that the basic transfer
                // info gets updated along side.
                statusListener = new TransferStatusListener();
                TransferStatusUpdater.registerListener(id, statusListener);
                transferListener = listener;
                TransferStatusUpdater.registerListener(id, transferListener);
            }
        }
    }

    /**
     * Gets the transfer id of the record.
     *
     * @return The transfer id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the bucket name of the record.
     *
     * @return The bucket name of the record.
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * Gets the key of the record.
     *
     * @return The key of the record.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the total bytes to transfer.
     *
     * @return The total bytes of the transfer.
     */
    public long getBytesTotal() {
        return bytesTotal;
    }

    /**
     * Gets the absolute path of file to transfer.
     *
     * @return The absolute path of the file transferred.
     */
    public String getAbsoluteFilePath() {
        return filePath;
    }

    /**
     * Gets the bytes transferred currently.
     *
     * @return The bytes currently transferred.
     */
    public long getBytesTransferred() {
        return bytesTransferred;
    }

    /**
     * Gets the state of the transfer task.
     *
     * @return The current state of the transfer.
     */
    public TransferState getState() {
        return transferState;
    }

    /**
     * Cleans the transfer listener.
     */
    public void cleanTransferListener() {
        synchronized (this) {
            if (transferListener != null) {
                TransferStatusUpdater.unregisterListener(id, transferListener);
                transferListener = null;
            }
            if (statusListener != null) {
                TransferStatusUpdater.unregisterListener(id, statusListener);
                statusListener = null;
            }
        }
    }

    /**
     * A listener that can update the {@link TransferObserver}.
     */
    private class TransferStatusListener implements TransferListener {

        @Override
        @SuppressWarnings("checkstyle:hiddenfield")
        public void onStateChanged(int id, TransferState state) {
            transferState = state;
        }

        @Override
        @SuppressWarnings("checkstyle:hiddenfield")
        public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
            TransferObserver.this.bytesTransferred = bytesCurrent;
            TransferObserver.this.bytesTotal = bytesTotal;
        }

        @Override
        @SuppressWarnings("checkstyle:hiddenfield")
        public void onError(int id, Exception ex) {
            // do nothing
        }
    }

    @Override
    public String toString() {
        return "TransferObserver{" +
                "id=" + id +
                ", bucket='" + bucket + '\'' +
                ", key='" + key + '\'' +
                ", bytesTotal=" + bytesTotal +
                ", bytesTransferred=" + bytesTransferred +
                ", transferState=" + transferState +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}


