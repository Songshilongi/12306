package com.songshilong.framework.starter.idempotent.core;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Songshilong
 */
@RequiredArgsConstructor
public class RepeatConsumptionException extends RuntimeException {
    @Getter
    private final Boolean error;
}
