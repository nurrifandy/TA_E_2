package tugas.akhir.siperpus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnggotaDetailModel{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("is-pengurus")
    private Boolean isPengurus;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }
    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }
    /**
     * @return the isPengurus
     */
    public Boolean getIsPengurus() {
        return isPengurus;
    }
    /**
     * @param isPengurus the isPengurus to set
     */
    public void setIsPengurus(Boolean isPengurus) {
        this.isPengurus = isPengurus;
    }
}