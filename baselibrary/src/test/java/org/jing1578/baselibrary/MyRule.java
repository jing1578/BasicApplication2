package org.jing1578.baselibrary;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Administrator on 2020/3/5 15:35.
 */
public class MyRule implements TestRule {

    @Override
    public Statement apply(final Statement base, final Description description) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                // evaluate前执行方法相当于@Before
                String methodName = description.getMethodName(); // 获取测试方法的名字
                System.out.println(methodName + "测试开始！");

                base.evaluate();  // 运行的测试方法

                // evaluate后执行方法相当于@After
                System.out.println(methodName + "测试结束！");
            }
        };
    }

}
