package org.jing1578.baselibrary;

import org.jing1578.baselibrary.utils.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Administrator on 2020/3/5 15:08.
 */
@RunWith(Parameterized.class)
public class DateFormatTest {

    private String time;

    public DateFormatTest(String time) {
        this.time = time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers(){
        return Arrays.asList(new String[]{
                "2017-10-15",
                "2017-10-15 16:00:02",
                "2017年10月15日 16时00分02秒",
        });
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
      DateUtil.dateToStamp(time);
    }
}
