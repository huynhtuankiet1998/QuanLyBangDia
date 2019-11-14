package Manager;

public class CD {
    private String  maCD;
    private String  tenCD;
    private String  theloai;
    private boolean tinhtrang;
    private String  hangSX;
    private String  ghichu;
    protected int     donGia;

    public CD() {
        super();
        this.maCD    = "";
        this.maCD    = "";
        this.tenCD   = "";
        this.theloai = "";
        this.hangSX  = "";
        this.ghichu  = "";
        this.donGia  = 0;
    }

    public CD(String maCD, String tenCD, String theloai, boolean tinhtrang, String hangSX, String ghichu, int donGia) {
        super();
        setMaCD(maCD);
        setTenCD(tenCD);
        setTheloai(theloai);
        setTinhtrang(tinhtrang);
        setHangSX(hangSX);
        setGhichu(ghichu);
        setDonGia(donGia);
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        if (ghichu == null) {
            this.ghichu = "";
        } else {
            this.ghichu = ghichu;
        }
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        if (hangSX == null) {
            this.hangSX = "";
        } else {
            this.hangSX = hangSX;
        }
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        if (maCD == null) {
            this.maCD = "";
        } else {
            this.maCD = maCD;
        }
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        if (tenCD == null) {
            this.tenCD = "";
        } else {
            this.tenCD = tenCD;
        }
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        if (theloai == null) {
            this.theloai = "";
        } else {
            this.theloai = theloai;
        }
    }

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}