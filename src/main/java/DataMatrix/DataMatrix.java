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

    /* ACCESSORS */

    public int calculateSignalWidth()
    {
        int total = 0;
        for(int col = 0; col < image.getWidth(); col++)
        {
            if(image.getPixel(image.getHeight() - 1, col))
            {
                total++;
            }
        }
        return total;
    }

    public int calculateSignalHeight()
    {
        int total = 0;
        for(int row = 0; row < image.getHeight(); row++)
        {
            if(image.getPixel(row, 0))
            {
                total++;
            }
        }
        return total;
    }

    /* Implementation of BarcodeIO methods */

    public boolean scan(BarcodeImage bc)
    {
        text = ""; // reset text
        // this will call BarcodeImage.clone, cleanImage, and set actualWidth and actualHeight
        // clone call should be embedded within a try/catch block
        try
        {
            image = (BarcodeImage) bc.clone();

        }
        catch(Exception e)
        {
            return false;
        }
        cleanImage(); // shifts image to bottom left
        actualWidth = calculateSignalWidth();
        actualHeight = calculateSignalHeight();
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
        if (text == null || text.length() > image.MAX_WIDTH)
        {
            return false;
        }
        resetImage(); // reset in case we have old data in image
        actualWidth = text.length() + 2; // extra to account for limit and border
        actualHeight = 10; // 8 bits and limit and border
        writeLimitLines();
        writeOpenBorders();

        int ascii = 0; // int representation of a character in text
        for(int col = 0; col < text.length(); col++)
        {
            ascii = (int)text.charAt(col);
            charToCol(ascii, col + 1); // col is shifted over 1 after limit line
        }
        return true;
    }

    public boolean translateImageToText()
    {
        if(image != null)
        {
            for(int col = 1; col < actualWidth - 2; col++)
            {
                text += colToChar(col);
            }
            return true;
        }
        return false;
    }

    public void displayTextToConsole()
    {
        System.out.println(text);

    }

    public void displayImageToConsole()
    {
        printHorizontalLine();
        for(int row = image.getHeight() - actualHeight; row < image.getHeight(); row++)
        {
            for(int col = 0; col < actualWidth + 2; col++)
            {
                if(col == 0)
                {
                    System.out.print("|");
                }
                if(col == actualWidth + 1)
                {
                    System.out.print("|");
                }
                else if(image.getPixel(row, col))
                {
                    System.out.print(BLACK_CHAR);
                }
                else
                {
                    System.out.print(WHITE_CHAR);
                }
            }
            System.out.println();
        }
        printHorizontalLine();
    }

    private void cleanImage()
    {
        moveImageToLowerLeft();
    }

    /* Helper methods for cleanImage */
    private void moveImageToLowerLeft()
    {
        boolean atBottomLeft = false;
        for(int row = image.getHeight() - 1; row >= 0; row--) // traverse bottom up
        {
            for(int col = 0; col < image.getWidth(); col++) // left to right
            {
                if(image.getPixel(row, col)) // true
                {
                    atBottomLeft = true; // found the bottom left edge
                    shiftImageLeft(col);
                    shiftImageDown((image.getHeight() - 1) - row);
                    break; // we're done
                }
            }

            if(atBottomLeft)
            {
                break; // break out of loop
            }

        }

    }

    private void shiftImageDown(int offset)
    {
        if (offset != 0) // if offset is 0, we don't need to do anything
        {
            for (int row = image.getHeight() - 1; row >= 0; row--) // bottom up
            {
                for (int col = 0; col < image.getWidth(); col++) // left to right
                {
                    if(row - offset >= 0)
                    {
                        // shifts down values by row offset
                        image.setPixel(row, col, image.getPixel(row - offset, col));
                    }
                }
            }
        }
    }

    private void shiftImageLeft(int offset)
    {

        if(offset != 0) // if offset is 0, we don't need to do anything
        {
            for (int row = image.getHeight() - 1; row >= 0; row--)
            {
                for (int col = 0; col < image.getWidth(); col++)
                {
                    if(col + offset < image.getWidth())
                    {
                        // shifts values to the left by col offset
                        image.setPixel(row, col, image.getPixel(row, col + offset));
                    }
                }
            }
        }
    }

    /* Helper method for translateImageToText */
    private char colToChar(int col)
    {
        char result = 0;
        int exponent = 0;
        for(int row = image.MAX_HEIGHT - 2; row > image.MAX_HEIGHT - actualHeight; row--)
        {
            if(image.getPixel(row, col))
            {
                result += Math.pow(2, exponent);
            }
            exponent++;
        }
        return result;
    }

    /* Helper methods for generateImageFromText */

    private void charToCol(int ascii, int col)
    {
        for(int row = image.getHeight() - 2; row >= image.getHeight() - actualHeight; row--)
        {

            if(ascii % 2 == 1)
            {
                image.setPixel(row, col, true);
            }
            ascii /= 2;
        }
    }

    private void resetImage()
    {
        for(int row = 0; row < image.getHeight(); row++)
        {
            for(int col = 0; col < image.getWidth(); col++)
            {
                image.setPixel(row, col, false);
            }
        }
    }

    private void writeLimitLines()
    {
        writeLeftLimit();
        writeBottomLimit();
    }

    private void writeLeftLimit()
    {
        for(int row = image.MAX_HEIGHT - 1; row >= image.MAX_HEIGHT - actualHeight; row--)
        {
            image.setPixel(row, 0, true); // set leftmost column to true for all data rows
        }
    }

    private void writeBottomLimit()
    {
        for(int col = 0; col < actualWidth + 2; col++)
        {
            image.setPixel(image.MAX_HEIGHT - 1, col, true);
        }
    }

    private void writeOpenBorders()
    {
        writeTopBorder();
        writeRightBorder();
    }

    private void writeTopBorder()
    {
        for(int col = 0; col < actualWidth + 2; col++)
        {
            // set even cols to even in top row
            image.setPixel(image.MAX_HEIGHT - actualHeight, col, col%2==0);
        }

    }

    private void writeRightBorder()
    {
        for(int row = image.MAX_HEIGHT - 1; row >= image.MAX_HEIGHT - actualHeight; row--)
        {
            // set even rows to true in last col
            image.setPixel(row, actualWidth, row%2==0);
        }

    }

    /* Helper method for displayImageToConsole */
    private void printHorizontalLine()
    {
        for(int col = 0; col < actualWidth + 2; col++)
        {
            System.out.print("-");
        }
        System.out.println();
    }


}

