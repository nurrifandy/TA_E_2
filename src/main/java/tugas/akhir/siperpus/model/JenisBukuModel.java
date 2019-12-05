package tugas.akhir.siperpus.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jenisBuku")
public class JenisBukuModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Long id;

    public Long getId() {
        return id;
    }

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @OneToMany(mappedBy = "jenisBuku", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private List<BukuModel> listBuku;

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */


    /**
     * @param id the id to set
     */


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
     * @return the listBuku
     */
    public List<BukuModel> getListBuku() {
        return listBuku;
    }

    /**
     * @param listBuku the listBuku to set
     */
    public void setListBuku(List<BukuModel> listBuku) {
        this.listBuku = listBuku;
    }

}