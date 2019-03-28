import java.io.*;
import java.util.StringTokenizer;

public class FindWhitePointsInImage {

    private KWArrayList<KWArrayList<Integer>> matrix;
    private int[][] whitePoints; /* bulunan noktalar*/
    private int sizeWhitePoints;
    private int col;
    private int row;
    private String path;

    public FindWhitePointsInImage(String path) {
        this.path = path;
        findRowAndCol();
        newMatrix(getRow(), getCol());
    }

    private void newMatrix(int r, int c){
        this.matrix = new KWArrayList<KWArrayList<Integer>>(r);
        /*for (int i = 0; i < r; i++) {
            System.out.println("i:" + i);
            this.matrix.set(i, new KWArrayList<Integer>(c));
        }*/

        whitePoints = new int[r*c][2];
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getSizeWhitePoints() {
        return sizeWhitePoints;
    }

    public void setSizeWhitePoints(int sizeWhitePoints) {
        this.sizeWhitePoints = sizeWhitePoints;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        if(path != null)
            this.path = path;
    }

    private void findRowAndCol(){
        String line = null;
        int c = 0;
        int r = 0;
        try {
            FileReader fileReader = new FileReader(getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                c=0;
                StringTokenizer tok = new StringTokenizer(line, " ");
                while (tok.hasMoreElements()) {
                    int t = Integer.parseInt((String) tok.nextElement());
                    c++;
                }
                r++;
            }
            bufferedReader.close();
            setRow(r);
            setCol(c);
            //System.out.println("row:" + getRow() + "\tcol:" + getCol());
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + getPath() + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + getPath() + "'");
            // ex.printStackTrace();
        }
    }


    public void fillMatrix() throws IOException {
        String line = null;
        try {
            FileReader fileReader = new FileReader(getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                KWArrayList<Integer> temp = new KWArrayList<Integer>(getCol());
                StringTokenizer tok = new StringTokenizer(line, " ");
                while (tok.hasMoreElements()) {
                    temp.add(Integer.parseInt((String) tok.nextElement()));
                }
                matrix.add(temp);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + getPath() + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + getPath() + "'");
            // ex.printStackTrace();
        }
    }

    public int getMatrixElement(int i, int j){
        if (i >= 0 && j >= 0 && i < getRow() && j < getCol())
            return matrix.get(i).get(j);
        else
            return -1;
    }

    public void setMatrixElement(int r, int c, int elm){
        matrix.get(r).set(c, elm);
    }

    public void printMatrix(){
        for (int i = 0; i < getRow(); i++){
            for (int j = 0; j < getCol(); j++){
                System.out.println(getMatrixElement(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void goAroundList(){
        int indexOfWhitePoints = 0;
        for (int i = 0; i < getRow(); i++){
            for (int j = 0; j < getCol(); j++){
                if(getMatrixElement(i, j) == 0){
                    whitePoints[indexOfWhitePoints][0] = i;
                    whitePoints[indexOfWhitePoints][1] = j;
                    setMatrixElement(i, j, 1);
                    indexOfWhitePoints++;

                    if(i-1 >= 0 && j-1 >=0 && getMatrixElement(i-1, j-1) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i-1;
                        whitePoints[indexOfWhitePoints][1] = j-1;
                        setMatrixElement(i-1, j-1, 1);
                        indexOfWhitePoints++;
                    }
                    else if(i >= 0 && j-1 >=0 && getMatrixElement(i, j-1) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i;
                        whitePoints[indexOfWhitePoints][1] = j-1;
                        setMatrixElement(i, j-1, 1);
                        indexOfWhitePoints++;
                    }
                    else if(i+1 < getRow() && j-1 >=0 && getMatrixElement(i+1, j-1) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i+1;
                        whitePoints[indexOfWhitePoints][1] = j-1;
                        setMatrixElement(i+1, j-1, 1);
                        indexOfWhitePoints++;
                    }
                    else if(i+1 < getRow() && j >=0 && getMatrixElement(i+1, j) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i+1;
                        whitePoints[indexOfWhitePoints][1] = j;
                        setMatrixElement(i+1, j, 1);
                        indexOfWhitePoints++;
                    }
                    else if(i+1 < getRow() && j+1 < getCol() && getMatrixElement(i+1, j+1) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i+1;
                        whitePoints[indexOfWhitePoints][1] = j+1;
                        setMatrixElement(i+1, j+1, 1);
                        indexOfWhitePoints++;
                    }
                    else if(i >= 0 && j+1 < getCol() && getMatrixElement(i, j+1) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i;
                        whitePoints[indexOfWhitePoints][1] = j+1;
                        setMatrixElement(i, j+1, 1);
                        indexOfWhitePoints++;
                    }
                    else if(i-1 >= 0 && j+1 < getCol() && getMatrixElement(i-1, j+1) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i-1;
                        whitePoints[indexOfWhitePoints][1] = j+1;
                        setMatrixElement(i-1, j+1, 1);
                        indexOfWhitePoints++;
                    }
                    else if(i-1 >= 0 && j >= 0 && getMatrixElement(i-1, j) == 0) {
                        whitePoints[indexOfWhitePoints][0] = i-1;
                        whitePoints[indexOfWhitePoints][1] = j;
                        setMatrixElement(i-1, j, 1);
                        indexOfWhitePoints++;
                    }
                }
            }
        }
        setSizeWhitePoints(indexOfWhitePoints);
    }

    public void printWhitePoints(){
        System.out.print("White Point contains the locations (in column, row format): ");
        for (int i = 0; i < getSizeWhitePoints(); i++){
            System.out.print("(" + whitePoints[i][1] + ", " + whitePoints[i][0] + ") ");
        }
        System.out.println("\nTotal white points:" + getSizeWhitePoints());
    }

    public void doOperation() throws IOException {
        fillMatrix();
        goAroundList();
        printWhitePoints();
    }
}
