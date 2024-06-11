package task9_8_1_part4.functions;

import java.lang.reflect.InvocationTargetException;

public interface ImageOperation {
    float[] execute(float[] rgb) throws InvocationTargetException, IllegalAccessException;
}
