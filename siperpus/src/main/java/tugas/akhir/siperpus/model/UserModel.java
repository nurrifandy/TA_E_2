package tugas.akhir.siperpus.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user")
public class UserModel implements Serializable{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @JsonIgnore
    private String uuid;

    @NotNull
    @Size(max = 200)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idRole", updatable = false, insertable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RoleModel role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonIgnore
    private List<PengadaanBukuModel> listPengadaan;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonIgnore
    private List<PeminjamanBukuModel> listPeminjaman;

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    /**
     * @return the listPengadaan
     */
    public List<PengadaanBukuModel> getListPengadaan() {
        return listPengadaan;
    }

    /**
     * @param listPengadaan the listPengadaan to set
     */
    public void setListPengadaan(List<PengadaanBukuModel> listPengadaan) {
        this.listPengadaan = listPengadaan;
    }

    /**
     * @return the listPeminjaman
     */
    public List<PeminjamanBukuModel> getListPeminjaman() {
        return listPeminjaman;
    }

    /**
     * @param listPeminjaman the listPeminjaman to set
     */
    public void setListPeminjaman(List<PeminjamanBukuModel> listPeminjaman) {
        this.listPeminjaman = listPeminjaman;
    }

}