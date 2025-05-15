public class RoiNonTrouveException extends RuntimeException {
    public RoiNonTrouveException(char couleur){
        super("Le roi " + (couleur == 'B' ? "blanc" : "noir") + " est introuvable.");
    }
}
