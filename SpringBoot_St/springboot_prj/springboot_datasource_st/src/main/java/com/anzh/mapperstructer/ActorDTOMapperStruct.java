package com.anzh.mapperstructer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActorDTOMapperStruct {
    ActorDTOMapperStruct ACTOR_DTO_MAPPER_STRUCT_INSTANCE = Mappers.getMapper(ActorDTOMapperStruct.class);
    
    
}
