package DataMatrix;

public interface BarcodeIO
{
    /**
     * scan
     *
     * Accepts some image represented as a BarcodeImage object
     * and stores a copy of this image
     *
     * @param  BarcodeImage bc  An image represented as a BardcodeImage object
     * @return boolean
     */
    public boolean scan(BarcodeImage bc);

    /**
     * readText
     *
     * Accepts a text string to be eventually encoded to an image
     *
     * @param  String text
     * @return boolean
     */
    public boolean readText(String text);

    /**
     * generateImageFromText
     *
     * Looks at the internal image stored in the implementing class
     * Produces companion BarcodeImage or image of particular format
     *
     * @return boolean
     */
    public boolean generateImageFromText();

    /**
     * translateImageToText
     *
     * Looks at the internal image stored in the implementing class,
     * and produces a companion text string, internally
     *
     * @return boolean
     */
    public boolean translateImageToText();

    /**
     * displayTextToConsole
     *
     * prints out the text string to the console
     *
     * @return void
     */
    public void displayTextToConsole();

    /**
     * displayImageToConsole
     *
     * prints out the image to the console
     *
     * @return void
     */
    public void displayImageToConsole();
}
