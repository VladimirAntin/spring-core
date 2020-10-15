package ${rootPackage}.coreImpl.${typeLower};

import ${package}.${type};
import ${rootPackage}.coreImpl.${typeLower}.${type}Repo;
import com.github.vladimirantin.core.service.CoreModelService;
import org.springframework.stereotype.Service;

@Service
public class ${type}Service extends CoreModelService<${type}Repo, ${type}> {
}
