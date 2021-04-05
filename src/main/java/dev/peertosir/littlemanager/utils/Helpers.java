package dev.peertosir.littlemanager.utils;

import dev.peertosir.littlemanager.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public class Helpers {
    public static<T extends BaseEntity> Long calcEntityId(List<T> tList, T t) {
        Optional<Long> maxId = tList.stream().map(T::getId).max(Long::compare);
        System.out.println("MaxId: " + (maxId.isPresent() ? maxId.get() : "null"));
        return maxId.isPresent() ? maxId.get() + 1 : 0;
    }
}
