package ${rootPackage}.generatedSources.${typeLower};

import ${package}.${type};
import ${rootPackage}.generatedSources.${typeLower}.${type}Repo;
import com.github.antin502.core.service.CoreModelService;
import org.springframework.stereotype.Service;

@Service
public class ${type}Service extends CoreModelService<${type}Repo, ${type}> {
}
