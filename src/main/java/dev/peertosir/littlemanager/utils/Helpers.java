package dev.peertosir.littlemanager.utils;

import dev.peertosir.littlemanager.model.BaseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Helpers {
    public static<T extends BaseEntity> Long calcEntityId(List<T> tList, T t) {
        Optional<Long> maxId = tList.stream().map(T::getId).max(Long::compare);
        return maxId.isPresent() ? maxId.get() + 1 : 0;
    }

    public static <T> T[] arrayConcat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
