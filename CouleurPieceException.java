public class CouleurPieceException extends RuntimeException {
    private char couleur;

    public CouleurPieceException(char couleur){
        super("Erreur dans la couleur de la pièce("+couleur+") est invalide.");
        this.couleur = couleur;
    }
    
    @Override
    public String getMessage(){
        StringBuilder msg = new StringBuilder();

        if(couleur != 'B' || couleur != 'N') {
            msg.append("Erreur dans la couleur de la pièce: ").append(couleur).append("- La couleur doit être B ou N.\n");
        }
        return msg.toString();
    }
}
