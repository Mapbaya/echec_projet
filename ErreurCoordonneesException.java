public class ErreurCoordonneesException extends RuntimeException{
    private int x,y;

    public ErreurCoordonneesException(int x, int y){
        super("Les coordonnées(" + x + ","+y+") sont invalides");
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage(){
        StringBuilder msg = new StringBuilder();

        if (x > 7 || x < 0)  {
            msg.append("Erreur dans la position X: ").append(x).append("- Les indices doivent être compris entre 0 et 7.\n");   
        }
        if (y > 7 || y < 0) {
            msg.append("Erreur dans la position Y : ").append(y).append("- Les indices doivent être compris entre 0 et 7.\n");
            
        }
        return msg.toString();      
    }
}



// Pour éviter de mettre des trycatch partout, l'exception est modifiée en exception unchecked, et plus besoin de laisser qu'elle hérite des exceptions vérifiés dans position.