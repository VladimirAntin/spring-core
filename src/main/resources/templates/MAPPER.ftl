package ${rootPackage}.generatedSources.${typeLower};

import ${package}.${type};
import ${DTO.canonicalName()};
import com.github.vladimirantin.core.web.mapper.CoreMapperImpl;
import org.springframework.stereotype.Component;

@Component
public class ${type}Mapper extends CoreMapperImpl<${DTO.simpleName()}, ${type}> {}
