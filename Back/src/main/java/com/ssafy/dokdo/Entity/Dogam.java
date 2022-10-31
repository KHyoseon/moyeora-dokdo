package com.ssafy.dokdo.Entity;



import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "Dogam")
public class Dogam {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dogam_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void setUser(User user){
        this.user = user;
        // 무한루프에 빠지지 않도록 체크
        if(!user.getDogamList().contains(this)){
            user.getDogamList().add(this);
        }
    }

    private String domain;
    private String mongo_id;
}
