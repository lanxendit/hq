package net.codejava.corporation;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Product {
	private Long id;
	private String istrongdiem;
	private String sttcap1;
	private String sttcap2;
	private String sttcap3;
	private String mst;
	private String tencty;
	private String diachi;
	private String tieuchitrongdiem;
	private String mahq;
	private LocalDate ngaythanhlap;
	private LocalDate ngaythaydoi;
	private String tinhtrang;
	private String linhvuc;
	private String nganhnghekd;
	private String tkvnaccs;
	private String tkdaily;
	private String boss;
	private String bossid;
	private String bossaddr;
	private String chicucthuequanly;
	private String doitacnuocngoai;
	private String chuhangthuctetaivietnam;
	private String chuhangthuctetainuocngoai;
	private String dailylogisticstainuocngoai;
	private String dailylogisticstaivietnam;
	private String thongtinthunhapvaphantich;
	private String congchucid;
	private String congchucname;
	private String mstdnsatnhap;
	private String tendnsatnhap;
	private String diachidnsatnhap;
	private String mstdnchiatach;
	private String tendnchiatach;
	private String diachidnchiatach;

	public Product() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIstrongdiem() {
		return istrongdiem;
	}

	public void setIstrongdiem(String istrongdiem) {
		this.istrongdiem = istrongdiem;
	}

	public String getSttcap1() {
		return sttcap1;
	}

	public void setSttcap1(String sttcap1) {
		this.sttcap1 = sttcap1;
	}

	public String getSttcap2() {
		return sttcap2;
	}

	public void setSttcap2(String sttcap2) {
		this.sttcap2 = sttcap2;
	}

	public String getSttcap3() {
		return sttcap3;
	}

	public void setSttcap3(String sttcap3) {
		this.sttcap3 = sttcap3;
	}

	public String getMst() {
		return mst;
	}

	public void setMst(String mst) {
		this.mst = mst;
	}

	public String getTencty() {
		return tencty;
	}

	public void setTencty(String tencty) {
		this.tencty = tencty;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getTieuchitrongdiem() {
		return tieuchitrongdiem;
	}

	public void setTieuchitrongdiem(String tieuchitrongdiem) {
		this.tieuchitrongdiem = tieuchitrongdiem;
	}

	public String getMahq() {
		return mahq;
	}

	public void setMahq(String mahq) {
		this.mahq = mahq;
	}
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public LocalDate getNgaythanhlap() {
		return ngaythanhlap;
	}
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public void setNgaythanhlap(LocalDate ngaythanhlap) {
		this.ngaythanhlap = ngaythanhlap;
	}
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public LocalDate getNgaythaydoi() {
		return ngaythaydoi;
	}
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public void setNgaythaydoi(LocalDate ngaythaydoi) {
		this.ngaythaydoi = ngaythaydoi;
	}

	public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public String getLinhvuc() {
		return linhvuc;
	}

	public void setLinhvuc(String linhvuc) {
		this.linhvuc = linhvuc;
	}

	public String getNganhnghekd() {
		return nganhnghekd;
	}

	public void setNganhnghekd(String nganhnghekd) {
		this.nganhnghekd = nganhnghekd;
	}

	public String getTkvnaccs() {
		return tkvnaccs;
	}

	public void setTkvnaccs(String tkvnaccs) {
		this.tkvnaccs = tkvnaccs;
	}

	public String getTkdaily() {
		return tkdaily;
	}

	public void setTkdaily(String tkdaily) {
		this.tkdaily = tkdaily;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getBossid() {
		return bossid;
	}

	public void setBossid(String bossid) {
		this.bossid = bossid;
	}

	public String getBossaddr() {
		return bossaddr;
	}

	public void setBossaddr(String bossaddr) {
		this.bossaddr = bossaddr;
	}

	public String getChicucthuequanly() {
		return chicucthuequanly;
	}

	public void setChicucthuequanly(String chicucthuequanly) {
		this.chicucthuequanly = chicucthuequanly;
	}

	public String getDoitacnuocngoai() {
		return doitacnuocngoai;
	}

	public void setDoitacnuocngoai(String doitacnuocngoai) {
		this.doitacnuocngoai = doitacnuocngoai;
	}

	public String getChuhangthuctetaivietnam() {
		return chuhangthuctetaivietnam;
	}

	public void setChuhangthuctetaivietnam(String chuhangthuctetaivietnam) {
		this.chuhangthuctetaivietnam = chuhangthuctetaivietnam;
	}

	public String getChuhangthuctetainuocngoai() {
		return chuhangthuctetainuocngoai;
	}

	public void setChuhangthuctetainuocngoai(String chuhangthuctetainuocngoai) {
		this.chuhangthuctetainuocngoai = chuhangthuctetainuocngoai;
	}

	public String getDailylogisticstainuocngoai() {
		return dailylogisticstainuocngoai;
	}

	public void setDailylogisticstainuocngoai(String dailylogisticstainuocngoai) {
		this.dailylogisticstainuocngoai = dailylogisticstainuocngoai;
	}

	public String getDailylogisticstaivietnam() {
		return dailylogisticstaivietnam;
	}

	public void setDailylogisticstaivietnam(String dailylogisticstaivietnam) {
		this.dailylogisticstaivietnam = dailylogisticstaivietnam;
	}

	public String getThongtinthunhapvaphantich() {
		return thongtinthunhapvaphantich;
	}

	public void setThongtinthunhapvaphantich(String thongtinthunhapvaphantich) {
		this.thongtinthunhapvaphantich = thongtinthunhapvaphantich;
	}

	public String getCongchucid() {
		return congchucid;
	}

	public void setCongchucid(String congchucid) {
		this.congchucid = congchucid;
	}

	public String getCongchucname() {
		return congchucname;
	}

	public void setCongchucname(String congchucname) {
		this.congchucname = congchucname;
	}

	public String getMstdnsatnhap() {
		return mstdnsatnhap;
	}

	public void setMstdnsatnhap(String mstdnsatnhap) {
		this.mstdnsatnhap = mstdnsatnhap;
	}

	public String getTendnsatnhap() {
		return tendnsatnhap;
	}

	public void setTendnsatnhap(String tendnsatnhap) {
		this.tendnsatnhap = tendnsatnhap;
	}

	public String getDiachidnsatnhap() {
		return diachidnsatnhap;
	}

	public void setDiachidnsatnhap(String diachidnsatnhap) {
		this.diachidnsatnhap = diachidnsatnhap;
	}

	public String getMstdnchiatach() {
		return mstdnchiatach;
	}

	public void setMstdnchiatach(String mstdnchiatach) {
		this.mstdnchiatach = mstdnchiatach;
	}

	public String getTendnchiatach() {
		return tendnchiatach;
	}

	public void setTendnchiatach(String tendnchiatach) {
		this.tendnchiatach = tendnchiatach;
	}

	public String getDiachidnchiatach() {
		return diachidnchiatach;
	}

	public void setDiachidnchiatach(String diachidnchiatach) {
		this.diachidnchiatach = diachidnchiatach;
	}
}
