


class Board {

    final static int boardHeight = 30;
    final static int boardWidth = 20;

    private int[][] boardArray = new int[boardHeight][boardWidth];

    Board() {
    }

 // kiem tra xem diem trong khoi hinh co dat duoc khong, neu cac so khac thi tra lai 1, con ko tra lai 0
    int placePiece(int[][] Piece, int row, int col, boolean shouldPlace) {
        int column = col;
        for (int[] aPiece : Piece) {
            for (int c = 0; c < Piece[0].length; c++) {
                try {
                    if (boardArray[row][col] != 0 && aPiece[c] != 0) {
                        return 1;
                    } 
                    // de kiem tra col phia duoi no
    				// tai cai diem (row,column+1) (func sau) da co dat so khac va cai (row,column) cua block hien tai cung co so 
    				// ta tra lai gia tri 1 la ko dat
                    
                    else {
                    	//day moi la phan dat
                        if (shouldPlace) {
                            boardArray[row][col] |= aPiece[c]; // dat vao phai xai | vi ko muon trung len nhau
                        }
                        col++;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return 1;
                }

            }
            row++;
            col = column;
        }
        return 0;
    }
    
    //co thang nao cham toi dau thi end game
    boolean isGameOver() {
        for (int i = 0; i < boardArray[0].length; i++) {
            if (boardArray[0][i] != 0) {
                return true;
            }
        }
        return false;
    }

    int calculateRowsCleared() {
        int clearedRows = 0;
        int completedLength = 0;

        for (int r = 0; r < boardArray.length; r++) {
            for (int c = 0; c < boardArray[0].length; c++) {
                if (boardArray[r][c] != 0) {
                    completedLength++;
                }
              //kt cai o nao khac =0 thi templength +1
            }

            if (completedLength >= boardArray[0].length) { //temp length dai hon x
                removeLine(r);
                clearedRows++;
            }

            completedLength = 0;
        }

        return clearedRows;
    }

    private void removeLine(int y) {
    	if (y < 0 || y >= boardHeight) {
            return;
        }
        while (y > 0) {
        	//ham copy tat ca row tren dat xuong row duoi lien tuc  
            System.arraycopy(boardArray[y - 1], 0, boardArray[y], 0, boardWidth);
            y--;
        }
    }

    int[][] getBoardArray() {
        return boardArray;
    }
}
