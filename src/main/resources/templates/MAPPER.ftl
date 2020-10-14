package ${rootPackage}.generatedSources.${typeLower};

import ${package}.${type};
import ${rootPackage}.generatedSources.${typeLower}.${type}DTO;
import CoreMapperImpl;
import org.springframework.stereotype.Component;

@Component
public class ${type}Mapper extends CoreMapperImpl<${type}DTO, ${type}> {}
