package Matrix;

public class MatrixElement {
    public Integer Row;
    public Integer Col;
    public float Value;
    public MatrixElement(Integer row, Integer col, Number value){
        this.Row = row;
        this.Col = col;
        this.Value = (float)value;
    }
}
