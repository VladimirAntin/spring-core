package ${rootPackage}.coreImpl.${typeLower};

import ${package}.${type};
import ${rootPackage}.coreImpl.${typeLower}.${type}Service;
import com.github.vladimirantin.core.web.rest.ExtendedRestController;
import ${DTO.canonicalName()};
import ${rootPackage}.coreImpl.${typeLower}.${type}Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${api}s")
public class ${type}Controller extends ExtendedRestController<${type}Service, ${type}Mapper, ${DTO.simpleName()}, ${type}> {
}
