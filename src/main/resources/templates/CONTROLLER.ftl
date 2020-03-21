package ${rootPackage}.generatedSources.${typeLower}.web.rest;

import ${package}.${type};
import ${rootPackage}.generatedSources.${typeLower}.service.${type}Service;
import com.github.antin502.core.web.rest.CoreRestController;
import ${rootPackage}.generatedSources.${typeLower}.web.DTO.${type}DTO;
import ${rootPackage}.generatedSources.${typeLower}.web.mapper.${type}Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${api}s")
public class ${type}Controller extends CoreRestController<${type}Service, ${type}Mapper, ${type}DTO, ${type}> {
}
