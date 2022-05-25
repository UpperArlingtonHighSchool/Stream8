class twoDouble implements Comparable<twoDouble> {
    public double temp;
    public double turb;
    public  twoDouble(double t, double tu) {
        temp = t;
        turb = tu;
    }
    @Override
    public int compareTo(twoDouble another){
        if(another.temp > this.temp)
            return -1;
        else
            return 1;
    }
}