package com.github.antin502.core.model;

import com.github.antin502.core.reflection.Invoker;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 09.06.2019
 * Time: 17:22
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class CoreModel implements Invoker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false, columnDefinition = "Datetime default now()", nullable = false)
    @CreatedDate
    private LocalDateTime createdOn;

    @Column(columnDefinition = "Datetime default now()", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateOn;

    @Version
    private int version;

    @NotNull
    @Column(columnDefinition = "Bit default 0")
    private boolean deleted = false;

}

