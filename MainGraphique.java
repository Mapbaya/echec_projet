import java.util.ArrayList;

import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;

class MainGraphique{

    public static final int tailleCase = 75;

    public static void dessinerPlateau(Fenetre f, Plateau p, char couleurQuiJoue){
	f.effacer();

	// echiquier
	for(int i = 0 ; i < 8 ; i++)
	    for(int j = 0; j < 8 ; j++){
		if( (i+j)%2 == 1)
		    f.ajouter(new Carre(Couleur.BLANC,new Point(i*MainGraphique.tailleCase,j*MainGraphique.tailleCase), MainGraphique.tailleCase, true));
		else
		    f.ajouter(new Carre(Couleur.GRIS_FONCE,new Point(i*MainGraphique.tailleCase,j*MainGraphique.tailleCase), MainGraphique.tailleCase, true));
	    }

	// Partie haute
	f.ajouter(new Rectangle(new Couleur(68, 158, 255), new Point(0, MainGraphique.tailleCase*8), MainGraphique.tailleCase*8, MainGraphique.tailleCase*2, true));
	if(couleurQuiJoue == 'B'){
	    f.ajouter(new Texte(Couleur.JAUNE, new String("A blanc de jouer"), new Font("Calibri", Font.TYPE1_FONT, 40), new Point(MainGraphique.tailleCase*4, (int)(MainGraphique.tailleCase*9.5))));
	    f.ajouter(new Cercle(Couleur.BLANC, new Point((int)(MainGraphique.tailleCase*1.5), (int)(MainGraphique.tailleCase*9.5)), (int)(MainGraphique.tailleCase*0.45), true));
	}else{
	    f.ajouter(new Texte(Couleur.JAUNE, new String("A noir de jouer"), new Font("Calibri", Font.TYPE1_FONT, 40), new Point(MainGraphique.tailleCase*4, (int)(MainGraphique.tailleCase*9.5))));
	    f.ajouter(new Cercle(Couleur.NOIR, new Point((int)(MainGraphique.tailleCase*1.5), (int)(MainGraphique.tailleCase*9.5)), (int)(MainGraphique.tailleCase*0.45), true));
	}

	// pieces banches
	ArrayList<Piece> listeB = p.getPiecesBlanches();

	for(Piece pi : listeB)
	    f.ajouter(new Texture("./images/"+pi.getNomLong()+".png", new Point(pi.getPosition().getX()*MainGraphique.tailleCase, pi.getPosition().getY()*MainGraphique.tailleCase), MainGraphique.tailleCase, MainGraphique.tailleCase));


	// pieces noires
	ArrayList<Piece> listeN = p.getPiecesNoires();
	
	for(Piece pi : listeN)
	    f.ajouter(new Texture("./images/"+pi.getNomLong()+".png",new Point(pi.getPosition().getX()*MainGraphique.tailleCase, pi.getPosition().getY()*MainGraphique.tailleCase), MainGraphique.tailleCase, MainGraphique.tailleCase));

	
	// Affichage de la mise en échec
	if(p.estEchec('B')){
	    Position posR = p.getRoiBlanc().getPosition();
	    f.ajouter(new Carre(Couleur.ROUGE,new Point(posR.getX()*MainGraphique.tailleCase,posR.getY()*MainGraphique.tailleCase), MainGraphique.tailleCase, true));
	    f.ajouter(new Texture("./images/roi_B.png",new Point(posR.getX()*MainGraphique.tailleCase, posR.getY()*MainGraphique.tailleCase), MainGraphique.tailleCase, MainGraphique.tailleCase));
	    f.ajouter(new Texte(Couleur.ROUGE, new String("Roi blanc échec"), new Font("Calibri", Font.TYPE1_FONT, 40), new Point(MainGraphique.tailleCase*4, (int)(MainGraphique.tailleCase*8.5))));
	}
	if(p.estEchec('N')){
	    Position posR = p.getRoiNoir().getPosition();
	    f.ajouter(new Carre(Couleur.ROUGE,new Point(posR.getX()*MainGraphique.tailleCase,posR.getY()*MainGraphique.tailleCase), MainGraphique.tailleCase, true));
	    f.ajouter(new Texture("./images/roi_N.png",new Point(posR.getX()*MainGraphique.tailleCase, posR.getY()*MainGraphique.tailleCase), MainGraphique.tailleCase, MainGraphique.tailleCase));
	    f.ajouter(new Texte(Couleur.ROUGE, new String("Roi noir échec"), new Font("Calibri", Font.TYPE1_FONT, 40), new Point(MainGraphique.tailleCase*4, (int)(MainGraphique.tailleCase*8.5))));
	}
    }

    

    public static void afficheDepPossible(Fenetre f, ArrayList<Position> l){
	for(Position p : l){
	    int indiceX = p.getX();
	    int indiceY = p.getY();
	    f.ajouter(new Cercle(Couleur.ROUGE,new Point(indiceX*MainGraphique.tailleCase+(MainGraphique.tailleCase/2), indiceY*MainGraphique.tailleCase+(MainGraphique.tailleCase/2)), (int)(MainGraphique.tailleCase*0.45), false));
	}
    }
    
    public static void main(String[] args) {
		boolean modeCheat = true; // Par défaut, on utilise le mode cheat
		Plateau p = new Plateau(modeCheat);
		Fenetre f = new Fenetre("Jeu d'échecs" + (modeCheat ? " (Mode Cheat)" : ""), 8 * MainGraphique.tailleCase, 10 * MainGraphique.tailleCase);
		Souris souris = f.getSouris();
		char couleurQuiJoue = 'B';  // Au début, c'est les blancs qui jouent
	
		Piece selectionne1 = null;
		Position selectionne2 = null;
	
		dessinerPlateau(f, p, couleurQuiJoue);
		f.rafraichir();
	
		while (true) {
			// Sélectionner la première pièce (toujours la pièce du joueur actuel)
			while (selectionne1 == null) {
				while (!souris.getClicGauche()) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
				int indX = souris.getPosition().getX() / MainGraphique.tailleCase;
				int indY = souris.getPosition().getY() / MainGraphique.tailleCase;
				if ((indX >= 0) && (indX <= 7) && (indY >= 0) && (indY <= 7)) {
					Piece pieceSelectionnee = p.getCase(indX, indY);
					if (pieceSelectionnee != null && pieceSelectionnee.getCouleur() == couleurQuiJoue) {
						selectionne1 = pieceSelectionnee; // Première sélection de la pièce
						// Affiche les déplacements possibles de la pièce
						afficheDepPossible(f, pieceSelectionnee.getDeplacementPossible(p));
					}
				}
			}
	
			// Sélectionner la deuxième case (cible de déplacement)
			while (selectionne2 == null) {
				while (!souris.getClicGauche()) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
				int indX = souris.getPosition().getX() / MainGraphique.tailleCase;
				int indY = souris.getPosition().getY() / MainGraphique.tailleCase;
	
				// Vérifie si la case est valide
				if ((indX >= 0) && (indX <= 7) && (indY >= 0) && (indY <= 7)) {
					selectionne2 = new Position(indX, indY);
				}
			}
	
			// Vérifie si le déplacement est valide et effectue le déplacement
			try {
				// Lève une exception si le coup est invalide
				ArrayList<Position> possibilites = selectionne1.getDeplacementPossible(p);
				if (!possibilites.contains(selectionne2)) {
					throw new ErreurDeplacementException("Déplacement invalide de " + selectionne1 + " à " + selectionne2);
				}
	
				// Effectue le déplacement
				p.deplacer(selectionne1.getPosition(), selectionne2);
	
				// Réinitialise la sélection pour le prochain tour
				selectionne1 = null;
				selectionne2 = null;
	
				// Change le joueur après le déplacement
				couleurQuiJoue = (couleurQuiJoue == 'B') ? 'N' : 'B'; // Alterne entre 'B' et 'N'
	
				// Dessiner à nouveau le plateau après chaque mouvement
				dessinerPlateau(f, p, couleurQuiJoue);
				f.rafraichir();
	
			} catch (ErreurDeplacementException e) {
				// Si une exception est levée, on affiche le message et on recommence
				System.out.println(e.getMessage());
				selectionne2 = null; // Redemande la case d'arrivée
			}
		}
	}
	
}
