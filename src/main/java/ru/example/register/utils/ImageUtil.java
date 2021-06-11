package ru.example.register.utils;

import java.util.Base64;

/**
 * @author Maxim Komov
 * Util for convert photo to byte array
 */

public class ImageUtil {

    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}