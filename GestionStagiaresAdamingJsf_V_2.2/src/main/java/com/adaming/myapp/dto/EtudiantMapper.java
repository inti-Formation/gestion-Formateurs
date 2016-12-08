package com.adaming.myapp.dto;

import javax.persistence.MappedSuperclass;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adaming.myapp.entities.Etudiant;

@Mapper
public interface EtudiantMapper {

	EtudiantMapper INSTANCE = Mappers.getMapper(EtudiantMapper.class);
	
	EtudiantDto etudiantToEtudiantDto(Etudiant etudiant);
}
