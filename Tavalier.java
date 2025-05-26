import java.util.ArrayList;

class Tavalier extends Piece {

    public Tavalier(char couleur, Position position) {
        super(couleur, position);
    }

    public String getType() {
        return "tavalier";
    }

    public ArrayList<Position> getDeplacementPossible(Plateau pl) {
        ArrayList<Position> retour = new ArrayList<Position>();
        
        Tour t = new Tour(this.getCouleur(), this.getPosition());
        Cavalier c = new Cavalier(this.getCouleur(), this.getPosition());

        retour.addAll(t.getDeplacementPossible(pl));
        retour.addAll(c.getDeplacementPossible(pl));
        
        return retour;
    }
}