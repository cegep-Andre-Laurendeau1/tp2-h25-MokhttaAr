package ca.cal.tp2.mapper;

import ca.cal.tp2.dto.EmpruntDTO;
import ca.cal.tp2.dto.EmpruntDetailDTO;
import ca.cal.tp2.dto.EmprunteurDTO;
import ca.cal.tp2.model.Document;
import ca.cal.tp2.model.Emprunt;
import ca.cal.tp2.model.EmpruntDetail;
import ca.cal.tp2.model.Emprunteur;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmpruntMapper {

    public static EmpruntDTO toDTO(Emprunt emprunt) {
        if (emprunt == null) return null;

        EmpruntDTO dto = new EmpruntDTO();
        dto.setBorrowID(emprunt.getBorrowID());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setStatus(emprunt.getStatus());

        if (emprunt.getEmprunteur() != null) {
            dto.setEmprunteur(toDTO(emprunt.getEmprunteur()));
        }

        if (emprunt.getItems() != null) {
            List<EmpruntDetailDTO> detailDTOs = emprunt.getItems().stream()
                    .map(EmpruntMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setItems(detailDTOs);
        }

        return dto;
    }


    public static EmpruntDetailDTO toDTO(EmpruntDetail detail) {
        if (detail == null) return null;

        EmpruntDetailDTO dto = new EmpruntDetailDTO();
        dto.setLineItemID(detail.getLineItemID());
        dto.setDateRetourPrevue(detail.getDateRetourPrevue());
        dto.setDateRetourActuelle(detail.getDateRetourActuelle());
        dto.setStatus(detail.getStatus());

        if (detail.getDocument() != null) {
            dto.setDocumentTitre(detail.getDocument().getTitre());
        }

        return dto;
    }


    public static EmprunteurDTO toDTO(Emprunteur emprunteur) {
        if (emprunteur == null) return null;

        EmprunteurDTO dto = new EmprunteurDTO();
        dto.setUserID(emprunteur.getUserID());
        dto.setName(emprunteur.getName());
        dto.setEmail(emprunteur.getEmail());
        dto.setPhoneNumber(emprunteur.getPhoneNumber());

        return dto;
    }


    public static List<EmpruntDTO> toDTOList(List<Emprunt> emprunts) {
        if (emprunts == null) return new ArrayList<>();
        return emprunts.stream()
                .map(EmpruntMapper::toDTO)
                .collect(Collectors.toList());
    }
}
