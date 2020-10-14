package ${rootPackage}.generatedSources.${typeLower};

import com.github.vladimirantin.core.web.DTO.CoreDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public class ${type}DTO extends CoreDTO {
<#list attributes as i>
    private ${i.getType().getCanonicalName()} ${i.getName()};
</#list>

}
