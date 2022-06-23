package TuyenSinh10;


public class hoso {
	private String MaHoSo, HoTenHs,SDT,
	DiemXet, Tenlop, GioiTinh, NgaySinh, NoiSinh;
	public hoso() {
	}
	
	public hoso(String MaHoSo,String HoTenHs,String Tenlop,String GioiTinh,String NgaySinh, String NoiSinh, String SDT, String DiemXet) {
		this.MaHoSo = MaHoSo;
		this.HoTenHs = HoTenHs;
		this.SDT = SDT;
		this.DiemXet = DiemXet;
		this.Tenlop = Tenlop;
		this.GioiTinh = GioiTinh;
		this.NgaySinh = NgaySinh;
		this.NoiSinh = NoiSinh;
	   
	}
	
	public String getMaHoSo() {
		return MaHoSo;
	}

	public void setMaHoSo(String maHoSo) {
		MaHoSo = maHoSo;
	}

	public String getHoTenHs() {
		return HoTenHs;
	}

	public void setHoTenHs(String hoTenHs) {
		HoTenHs = hoTenHs;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getDiemXet() {
		return DiemXet;
	}

	public void setDiemXet(String diemXet) {
		DiemXet = diemXet;
	}

	public String getTenlop() {
		return Tenlop;
	}

	public void setTenlop(String tenlop) {
		Tenlop = tenlop;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getNoiSinh() {
		return NoiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		NoiSinh = noiSinh;
	}
	
}