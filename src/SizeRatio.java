public class SizeRatio {

    public final int originalImageWidth;
    public final int originalImageHeight;
    private final double ratio;

    private double size;
    private int width;
    private int height;


    public SizeRatio(int width, int height, double size) {
        originalImageWidth = width;
        originalImageHeight = height;
        ratio = (double) height /width;
        this.size = size;
        this.width = (int) size;
        this.height = (int)(ratio*size);
    }

    public void setSize(double size) {
        this.size = size;
        this.width = (int)size;
        this.height = (int)(ratio*size);
    }

    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }

}
