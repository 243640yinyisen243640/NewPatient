package com.vice.bloodpressure.modules.zxing.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by xiao on 2017/8/3.
 */

public class QRCodeMatrix {
        private static final String TAG= QRCodeMatrix.class.getSimpleName();

    public static Bitmap createQRCode(String url, int widthAndHeight) throws WriterException {
        Log.i(TAG, "createQRCode==url==" + url + "==width==" + widthAndHeight);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, "0");
        BitMatrix matrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, hints);
        int width = widthAndHeight;
        int height = widthAndHeight;
        // 二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }else {
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
