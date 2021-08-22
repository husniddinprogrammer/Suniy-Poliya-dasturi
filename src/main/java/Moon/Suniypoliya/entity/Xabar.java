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
public class Xabar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Buyurtma buyurtma;
    private String qisqaXabar;
    @ManyToOne
    private User user;
    @ManyToOne
    private XabarTuri xabarTuri;
    private String tuliqXabar1;
    private String tuliqXabar2;
    private String tuliqXabar3;
    private String tuliqXabar4;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime xabarVaqti;

    public Xabar() {
    }

    public Xabar(Buyurtma buyurtma, String qisqaXabar, User user, XabarTuri xabarTuri, String tuliqXabar1, String tuliqXabar2, String tuliqXabar3, String tuliqXabar4, Integer status, LocalDateTime xabarVaqti) {
        this.buyurtma = buyurtma;
        this.qisqaXabar = qisqaXabar;
        this.user = user;
        this.xabarTuri = xabarTuri;
        this.tuliqXabar1 = tuliqXabar1;
        this.tuliqXabar2 = tuliqXabar2;
        this.tuliqXabar3 = tuliqXabar3;
        this.tuliqXabar4 = tuliqXabar4;
        this.status = status;
        this.xabarVaqti = xabarVaqti;
    }

    public Xabar(Long id, Buyurtma buyurtma, String qisqaXabar, User user, XabarTuri xabarTuri, String tuliqXabar1, String tuliqXabar2, String tuliqXabar3, String tuliqXabar4, Integer status, LocalDateTime xabarVaqti) {
        this.id = id;
        this.buyurtma = buyurtma;
        this.qisqaXabar = qisqaXabar;
        this.user = user;
        this.xabarTuri = xabarTuri;
        this.tuliqXabar1 = tuliqXabar1;
        this.tuliqXabar2 = tuliqXabar2;
        this.tuliqXabar3 = tuliqXabar3;
        this.tuliqXabar4 = tuliqXabar4;
        this.status = status;
        this.xabarVaqti = xabarVaqti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyurtma getBuyurtma() {
        return buyurtma;
    }

    public void setBuyurtma(Buyurtma buyurtma) {
        this.buyurtma = buyurtma;
    }

    public String getQisqaXabar() {
        return qisqaXabar;
    }

    public void setQisqaXabar(String qisqaXabar) {
        this.qisqaXabar = qisqaXabar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public XabarTuri getXabarTuri() {
        return xabarTuri;
    }

    public void setXabarTuri(XabarTuri xabarTuri) {
        this.xabarTuri = xabarTuri;
    }

    public String getTuliqXabar1() {
        return tuliqXabar1;
    }

    public void setTuliqXabar1(String tuliqXabar1) {
        this.tuliqXabar1 = tuliqXabar1;
    }

    public String getTuliqXabar2() {
        return tuliqXabar2;
    }

    public void setTuliqXabar2(String tuliqXabar2) {
        this.tuliqXabar2 = tuliqXabar2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getXabarVaqti() {
        return xabarVaqti;
    }

    public void setXabarVaqti(LocalDateTime xabarVaqti) {
        this.xabarVaqti = xabarVaqti;
    }

    public String getTuliqXabar3() {
        return tuliqXabar3;
    }

    public void setTuliqXabar3(String tuliqXabar3) {
        this.tuliqXabar3 = tuliqXabar3;
    }

    public String getTuliqXabar4() {
        return tuliqXabar4;
    }

    public void setTuliqXabar4(String tuliqXabar4) {
        this.tuliqXabar4 = tuliqXabar4;
    }
}
