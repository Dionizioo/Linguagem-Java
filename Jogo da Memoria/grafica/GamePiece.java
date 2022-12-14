package grafica;

import javax.swing. JToggleButton;

public class GamePiece extends JToggleButton {
    private int number;
    private boolean isSelected;

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public int getNumber() {
        return number;
    }

    public GamePiece(int number) {
        this.number = number;
        setBounds(15,15,15,15);
    }    

    public void showPiece() {
        String textNumber = Integer.toString(number);
        setIsSelected(true);
        setText(textNumber);
    }

    public void hidePiece(){
        setIsSelected(false);
        setText("?");
    }

}
