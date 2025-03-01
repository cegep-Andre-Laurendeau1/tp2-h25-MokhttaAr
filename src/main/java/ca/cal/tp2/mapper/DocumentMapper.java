package ca.cal.tp2.mapper;

import ca.cal.tp2.dto.CDDTO;
import ca.cal.tp2.dto.DVDDTO;
import ca.cal.tp2.dto.DocumentDTO;
import ca.cal.tp2.dto.LivreDTO;
import ca.cal.tp2.model.CD;
import ca.cal.tp2.model.DVD;
import ca.cal.tp2.model.Document;
import ca.cal.tp2.model.Livre;
import ca.cal.tp2.repository.DocumentDAO;
import ca.cal.tp2.service.DocumentService;

public class DocumentMapper <T extends Document, D extends DocumentDTO> extends DocumentService<T, D> {
    private Class<T> entityClass;
    private Class<D> dtoClass;

    public DocumentMapper(Class<T> entityClass, Class<D> dtoClass) {
        super(new DocumentDAO<>(entityClass));
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected T convertDTOToEntity(D dto) {
        if (entityClass == Livre.class && dto instanceof LivreDTO) {
            LivreDTO livreDTO = (LivreDTO) dto;
            return (T) new Livre(
                    livreDTO.getDocumentID(),
                    livreDTO.getTitre(),
                    livreDTO.getNombreExemplaires(),
                    livreDTO.getISBN(),
                    livreDTO.getAuteur(),
                    livreDTO.getEditeur(),
                    livreDTO.getNombrePages()
            );
        } else if (entityClass == CD.class && dto instanceof CDDTO) {
            CDDTO cdDTO = (CDDTO) dto;
            return (T) new CD(
                    cdDTO.getDocumentID(),
                    cdDTO.getTitre(),
                    cdDTO.getNombreExemplaires(),
                    cdDTO.getArtiste(),
                    cdDTO.getDuree(),
                    cdDTO.getGenre()
            );
        } else if (entityClass == DVD.class && dto instanceof DVDDTO) {
            DVDDTO dvdDTO = (DVDDTO) dto;
            return (T) new DVD(
                    dvdDTO.getDocumentID(),
                    dvdDTO.getTitre(),
                    dvdDTO.getNombreExemplaires(),
                    dvdDTO.getDirector(),
                    dvdDTO.getDuree(),
                    dvdDTO.getRating()
            );
        }
        throw new UnsupportedOperationException("Conversion non supportée pour ce type");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected D convertEntityToDTO(T entity) {
        if (entity instanceof Livre && dtoClass == LivreDTO.class) {
            Livre livre = (Livre) entity;
            LivreDTO dto = new LivreDTO();
            dto.setDocumentID(livre.getDocumentID());
            dto.setTitre(livre.getTitre());
            dto.setNombreExemplaires(livre.getNombreExemplaires());
            dto.setISBN(livre.getISBN());
            dto.setAuteur(livre.getAuteur());
            dto.setEditeur(livre.getEditeur());
            dto.setNombrePages(livre.getNombrePages());
            return (D) dto;
        } else if (entity instanceof CD && dtoClass == CDDTO.class) {
            CD cd = (CD) entity;
            CDDTO dto = new CDDTO();
            dto.setDocumentID(cd.getDocumentID());
            dto.setTitre(cd.getTitre());
            dto.setNombreExemplaires(cd.getNombreExemplaires());
            dto.setArtiste(cd.getArtiste());
            dto.setDuree(cd.getDuree());
            dto.setGenre(cd.getGenre());
            return (D) dto;
        } else if (entity instanceof DVD && dtoClass == DVDDTO.class) {
            DVD dvd = (DVD) entity;
            DVDDTO dto = new DVDDTO();
            dto.setDocumentID(dvd.getDocumentID());
            dto.setTitre(dvd.getTitre());
            dto.setNombreExemplaires(dvd.getNombreExemplaires());
            dto.setDirector(dvd.getDirector());
            dto.setDuree(dvd.getDuree());
            dto.setRating(dvd.getRating());
            return (D) dto;
        }
        throw new UnsupportedOperationException("Conversion non supportée pour ce type");
    }
}