package DataMatrix;

public class DataMatrix implements BarcodeIO
{
    public static final char BLACK_CHAR = '*'; // asterisk
    public static final char WHITE_CHAR = ' '; // space

    // private instance variables
    private BarcodeImage image;
    private String text;
    private int actualWWidth;
    private int actualHeight;

    /* CONSTRUCTORS */

    // zero argument consttructor
    public DataMatrix()
    {
        image = new BarcodeImage();
        text = "";
        actualHeight = 0;
        actualHeight = 0;
    }

    public DataMatrix(BarcodeImage image)
    {

    }

    public DataMatrix(String text)
    {

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
}
