
import java.awt.Canvas;   

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;


//A Canvas component represents a blank rectangular area of the screen onto which the application can draw or
//from which the application can trap input events from the user.

class DrawingBoard extends Canvas { 

    private static final int PIECE_WIDTH = 16;
   // offset boi vi paint bi lech so voi piece nen phai chinh
 

    private static final int SIZEPIECE = 14;
    private static final int GAMEAREAWIDTH = Board.boardWidth * PIECE_WIDTH ; // khung khong giang tro choi
    private static final int GAMEAREAHEIGHT = Board.boardHeight * PIECE_WIDTH ; //khung khong giang tro choi 

    private static final int GAMEAREAXLOC = 1; //khung khong giang tro choi tu vi tri 1
    private static final int GAMEAREAYLOC = 1; //khung khong giang tro choi tu vi tri 1
    
    //phai tren
    private static final int NPAREAXLOC = Board.boardWidth * PIECE_WIDTH + 10;
    private static final int NPAREAYLOC = 1;
    private static final int WIDTH = 128;
    private static final int NPHEIGHT = 100;
    
    //phai duoi
    
    private static final int GAMEINFOWIDTH = 128;
    private static final int GAMEINFOHEIGHT = 299;
    private static final int GAMEINFOXLOC = Board.boardWidth * PIECE_WIDTH + 10;
    private static final int GAMEINFOYLOC = 100 ;
    
    //thong bao diem
    private static final int ROWSXLOC = Board.boardWidth * PIECE_WIDTH + 15;
    private static final int ROWSYLOC = 160 ;
 

    public void paint(Graphics g) { // su dung trong Graphic
        if (screen == null) {
            screen = createImage(getWidth(), getHeight()); //neu ko co screen thi create image
        }

     
        Graphics graphics = screen.getGraphics();
       
        
        
        graphics.clearRect(0, 0, getWidth(), getHeight()); //Clears cai background color cua drawing surface

        graphics.setColor(Color.WHITE);   // graphics context to white
        
        graphics.fillRect(0, 0, getWidth(), getHeight()); //Fill cai backgroud

        graphics.setColor(Color.BLACK);

        graphics.drawRect(GAMEAREAXLOC, GAMEAREAYLOC, GAMEAREAWIDTH, GAMEAREAHEIGHT); // fill cai khung khong giang tro choi

        
        graphics.drawRect(NPAREAXLOC, NPAREAYLOC, WIDTH, NPHEIGHT); // khong giang box tinh, phai tren


        graphics.drawRect(GAMEINFOXLOC, GAMEINFOYLOC, GAMEINFOWIDTH, GAMEINFOHEIGHT); //khong giang info tro choi, phai duoi


        int r = PIECE_WIDTH * rowLocation + 3 ;
        int c = PIECE_WIDTH * columnLocation + 3;
        
        //Row va column location cua cai piece ban dau = 3 (canh chinh cho dep) thi se paint cai piece do tu pixel 3 va tiep tuc doc tiep vi tri cua piece de paint tiep
        //piece tu cai thang class block

        for (int[] aPiece : piece) {
            for (int j = 0; j < piece[0].length; j++) {
                if (aPiece[j] != 0) {
                    graphics.setColor(Block.getColour(aPiece[j]));
                    graphics.drawRect(c, r, SIZEPIECE, SIZEPIECE);
                }
                c += PIECE_WIDTH;
            }
            c = PIECE_WIDTH * columnLocation + 3;
            r += PIECE_WIDTH;
        }

        // paint cai board array nhung piece da duoc dat
        
        r = 5;
        c = 3;
        for (int[] aBoardArray : boardArray) {
            for (int l = 0; l < boardArray[0].length; l++) {
                if (aBoardArray[l] != 0) {
                    graphics.setColor(Block.getColour(aBoardArray[l]));
                    graphics.fillRect(c, r,SIZEPIECE, SIZEPIECE);
                }
                c += PIECE_WIDTH;
            }
            c = 5;
            r += PIECE_WIDTH;
        }
        
        
        graphics.setColor(Color.BLACK); // set cua next piece

        int midr = (NPHEIGHT - (nextPiece.length * PIECE_WIDTH)) / 2;
        int midc = (WIDTH - (nextPiece[0].length * PIECE_WIDTH)) / 2;
        int row = 2 + midr, col = Board.boardWidth * PIECE_WIDTH + 10 + midc;

        for (int[] aNextPiece : nextPiece) {
            for (int j = 0; j < nextPiece[0].length; j++) {
                if (aNextPiece[j] != 0) {
                    graphics.setColor(Block.getColour(aNextPiece[j]));
                    graphics.drawRect(col, row, SIZEPIECE, SIZEPIECE);
                }
                col += PIECE_WIDTH;
            }
            col = Board.boardWidth * PIECE_WIDTH + 10 + midc;
            row += PIECE_WIDTH;
        } // doc piece tiep theo va paint no

        
        
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("", Font.BOLD, 12));
       
        graphics.drawString("Completed Rows: ", ROWSXLOC, ROWSYLOC);
        graphics.drawString(String.valueOf(completedRows), ROWSXLOC+20, ROWSYLOC+20); //lay diem tu completeRows de paint diem

       

       

      
        if (drawGameOver) { //paint gameover, drawGameOver la boolean true or false, true thi thuc hien paint gameover
                    	  
            graphics.setFont(new Font("", Font.BOLD, 16));
            graphics.setColor(Color.GREEN);
            graphics.drawString("GAME OVER!", Board.boardWidth * PIECE_WIDTH + 20, 300);
            graphics.drawString("Press 'x' ", Board.boardWidth * PIECE_WIDTH + 20, 320);
            graphics.drawString("to restart", Board.boardWidth * PIECE_WIDTH + 20, 340);
            
            
        
        } else if (drawPaused) { //paint pause
            graphics.setFont(new Font("", Font.BOLD, 16));
            graphics.setColor(Color.GREEN);
            graphics.drawString("PAUSED", Board.boardWidth * PIECE_WIDTH + 20, 300);
            graphics.drawString("Press 'p' ", Board.boardWidth * PIECE_WIDTH + 20, 320);
            graphics.drawString("to continue", Board.boardWidth * PIECE_WIDTH + 20, 340);
            graphics.drawString("Press 'x' ", Board.boardWidth * PIECE_WIDTH + 20, 360);
            graphics.drawString("to restart", Board.boardWidth * PIECE_WIDTH + 20, 380);
        }
        
        graphics.setFont(new Font("", Font.PLAIN, 12));
        graphics.setColor(Color.BLACK);
        

        graphics.dispose(); // huy graphic context va tat ca tai nguyen dang su dung

      
        g.drawImage(screen, 0, 0, this);
    }
   


    private int[][] boardArray;
    private int[][] piece, nextPiece;

    private int rowLocation = 0;
    private int columnLocation = 0;

    private boolean drawGameOver = false;
    private boolean drawPaused = false;


    private int completedRows = 0;
  

    private Image screen;

   

    void setArrayPiece(int[][] arr) { //ham xai trong tetris, tuong ung voi current piece cua Block
        piece = arr;
    }

    void setNextPiece(int[][] arr) { // tuong ung voi Nextpiece cua Block
        nextPiece = arr;
    }

    void setGameOver(boolean go) {
        drawGameOver = go;
    }

    void setArrayBoard(int[][] arr) {//ham xai trong tetris,
        boardArray = arr;
    }

    void setPaintLocation(int columnLocation, int rowLocation) {
        this.columnLocation = columnLocation;
        this.rowLocation = rowLocation;
    }

    void setRowsComplete(int rows) {//ham xai trong tetris, lay tu board
        completedRows = rows;
    }

  

    void setPaused(boolean isPaused) {//ham xai trong tetris, lay tu board
        drawPaused = isPaused;
    }

   
   

    public void update(Graphics g) { //neu ko update ngay thi se phai mat time render lai
        this.paint(g);
       
    }


}
