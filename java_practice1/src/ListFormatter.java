import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ListFormatter {
    private final int PADDING_SIZE = 2;
    private final String NEW_LINE = "\n";
    private final String TABLE_JOINT_SYMBOL = "+";
    private final String TABLE_V_SPLIT_SYMBOL = "|";
    private final String TABLE_H_SPLIT_SYMBOL = "-";

    public String generateTable(int nRows, int nCols, List<String> headersList, Matrix matrix, int... overRiddenHeaderHeight) {
        StringBuilder stringBuilder = new StringBuilder();

        int rowHeight = overRiddenHeaderHeight.length > 0 ? overRiddenHeaderHeight[0] : 1;

        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, nRows + 1, nCols);
        stringBuilder.append(NEW_LINE);


        for (int headerIndex = 0; headerIndex < headersList.size(); headerIndex++) {
            fillCell(stringBuilder, String.format("%4s", headersList.get(headerIndex)), headerIndex, nCols);
        }

        stringBuilder.append(NEW_LINE);

        createRowLine(stringBuilder, headersList.size(), nCols);

        for (int i = 0; i < nRows - 1; i++){
            for (int j = 0; j < rowHeight; j++) {
                stringBuilder.append(NEW_LINE);
            }

            fillCell(stringBuilder, Integer.toString(i), 0, nCols);
            for (int cellIndex = 0; cellIndex < nCols - 1; cellIndex++) {
                fillCell(stringBuilder, String.format("%4s", matrix.get(i, cellIndex).Value), cellIndex, nCols);
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
            if (i == 0) {
                stringBuilder.append(TABLE_JOINT_SYMBOL);
            }

            for (int j = 0; j < nCols + PADDING_SIZE * 2; j++) {
                stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
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

    private void fillCell(StringBuilder stringBuilder, String cell, int cellIndex, int nCols) {

        int cellPaddingSize = getOptimumCellPadding(cell.length(), nCols, PADDING_SIZE);
        fillSpace(stringBuilder, cellPaddingSize);
        stringBuilder.append(cell);
        if (cell.length() % 2 != 0) {
            stringBuilder.append(" ");
        }

        fillSpace(stringBuilder, cellPaddingSize);

        stringBuilder.append(TABLE_V_SPLIT_SYMBOL);

    }
}
