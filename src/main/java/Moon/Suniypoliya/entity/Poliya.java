package Moon.Suniypoliya.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "poliya")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Poliya {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nomi;
    private Integer narxi;
    @ManyToOne
    private User user;
    private String manzil;
    private Integer status;
    private String googleMap;
    private String koordinata;
    public Poliya(){};

    public Poliya(Long id, String nomi, Integer narxi, User user, String manzil, Integer status,String googleMap,String koordinata) {
        this.id = id;
        this.nomi = nomi;
        this.narxi = narxi;
        this.user = user;
        this.manzil = manzil;
        this.status = status;
        this.googleMap = googleMap;
        this.koordinata = koordinata;
    }

    public String getGoogleMap() {
        return googleMap;
    }

    public void setGoogleMap(String googleMap) {
        this.googleMap = googleMap;
    }

    public String getKoordinata() {
        return koordinata;
    }

    public void setKoordinata(String koordinata) {
        this.koordinata = koordinata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomi() {
        return nomi;
    }

    public void setNomi(String nomi) {
        this.nomi = nomi;
    }

    public Integer getNarxi() {
        return narxi;
    }

    public void setNarxi(Integer narxi) {
        this.narxi = narxi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getManzil() {
        return manzil;
    }

    public void setManzil(String manzil) {
        this.manzil = manzil;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
