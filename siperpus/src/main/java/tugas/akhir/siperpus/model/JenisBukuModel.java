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
    private long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @OneToMany(mappedBy = "jenisBuku", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private List<BukuModel> listBuku;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
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