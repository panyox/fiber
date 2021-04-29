package com.fiber.common.utils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author panyox
 */
public class ListUtil {

    public static <T> Consumer<T> listWithIndex(BiConsumer<T, Integer> consumer) {
        class Obj {
            int i;
        }
        Obj obj = new Obj();
        return t -> {
            int index = obj.i++;
            consumer.accept(t, index);
        };
    }
}
