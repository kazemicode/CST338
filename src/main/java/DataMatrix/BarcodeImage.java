package DataMatrix;

public class BarcodeImage implements Cloneable
{
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 65;
    // private instance variables
    private boolean[][] imageData; // stores image -- false is white true is black

    // Zero argument constructor
    public BarcodeImage()
    {
        imageData = new boolean[MAX_HEIGHT][MAX_WIDTH]; //row major
    }

    public boolean[][] BarcodeImage(String[] strData)
    {

    }

    public boolean getPixel(int row, int col)
    {
        //For the getPixel(), you can use the return
        // value for both the actual data and also the
        // error condition -- so that we don't "create a scene"
        // if there is an error; we just return false.
        return imageData[row][col];
    }

    public boolean setPixel(int row, int col, boolean value)
    {
        imageData[row][col] = value;
    }

    @Override
    protected Object clone()
    {

    }

    private boolean checkSize(String[] data)
    {

    }
}
