package demo.test;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;
import org.openjdk.btrace.core.types.AnyType;


import static org.openjdk.btrace.core.BTraceUtils.*;
import static org.openjdk.btrace.core.BTraceUtils.Sys.Env.printEnv;

@BTrace  // 注明这是一个BTrace脚本
public class PrintArgSimple {

    static {
        printVmArguments();
        printProperties();
        printEnv();
    }

    // 指定需要拦截的方法
    @OnMethod(
            // 类的路径
            clazz = "demo.controller.CoffeeController",
            // 方法名
            method = "findAll",
            // 在什么时候进行拦截
            location = @Location(Kind.RETURN)
    )
    public static void anyRead(@ProbeClassName String pcn, // 被拦截的类名
                               @ProbeMethodName String pmn, // 被拦截的方法名
                               AnyType[] args, // 被拦截的方法的参数值
                               @Return AnyType result //被拦截的返回结果

    )  {
        // 打印数组
        BTraceUtils.printArray(args);
        // 打印行
        BTraceUtils.println("className: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println("result: " + result);
    }
}
