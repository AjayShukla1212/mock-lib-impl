package com.ajay.mocklibimpl.agent;

import com.ajay.mocklibimpl.agent.constants.MockConstants;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.sql.ResultSet;

public class MockAgent {
    public static void premain(String args, Instrumentation ins) {
        String mode = System.getenv(MockConstants.HT_MODE);

        // Mocking Rest Template
        interceptMethod("org.springframework.web.client", MockConstants.GET_OBJECT, mode, MockConstants.MOCK_DATA , ins);

        // Mocking Save Method of JDBC
        // interceptMethod(MockConstants.POST_REPO, MockConstants.SAVE, mode, new PostMock(), ins);

        //Mocking JDBC
       // interceptMethod("org.springframework.data.jpa.repository", "executeQuery", mode, new ExecuteQueryInterceptor(), ins);
        interceptMethod("org.springframework.data.jpa.repository", "save", mode, new SaveInterceptor(), ins);
    }

    private static void interceptMethod(String type, String method, String mode, Object returnValue, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith(type))
                .transform((builder, typeDescription, classLoader, javaModule) -> {
                    if (MockConstants.REPLAY_MODE.equalsIgnoreCase(mode)) {
                        return builder.method(ElementMatchers.named(method))
                                .intercept(FixedValue.value(returnValue));
                    }
                    return builder;
                }).installOn(inst);
    }

//    private static class PostMock {
//        public Object save(Object post) {
//            return post;
//        }
//    }

//    @Advice.OnMethodExit
//    public static void onExit(@Advice.Return(readOnly = false) ResultSet resultSet) {
//        // Provide mock data here
//        resultSet = new MockResultSet();
//    }
//
//    public static class ExecuteQueryInterceptor {
//        @Advice.OnMethodEnter
//        public static void onEnter(@Advice.Argument(0) String sql) {
//            System.out.println("Intercepted query: " + sql);
//        }
//    }

    public static class SaveInterceptor {
        @Advice.OnMethodEnter
        public static void onEnter() {
            System.out.println("Intercepted JPA save method");
        }

        @Advice.OnMethodExit
        public static void onExit(@Advice.Return(readOnly = false) Object returnValue) {
            // Provide hardcoded mock response here
            returnValue = new MockEntity(); // Replace with your mock entity
        }
    }

    @Advice.OnMethodExit
    public static void onExit(@Advice.Return(readOnly = false) ResultSet resultSet) {
        // Provide mock data here
        resultSet = new MockResultSet();
    }

}
