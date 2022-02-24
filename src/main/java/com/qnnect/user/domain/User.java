package com.qnnect.user.domain;

import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.ingredients.domain.UserIngredients;
import com.qnnect.questions.domain.QuestionUserMade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private String socialId;

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

    @OneToMany(mappedBy="user")
    private List<UserDrinkSelected> userDrinkSelectedList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserIngredients> userIngredientsList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<QuestionUserMade> questionUserMadeList = new ArrayList<>();


    @Builder
    public User(String socialId, String profilePicture) {
        this.socialId = socialId;
        this.profilePicture = profilePicture;
    }

    @Builder
    public User(String socialId) {
        this.socialId = socialId;
    }

}
