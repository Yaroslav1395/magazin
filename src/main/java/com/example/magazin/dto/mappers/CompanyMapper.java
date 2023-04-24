package com.example.magazin.dto.mappers;

import com.example.magazin.dto.company.CompanyDto;
import com.example.magazin.entity.company.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CompanyDto companyDto);
    CompanyDto toDto(Company company);
}
