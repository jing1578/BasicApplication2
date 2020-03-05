package org.jing1578.baselibrary;


import org.jing1578.baselibrary.utils.DateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Administrator on 2020/3/5 14:28.
 */
public class DateUtilTest {

    private String time = "2017-10-15 16:00:02";

    private long timeStamp = 1508054402000L;

    private Date mDate;

    @Before
    public void setUp() throws Exception{
        System.out.println("测试开始!");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void  tearDown() throws Exception{
        System.out.println("测试结束!");
    }

    @Test
    public void stampToDateTest() throws Exception{
        Assert.assertEquals("预期时间", DateUtil.stampToDate(timeStamp));
    }

    @Test
    public void dateToStampTest() throws Exception{
        Assert.assertNotEquals(4,DateUtil.dateToStamp(time));
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp("2017-03-03");
    }
}
