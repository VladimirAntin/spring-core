package ${rootPackage}.coreImpl.${typeLower};

import ${package}.${type};
import com.github.vladimirantin.core.repo.CoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${type}Repo extends CoreRepository<${type}> {
}
