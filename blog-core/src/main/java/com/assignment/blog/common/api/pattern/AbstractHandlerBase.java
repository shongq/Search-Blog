package com.assignment.blog.common.api.pattern;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHandlerBase<P extends IHandlerRequest,R extends IHandlerResponse> {
    @Setter
    private AbstractHandlerBase<P,R> nextHandler;

    private boolean hasNext(){
        return nextHandler != null;
    }

    public R process( P param, R result){
        long timeElapsed = 0L;

        Long start = System.currentTimeMillis();
        result = resolve(param, result);
        timeElapsed = System.currentTimeMillis() - start;

        if(result.isSuccess() && hasNext()){
            log.info(String.format("[Chain] (%d) Chain is good, %s success to resolve. Elapsed Time: %d", getHandlerOrder(), this.getClass().getSimpleName(), timeElapsed));
            return nextHandler.process(param, result);
        }else if(!result.isSuccess()){
            log.warn(String.format("[Chain] (%d) Chain is broken, %s fail to resolve. Elapsed Time: %d", getHandlerOrder(), this.getClass().getSimpleName(), timeElapsed));
        }else if(!hasNext()){
            log.info(String.format("[Chain] (%d) Chain is finished, %s is last handler. Elapsed Time: %d", getHandlerOrder(), this.getClass().getSimpleName(), timeElapsed));
        }
        return result;
    }

    protected abstract R resolve(P param, R result);

    private Integer getHandlerOrder(){
        HandlerOrder handlerOrder = this.getClass().getAnnotation(HandlerOrder.class);
        if(handlerOrder != null){
            return handlerOrder.order();
        }
        return null;
    }
}