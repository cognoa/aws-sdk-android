package com.amazonaws.kinesisvideo.internal.mediasource;

import androidx.annotation.NonNull;
import com.amazonaws.kinesisvideo.common.exception.KinesisVideoException;
import com.amazonaws.kinesisvideo.common.preconditions.Preconditions;
import com.amazonaws.kinesisvideo.internal.client.mediasource.MediaSourceSink;
import com.amazonaws.kinesisvideo.producer.KinesisVideoFrame;

/**
 * This class demonstrates a sample OnStreamDataAvailable handler.
 */
public class DefaultOnStreamDataAvailable implements OnStreamDataAvailable {
    final MediaSourceSink mediaSourceSink;

    public DefaultOnStreamDataAvailable(final MediaSourceSink mediaSourceSink) {
        this.mediaSourceSink = mediaSourceSink;
    }

    @Override
    public void onFrameDataAvailable(final KinesisVideoFrame frame) throws KinesisVideoException {
        Preconditions.checkNotNull(frame);
        // ignore frame of size 0
        if (frame.getSize() == 0) {
            throw new KinesisVideoException("Empty frame is provided in frame data available.");
        }

        mediaSourceSink.onFrame(frame);
    }

    @Override
    public void onFragmentMetadataAvailable(final String metadataName, final String metadataValue,
                                            final boolean persistent) throws KinesisVideoException {
        mediaSourceSink.onFragmentMetadata(metadataName, metadataValue, persistent);
    }
}
