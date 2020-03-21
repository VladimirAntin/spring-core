package com.github.antin502.core.web.mapper;

import com.github.antin502.core.model.CoreModel;
import com.github.antin502.core.web.DTO.CoreDTO;
import com.google.common.reflect.TypeToken;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 27.02.2020
 * Time: 22:29
 */
public abstract class CoreMapperImpl<D extends CoreDTO, E extends CoreModel> {

    private ModelMapper modelMapper = new ModelMapper();

    public E toEntity(D dto) {
        if(dto == null) {
            return null;
        }
        return modelMapper.map(dto, (Type) getEntityType());
    }

    public D toDto(E entity) {
        if(entity == null) {
            return null;
        }
        return modelMapper.map(entity, (Type) getDTOType());
    }

    public List<E> toEntity(Collection<D> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<D> toDto(Collection<E> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<D> toMinimalDTO(Collection<E> entityList) {
        return entityList.stream().map(this::toMinimalDTO).collect(Collectors.toList());
    }

    public D toMinimalDTO(E entity) {
        if(entity == null) {
            return null;
        }
        return modelMapper.map(entity, (Type) getDTOType());
    }


    private Class<? super D> getDTOType() {
        final TypeToken<D> typeToken = new TypeToken<D>(getClass()) { };
        return typeToken.getRawType();
    }

    private Class<? super E> getEntityType() {
        final TypeToken<E> typeToken = new TypeToken<E>(getClass()) { };
        return typeToken.getRawType();
    }
}
