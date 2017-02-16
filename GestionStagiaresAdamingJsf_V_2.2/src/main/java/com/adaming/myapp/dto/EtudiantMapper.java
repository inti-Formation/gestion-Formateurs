package com.adaming.myapp.dto;

import javax.persistence.MappedSuperclass;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.adaming.myapp.entities.Etudiant;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@Mapper
public interface EtudiantMapper {

	EtudiantMapper INSTANCE = Mappers.getMapper(EtudiantMapper.class);
	
	EtudiantDto etudiantToEtudiantDto(Etudiant etudiant);
}
