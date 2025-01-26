package com.DiagramToDb.LMDToDB.Model.Entity;

import com.DiagramToDb.LMDToDB.Model.Dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities= Collections.singleton(new SimpleGrantedAuthority("USER"));
    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private  boolean enabled=true;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DatabaseEntity> dbs= new ArrayList<>();
    public static UserEntity toEntity(UserDto u){
        return UserEntity.builder()
                .id(u.getId())
                .username(u.getUsername())
                .password(u.getPassword())
                .email(u.getEmail())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .authorities(Collections.singleton(new SimpleGrantedAuthority("USER")))
                .build();

    }
}
