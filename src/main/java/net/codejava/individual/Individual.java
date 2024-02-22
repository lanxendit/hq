package net.codejava.individual;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Individual {
    private Long id;
    private String is_trongdiem;
    private String cn_id;
    private String tencn;
    private String noicap;
    private LocalDate ngaycap;
    private LocalDate ngayhethan;
    private String gioitinh;
    private String dantoc;
    private String quoctich;
    private String hokhau_thuongtru;
    private String hokhau_tamtru;
    private String tieuchi;
    private String mahq;
    private String linhvuc;
    private String diachicssx;
    private String coquan_congtac;
    private String dang_congtac;
    private String quanhe;
    private String congchuc_id;
    private String congchuc_name;

    public Individual() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIs_trongdiem() {
        return is_trongdiem;
    }

    public void setIs_trongdiem(String is_trongdiem) {
        this.is_trongdiem = is_trongdiem;
    }

    public String getCn_id() {
        return cn_id;
    }

    public void setCn_id(String cn_id) {
        this.cn_id = cn_id;
    }

    public String getTencn() {
        return tencn;
    }

    public void setTencn(String tencn) {
        this.tencn = tencn;
    }

    public String getNoicap() {
        return noicap;
    }

    public void setNoicap(String noicap) {
        this.noicap = noicap;
    }
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getNgaycap() {
        return ngaycap;
    }
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setNgaycap(LocalDate ngaycap) {
        this.ngaycap = ngaycap;
    }
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getNgayhethan() {
        return ngayhethan;
    }
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setNgayhethan(LocalDate ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDantoc() {
        return dantoc;
    }

    public void setDantoc(String dantoc) {
        this.dantoc = dantoc;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    public String getHokhau_thuongtru() {
        return hokhau_thuongtru;
    }

    public void setHokhau_thuongtru(String hokhau_thuongtru) {
        this.hokhau_thuongtru = hokhau_thuongtru;
    }

    public String getHokhau_tamtru() {
        return hokhau_tamtru;
    }

    public void setHokhau_tamtru(String hokhau_tamtru) {
        this.hokhau_tamtru = hokhau_tamtru;
    }

    public String getTieuchi() {
        return tieuchi;
    }

    public void setTieuchi(String tieuchi) {
        this.tieuchi = tieuchi;
    }

    public String getMahq() {
        return mahq;
    }

    public void setMahq(String mahq) {
        this.mahq = mahq;
    }

    public String getLinhvuc() {
        return linhvuc;
    }

    public void setLinhvuc(String linhvuc) {
        this.linhvuc = linhvuc;
    }

    public String getDiachicssx() {
        return diachicssx;
    }

    public void setDiachicssx(String diachicssx) {
        this.diachicssx = diachicssx;
    }

    public String getCoquan_congtac() {
        return coquan_congtac;
    }

    public void setCoquan_congtac(String coquan_congtac) {
        this.coquan_congtac = coquan_congtac;
    }

    public String getDang_congtac() {
        return dang_congtac;
    }

    public void setDang_congtac(String dang_congtac) {
        this.dang_congtac = dang_congtac;
    }

    public String getQuanhe() {
        return quanhe;
    }

    public void setQuanhe(String quanhe) {
        this.quanhe = quanhe;
    }

    public String getCongchuc_id() {
        return congchuc_id;
    }

    public void setCongchuc_id(String congchuc_id) {
        this.congchuc_id = congchuc_id;
    }

    public String getCongchuc_name() {
        return congchuc_name;
    }

    public void setCongchuc_name(String congchuc_name) {
        this.congchuc_name = congchuc_name;
    }
}

