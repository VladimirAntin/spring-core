package com.github.vladimirantin.core.security.model;

import com.github.vladimirantin.core.GlobalVariables;
import com.github.vladimirantin.core.model.CoreModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Where(clause = GlobalVariables.WHERE_CLAUSE)
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class CoreUser extends CoreModel {

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private boolean active;

/*
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @BatchSize(size = 20)
    private Set<Role> roles = new HashSet<>();
*/


}
