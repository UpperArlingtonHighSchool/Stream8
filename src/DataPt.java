public class DataPt<T> {
    private Object data;
    private String category;
    public <T>DataPt(T d, String c){
        data = d;
        category = c;
    }

    public Object getData() {
        return data;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category + ": " + data;
    }
}
