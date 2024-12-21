package com.songshilong.framework.starter.idempotent.core;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Songshilong
 */
@Getter
@RequiredArgsConstructor
public class RepeatConsumptionException extends RuntimeException {
    private final Boolean error;
}
