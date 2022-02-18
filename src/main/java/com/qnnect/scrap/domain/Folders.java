package com.qnnect.scrap.domain;

import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Folders {
    @Id
    @Column(name = "folder_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String folderName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
