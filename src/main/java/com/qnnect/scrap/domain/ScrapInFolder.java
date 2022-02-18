package com.qnnect.scrap.domain;

import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ScrapInFolder {

    @Id
    @Column(name = "scrap_in_folder_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folders folder;

    @ManyToOne
    @JoinColumn(name = "scrap_id")
    private Scraps scraps;
}
