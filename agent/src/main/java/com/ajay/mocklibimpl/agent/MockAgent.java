package com.ajay.mocklibimpl.agent;

import com.ajay.mocklibimpl.agent.constants.MockConstants;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class MockAgent {
    public static void premain(String args, Instrumentation ins) {
        String mode = System.getenv(MockConstants.HT_MODE);

        // Mocking Rest Template
        interceptMethod(MockConstants.REST_TEMPLATE, MockConstants.GET_OBJECT, mode, MockConstants.MOCK_DATA , ins);

        // Mocking Save Method of JDBC
        // interceptMethod(MockConstants.POST_REPO, MockConstants.SAVE, mode, new PostMock(), ins);

        //Mocking JDBC
        interceptMethod("org.springframework.jdbc.core.JdbcTemplate", "update", mode, new PostMock(), ins);
    }

    private static void interceptMethod(String type, String method, String mode, Object returnValue, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameContains(type))
                .transform((builder, typeDescription, classLoader, javaModule) -> {
                    if (MockConstants.REPLAY_MODE.equalsIgnoreCase(mode)) {
                        return builder.method(ElementMatchers.named(method))
                                .intercept(FixedValue.value(returnValue));
                    }
                    return builder;
                }).installOn(inst);
    }

    private static class PostMock {
        public Object save(Object post) {
            return post;
        }
    }
}
