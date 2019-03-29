package DataMatrix;

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
        image = new BarcodeImage();
        text = "";
        actualHeight = 0;
        actualWidth = 0;
    }

    public DataMatrix(BarcodeImage image)
    {
        this.image = image;
        text = ""; // to do -- use scan to determine text
        actualHeight = image.getHeight();
        actualWidth = image.getWidth();

    }

    public DataMatrix(String text)
    {
        this.text = text;
        // to do

    }

    /* Implementation of BarcodeIO methods */

    public boolean scan(BarcodeImage bc)
    {
        return false;
    }

    public boolean readText(String text)
    {
        return false;
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

    }

    /* Helper methods for cleanImage */
    private void moveImageToLowerLeft()
    {

    }

    private void shiftImageDown(int offset)
    {

    }

    private void shiftImageLeft(int offset)
    {

    }
}
