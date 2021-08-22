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
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class TulovQilish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Integer plastik;
    private Integer naqd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime tulovVaqti;
    @ManyToOne
    private Buyurtma buyurtma;
    private Integer status;

    public TulovQilish() {
    }

    public TulovQilish(Long id, Integer plastik, Integer naqd, LocalDateTime tulovVaqti, Buyurtma buyurtma, Integer status) {
        this.id = id;
        this.plastik = plastik;
        this.naqd = naqd;
        this.tulovVaqti = tulovVaqti;
        this.buyurtma = buyurtma;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlastik() {
        return plastik;
    }

    public void setPlastik(Integer plastik) {
        this.plastik = plastik;
    }

    public Integer getNaqd() {
        return naqd;
    }

    public void setNaqd(Integer naqd) {
        this.naqd = naqd;
    }

    public LocalDateTime getTulovVaqti() {
        return tulovVaqti;
    }

    public void setTulovVaqti(LocalDateTime tulovVaqti) {
        this.tulovVaqti = tulovVaqti;
    }

    public Buyurtma getBuyurtma() {
        return buyurtma;
    }

    public void setBuyurtma(Buyurtma buyurtma) {
        this.buyurtma = buyurtma;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
