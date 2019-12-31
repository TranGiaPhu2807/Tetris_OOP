import java.awt.Color;

// Single responsibility principle chi chiu trach nhiem cho object block, co the thay doi 
class Block {
	
	//vi tri xuat phat
    private int blockColLocation = 7; // vd vi tri cua block la 8,9,10 ben phai
    private int blockRowLocation = 0; //

    static final int BLOCK_LENGTH = 16;
    
    

    private static final int[][] tetrisBlockS = {
    		{0, 1, 1}, 
    		{1, 1, 0}};
	private static final int[][] tetrisBlockL = {
    		{0, 0, 2}, 
    		{2, 2, 2}};
    private static final int[][] tetrisBlockJ = {
    		{3, 0, 0}, 
    		{3, 3, 3}};
    private static final int[][] tetrisBlockO = {
    		{4, 4}, 
    		{4, 4}};
    private static final int[][] tetrisBlockZ = {
    		{5, 5, 0}, 
    		{0, 5, 5}};
    private static final int[][] tetrisBlockT = {
    		{0, 6, 0}, 
    		{6, 6, 6}};
    
    private static final int[][] tetrisBlockI = {{7}, {7}, {7}, {7}};
    

    private int[][] currentBlockState; // return which type



    Block() {
    }
    
  
    

    int[][] getRandom() {
        switch ((int) (0 + Math.random() * 7)) {
            case 0:
                currentBlockState = tetrisBlockI;
                return tetrisBlockI;
            case 1:
                currentBlockState = tetrisBlockJ;
                return tetrisBlockJ;
            case 2:
                currentBlockState = tetrisBlockL;
                return tetrisBlockL;
            case 3:
                currentBlockState = tetrisBlockO;
                return tetrisBlockO;
            case 4:
                currentBlockState = tetrisBlockS;
                return tetrisBlockS;
            case 5:
                currentBlockState = tetrisBlockT;
                return tetrisBlockT;
            case 6:
                currentBlockState = tetrisBlockZ;
                return tetrisBlockZ;
            default:
                return null;
        }
    }


    static Color getColour(int color) {
        switch (color) {
            case 1:
                return Color.DARK_GRAY;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.RED;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.BLACK;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.GRAY;
        }
        return null;
    }

    int[][] getCurrentState() {
        return currentBlockState;
    }

    int getColNum() {
        return blockColLocation;
    }

    int getRowNum() {
        return blockRowLocation;
    }

  
    int[][] rotateRight(boolean shouldRotate) {
        int newColLength = currentBlockState.length;
        int newRowLength = currentBlockState[0].length;

        int[][] rotate90Array = new int[newRowLength][newColLength];
        for (int r = 0; r < newRowLength; r++) {
            for (int c = 0; c < newColLength; c++) {
                rotate90Array[r][c] = currentBlockState[newColLength - c - 1][r];
                // trao row voi lai column de quay 
            }
        }

        //xet truong hop quay o canh mep phai 
        if (blockColLocation + newColLength > Board.boardWidth) {
            blockColLocation = Board.boardWidth - newColLength;
        }

        if (shouldRotate) {
            currentBlockState = rotate90Array;
        }

        return rotate90Array;
    }
    
    //tuong tu tren nhung trong truong hop bam R thi se xoay theo canh trai

    int[][] rotateLeft(boolean shouldRotate) {
        int newColLength = currentBlockState.length;
        int newRowLength = currentBlockState[0].length;

        int[][] rotate270Array = new int[newRowLength][newColLength];
        for (int r = 0; r < newRowLength; r++) {
            for (int c = 0; c < newColLength; c++) {
                rotate270Array[r][c] = currentBlockState[c][newRowLength - r - 1];
            }
        }

        if (blockColLocation + newColLength > Board.boardWidth) {
            blockColLocation = Board.boardWidth - newColLength;
        }
        if (shouldRotate) {
            currentBlockState = rotate270Array;
        }

        return rotate270Array;
    }

 
    void moveLeft() {
        if (blockColLocation > 0) {
            blockColLocation--;
        }
    }

    void moveRight() {
        if (blockColLocation + currentBlockState[0].length < Board.boardWidth) {
            blockColLocation++;
        }
    }
    
    
    //cap nhap vi tri cua Row cua piece
    boolean moveDown() {
        if (currentBlockState.length + blockRowLocation < Board.boardHeight) {
            blockRowLocation++;
            return false;
        }
        return true;
    }
}
