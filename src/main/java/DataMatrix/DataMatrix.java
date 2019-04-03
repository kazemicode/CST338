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

    // one arg constructor; takes a BarcodeImage
    // calls default constructor to get all pivs
    // initialized, then scans the BarcodeImage
    // to clean it up and compute the actual height
    // and actual width
    // text can be determined with a call to
    // translateImageToText
    public DataMatrix(BarcodeImage image)
    {
        this();
        scan(image); // should set the actualHeight, actualWidth, and a cleaned up version of image
    }

    // one arg constructor; takes a String
    // calls default constructor to get all pivs
    // initialized then sets the text piv to the
    // param text via readText
    // BarcodeImage can be created with call to
    // generateImageFromText
    public DataMatrix(String text)
    {
        this();
        readText(text);
    }

    /* ACCESSORS */

    // Calculates the width of the signal by counting how many
    // "true" values are in each column of the bottom limit line
    // the signal includes the left limit line, the data, and the
    // right open border
    public int calculateSignalWidth()
    {
        int total = 0;
        for(int col = 0; col < image.MAX_WIDTH; col++)
        {
            if(image.getPixel(image.MAX_HEIGHT - 1, col))
            {
                total++;
            }
        }
        return total;
    }

    // Calculates the height of the signal by counting how many
    // "true" values are in each row of the left limit line
    // the signal includes the top open border, the data, and the
    // bottom limit line
    public int calculateSignalHeight()
    {
        int total = 0;
        for(int row = 0; row < image.MAX_HEIGHT; row++)
        {
            if(image.getPixel(row, 0))
            {
                total++;
            }
        }
        return total;
    }

    /* Accessors */

    // returns the BarcodeImage
    public BarcodeImage getImage()
    {
        return image;
    }

    // returns actual width
    public int getActualWidth()
    {
        return actualWidth;
    }

    // returns actual height
    public int getActualHeight()
    {
        return actualHeight;
    }

    // returns text
    public String getText()
    {
        return text;
    }

    /* Implementation of BarcodeIO methods */

    // scan
    // Resets the text in case we are reusing a DataMatrix that had existing text
    // clones the BarcodeImage to keep private data members private
    // cleans the image up by shifting it to the bottom left of the matrix
    // computes actual width and height
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

    // mutator for text
    public boolean readText(String text)
    {
        if(text == null || text == "")
        {
            return false;
        }
        this.text = text;
        return true;
    }

    // Writes data to existing BarcodeImage piv
    // encodes each character in text as a binary representation
    // where each column represents a column
    // and each row represents each place of an 8-bit
    // binary number. True indicates a 1 and False indicates a 0
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

    // Sends each column index of data to helper method
    // colToChar to decode the binary representation of
    // each character in the image.
    // Concatenates each returned character to a final String
    // which is stored in the text piv
    public boolean translateImageToText()
    {
        if(image != null)
        {

            for(int col = 1; col < actualWidth - 1; col++)
            {
                text += colToChar(col);
            }
            return true;

        }
        return false;
    }

    // prints the text piv value
    public void displayTextToConsole()
    {
        System.out.println(text);
    }

    // Prints out a formatted version of the image
    // enclosed in a border
    public void displayImageToConsole()
    {
        printHorizontalLine(); // top border
        for(int row = image.getHeight() - actualHeight; row < image.getHeight(); row++)
        {
            for(int col = 0; col < actualWidth + 2; col++)
            {
                if(col == 0)
                {
                    System.out.print("|"); // left border
                }
                else if(col == actualWidth + 1)
                {
                    System.out.print("|"); // right border
                }
                else if(image.getPixel(row, col-1))
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
        printHorizontalLine(); // bottom border
    }

    // Uses helper method moveImageToLowerLeft
    // to move the contents of the image to
    // the lower left corner of the matrix
    private void cleanImage()
    {
        moveImageToLowerLeft();
    }

    /* Helper methods for cleanImage */

    // Shifts everything to the bottom left of the
    // matrix using helper methods shiftImageDown
    // and shiftImageLeft after determining the
    // bottom left of the signal is found and
    // determining the row and col offset
    private void moveImageToLowerLeft()
    {
        boolean atBottomLeft = false;
        for(int row = image.getHeight() - 1; row >= 0; row--) // traverse bottom up
        {
            for(int col = 0; col < image.MAX_WIDTH; col++) // left to right
            {
                if(image.getPixel(row, col)) // true
                {
                    atBottomLeft = true; // found the bottom left edge
                    shiftImageDown((image.MAX_HEIGHT - 1) - row);
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

    // Uses row offset determined in moveImageToLowerLeft
    // to shift data down towards against bottom limit line
    private void shiftImageDown(int offset)
    {
        if (offset != 0) // if offset is 0, we don't need to do anything
        {
            for (int row = image.MAX_HEIGHT - 1; row >= 0; row--) // bottom up
            {
                for (int col = 0; col < image.MAX_WIDTH; col++) // left to right
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

    // Uses low offset determined in moveImageToLowerLeft
    // to shift data to the left against left limit line
    private void shiftImageLeft(int offset)
    {

        if(offset != 0) // if offset is 0, we don't need to do anything
        {
            for (int row = image.MAX_HEIGHT - 1; row >= 0; row--)
            {
                for (int col = 0; col < image.MAX_WIDTH; col++)
                {
                    if(col + offset < image.MAX_WIDTH)
                    {
                        // shifts values to the left by col offset
                        image.setPixel(row, col, image.getPixel(row, col + offset));
                    }
                }
            }
        }
    }

    /* Helper method for translateImageToText */

    // Given a col index in the matrix,
    // reads the boolean value in each row in that col
    // Each col represents a different place value
    // of an 8-bit number, meaning that the bottom row
    // is 2^0 and the top row is 2^7
    // Adds all the true values together to
    // determine the decimal value of the character
    // encoded in that col and returns it to translateImageToText
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

    // given an ascii represented by a decimal (base 10) number,
    // divides the number iteratively by 2, taking the remainder (a 1 or a 0).
    // If it is a 1, we make the row and col we are currently on TRUE
    // If its not (0), we keep the row and col FALSE
    // Does this repeatedly for each col value passed by generateImageFromText
    private void charToCol(int ascii, int col)
    {
        for(int row = image.getHeight() - 2; row > image.getHeight() - actualHeight; row--)
        {

            if(ascii % 2 == 1)
            {
                image.setPixel(row, col, true);
            }
            ascii /= 2;
        }
    }

    // Reverts all values in the 2D boolean array to false
    private void resetImage()
    {
        for(int row = 0; row < image.MAX_HEIGHT; row++)
        {
            for(int col = 0; col < image.MAX_WIDTH; col++)
            {
                image.setPixel(row, col, false);
            }
        }
    }

    // Generates the limit lines on the left and bottom
    // of image with two helper methods
    private void writeLimitLines()
    {
        writeLeftLimit();
        writeBottomLimit();
    }

    // Writes left limit line to image
    // all rows in the leftmost col (0) for the actual height
    // of the signal get changed to true
    private void writeLeftLimit()
    {
        for(int row = image.MAX_HEIGHT - 1; row > image.MAX_HEIGHT - actualHeight; row--)
        {
            image.setPixel(row, 0, true); // set leftmost column to true for all data rows
        }
    }

    // Writes bottom limit line to image
    // all columns in the bottom row for the actual width
    // of the signal get changed to true
    private void writeBottomLimit()
    {
        for(int col = 0; col < actualWidth; col++)
        {
            image.setPixel(image.MAX_HEIGHT - 1, col, true);
        }
    }

    // Writes the top and right open borders to the image
    // using two helper methods
    private void writeOpenBorders()
    {
        writeTopBorder();
        writeRightBorder();
    }

    // Writes top open border to image
    // Oscillates between true and false based on even/odd cols
    // Even cols are true and odd cols are false
    private void writeTopBorder()
    {
        for(int col = 0; col < actualWidth + 2; col++)
        {
            // set even cols to even in top row
            image.setPixel(image.MAX_HEIGHT - actualHeight, col, col%2==0);
        }

    }

    // Writes right open border to image
    // Oscillates between true and false based on even/odd cols
    // Even cols are false and odd cols are true
    private void writeRightBorder()
    {
        for(int row = image.MAX_HEIGHT - 1; row > image.MAX_HEIGHT - actualHeight; row--)
        {
            // set even rows to true in last col
            image.setPixel(row, actualWidth-1, row%2==1);
        }

    }

    /* Helper method for displayImageToConsole */
    // Prints a horizontal line as wide as the signal plus two to
    // compensate for printed left and right borders
    private void printHorizontalLine()
    {
        for(int col = 0; col < actualWidth + 2; col++)
        {
            System.out.print("-");
        }
        System.out.println();
    }

}