package model.board;

public class BoardParser {
	
		// the following four methods were moved from the BoardParserV1 (Benjamin) into this V2 (Berni) because TreeBuilder requires them
	
		public static String mergeArrIntoString(int[] boardArr){
			String temp = "";		
			for(int i = 0; i < boardArr.length; i++)
				temp += boardArr[i];		
			return temp;
		}
		
		public static int getEmptyFields(int[] arr) {
			int i = 0;
			for (int b : arr)
				if (b == 0)
					++i;

			return i;
		}
		
		public static int[] getEmptyIndizeOpt(int[] arr){		
			int[] temp = new int[getEmptyFields(arr)];
			int at = 0; //temp index
			for(int i = 0 ; i < arr.length;i++)
				if(arr[i] == 0)
					temp[at++] = i;
					
			return temp;
		}
		
		public static int[] boardArrCopy(int[] boardArr){		
			int[] copy = new int[boardArr.length];
			for(int i = 0; i < boardArr.length; i++)
				copy[i] = boardArr[i];	
			return copy;
		}
        
        int totalCount = 0;
        
        int winCount = 1;
        int lastVal = -1;
        int[] arr;
        int len;
        int wLen;   
        int winner = 0;  
        
        public int getWinner(){
        	return winner;
        }
        
        private void flush(){
                lastVal = -1;
                winCount = 1;
        }
        
        private void nextVal(int val){
                totalCount ++;
                
                if(val == lastVal && val != 0)
                        winCount ++;
                else{
                        lastVal = val;
                        winCount = 1;
                }                
                if(winCount == wLen)
                	winner = lastVal;
                        //System.out.println(lastVal + " has won");
        }
        
        private void goDiag(int session, int inSessionPointer){                
                flush();
                while(inSessionPointer < len && session < len){
                        int pos = session++ * len + inSessionPointer++; 
                        nextVal(arr[pos]);
                }
        }
        
        private void mirrorB(){ //along the vertical axe!
                int[] newArr = new int[arr.length];                        
                int count = 0;                
                for(int i = 0; i < len; i++)
                        for(int j = len - 1; j >= 0; j--)
                                newArr[count++] = arr[(len * i) + j];                
                arr = newArr;
        }        
        
        public void testBoardForWinner(int[] arr, int len, int wLen) {
                totalCount = 0;
                winner = 0;
                flush();
                
                this.arr = arr;
                this.len = len;
                this.wLen = wLen;
                
                //ROWS                
                for(int row = 0; row < len; row++){
                        flush();
                        for(int i = row * len; i < row * len + len; i ++)                                
                                nextVal(arr[i]);
                }
                
                //COLUMNS        
                for(int col = 0; col < len; col++){
                        flush();
                        for(int i = col; i < arr.length; i += len)                        
                                nextVal(arr[i]);
                }

                //DIAGONAL        
                //slash
                for(int i = 0; i < len; i ++)                
                        goDiag(0, i);
                for(int i = 1; i < len; i++)
                        goDiag(i, 0);
                
                //backslash
                mirrorB();                
                for(int i = 0; i < len; i ++)        
                        goDiag(0, i);
                for(int i = 1; i < len; i++)
                        goDiag(i, 0);
                
        }       
        
/* TESTING       
				public static void main(String...args) {
                      
                int[] arr1 = new int[] {
                                        0,1,2,2, 
                                        1,1,0,1, 
                                        2,0,2,1, 
                                        2,0,0,1
                                };

                int[] arr2 = new int[] {
                                0,1,0,0,1,2,
                                1,1,2,2,1,0,
                                1,2,1,1,0,2, 
                                0,1,2,0,1,2,
                                0,1,2,1,1,0,
                                1,2,1,1,2,2
                };
                

                int[] arr3 = new int[] {
                                2,2,1,1,2,2,
                                1,1,2,2,1,1,
                                2,2,1,1,2,2, 
                                1,1,2,2,1,1,
                                2,2,1,1,2,2,
                                1,1,2,2,1,1
                };
                
                int[] arr4 = {2, 0, 1, 
                              1, 2, 0, 
                              0, 1, 2};
                
                int[] arr5 = {0, 1, 2, 1, 
                              2, 1, 1, 0, 
                              2, 2, 1, 1, 
                              2, 1, 2, 1};
                
                BoardParserV2 dings = new BoardParserV2();
                        
                dings.testBoardForWinner(arr1, 4, 3);
                System.out.println("\n\n");                
                dings.testBoardForWinner(arr2, 6, 3);
                System.out.println("\n\n");        
                dings.testBoardForWinner(arr3, 6, 3);
                System.out.println("\n\n");        
                dings.testBoardForWinner(arr4, 3, 3);        
                System.out.println("\n\n");        
                dings.testBoardForWinner(arr5, 4, 3);        

                System.out.println("\ntotal count for last testBoardForWinner: " + dings.totalCount);
        }*/
}