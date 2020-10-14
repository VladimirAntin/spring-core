package ${rootPackage}.generatedSources.${typeLower};

import ${package}.${type};
import ${rootPackage}.generatedSources.${typeLower}.${type}DTO;
import com.github.vladimirantin.core.web.mapper.CoreMapperImpl;
import org.springframework.stereotype.Component;

@Component
public class ${type}Mapper extends CoreMapperImpl<${type}DTO, ${type}> {}
