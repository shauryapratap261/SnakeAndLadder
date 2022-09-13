package models;

public class Board {
    int size;
    Cell [] cells;

    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size+1];
        initialiseBoard();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    private void initialiseBoard(){
        for(int i=1;i<=size;i++){
            cells[i] = new Cell(i, null);
        }
    }

}
