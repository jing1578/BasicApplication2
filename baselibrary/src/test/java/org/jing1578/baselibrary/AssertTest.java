package org.jing1578.baselibrary;

import org.junit.Rule;
import org.junit.Test;


import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2020/3/5 15:20.
 */
public class AssertTest {

    @Rule
    public MyRule myRule = new MyRule();

    @Test
    public void testAssertThat2() throws Exception{
        assertThat("2",nullValue());
    }

    @Test
    public void testAssertThat3() throws Exception{
        assertThat("Hello world!",both(startsWith("Hello")).and(endsWith("world!")));
    }

    @Test
    public void testAssertThat4() throws Exception{
        assertThat("8613588888888",new IsMobilePhoneMatcher());
    }
}
