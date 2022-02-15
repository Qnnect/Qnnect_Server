package com.qnnect.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(name= "user_id")
    private UUID id;

    @Column()
    private String nickName;

    @Column()
    private String profilePicture;

    @Column()
    private boolean pushEnabled;

    @Column()
    private EProviderType providerType;

    @Column()
    private ERole role;
}
