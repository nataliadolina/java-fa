import java.lang.reflect.Array;
import java.util.*;


public class Matrix{
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

    public Matrix(ArrayList<MatrixElement> data, HashMap<String, Integer> dataMap, Integer numRows, Integer numCols, Float defaultValue){
        int default_size = 1;
        _numRows = numRows != null && numRows > 0 ? numRows: default_size;
        _numCols = numCols != null && numCols > 0 ? numCols: default_size;
        VALUE_FOR_EMPTY_CELLS = defaultValue != null ? defaultValue : DEFAULT_VALUE;
        _data = new ArrayList<>();
        _data.addAll(data);
        _dataMap = new HashMap<>();
        _dataMap.putAll(dataMap);
    }

    public void setNewData(ArrayList<MatrixElement> data) {
        _data = new ArrayList<>();
        _data.addAll(data);
        for (MatrixElement el : _data){
            int row = el.Row;
            int col = el.Col;
            _dataMap.put(getCode(row, col), _data.indexOf(el));
        }
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
     * <p>Матрицы должны иметь одинаковые {@code getRows(); getCols()}.
     * @return the new Matrix
     * @throws    IllegalArgumentException  если у матриц разные {@code _numRows; _numCols}
     */
    public Matrix add(Matrix other){
        if (other.numRows() != _numRows || other.numCols() != _numCols){
            throw new IllegalArgumentException("разная размерность у матриц!");
        }


        Matrix newMatrix = new Matrix(new Float[]{0f}, _numRows, _numCols, null);
        for (MatrixElement el : _data){
            int row = el.Row;
            int col = el.Col;
            Float newValue = other.get(row, col).Value + el.Value;
            MatrixElement newElement = new MatrixElement(row, col, newValue);
            newMatrix.set(row, col, newElement);
        }
        return newMatrix;
    }

    /**
     * Разность 2ух матриц
     *
     * <p>Матрицы должны иметь одинаковые {@code _numRows; _numCols}.
     * @return the new Matrix
     * @throws    IllegalArgumentException  если у матриц разные свойство {@code _numRows; _numCols}
     */
    public Matrix sub(Matrix other){
        if (other.numRows() != _numRows || other.numCols() != _numCols){
            throw new IllegalArgumentException("разная размерность у матриц!");
        }


        Matrix newMatrix = new Matrix(new Float[]{0f}, _numRows, _numCols, null);
        for (MatrixElement el : _data){
            int row = el.Row;
            int col = el.Col;
            Float newValue = other.get(row, col).Value - el.Value;
            MatrixElement newElement = new MatrixElement(row, col, newValue);
            newMatrix.set(row, col, newElement);
        }
        return newMatrix;
    }

    /**
     * Умножает матрицу на число
     *
     * @return the new Matrix
     */
    public Matrix mulNumber(float number){
        Matrix newMatrix = new Matrix(new Float[]{0f}, _numRows, _numCols, null);
        for (MatrixElement el : _data){
            int row = el.Row;
            int col = el.Col;
            newMatrix.set(row, col, new MatrixElement(row, col, el.Value * number));
        }
        return newMatrix;
    }

    /**
     * Умножение матрицы на матрицу
     *
     * <p>Матрица {@code this} должна иметь столько же {@code _numCols}, сколько в {@code other} {@code _numRows}.
     * @return the new Matrix
     * @throws    IllegalArgumentException  если в матрице {@code this} число {@code _numCols}, не равно {@code other.numRows()}
     */
    public Matrix mulMatrix(Matrix other){
        int otherCols = other.numCols();
        Matrix resultMatrix = new Matrix(new Float[]{0f}, _numRows, otherCols, null);
        mulMatrixInternal(other, resultMatrix, otherCols);
        return resultMatrix;
    }

    private Matrix mulMatrixSeveralTimes(Matrix other, Matrix resultMatrix, int size, int n){
        for (int i = 0; i < n; i++){
            mulMatrixInternal(other, resultMatrix, size);
        }
        return resultMatrix;
    }

    private void mulMatrixInternal(Matrix other, Matrix newMatrix, int otherCols){
        int otherRows = other.numRows();
        if (_numCols != otherRows){
            throw new IllegalArgumentException("Переданная матрица не удовлетворяет условию \n" +
                    "Матрица {@code this} должна иметь столько же {@code _num{@code other} {@code _numRows}Cols}, сколько в " +
                    "{@code other} {@code _numRows}");
        }

        ArrayList<MatrixElement> _newData = new ArrayList<>();
        for (int i = 0; i < _numRows; i++){
            for (int j=0; j < otherCols; j++){
                float value = 0;
                for (int otherI = 0; otherI < otherRows; otherI ++){
                    value += get(i, otherI).Value * other.get(otherI, j).Value;
                }
                MatrixElement newElement = new MatrixElement(i, j, value);
                _newData.add(newElement);
            }
        }
        newMatrix.setNewData(_newData);
    }

    public Matrix transpose(){
        Matrix newMatrix = new Matrix(new Float[]{0f}, _numCols, _numRows, null);
        for (int i = 0; i < _numCols; i++){
            for (int j = 0; j < _numRows; j++){
                newMatrix.set(i, j, get(j, i));
            }
        }
        return newMatrix;
    }

    /**
     * Возводит матрицу в степень k
     *
     * <p>работае только для квадратных матриц.
     * @return the new Matrix
     * @throws    IllegalArgumentException  если матрица не квадратная или k < 0}
     */
    public Matrix powMatrix(int k){
        if (_numRows != _numCols){
            throw new IllegalArgumentException("Невозможно возвести неквадратную матрицу в нулевую степень");
        }

        if (k < 0){
            throw new IllegalArgumentException("Матрица может быть возведена только в целое положительное число или 0");
        }

        if (k == 0){
            Matrix newMatrix = new Matrix(new Float[]{}, _numRows, _numCols, 0f);
            for (int i = 0; i< _numRows; i++){
                newMatrix.set(i, i, new MatrixElement(i, i, 1f));
            }
            return newMatrix;
        }

        Matrix newMatrix = new Matrix(_data, _dataMap, _numRows, _numRows, VALUE_FOR_EMPTY_CELLS);
        newMatrix.mulMatrixSeveralTimes(this, newMatrix, _numRows, k-1);
        return newMatrix;
    }

    @Override
    public String toString(){
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
