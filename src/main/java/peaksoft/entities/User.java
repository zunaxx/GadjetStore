package peaksoft.entities;

import peaksoft.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDate createdEt;
    private LocalDate updatedEt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Basket basket;

    @OneToMany(mappedBy = "user")
    private List<Favorite>favorites;

    @OneToMany(mappedBy = "user")
    private List<Comment>comments;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addComment(Comment comment) {
        if (comment != null){
            comments.add(comment);
            comment.setUser(this);
        }else {
          throw new RuntimeException("Comments is null!!!");
        }
    }

    public void addFavorite(Favorite favorite) {
        if (favorite != null){
            favorites.add(favorite);
            favorite.setUser(this);
        } else {
            throw new RuntimeException("Favorite is null!!!");
        }
    }
}
