package tugas.akhir.siperpus.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table (name = "pengadaanBuku")
public class PengadaanBukuModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "judul", nullable = false)
    private String judul;

    @NotNull
    @Size(max = 200)
    @Column(name = "pengarang", nullable = false)
    private String pengarang;

    @NotNull
    @Size(max = 200)
    @Column(name = "penerbit", nullable = false)
    private String penerbit;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private int jumlah;

    @NotNull
    @Column(name = "harga", nullable = false)
    private int harga;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;


//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "uuid_user", referencedColumnName = "uuid", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private UserModel user;
//
//    public UserModel getUser() {
//        return user;
//    }
//
//    public void setUser(UserModel user) {
//        this.user = user;
//    }




    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
