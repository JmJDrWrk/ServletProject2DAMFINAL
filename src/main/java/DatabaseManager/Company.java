package DatabaseManager;

public class Company {
    private int id;
    private String compname;
    private String imageurl;
    
    public Company(int id, String compname, String imageurl){
        this.id = id;
        this.compname = compname;
        this.imageurl = imageurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", compname=" + compname + ", imageurl=" + imageurl + '}';
    }
    
    
}
