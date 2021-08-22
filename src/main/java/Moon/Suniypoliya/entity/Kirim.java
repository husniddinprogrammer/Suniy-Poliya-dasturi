package Moon.Suniypoliya.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Kirim {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Poliya poliya;
    @ManyToOne
    private TulovTuri tulovTuri;
    private Integer summa;
    private String izoh;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate sana;
    @ManyToOne
    private TulovQilish tulovQilish;
    private String status;

    public Kirim() {
    }

    public Kirim(Long id, User user, Poliya poliya, TulovTuri tulovTuri, Integer summa, String izoh, LocalDate sana, TulovQilish tulovQilish, String status) {
        this.id = id;
        this.user = user;
        this.poliya = poliya;
        this.tulovTuri = tulovTuri;
        this.summa = summa;
        this.izoh = izoh;
        this.sana = sana;
        this.tulovQilish = tulovQilish;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Poliya getPoliya() {
        return poliya;
    }

    public void setPoliya(Poliya poliya) {
        this.poliya = poliya;
    }

    public TulovTuri getTulovTuri() {
        return tulovTuri;
    }

    public void setTulovTuri(TulovTuri tulovTuri) {
        this.tulovTuri = tulovTuri;
    }

    public Integer getSumma() {
        return summa;
    }

    public void setSumma(Integer summa) {
        this.summa = summa;
    }

    public String getIzoh() {
        return izoh;
    }

    public void setIzoh(String izoh) {
        this.izoh = izoh;
    }

    public LocalDate getSana() {
        return sana;
    }

    public void setSana(LocalDate sana) {
        this.sana = sana;
    }

    public TulovQilish getTulovQilish() {
        return tulovQilish;
    }

    public void setTulovQilish(TulovQilish tulovQilish) {
        this.tulovQilish = tulovQilish;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
