package Moon.Suniypoliya.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "buyurtma")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Buyurtma {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Mijoz mijoz;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate sana;
    private Integer status;
    @ManyToOne
    private Poliya poliya;
    private String boshlanishVaqti;
    private String tugashVaqti;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime buyurtmaBerilganVaqti;
    private Integer narxi;
    private String izoh;
    
    public Buyurtma(){}

    public Buyurtma(Long id, Mijoz mijoz, LocalDate sana, Integer status, Poliya poliya, String boshlanishVaqti, String tugashVaqti, LocalDateTime buyurtmaBerilganVaqti, Integer narxi, String izoh) {
        this.id = id;
        this.mijoz = mijoz;
        this.sana = sana;
        this.status = status;
        this.poliya = poliya;
        this.boshlanishVaqti = boshlanishVaqti;
        this.tugashVaqti = tugashVaqti;
        this.buyurtmaBerilganVaqti = buyurtmaBerilganVaqti;
        this.narxi = narxi;
        this.izoh = izoh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mijoz getMijoz() {
        return mijoz;
    }

    public void setMijoz(Mijoz mijoz) {
        this.mijoz = mijoz;
    }

    public LocalDate getSana() {
        return sana;
    }

    public void setSana(LocalDate sana) {
        this.sana = sana;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Poliya getPoliya() {
        return poliya;
    }

    public void setPoliya(Poliya poliya) {
        this.poliya = poliya;
    }

    public String getBoshlanishVaqti() {
        return boshlanishVaqti;
    }

    public void setBoshlanishVaqti(String boshlanishVaqti) {
        this.boshlanishVaqti = boshlanishVaqti;
    }

    public String getTugashVaqti() {
        return tugashVaqti;
    }

    public void setTugashVaqti(String tugashVaqti) {
        this.tugashVaqti = tugashVaqti;
    }

    public LocalDateTime getBuyurtmaBerilganVaqti() {
        return buyurtmaBerilganVaqti;
    }

    public void setBuyurtmaBerilganVaqti(LocalDateTime buyurtmaBerilganVaqti) {
        this.buyurtmaBerilganVaqti = buyurtmaBerilganVaqti;
    }

    public Integer getNarxi() {
        return narxi;
    }

    public void setNarxi(Integer narxi) {
        this.narxi = narxi;
    }

    public String getIzoh() {
        return izoh;
    }

    public void setIzoh(String izoh) {
        this.izoh = izoh;
    }
}
