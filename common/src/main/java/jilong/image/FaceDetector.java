package jilong.image;

/**
 * @author jilong.qiu
 * @date 2017/3/20.
 */
public class FaceDetector {

//    public static void main(String[] args) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.out.println("\nRunning FaceDetector");
//
//        CascadeClassifier faceDetector = new CascadeClassifier();
//        faceDetector.load("d:\\temp\\haarcascades\\");
//
//        Mat image = Imgcodecs.imread("d:\\temp\\345.jpg");
//
//        MatOfRect faceDetections = new MatOfRect();
//        faceDetector.detectMultiScale(image, faceDetections);
//
//        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
//
//        for (Rect rect : faceDetections.toArray()) {
//            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0));
//        }
//
//        String filename = "ouput.png";
//        System.out.println(String.format("Writing %s", filename));
//        Imgcodecs.imwrite(filename, image);
//    }

}