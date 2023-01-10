package com.tfm.tfmbackend.mapper;

import com.tfm.tfmbackend.dto.LogDTO;
import com.tfm.tfmbackend.entity.Log;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LogMapper {

    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    @Mappings({
            @Mapping(target = "logId", ignore = true)
    })
    Log toEntity(LogDTO logDTO);

    LogDTO toDto(Log log);

    List<Log> toEntityList(List<LogDTO> logDTOList);

    List<LogDTO> toDtoList(List<Log> logList);

    @AfterMapping
    default void setEntityId(LogDTO logDTO, @MappingTarget Log log) {
        if(Objects.nonNull(logDTO.getLogId()))
            log.setLogId(logDTO.getLogId());
    }

}
