package ${rootPackage}.generatedSources.${typeLower}.web.mapper;

import ${package}.${type};
import ${rootPackage}.generatedSources.${typeLower}.web.DTO.${type}DTO;
import com.github.antin502.core.web.mapper.CoreMapperImpl;
import org.springframework.stereotype.Component;

@Component
public class ${type}Mapper extends CoreMapperImpl<${type}DTO, ${type}> {}
