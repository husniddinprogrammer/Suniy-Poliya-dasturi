package Moon.Suniypoliya.security;

import Moon.Suniypoliya.entity.Lavozim;
import Moon.Suniypoliya.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * Spring security uchun yaratilgan maxsus user
 *
 * */
public class UserMaxsus implements UserDetails {
   private String username;
   private String password;
   private Collection<SimpleGrantedAuthority> authorities;

   public UserMaxsus(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
   }

   @Override
   public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLavozimlar(Set<Lavozim> lavozimlar){
        this.authorities = new HashSet<SimpleGrantedAuthority>();

        lavozimlar.forEach(l -> authorities.add(new SimpleGrantedAuthority("ROLE_"+l.name())));
    }
}
