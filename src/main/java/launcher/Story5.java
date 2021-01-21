package launcher;

import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

public class Story5 {
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    Java2DFrameConverter converter = new Java2DFrameConverter();

    public void grabber() throws FrameGrabber.Exception {
        int captureWidth = 1280;
        int captureHeight = 480;
        grabber.setImageWidth(captureWidth);
        grabber.setImageHeight(captureHeight);
        try {
            System.out.println("Webcam will start...");
            grabber.start();

        } catch (FrameGrabber.Exception e) {
            System.out.println("Couldn't start frameGrabber..." + e);
            e.printStackTrace();
        }
    }
}
