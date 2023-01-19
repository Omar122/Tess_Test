import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class main2 {

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		float RATIO = (float) 2;
		int KERNEL_SIZE = 3;
		Size BLUR_SIZE = new Size(3, 3);
		// int lowThresh = 80;

		Mat srcBlur = new Mat();
		Mat detectedEdges = new Mat();

		Path currentRelativePath = Paths.get("","pics");
		 
		String s = currentRelativePath.toAbsolutePath().toString();
		 
		System.out.println(currentRelativePath.getFileName());
		//System.out.println(currentRelativePath.relativize(currentRelativePath.getRoot()));
		// Creating a File object for directory
		File directoryPath = new File(s);
		// List of all files and directories
		String contents[] = directoryPath.list();
		
		
		for (int i = 0; i < contents.length; i++) {

			// Reading the Image from the file/ directory
			
			// Storing the image in a Matrix object
			// of Mat type
			Mat src = Imgcodecs.imread(currentRelativePath.getFileName()+"/"+contents[i]);

			// New matrix to store the final image
			// where the input image is supposed to be written
			Mat dst = new Mat();

			// Scaling the Image using Resize function
			//Imgproc.resize(src, dst, new Size(0, 0), 2, 2, Imgproc.INTER_CUBIC);

			// Writing the image from src to destination

			Imgproc.blur(src, srcBlur, BLUR_SIZE);
			Imgproc.Canny(srcBlur, detectedEdges, 10, 10 * RATIO, KERNEL_SIZE, true);

			dst = new Mat(src.size(), CvType.CV_8UC3, Scalar.all(0));
			src.copyTo(dst, detectedEdges);
			String name=contents[i].substring(1,contents[i].lastIndexOf('.')-1);
			System.out.println("name "+name);
			Imgcodecs.imwrite(name+"_edge.jpg", dst);
			 
			System.out.println("Image Processed "+i);
		}
		
 

	}

}
