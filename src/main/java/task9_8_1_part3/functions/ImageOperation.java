package task9_8_1_part3.functions;

import java.lang.reflect.InvocationTargetException;

public interface ImageOperation {
    float[] execute(float[] rgb) throws InvocationTargetException, IllegalAccessException;
}
