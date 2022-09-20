import java.lang.reflect.Array;
import java.util.*;


public class Matrix {
    private final float VALUE_FOR_EMPTY_CELLS;
    private final float DEFAULT_VALUE = 1;
    private int _numRows;
    private int _numCols;
    private ArrayList<MatrixElement> _data;
    private HashMap<String, Integer> _dataMap;

    private String _matrixVisualization;

    public Matrix(Float[] data, Integer numRows, Integer numCols, Float defaultValue){
        int default_size = 1;
        _numRows = numRows != null && numRows > 0 ? numRows: default_size;
        _numCols = numCols != null && numCols > 0 ? numCols: default_size;
        VALUE_FOR_EMPTY_CELLS = defaultValue != null ? defaultValue : DEFAULT_VALUE;
        createData(data);
    }

    private void createData(Number[] data){
        int maxIndex = data.length - 1;
        _data = new ArrayList<>();
        _dataMap = new HashMap<>();
        int currentDataIndex = -1;
        for (int i = 0; i < _numRows; i++){
            for (int j = 0; j < _numCols; j++){
                currentDataIndex ++;
                Number elementData =  currentDataIndex<= maxIndex ? (Float) Array.get(data, currentDataIndex) : VALUE_FOR_EMPTY_CELLS;
                MatrixElement el = new MatrixElement(i, j, elementData);
                _data.add(el);
                _dataMap.put(getCode(i, j), currentDataIndex);
            }
        }
    }

    private String getCode(int row, int col){
        return String.format("%d %d", row, col);
    }
    /**
     * Складывает 2 матрицы
     *
     * <p>Матрицы должны иметь одинаковую {@code shape}.
     * @return the new Matrix
     * @throws    IllegalArgumentException  если у матриц разное свойство {@code shape}
     */
    public Matrix add(Matrix other){
        if (other.numRows() != _numRows || other.numCols() != _numCols){
            throw new IllegalArgumentException("разная размерность у матриц!");
        }


        Matrix newMatrix = new Matrix(new Float[]{0f}, numRows(), numCols(), null);
        for (MatrixElement el : _data){
            int row = el.Row;
            int col = el.Col;
            Float newValue = other.get(row, col).Value + el.Value;
            MatrixElement newElement = new MatrixElement(row, col, newValue);
            newMatrix.set(row, col, newElement);
        }
        return newMatrix;
    }

    public String VisualString(){
        if (_matrixVisualization != null){
            return _matrixVisualization;
        }
        ArrayList<String> header = new ArrayList<>();
        header.add(" ");
        for (int i = 0; i < _numCols; i++){
            header.add(Integer.toString(i));
        }

        ListFormatter listFormatter = new ListFormatter();
        _matrixVisualization = listFormatter.generateTable(_numRows + 1, _numCols + 1, header, this);
        return _matrixVisualization;
    }
    public MatrixElement get(int row, int col){
        return _data.get(_dataMap.get(getCode(row, col)));
    }

    public void set(int row, int col, MatrixElement element){
        _data.set(_dataMap.get(getCode(row, col)), element);
    }

    public int numRows(){
        return _numRows;
    }

    public int numCols(){
        return _numCols;
    }
}
