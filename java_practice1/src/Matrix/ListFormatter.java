package Matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ListFormatter {
    private final int PADDING_SIZE = 2;
    private final int CELL_LENGTH = 5;

    private final String CELL_LENGTH_STR = String.valueOf(CELL_LENGTH);
    private final String NEW_LINE = "\n";
    private final String TABLE_JOINT_SYMBOL = "+";
    private final String TABLE_V_SPLIT_SYMBOL = "|";
    private final String TABLE_H_SPLIT_SYMBOL = "-";

    public String generateTable(int nRows, int nCols, ArrayList<String> headersList, Matrix matrix, int... overRiddenHeaderHeight) {
        StringBuilder stringBuilder = new StringBuilder();

        int rowHeight = overRiddenHeaderHeight.length > 0 ? overRiddenHeaderHeight[0] : 1;

        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), nCols);
        stringBuilder.append(NEW_LINE);


        for (int headerIndex = 0; headerIndex < headersList.size(); headerIndex++) {
            fillCell(stringBuilder, headersList.get(headerIndex), nCols);
        }

        stringBuilder.append(NEW_LINE);

        createRowLine(stringBuilder, headersList.size(), nCols);

        for (int i = 0; i < nRows - 1; i++){
            for (int j = 0; j < rowHeight; j++) {
                stringBuilder.append(NEW_LINE);
            }

            fillCell(stringBuilder, i, nCols);
            for (int cellIndex = 0; cellIndex < nCols - 1; cellIndex++) {
                fillCell(stringBuilder, matrix.get(i, cellIndex).Value, nCols);
            }
        }

        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), nCols);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);

        return stringBuilder.toString();
    }

    private void fillSpace(StringBuilder stringBuilder, int length) {
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
    }

    private void createRowLine(StringBuilder stringBuilder, int headersListSize, int nCols) {
        for (int i = 0; i < headersListSize; i++) {
            for (int j = 0; j < CELL_LENGTH + PADDING_SIZE * 2; j++) {
                if (i == 0 && j == 0){
                    stringBuilder.append(TABLE_JOINT_SYMBOL);
                } else {
                    stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
                }
            }
            stringBuilder.append(TABLE_JOINT_SYMBOL);
        }
    }

    private int getOptimumCellPadding(int datalength, int nCols, int cellPaddingSize) {
        if (datalength % 2 != 0) {
            datalength++;
        }

        if (datalength < nCols) {
            cellPaddingSize = cellPaddingSize + (nCols - datalength) / 2;
        }

        return cellPaddingSize;
    }

    private void fillCell(StringBuilder stringBuilder, Object cell, int nCols) {
        String cell1 = String.valueOf(cell);
        String cell2 = cell1.length() > CELL_LENGTH ? String.valueOf(cell).substring(0, CELL_LENGTH) : cell1;
        String cellStr = String.format("%" + CELL_LENGTH_STR + "s", cell2);
        int cellPaddingSize = getOptimumCellPadding(cellStr.length(), nCols, PADDING_SIZE);
        fillSpace(stringBuilder, cellPaddingSize);
        stringBuilder.append(cellStr);
        fillSpace(stringBuilder, cellPaddingSize);

        stringBuilder.append(TABLE_V_SPLIT_SYMBOL);

    }
}
