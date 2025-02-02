package tugas.akhir.siperpus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class AnggotaDetailModel{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("is_pengurus")
    private Boolean isPengurus;

    @JsonProperty("nia")
    private String nia;

    @JsonProperty("total_simpanan")
    private int totalSimpanan;

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

    /**
     * @return the nia
     */
    public String getNia() {
        return nia;
    }

    /**
     * @param nia the nia to set
     */
    public void setNia(String nia) {
        this.nia = nia;
    }

    /**
     * @return the totalSimpanan
     */
    public int getTotalSimpanan() {
        return totalSimpanan;
    }

    /**
     * @param totalSimpanan the totalSimpanan to set
     */
    public void setTotalSimpanan(int totalSimpanan) {
        this.totalSimpanan = totalSimpanan;
    }
}