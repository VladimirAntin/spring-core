package ${rootPackage}.generatedSources.${typeLower};

import ${package}.${type};
import ${rootPackage}.generatedSources.${typeLower}.${type}Service;
import CoreRestController;
import ${rootPackage}.generatedSources.${typeLower}.${type}DTO;
import ${rootPackage}.generatedSources.${typeLower}.${type}Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${api}s")
public class ${type}Controller extends CoreRestController<${type}Service, ${type}Mapper, ${type}DTO, ${type}> {
}
