package anabi.utilities;
//package anabi.loader;
//
//import anabi.model.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.NoResultException;
//import javax.persistence.NonUniqueResultException;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//import javax.persistence.RollbackException;
//
///**
// *
// * @author yosamac
// */
//public class InsertDataDB {
//
//    
//    private Affiliation tableAffiliation;
//    private Author tableAuthor;
//    private Document tableDocument;
//    private Publisher tablePublisher;
//    private Journal tableJournal;
//    private TDocument tableTypeDocument;
//    private CDocument tableCategoryDocument;
//    private Funding tableFunding;
//    private HashMap<Integer, List<String>> hashListAuthorAffiliation;
//
//
//    public InsertDataDB() {
//
//    }
//
//   
//
//    public void insertAffiliation(Integer codAffiliation, String nameAffiliation, String cityAffiliation, String countryAffiliation) {
//        try {
//            tableAffiliation = null;
//
//            nameAffiliation = nameAffiliation.trim();
//            cityAffiliation = cityAffiliation.trim();
//            countryAffiliation = countryAffiliation.trim();
//
//            String queryValidateAffiliation = "SELECT a FROM Affiliation a "
//                    + "WHERE a.nameAffiliation =:nameAffiliation AND "
//                    + "a.cityAffiliation = :cityAffiliation AND "
//                    + "a.countryAffiliation = :countryAffiliation" //  + "a.nameAffiliation LIKE 'P%'"
//                    ;
//
//            System.out.println("nombre:" + nameAffiliation);
//            System.out.println("ciudad:" + cityAffiliation);
//            System.out.println("pais:" + countryAffiliation);
//            System.out.println("==========================");
//
//            tableAffiliation = em.createQuery(queryValidateAffiliation, Affiliation.class)
//                    .setMaxResults(1)
//                    .setParameter("nameAffiliation", nameAffiliation.trim())
//                    .setParameter("cityAffiliation", cityAffiliation.trim())
//                    .setParameter("countryAffiliation", countryAffiliation.trim())
//                    .getSingleResult();
//
//            System.out.println("Encontrada");
//
//            System.out.println("Filiacion existente: " + codAffiliation + " " + nameAffiliation + " -" + cityAffiliation + "-" + countryAffiliation);
//
//            changeKeyList(tableAffiliation.getCodAffiliation(), codAffiliation, hashListAuthorAffiliation);
//
//        } catch (NoResultException nre) {
//            System.out.println("Filiacion no encontrada");
//
//            tableAffiliation = new Affiliation();
//            tableAffiliation.setCodAffiliation(codAffiliation);
//            tableAffiliation.setNameAffiliation(nameAffiliation);
//            tableAffiliation.setCityAffiliation(cityAffiliation);
//            tableAffiliation.setCountryAffiliation(countryAffiliation);
//            em.getTransaction().begin();
//            em.persist(tableAffiliation);
//            em.getTransaction().commit();
//
//            System.out.println("Filiacion insertada");
//        } catch (NullPointerException npe) {
//
//            System.out.println("Lista vacia");
//        }
//
//    }
//
//    /**
//     * Inserta un autor en la base de datos.
//     *
//     * @param codAuthor
//     * @param nameAU
//     * @param nameFN
//     * @param codAuthorRP
//     */
//    public void insertAuthor(Integer codAuthor, String nameAU, String nameFN, String codAuthorRP) {
//        tableAuthor = null;
//
//        if (nameAU.contains("'")){
//            nameAU = nameAU.replace("'", ".");
//        }
//        
//        nameAU = nameAU.trim();
//        nameFN = nameFN.trim();
//        codAuthorRP = codAuthorRP.trim();
//
//        String queryValidateAuthor = "SELECT a FROM Author a "
//                + "WHERE a.nameAu =:nameAuthor AND "
//                + "a.nameFn = :nameAuthorFN AND "
//                + "a.codAuthorRp = :codAuthorRP";
//
//        // Validando para no insertar autores repetidos
//        try {
//            tableAuthor = em.createQuery(queryValidateAuthor, Author.class)
//                    .setMaxResults(1)
//                    .setParameter("nameAuthor", nameAU)
//                    .setParameter("nameAuthorFN", nameFN)
//                    .setParameter("codAuthorRP", codAuthorRP)
//                    .getSingleResult();
//
//            System.out.println("Autor existente: " + codAuthor + " " + nameFN);
//
//            //  listCodAuthor.put(codAuthor, tableAuthor.getCodAuthor());
//        } catch (NoResultException nre) {
//            System.out.println(" Author no encontrado");
//
//            tableAuthor = new Author();
//            tableAuthor.setCodAuthor(codAuthor);
//            tableAuthor.setNameAu(nameAU);
//            tableAuthor.setNameFn(nameFN);
//            tableAuthor.setCodAuthorRp(codAuthorRP);
//            em.getTransaction().begin();
//            em.persist(tableAuthor);
//            em.getTransaction().commit();
//
//            System.out.println("Autor insertado");
//        }
//    }
//
//    /**
//     * Inserta el autor corresponsal en la base datos.
//     *
//     * @param codAuthor
//     * @param nameAU
//     * @param nameFN
//     * @param email
//     * @param codAuthorRP
//     */
//    public void insertAuthorRP(Integer codAuthor, String nameAU, String nameFN, String email, String codAuthorRP) {
//
//        tableAuthor = null;
//        try {
//
//            nameAU = nameAU.trim();
//            nameFN = nameFN.trim();
////            email = email.trim();
//            codAuthorRP = codAuthorRP.trim();
//
//            String queryValidateAuthorRP = "SELECT a FROM Author a "
//                    + "WHERE a.nameAu =:nameAuthor AND "
//                    + "a.nameFn = :nameAuthorFN AND "
//                    + "a.codAuthorRp = :codAuthorRP";
//
//            tableAuthor = em.createQuery(queryValidateAuthorRP, Author.class)
//                    .setMaxResults(1)
//                    .setParameter("nameAuthor", nameAU)
//                    .setParameter("nameAuthorFN", nameFN)
//                    .setParameter("codAuthorRP", codAuthorRP)
//                    .getSingleResult();
//
//            System.out.println("Autor existente: " + codAuthor + " - nombre:" + nameAU + " - nombreFN:" + nameFN + " - email:" + email);
////            listCodAuthor.put(codAuthor, tableAuthor.getCodAuthor());
//
//            //changeKeyList(tableAuthor.getCodAuthor(), codAuthor, hashListAuthorAffiliation);
//        } catch (NoResultException nre) {
//            tableAuthor = new Author();
//            tableAuthor.setCodAuthor(codAuthor);
//            tableAuthor.setNameAu(nameAU);
//            tableAuthor.setNameFn(nameFN);
//            tableAuthor.setEmailEm(email);
//            tableAuthor.setCodAuthorRp(codAuthorRP);
//            em.getTransaction().begin();
//            em.persist(tableAuthor);
//            em.getTransaction().commit();
//            System.out.println("Autor Corresponsal insertado");
//        } catch (NullPointerException npe) {
//            System.out.println("datos null");
//        }
//
//    }
//
//    public void setListAuthorAffiliation(HashMap<Integer, List<String>> hashAffiliationAuthor) {
//
//        this.hashListAuthorAffiliation = hashAffiliationAuthor;
//    }
//
////    public boolean findCodAffiliation(Integer codAffiliation) {
////        boolean result = false;
////
////        for (Integer key : hashListAuthorAffiliation.keySet()) {
////            if (key == codAffiliation) {
////                result = true;
////            }
////        }
////        return result;
////    }
//    public void changeKeyList(Integer codeNew, Integer codeOld, HashMap<Integer, List<String>> listToFind) {
//        
//        try {
//            for (int key : listToFind.keySet()) {
//
//                if (key == codeOld) {
//
//                    List<String> listValueNew = new ArrayList<>();
//                    listValueNew.addAll(listToFind.get(key));
//                    List<String> listSaved = listToFind.get(codeNew);
//                    listValueNew.addAll(listSaved);
//                    listToFind.remove(key);
//
//                    listToFind.put(codeNew, listValueNew);
//                    break;
//                } else {
//                }
//            }
//        } catch (NullPointerException e) {
//            System.out.println("No se encuentra en la lista");
//        }
//
//    }
//
//    
//    /**
//     * Inserta una institucion en la base de datos. Recibe una lista de autores
//     * y filiaciones almacenadas en un array asociativo.
//     *
//     */
//    public void insertAffiliationAuthor() {
//
//        Author dataAuthor = new Author();
//        Affiliation dataAffiliation = new Affiliation();
//        List<Author> listAuthorRepeat = new ArrayList<>();
//        List<Affiliation> listAffiliationRepeat = new ArrayList<>();
//
//        for (Integer clave : hashListAuthorAffiliation.keySet()) {
//
//            List<Affiliation> listAffiliations = new ArrayList<>();
//            List<Author> listAuthors = new ArrayList<>();
//
//            dataAffiliation = em.find(Affiliation.class, clave);
//
//            for (String nameFN : hashListAuthorAffiliation.get(clave)) {
//
//                try {
//                    nameFN = nameFN.trim();
//
//                    dataAuthor = em.createNamedQuery("Author.findByNameFn", Author.class)
//                            .setMaxResults(1)
//                            .setParameter("nameFn", nameFN)
//                            .getSingleResult();
//
//                } catch (NoResultException nre) {
//                    System.out.println("Autor no encontrado: " + nameFN);
//                } catch (NonUniqueResultException nure) {
//
//                    listAuthorRepeat.add(dataAuthor);
//                }
//
//                if (dataAuthor != null && dataAffiliation != null) {
//                    listAuthors.add(dataAuthor);
//                    listAffiliations.add(dataAffiliation);
//                }
//            }
//
//            try {
//                dataAffiliation.setAuthorList(listAuthors);
//                dataAuthor.setAffiliationList(listAffiliations);
//                em.getTransaction().begin();
//                em.persist(dataAffiliation);
//                em.persist(dataAuthor);
//                em.getTransaction().commit();
//
//            } catch (NullPointerException e) {
//                System.out.println("Fallo en filiacion_autor");
//            } catch (RollbackException re) {
//                // em.getTransaction().rollback();
//                listAuthorRepeat.add(dataAuthor);
//                listAffiliationRepeat.add(dataAffiliation);
//                System.out.println("agregadada filiacion repetida");
//            }
//        }
//    }
//
//    /**
//     * Inserta una editorial a la base de datos.
//     *
//     * @param codPublisher
//     * @param namePU
//     * @param addressPA
//     * @param cityPI
//     */
//    public void insertPublisher(Integer codPublisher, String namePU, String addressPA, String cityPI) {
//
//        tablePublisher = new Publisher();
//        tablePublisher.setCodPublisher(codPublisher);
//        tablePublisher.setNamePu(namePU);
//        tablePublisher.setAddressPa(addressPA);
//        tablePublisher.setCityPi(cityPI);
//        em.getTransaction().begin();
//        em.persist(tablePublisher);
//        em.getTransaction().commit();
//
//    }
//
//    /**
//     * Inserta una revista a la base de datos.
//     *
//     * @param codJournal
//     * @param nameJournalSO
//     * @param issnSN
//     * @param webScienceWC
//     * @param researchSC
//     * @param issueIS
//     * @param sourceAbbrJ9
//     * @param sourceAbbrJI
//     * @param codPublisher
//     */
//    public void insertJournal(Integer codJournal, String nameJournalSO, String issnSN, String webScienceWC, String researchSC, String issueIS, String sourceAbbrJ9, String sourceAbbrJI, Integer codPublisher) {
//
//        tableJournal = new Journal();
//        tableJournal.setCodJournal(codJournal);
//        tableJournal.setNameSo(nameJournalSO);
//        tableJournal.setIssnSo(issnSN);
//        tableJournal.setWebSciencewc(webScienceWC);
//        tableJournal.setResearchAreasc(researchSC);
//        tableJournal.setIssueIs(issueIS);
//        tableJournal.setSourceAbbrej9(sourceAbbrJ9);
//        tableJournal.setSourceAbbreji(sourceAbbrJI);
//
//        try {
//            tablePublisher = em.find(Publisher.class, codPublisher);
//            if (tablePublisher != null) {
//                tableJournal.setCodPublisher(tablePublisher);
//                em.getTransaction().begin();
//                em.persist(tableJournal);
//                em.getTransaction().commit();
//
//            } else {
//                System.out.println("Editorial invalida");
//            }
//        } catch (Exception e) {
//        }
//
//    }
//    
//    
//
//    /**
//     * Inserta una fundacion que financia el documento publicado.
//     *
//     * @param codFunding
//     * @param nameFundingFU
//     * @param descriptionFundingFX
//     */
//    public void insertFunding(Integer codFunding, String nameFundingFU, String descriptionFundingFX) {
//
//        if (codFunding != null) {
//
//            tableFunding = new Funding();
//            tableFunding.setCodFunding(codFunding);
//            tableFunding.setNameFu(nameFundingFU);
//            tableFunding.setDescriptionFx(descriptionFundingFX);
//        } else {
//
//            tableFunding = new Funding();
//            tableFunding.setCodFunding(0);
//            tableFunding.setNameFu(null);
//            tableFunding.setDescriptionFx(null);
//        }
//
//        try {
//            em.getTransaction().begin();
//            em.persist(tableFunding);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//        }
//
//    }
//    
//    
//
//    /**
//     * Inserta un documento en la base de datos.
//     *
//     * @param codArticleUT
//     * @param titleTI
//     * @param languageLA
//     * @param abstractAB
//     * @param volumenVL
//     * @param yearPublicationPY
//     * @param datePublicationPD
//     * @param titleSourceDI
//     * @param indexkewordsID
//     * @param authKewordsDE
//     * @param citedCountNR
//     * @param citedTotalZ9
//     * @param citedReferenceTC
//     * @param pageCountPG
//     * @param pageBeginBP
//     * @param pageEndEP
//     * @param codTypeDocument
//     * @param codCategoryDocument
//     * @param codTDocument
//     * @param codJournal
//     * @param codOrgFinancia
//     */
//    public void insertDocument(String codArticleUT, String titleTI, String languageLA, String abstractAB,
//            String volumenVL, String yearPublicationPY, String datePublicationPD, String titleSourceDI,
//            String indexkewordsID, String authKewordsDE, String citedCountNR, String citedTotalZ9, String citedReferenceTC,
//            String pageCountPG, String pageBeginBP, String pageEndEP, Integer codTypeDocument, Integer codCategoryDocument, Integer codJournal, Integer codOrgFinancia) {
//
//        tableDocument = new Document();
//        tableDocument.setCodDocumentUt(codArticleUT);
//        tableDocument.setTitleTi(titleTI);
//        tableDocument.setLanguageTi(languageLA);
//        tableDocument.setAbstractAb(abstractAB);
//        tableDocument.setVolumenVl(volumenVL);
//        tableDocument.setYearPublicationPy(yearPublicationPY);
//        tableDocument.setDatePublicationPd(datePublicationPD);
//        tableDocument.setTitleSourceDi(titleSourceDI);
//        tableDocument.setIndexKewordsId(indexkewordsID);
//        tableDocument.setAuthorKewordsDe(authKewordsDE);
//        tableDocument.setCitedCountNr(citedCountNR);
//
//        try {
//            tableDocument.setCitedTotalZ9(Integer.parseInt(citedTotalZ9));
//            tableDocument.setCitedReferenceTc(Integer.parseInt(citedReferenceTC));
//            tableDocument.setPageCountPg(Integer.parseInt(pageCountPG));
//            tableDocument.setPageBp(Integer.parseInt(pageBeginBP));
//            tableDocument.setPageEp(Integer.parseInt(pageEndEP));
//        } catch (Exception e) {
//            System.out.println("Error: citas tc>" + citedReferenceTC + " z9> " + citedTotalZ9 + " bp> " + pageBeginBP + " ep> " + pageEndEP + " pg> " + pageCountPG);
//        }
//
//        tableTypeDocument = em.find(TDocument.class, codTypeDocument);
//        tableJournal = em.find(Journal.class, codJournal);
//        tableFunding = em.find(Funding.class, codOrgFinancia);
//        tableCategoryDocument = em.find(CDocument.class, codCategoryDocument);
//
//        if (tableTypeDocument != null) {
//            tableDocument.setCodTDocument(tableTypeDocument);
//        } else {
//            System.out.println(" Tipo de documento invalido");
//        }
//
//        if (tableJournal != null) {
//            tableDocument.setCodJournal(tableJournal);
//        } else {
//            System.out.println("Revista invalida");
//        }
//
//        if (tableFunding != null) {
//            tableDocument.setCodFunding(tableFunding);
//        } else {
//            System.out.println(" Organizacion financiera invalida");
//
//        }
//        if (tableCategoryDocument != null) {
//            tableDocument.setCodCDocument(tableCategoryDocument);
//        } else {
//            System.out.println(" Categoria de documento invalida");
//        }
//
//        em.getTransaction().begin();
//        em.persist(tableDocument);
//        em.getTransaction().commit();
//        System.out.println("Documento registrado");
//    }
//
//    
//    /**
//     * Insertar codigo de documento y codigo de autor en tabla DocumentAuthor
//     *
//     * @param hashDocumentAuthor
//     */
//    public void insertDocumentAuthor(HashMap<String, List<Integer>> hashDocumentAuthor) {
//
//        Author dataAuthor = new Author();
//        Document dataDocument = new Document();
//
//        for (String clave : hashDocumentAuthor.keySet()) {
//
//            List<Document> listDocument = new ArrayList<>();
//            List<Author> listAuthors = new ArrayList<>();
//
//            dataDocument = em.find(Document.class, clave);
//
//            for (Integer codAuthor : hashDocumentAuthor.get(clave)) {
//
//                try {
//                    dataAuthor = em.createNamedQuery("Author.findByCodAuthor", Author.class)
//                            .setParameter("codAuthor", codAuthor)
//                            .getSingleResult();
//                } catch (NoResultException nre) {
//                    System.out.println("Autor no encontrado: " + codAuthor);
//                }
//
//                if (dataDocument != null && dataAuthor != null) {
//                    listDocument.add(dataDocument);
//                    listAuthors.add(dataAuthor);
//                }
//            }
//
//            try {
//                dataAuthor.setDocumentList(listDocument);
//                dataDocument.setAuthorList(listAuthors);
//                em.getTransaction().begin();
//                em.persist(dataAuthor);
//                em.persist(dataDocument);
//                em.getTransaction().commit();
//            } catch (Exception e) {
//                System.out.println("Fallo");
//            }
//
//        }
//    }
//
//    public void deleteDataDatabase() {
//        try {
//            em.getTransaction().begin();
//            Query deleteDocument = em.createNativeQuery("DELETE FROM document");
//            Query deleteJournal = em.createNativeQuery("DELETE FROM journal");
//            Query deletePublisher = em.createNativeQuery("DELETE FROM publisher");
//            Query deleteFunding = em.createNativeQuery("DELETE FROM funding");
//            Query deleteAuthor = em.createNativeQuery("DELETE FROM author");
//            Query deleteAffiliation = em.createNativeQuery("DELETE FROM affiliation");
//
//            deleteDocument.executeUpdate();
//            deleteJournal.executeUpdate();
//            deletePublisher.executeUpdate();
//            deleteFunding.executeUpdate();
//            deleteAuthor.executeUpdate();
//            deleteAffiliation.executeUpdate();
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
