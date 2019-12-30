


import java.awt.Dimension;   
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;





class Tetris extends JFrame implements Runnable, KeyListener {
	


    private static final String TITLE = " Tetris OOP Project"; // ten
    private static final int DROP_INTERVAL = 500; // thoi gian drop ms
    
   
    private static boolean gameOver = false;    
    public static boolean isGameOver() {
		return gameOver;
	}

	private Block cp, np;                       // current piece, next piece
    private DrawingBoard drawingboard;          // Object cua class Drawing Board
    private Board board = new Board();             // Object cua class Board
    private boolean paused = false;      
    
               
    public static int rowsCleared = 0;
                      
   

	private int dropWait = DROP_INTERVAL;     // thoi gian drop ms
    private int tempRes = 0;           		// bien 1 hay 0 de quyet dinh co dat piece hay ko tuy theo class placePiece() trong Board  
    private char rotateDirection = 'R'; 	// quyet dinh huong quay
  

    
 
    
    Tetris() {
    	
    	
        this.setTitle(TITLE);


        
        drawingboard = new DrawingBoard(); 
        drawingboard.setFocusable(false); 
       
        this.add(drawingboard);

        //chon piece
        
        cp = new Block(); 
        cp.getRandom();

        
        np = new Block();
        np.getRandom();

        
        this.addKeyListener(this);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
        //set vi tri cua window
        this.setLocation(dim.width/2-this.getWidth(), dim.height/2-this.getHeight());
        
        //Toolkit abstract class are used to bind the various components to particular native toolkit implementations.
       
        start(); //class duoi, start 1 cai thread moi
        
    }

    @Override
    public int getWidth() {
        return Block.BLOCK_LENGTH * Board.boardWidth + 140; //140 la length cua ben canh
    } 

    @Override
    public int getHeight() {
        return Block.BLOCK_LENGTH * Board.boardHeight + 28;
    } 
    
    
    public void playMusic(String path, int loop) {
    	AudioInputStream music;
    	try {
			music = AudioSystem.getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(music);
			clip.start();
			if (loop==1) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
			} else {
				clip.loop(0); // repeat none
			}
			
		} catch (Exception e) {
			System.out.print(e);
		}
    } 

    public void run() {  //run function trong Runnable interface chi co the execute bang Thread  
    	
    	
        while (!gameOver) {
            if (!paused) {
            	
                tempRes = board.placePiece(cp.getCurrentState(), cp.getRowNum() + 1, cp.getColNum(), false);
                

                if (tempRes == 1) {
                    
                    piecePlaced(); //function duoi
                } else {
                    
                    cp.moveDown();
                }
                drawGame();
            }

            try {
                Thread.sleep(dropWait);  // time truoc khi tiep tuc 500ms 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
    
   

    private void piecePlaced() {
        int rows;

        board.placePiece(cp.getCurrentState(), cp.getRowNum(), cp.getColNum(), true); // dat piece trong board

        if (board.isGameOver()) {
            gameOver = true;
            drawingboard.setGameOver(true);
            
            pauseGame();
            
            
        //kt game over hay chua
            
        } else {
        	//ko thua thi tiep tuc cai moi va tinh row clear
            cp = np;
            np = new Block();
            np.getRandom();

            rows = board.calculateRowsCleared();
            rowsCleared += rows;   
          
        }
    }

    private void start() {
        gameOver = false;
        new Thread(this).start();
        
    }

    private void restart() {
       
        board = new Board();

        cp = new Block();
        cp.getRandom();

        np = new Block();
        np.getRandom();

        dropWait = DROP_INTERVAL;

        paused = false;
        drawingboard.setPaused(false);
        drawingboard.setGameOver(false);
       
    
        rowsCleared = 0;
   
        gameOver = true;

        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        start(); //Gets the game rolling again
    }

   

    private void drawGame() {
    	
    	//set tat ca moi thu cho object Drawingboard drawingboard
    	//de cai class nay paint ra
    	//tat ca tu trong object Block
       
        drawingboard.setRowsComplete(rowsCleared);   // Sets rows cleared value
        drawingboard.setNextPiece(np.getCurrentState());  // Sets the next piece
        drawingboard.setArrayBoard(board.getBoardArray());   // Sets the board
        drawingboard.setPaintLocation(cp.getColNum(), cp.getRowNum());   //Sets the location to draw the current piece
        drawingboard.setArrayPiece(cp.getCurrentState()); // Sets the current piece

        drawingboard.repaint(); // PAINT GAME drawingboard AGAIN
    }

    public void keyPressed(KeyEvent e) {
        if (paused && e.getKeyCode() != KeyEvent.VK_P && e.getKeyCode() != KeyEvent.VK_X) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            
            tempRes = board.placePiece(cp.getCurrentState(), cp.getRowNum() + 1, cp.getColNum(), false);
            //kt column o duoi truoc khi thuc hien
            if (tempRes == 1) {
                piecePlaced();
            } else {
               
                cp.moveDown();
                drawGame();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
           
            tempRes = board.placePiece(cp.rotateRight(false), cp.getRowNum(), cp.getColNum(), false);
            if (tempRes != 1) {
                if (rotateDirection == 'R') {
                    cp.rotateRight(true);
                    drawGame();
                } else {
                    cp.rotateLeft(true);
                    drawGame();
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
           
            tempRes = board.placePiece(cp.getCurrentState(), cp.getRowNum(), cp.getColNum() + 1, false);
            if (tempRes != 1) {
                cp.moveRight();
                drawGame();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
           
            tempRes = board.placePiece(cp.getCurrentState(), cp.getRowNum(), cp.getColNum() - 1, false);
            if (tempRes != 1) {
                cp.moveLeft();
                drawGame();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        	new Thread(() -> {
                playMusic("source/down.wav", 0);
            }).start();
            
            boolean movedDown = false;
            while (!movedDown) {
                tempRes = board.placePiece(cp.getCurrentState(), cp.getRowNum() + 1, cp.getColNum(), false);
                if (tempRes == 1) {
                    piecePlaced();
                    movedDown = true;
                } else {
            
                    movedDown = cp.moveDown();
                }
            }
            drawGame();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
           
            if (rotateDirection == 'R') {
                rotateDirection = 'L';
                
            } else {
                rotateDirection = 'R';
                
            }
            drawGame();
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
           
            pauseGame();
        } else if (e.getKeyCode() == KeyEvent.VK_X) {
           
            restart();
        }
    }

    void pauseGame() {
    	
        paused = !paused;
        drawingboard.setPaused(paused);
       
        drawGame();
    }



    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
