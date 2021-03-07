package org.java.smartrestaurant.util.entity;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityUtil<E, D> {
    D createDtoFromEntity(E objectEntity);

    default List<D> createDtoListFromEntityList(List<E> objectEntityList) {
        return objectEntityList.stream().map(this::createDtoFromEntity).collect(Collectors.toList());
    }

    E createEntityFromDto(D objectDto);

    E updateEntityFromDto(E objectEntity, D objectDto);

    E createNewEntityFromAnother(E objectEntity);

}
