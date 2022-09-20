package com.assignment.blog.common.api.pattern;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractHandlerChain<P extends IHandlerRequest,R extends IHandlerResponse> {
    private final AbstractHandlerBase<P,R> firstHandler;

    public AbstractHandlerChain(AbstractHandlerBase<P,R>... handlerChains) {
        List<AbstractHandlerBase<P,R>> handlerList = Arrays.stream(handlerChains)
                .sorted(Comparator.comparingInt(o -> o.getClass().getAnnotation(HandlerOrder.class).order()))
                .collect(Collectors.toList());

        firstHandler = handlerList.get(0);

        for(int i = 0 ; i < handlerList.size() -1 ; i++) {
            handlerList.get(i).setNextHandler(handlerList.get(i+1));
        }
    }

    public R start(P myParam, R result) {
        return (R) firstHandler.process(myParam, result);
    }
}
