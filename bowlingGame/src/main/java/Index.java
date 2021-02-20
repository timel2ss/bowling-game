public class Index {
    private int index;

    private Index(int idx){
        this.index = idx;
    }

    public static Index create(int idx) {
        return new Index(idx);
    }

    public int get() {
        return index++;
    }

}
