package org.jing1578.basicapplication.save;

import android.content.Context;
import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by jing1578 on 2016/12/16.
 * 内部存储,所有的文件都是默认存储到/data/data/<packagename>/files/ 目录下
 * 用的很少
 */

public class InnerFileSaveUtil {

    /**
     * 将字符串保存到文件
     * @param context
     */
    public void save(Context context,String data,@NonNull String filename) {
//        String data = "Data to save";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
//            out = context.openFileOutput("data", Context.MODE_PRIVATE);
            out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用BufferReader进行一行行地读取
     * @param context
     * @param filename
     * @return
     */
    public String load(Context context,String filename) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
//            in = context.openFileInput("data");
            in = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
