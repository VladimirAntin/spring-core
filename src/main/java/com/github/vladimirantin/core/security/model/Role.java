package com.github.vladimirantin.core.security.model;

import com.github.vladimirantin.core.GlobalVariables;
import com.github.vladimirantin.core.model.CoreModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Entity
@Where(clause = GlobalVariables.WHERE_CLAUSE)
@Getter
@Setter
@EqualsAndHashCode
public class Role extends CoreModel {

    @NotNull
    @Column(unique = true)
    @Size(min = 4, max = 25) //ROLE...
    private String name;

/*
    @ManyToMany(mappedBy = "roles")
    private Set<CoreUser> users = new HashSet<>();
*/

}
