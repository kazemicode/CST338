package DataMatrix;

/* Phase 3
 * DataMatrix: A pseudo DataMatrix data structure since it does not
 * contain any error correction or encoding. It does have the 2D array
 * format and bottom BLACK "spine" as well as an alternating right and
 * top BLACK-WHITE pattern.
 */
public class DataMatrix implements BarcodeIO
{
    public static final char BLACK_CHAR = '*'; // asterisk
    public static final char WHITE_CHAR = ' '; // space

    // private instance variables
    private BarcodeImage image;
    private String text;
    private int actualWidth;
    private int actualHeight;

    /* CONSTRUCTORS */

    // zero argument constructor
    public DataMatrix()
    {
        // default values
        image = new BarcodeImage();
        text = "";
        actualHeight = 0;
        actualWidth = 0;
    }

    public DataMatrix(BarcodeImage image)
    {
        this();
        scan(image); // should set the actualHeight, actualWidth, and a cleaned up version of image
    }

    public DataMatrix(String text)
    {
        this();
        readText(text);
    }

    /* Implementation of BarcodeIO methods */

    public boolean scan(BarcodeImage bc)
    {
        // this will call BarcodeImage.clone, cleanImage, and set actualWidth and actualHeight
        // clone call should be embedded within a try/catch block
        try
        {
            image = (BarcodeImage) bc.clone();
            cleanImage(); // to do
            // to do - compute actual width and height
        }
        catch(Exception e)
        {
            return false;
        }
        return true;

    }

    public boolean readText(String text)
    {
        if(text == null || text.trim() == "")
        {
            return false; // do we also need to check for empty string? or just spaces using trim?
        }
        this.text = text;
        return true;
    }

    public boolean generateImageFromText()
    {
        return false;
    }

    public boolean translateImageToText()
    {
        return false;
    }

    public void displayTextToConsole()
    {

    }

    public void displayImageToConsole()
    {

    }

    private void cleanImage()
    {
        boolean atBottomLeft = false;
        for(int row = image.getHeight() - 1; row >= 0; row--) // traverse bottom up
        {
            for(int col = 0; col < image.getWidth(); col++) // left to right
            {
               if(image.getPixel(row, col)) // true
               {
                   atBottomLeft = true; // found the edge where data begins
                   shiftImageDown((image.getHeight() - 1) - row);
                   shiftImageLeft(col);
                   break; // we're done
               }
            }

            if(atBottomLeft)
            {
                break; // break out of loop
            }

        }

    }

    /* Helper methods for cleanImage */
    private void moveImageToLowerLeft()
    {

    }

    private void shiftImageDown(int offset)
    {
        for(int row = image.getHeight() -1; row >= 0; row--) // bottom up
        {
            for(int col = 0; col < image.getWidth(); col++) // left to right
            {
                if(image.getPixel(row - offset, col))
                {
                    image.setPixel(row, col, true);
                }
                else
                {
                    image.setPixel(row, col, false);
                }
            }
        }

    }

    private void shiftImageLeft(int offset)
    {
        for(int row = image.getHeight() - 1; row >= 0; row--)
        {
            for(int col = 0; col < image.getWidth(); col++)
            {

            }
        }

    }
}
