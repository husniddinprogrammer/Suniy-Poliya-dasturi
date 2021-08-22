package Moon.Suniypoliya.service.vm;

import Moon.Suniypoliya.configuration.Constants;
import Moon.Suniypoliya.entity.Lavozim;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

public class UserVM {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime qushilganVaqti;
    private Long aktiv;
    protected Set<Lavozim> lavozimlar;

    public UserVM() {
    }

    public UserVM(Long id, String username, String password, String name, String number, LocalDateTime qushilganVaqti, Long aktiv, Set<Lavozim> lavozimlar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.number = number;
        this.qushilganVaqti = qushilganVaqti;
        this.aktiv = aktiv;
        this.lavozimlar = lavozimlar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getQushilganVaqti() {
        return qushilganVaqti;
    }

    public void setQushilganVaqti(LocalDateTime qushilganVaqti) {
        this.qushilganVaqti = qushilganVaqti;
    }

    public Long getAktiv() {
        return aktiv;
    }

    public void setAktiv(Long aktiv) {
        this.aktiv = aktiv;
    }

    public Set<Lavozim> getLavozimlar() {
        return lavozimlar;
    }

    public void setLavozimlar(Set<Lavozim> lavozimlar) {
        this.lavozimlar = lavozimlar;
    }
}
