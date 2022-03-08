package DatabaseManager;

public class Gpu {
    //(`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`, `type`, `imageurl`) 
    private int id;
    private String modelname;
    private String company;
    private String year;
    private Double memory;
    private Double power;
    private String socket;
    private Double price;
    private String type;
    private String imageurl;

    public Gpu(int id, String modelname, String company, String year, Double memory, Double power, String socket, Double price, String type, String imageurl) {
        this.id = id;
        this.modelname = modelname;
        this.company = company;
        this.year = year;
        this.memory = memory;
        this.power = power;
        this.socket = socket;
        this.price = price;
        this.type = type;
        this.imageurl = imageurl;
    }
    
    public Gpu(int id){
        System.out.println("[WARNING] Using only 1 argument constructor");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "Gpu{" + "id=" + id + ", modelname=" + modelname + ", company=" + company + ", year=" + year + ", memory=" + memory + ", power=" + power + ", socket=" + socket + ", price=" + price + ", type=" + type + ", imageurl=" + imageurl + '}';
    }
    
    
}
