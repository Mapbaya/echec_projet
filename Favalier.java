import java.util.ArrayList;

class Favalier extends Piece {

    public Favalier(char couleur, Position position) {
        super(couleur, position);
    }

    public String getType() {
        return "favalier";
    }

    public ArrayList<Position> getDeplacementPossible(Plateau pl) {
        ArrayList<Position> retour = new ArrayList<Position>();
        
        Fou f = new Fou(this.getCouleur(), this.getPosition());
        Cavalier c = new Cavalier(this.getCouleur(), this.getPosition());
        
        retour.addAll(f.getDeplacementPossible(pl));
        retour.addAll(c.getDeplacementPossible(pl));
        
        return retour;
    }
} 