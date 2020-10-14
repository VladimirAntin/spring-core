package com.github.vladimirantin.core.model;

import com.github.vladimirantin.core.reflection.Invoker;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    @Column(updatable = false, columnDefinition = "Datetime not null default now()", nullable = false)
    private LocalDateTime createdOn;

    @Column(columnDefinition = "Datetime default now()", nullable = false)
    private LocalDateTime updateOn;

    @Version
    private int version;

    @NotNull
    @Column(columnDefinition = "Bit default 0")
    private boolean deleted = false;


    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
        this.updateOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateOn = LocalDateTime.now();
    }

}

