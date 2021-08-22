package Moon.Suniypoliya.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class XabarTuri {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nomi;
    private Integer status;

    public XabarTuri() {
    }

    public XabarTuri(Long id, String nomi, Integer status) {
        this.id = id;
        this.nomi = nomi;
        this.status = status;
    }
    public XabarTuri(String nomi, Integer status) {
        this.nomi = nomi;
        this.status = status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
