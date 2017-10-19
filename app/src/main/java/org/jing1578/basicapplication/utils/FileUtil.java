package org.jing1578.basicapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jing1578 on 2017/10/12 09:14.
 */

public class FileUtil {

    public static String setFileName(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String fileName=sdf.format(date);
        return fileName;
    }
}
