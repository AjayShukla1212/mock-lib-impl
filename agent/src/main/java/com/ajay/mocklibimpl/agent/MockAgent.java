package com.ajay.mocklibimpl.agent;

import com.ajay.mocklibimpl.agent.constants.MockConstants;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;

public class MockAgent {
//    public static void premain(String args, Instrumentation ins) {
//        String mode = System.getenv(MockConstants.HT_MODE);
//
//        // Mocking Rest Template
//        interceptMethod("org.springframework.web.client.RestTemplate", MockConstants.GET_OBJECT, mode, MockConstants.MOCK_DATA , ins);
//
//        // Mocking Save Method of JDBC
//        // interceptMethod(MockConstants.POST_REPO, MockConstants.SAVE, mode, new PostMock(), ins);
//
//        //Mocking JDBC
//        interceptMethod("org.springframework.jdbc.core.JdbcTemplate", "update", mode, new PostMock(), ins);
//    }
//
//    private static void interceptMethod(String type, String method, String mode, Object returnValue, Instrumentation inst) {
//        new AgentBuilder.Default()
//                .type(ElementMatchers.nameContains(type))
//                .transform((builder, typeDescription, classLoader, javaModule) -> {
//                    if (MockConstants.REPLAY_MODE.equalsIgnoreCase(mode)) {
//                        return builder.method(ElementMatchers.named(method))
//                                .intercept(FixedValue.value(returnValue));
//                    }
//                    return builder;
//                }).installOn(inst);
//    }
//
//    private static class PostMock {
//        public Object save(Object post) {
//            return post;
//        }
//    }



    public static void premain(String args, Instrumentation ins) {
        String mode = System.getenv(MockConstants.HT_MODE);

        // Mocking Rest Template
//        interceptMethod(mode, ins);

        // Mocking Save Method of JDBC
        // interceptMethod(MockConstants.POST_REPO, MockConstants.SAVE, mode, new PostMock(), ins);

        //Mocking JDBC
//        interceptMethod("org.springframework.jdbc.core.JdbcTemplate", "update", mode, new PostMock(), ins);

        System.out.println("MockingLibraryAgent loaded");
        interceptJdbcTemplateUpdate(mode, ins);
    }

//    private static void interceptMethod(String mode, Instrumentation inst) {
//        new AgentBuilder.Default()
//                .type(ElementMatchers.nameContains("org.springframework.web.client.RestTemplate"))
//                .transform((builder, typeDescription, classLoader, javaModule) -> {
//                    if (MockConstants.REPLAY_MODE.equalsIgnoreCase(mode)) {
//                        return builder.method(ElementMatchers.named(MockConstants.GET_OBJECT))
//                                .intercept(FixedValue.value(MockConstants.MOCK_DATA));
//                    }
//                    return builder;
//                }).installOn(inst);
//    }

    private static void interceptJdbcTemplateUpdate(String mode, Instrumentation inst) {
        new AgentBuilder.Default()
//                .with(new AgentBuilder.Listener() {
//                    @Override
//                    public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
//                        System.out.println("Discovered: " + typeName);
//                    }
//
//                    @Override
//                    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
//                        System.out.println("Transformed: " + typeDescription);
//                    }
//
//                    @Override
//                    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {
//                        System.out.println("Ignored: " + typeDescription);
//                    }
//
//                    @Override
//                    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
//                        System.err.println("Error: " + typeName);
//                        throwable.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
//                        System.out.println("Complete: " + typeName);
//                    }
//                })
                .type(ElementMatchers.named("org.springframework.jdbc.core.JdbcTemplate"))
                .transform((builder, typeDescription, classLoader, javaModule) -> {
                    if (MockConstants.REPLAY_MODE.equalsIgnoreCase(mode)) {
                        return builder.method(ElementMatchers.named("update")
                                .and(ElementMatchers.takesArguments(
                                       2
                                )))  // Second argument type)))
                                .intercept(Advice.to(PostMock.class));  // Mock return value
                    }
                    return builder;
                }).installOn(inst);
    }

    private static class PostMock {
        @Advice.OnMethodEnter
        public Object interceptUpdate(Object post) throws InstantiationException, IllegalAccessException {
            System.out.println("Mocking save method of PostRepo");
            Object result = post.getClass().newInstance();
            for (Field field : result.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase("id") && field.getType() == Long.class) {
                    field.set(result, 1L);
                } else if (field.getName().equalsIgnoreCase("name") && field.getType() == String.class) {
                    field.set(result, "Test Post 1");
                } else {
                    field.set(result, "This is a test post content.");
                }
            }
            System.out.println("Mocked result: " + result.toString());
            return result;
        }
    }
}
