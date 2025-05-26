import java.util.ArrayList;

class Dame extends Piece{
    
    public Dame(){
	super('B', new Position());
    }

    public Dame(char couleur, Position position){
	super(couleur, position);
    }


    public String getType(){
    	return new String("dame");
    }
    

    public ArrayList<Position> getDeplacementPossible(Plateau pl)
    {
	// Une dame a les coups possibles de la tour et du fou combinés
	ArrayList<Position> retour = new ArrayList<Position>();
	
	Tour t = new Tour(this.getCouleur(), this.getPosition());
	Fou f = new Fou(this.getCouleur(), this.getPosition());

	
	ArrayList<Position> deplacementPossible = t.getDeplacementPossible(pl);
	for(Position p : deplacementPossible)
	    retour.add(p);

	deplacementPossible = f.getDeplacementPossible(pl);
	for(Position p : deplacementPossible)
	    retour.add(p);

	return retour;
    }

    
}

/**Étudiant 1
Il doit ajouter le Tavalier qui combine les déplacements possibles de la tour et du
cavalier. Cette pièce se placera à la place des pions devant les fous. */