import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

    public static void main(String[] args) {
        
        //// Hide / change / add to the testing code below, as needed.
        
        // Tests the reading and printing of an image:  
        Color[][] tinypic = read("tinypic.ppm");
        print(tinypic);

        // Creates an image which will be the result of various 
        // image processing operations:
        Color[][] image;

        // Tests the horizontal flipping of an image:
        image = flippedHorizontally(tinypic);
        System.out.println();
        print(image);
        
        //// Write here whatever code you need in order to test your work.
        //// You can continue using the image array.
    }

    /** Returns a 2D array of Color values, representing the image data
     * stored in the given PPM file. */
    public static Color[][] read(String fileName) {
        In in = new In(fileName);
        // Reads the file header, ignoring the first and the third lines.
        in.readString();
        int numCols = in.readInt();
        int numRows = in.readInt();
        in.readInt();
        // Creates the image array
        Color[][] image = new Color[numRows][numCols];
        // Reads the RGB values from the file into the image array. 
        // For each pixel (i,j), reads 3 values from the file,
        // creates from the 3 colors a new Color object, and 
        // makes pixel (i,j) refer to that object.
        //// Replace the following statement with your code.
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                int red = in.readInt();
                int green = in.readInt();
                int blue = in.readInt();
                image[i][j] = new Color(red, green, blue);
            }
        }

        return image;
    }

    // Prints the RGB values of a given color.
    private static void print(Color c) {
        System.out.print("(");
        System.out.printf("%3s,", c.getRed());   // Prints the red component
        System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
    }

    // Prints the pixels of the given image.
    // Each pixel is printed as a triplet of (r,g,b) values.
    // This function is used for debugging purposes.
    // For example, to check that some image processing function works correctly,
    // we can apply the function and then use this function to print the resulting image.
    private static void print(Color[][] image) {
        //// Replace this comment with your code
        //// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
        for (int i=0; i<image.length; i++) {
            for (int j=0; j<image[i].length; j++) {
                System.out.print("(" + image[i][j].getRed() + ", " 
                               + image[i][j].getGreen() + ", " 
                               + image[i][j].getBlue() + ") ");
                System.out.println();
            }
        }
    }
    
    /**
     * Returns an image which is the horizontally flipped version of the given image. 
     */
    public static Color[][] flippedHorizontally(Color[][] image) {
        //// Replace the following statement with your code
        int rows = image.length;
        int columns = image[0].length;
        Color[][] flippedHorizontallyImage = new Color[rows][columns];

        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                flippedHorizontallyImage[i][j] = image[i][columns-j-1];
            }
        }
        
        return flippedHorizontallyImage;
    }
    
    /**
     * Returns an image which is the vertically flipped version of the given image. 
     */
    public static Color[][] flippedVertically(Color[][] image){
        //// Replace the following statement with your code
        int rows = image.length;
        int columns = image[0].length;
        Color[][] flippedVerticallyImage = new Color[rows][columns];

        for (int i=0; i<rows; i++) {
            flippedVerticallyImage[i] = image[rows-i-1];
        }
        
        return flippedVerticallyImage;
    }
    
    
    // Computes the luminance of the RGB values of the given pixel, using the formula 
    // lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
    // the three values r = lum, g = lum, b = lum.
    private static Color luminance(Color pixel) {
        //// Replace the following statement with your code
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int lum = (int)(0.299 * r + 0.587 * g + 0.114 * b);
        return new Color(lum, lum, lum);
    }
    
    /**
     * Returns an image which is the grayscaled version of the given image.
     */
    public static Color[][] grayScaled(Color[][] image) {
        //// Replace the following statement with your code
        int rows = image.length;
        int columns = image[0].length;
        Color[][] gsImage = new Color[rows][columns];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                gsImage[i][j] = luminance(image[i][j]);
            }

        }

        return gsImage;
    }   
    
    /**
     * Returns an image which is the scaled version of the given image. 
     * The image is scaled (resized) to have the given width and height.
     */
    public static Color[][] scaled(Color[][] image, int width, int height) {
        //// Replace the following statement with your code
        int originalHeight = image.length;
        int originalWidth = image[0].length;

        Color[][] scaledImage = new Color[height][width];

        double scaleHeight = (double)originalHeight / height;
        double scaleWidth = (double)originalWidth / width;

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                int originalX = (int) (j * scaleWidth);
                int originalY = (int) (i * scaleHeight);

                scaledImage[i][j] = image[originalY][originalX];
            }
        }
        
        
        return scaledImage;
    }
    
    /**
     * Computes and returns a blended color which is a linear combination of the two given
     * colors. Each r, g, b, value v in the returned color is calculated using the formula 
     * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
     * values in the two input color.
     */
    public static Color blend(Color c1, Color c2, double alpha) {
        //// Replace the following statement with your code
        int r1 = c1.getRed();
        int g1 = c1.getGreen();
        int b1 = c1.getBlue();

        int r2 = c2.getRed();
        int g2 = c2.getGreen();
        int b2 = c2.getBlue();

        int blendedRed = (int)(alpha * r1 + (1 - alpha) * r2);
        int blendedGreen = (int)(alpha * g1 + (1 - alpha) * g2);
        int blendedBlue = (int)(alpha * b1 + (1 - alpha) * b2);

        blendedRed = Math.min(255, Math.max(0, blendedRed));
        blendedGreen = Math.min(255, Math.max(0, blendedGreen));
        blendedBlue = Math.min(255, Math.max(0, blendedBlue));

        return new Color(blendedRed, blendedGreen, blendedBlue);
    }
    
    /**
     * Cosntructs and returns an image which is the blending of the two given images.
     * The blended image is the linear combination of (alpha) part of the first image
     * and (1 - alpha) part the second image.
     * The two images must have the same dimensions.
     */
    public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
        //// Replace the following statement with your code
        int rows = image1.length;
        int columns = image1[0].length;
        Color[][] blendedImage = new Color[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                blendedImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
            }
        }
        return blendedImage;
    }

    /**
     * Morphs the source image into the target image, gradually, in n steps.
     * Animates the morphing process by displaying the morphed image in each step.
     * Before starting the process, scales the target image to the dimensions
     * of the source image.
     */
    public static void morph(Color[][] source, Color[][] target, int n) {
        //// Replace this comment with your code
        int rows = source.length;
        int columns = source[0].length;
        if (target.length != rows && target[0].length != columns) {
            target = scaled(target, columns, rows);
        }

        for (int i=0; i<=n; i++) {
            double alpha = (double) (n - i) / n;
            Color[][] blendedImage = blend(source, target, alpha);
            Runigram.display(blendedImage);
            StdDraw.pause(500);
        }


    }
    
    /** Creates a canvas for the given image. */
    public static void setCanvas(Color[][] image) {
        StdDraw.setTitle("Runigram 2023");
        int height = image.length;
        int width = image[0].length;
        StdDraw.setCanvasSize(height, width);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
        // the StdDraw.show function is called.
        StdDraw.enableDoubleBuffering();
    }

    /** Displays the given image on the current canvas. */
    public static void display(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Sets the pen color to the pixel color
                StdDraw.setPenColor( image[i][j].getRed(),
                                     image[i][j].getGreen(),
                                     image[i][j].getBlue() );
                // Draws the pixel as a filled square of size 1
                StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
            }
        }
        StdDraw.show();
    }
}