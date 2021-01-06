public class info{
    double f;
    int x;
    int y;

    public info(double f, int x, int y) {
        this.x = x;
        this.f = f;
        this.y = y;
    }

    public int hashCode() {
        System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = x * 17;
        return hashcode;
    }

    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof info) {
            info pp = (info) obj;
            return pp.f == f && pp.x == x && pp.y == y;
        } else {
            return false;
        }
        
    }

}
