public class CaseOccupeeException extends RuntimeException {
    
    public CaseOccupeeException(Piece piece, Piece pieceOccupee) {
        super("Impossible de placer le " + piece.getType() + " " + piece.getCouleur()
                + " en " + piece.getPosition() + ". Une " + pieceOccupee.getType() + " "
                + pieceOccupee.getCouleur() + " en " + pieceOccupee.getPosition() + " y est déjà présente.");
    }
}
