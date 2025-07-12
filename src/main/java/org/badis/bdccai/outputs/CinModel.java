package org.badis.bdccai.outputs;

public record CinModel(String nomFrancais,
                       String prenomFrancais,
                       String arabicNom,
                       String arabicPrenom,
                       String sexe,
                       String cin,
                       String dateNaissance,
                       String lieuNaissance,
                       String adresse,
                       String ville,
                       String pays,
                       String dateExpiration) {
}
