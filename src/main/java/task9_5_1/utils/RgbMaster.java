package task9_5_1.utils;
// лямбда начинается на мин 10 43

//import Task2_1_10_1.task9_4_1.functions.ImageOperation;

import task9_5_1.functions.ImageOperation;

import java.awt.image.BufferedImage;

public class RgbMaster {

    private BufferedImage image;
    private int width;
    private int height;
    private boolean hasAlphaChannel;
    private int[] pixels;

    public RgbMaster(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        hasAlphaChannel = image.getAlphaRaster() != null;
        pixels = image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void changeImage(ImageOperation operation) throws Exception {
        for (int i = 0; i < pixels.length; i++) {
            float[] pixel = ImageUtils.rgbIntToArray(pixels[i]);
            float[] newPixel = operation.execute(pixel);
            pixels[i] = ImageUtils.arrayToRgbInt(newPixel);
        }
        image.setRGB(0, 0, width, height, pixels, 0, width);
    }
}
