package com.tcmain.zxinglibrary;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Administrator on 2019/6/13 16:38.
 */
public class GenerateCode {
    final String TAG = "GenerateCode";
    final int QR_WIDTH = 180;  //二维码的宽度
    final int QR_HEIGHT = 180;  //二维码的高度
    /**
     * 生成二维码图片 如果二维码已存在则不会生成
     *
     * @param text
     */
    public String generateCode(String text, String qrName,String parentPath) {
        String path = "";
        try {
            File file = new File( parentPath,qrName + ".jpg");
            if (file.exists()) {
                path = file.getPath();
            }
            // 需要引入core包
            QRCodeWriter writer = new QRCodeWriter();

            Log.i(TAG, "生成的文本：" + text);
            if (text == null || "".equals(text) || text.length() < 1) {
                return path = "";
            }

            // 把输入的文本转为二维码
            BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
                    QR_WIDTH, QR_HEIGHT);

            System.out.println("w:" + martix.getWidth() + "h:"
                    + martix.getHeight());

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_8888);

            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            if (saveBitmap(bitmap, file.getPath())) {
                path = file.getPath();
            }
        } catch (WriterException e) {
            path = "";
        }
        return path;
    }

    /**
     * 保存图片
     *
     * @param b    要保存的Bitmap
     * @param path 保存的图片路径
     * @return true为保存成功否则为保存失败
     */
    public boolean saveBitmap(Bitmap b, String path) {
        boolean isSave = false;
        File file = new File(path);
        FileOutputStream outStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            outStream = new FileOutputStream(file);
            b.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            isSave = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSave;
    }
}
