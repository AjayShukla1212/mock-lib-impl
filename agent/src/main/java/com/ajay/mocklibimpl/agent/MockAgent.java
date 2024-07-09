package com.ajay.mocklibimpl.agent;

import com.ajay.mocklibimpl.agent.constants.MockConstants;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class MockAgent {
    public static void premain(String args, Instrumentation ins) {
        String mode = System.getenv(MockConstants.HT_MODE);

        // Mocking Rest Template
        interceptMethod(MockConstants.REST_TEMPLATE, MockConstants.GET_OBJECT, mode, MockConstants.MOCK_DATA , ins);

        System.out.println("JPA DB Interception");

        interceptDatabase(ins, mode);
    }

    private static void interceptDatabase(Instrumentation inst, String mode) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameContains("SimpleJpaRepository"))
                .transform((builder, typeDescription, classLoader, javaModule) -> {
                    if (MockConstants.REPLAY_MODE.equalsIgnoreCase(mode)) {
                        return builder.method(ElementMatchers.named("save"))
                                .intercept(Advice.to(MockDBAdvice.class));
                    }
                    return builder;
                }).installOn(inst);
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
}
