package DataMatrix;

/*
 * PHASE 2
 * A BarcodeImage object describes a 2D dot-matrix pattern, or "image".
 * This class contains some methods for storing, modifying and
 * retrieving the data in a 2D image.
 *
 * The interpretation of the data is not part of this class.
 * Its job is only to manage the optical data.
 * It will implement Cloneable interface because it contains deep data.
 */

public class BarcodeImage implements Cloneable
{
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 65;
    // private instance variables
    private boolean[][] imageData; // stores image -- false is white true is black

    /* CONSTRUCTORS */

    // Zero argument constructor
    public BarcodeImage()
    {
        imageData = new boolean[MAX_HEIGHT][MAX_WIDTH]; //row major
    }

    // 1-arg constructor Converts strData to imageData
    public BarcodeImage(String[] strData)
    {
        this();
        int index = strData.length - 1;
        if(checkSize(strData))
        {
            // traverse imageData from bottom-left
            for (int row = imageData.length - 1; row >= 0; row--)
            {
                for (int col = 0; col < imageData[row].length; col++)
                {
                    if (index >= 0 && col < strData[index].length())
                    {
                        if (strData[index].charAt(col) == ' ')
                        {
                            setPixel(row, col, false);
                        } else
                            setPixel(row, col, true);
                    }
                }
                index--;
            }
        }

    }

    /* ACCESSORS */

    public boolean getPixel(int row, int col)
    {
        //For the getPixel(), you can use the return
        // value for both the actual data and also the
        // error condition -- so that we don't "create a scene"
        // if there is an error; we just return false.
        if(row >= MAX_HEIGHT || col > MAX_WIDTH)
        {
            return false;
        }
        return imageData[row][col];
    }

    public int getHeight()
    {
        return imageData.length;
    }

    public int getWidth()
    {
        return imageData[0].length;
    }

    /* MUTATORS */

    public boolean setPixel(int row, int col, boolean value)
    {
        if(row >= MAX_HEIGHT || col > MAX_WIDTH)
        {
            return false;
        }
        imageData[row][col] = value;
        return true;
    }

    /* Override Object.clone() */
    // to do: Determine if we should return an Object or BarcodeImage
    @Override
    protected Object clone()
    {
        try
        {
            BarcodeImage copy = (BarcodeImage)super.clone();
            copy.imageData = (boolean[][])imageData.clone();
            return copy;
        }
        catch(CloneNotSupportedException e)

        {
            return null;
        }
    }

    private boolean checkSize(String[] data)
    {
        // check if array is null or if the length surpasses
        // the height of our 2D array
        if(data == null || data.length > MAX_HEIGHT)
        {
            return false;
        }
        // checks to see if each entry in array is not null and
        // length of string does not surpass the width of our 2D array
        for(String text : data)
        {
            if(text == null || text.length() > MAX_WIDTH)
            {
                return false;
            }
        }
        return true; // data is okay if we get to this point!

    }

    public void displayImage()
    {
        for(int row = 0; row < imageData.length; row++)
        {
            for(int col = 0; col < imageData[row].length; col++)
            {

                if(getPixel(row, col))
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
