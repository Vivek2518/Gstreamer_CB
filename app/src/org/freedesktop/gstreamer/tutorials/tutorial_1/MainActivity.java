package org.freedesktop.gstreamer.tutorials.tutorial_1;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.freedesktop.gstreamer.GStreamer;

public class MainActivity extends Activity {
    private native void nativeInit();  // Initialize GStreamer in native code
    private native void nativeFinalize();  // Finalize GStreamer in native code
    private native void nativePlay();  // Play the pipeline
    private native void nativePause(); // Pause the pipeline

    private String pipeline = "udpsrc port=5600 ! application/x-rtp, payload=96 ! rtph264depay ! h264parse ! avdec_h264 ! videoconvert ! autovideosink";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GStreamer.init(this);
        } catch (Exception e) {
            Log.e("GStreamer", "Could not initialize GStreamer", e);
            finish();
            return;
        }

        nativeInit();  // Initialize the native GStreamer pipeline
    }

    @Override
    protected void onDestroy() {
        nativeFinalize();  // Clean up resources
        super.onDestroy();
 }
}








