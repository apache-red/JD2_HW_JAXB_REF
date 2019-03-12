package com.redcompany.red.jaxbcategory.service.impl;

import com.redcompany.red.jaxbcategory.entity.Category;
import com.redcompany.red.jaxbcategory.entity.MovieType;
import com.redcompany.red.jaxbcategory.entity.SubCategoryType;
import com.redcompany.red.jaxbcategory.service.XmlService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static com.redcompany.red.jaxbcategory.service.util.ServiceConstantStorage.JAXB_CONTEXT_PATH;
import static com.redcompany.red.jaxbcategory.service.util.ServiceConstantStorage.XML_FILE_PATH;

public class AddNewsService implements XmlService {

    private static final AddNewsService instance = new AddNewsService();

    @Override
    public boolean doService(HashMap<String, String> param) {
        try {
            marshalling(param);
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void marshalling(HashMap<String, String> param) throws JAXBException {
        int idSubCategory;
        File file = new File(XML_FILE_PATH);
        JAXBContext context = null;
        context = JAXBContext.newInstance(JAXB_CONTEXT_PATH);
        idSubCategory = getIdSubcategory(file,context);
        Marshaller marshaller  = context.createMarshaller();


        marshaller.marshal(  preparationEntity(param, file, context,idSubCategory), file);



    }
    private SubCategoryType preparationEntity(HashMap<String, String> param, File file, JAXBContext context, int idSubCategory) throws JAXBException {

        String datefromString;
        SubCategoryType subCategoryType  = new SubCategoryType();
        subCategoryType.setId(idSubCategory);
        MovieType movieType = new MovieType();
        movieType.setId(1);
        movieType.setTitle(param.get("title"));
        movieType.setDirector(param.get("director"));
        datefromString = param.get("date_of_issue");
        movieType.setDateOfIssue(convertDateToXMLGregorianCalendar(datefromString));
        movieType.setNewsBody(param.get("news_body"));
        subCategoryType.getMovie().add(movieType);
        return subCategoryType;
    }

    private XMLGregorianCalendar convertDateToXMLGregorianCalendar(String datefromString){
        Date date = null;
        try {
            date = new SimpleDateFormat( "yyyy.MM.dd" ).parse( datefromString );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        XMLGregorianCalendar xmlDate = null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);

        try{
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        }catch(Exception e){
            e.printStackTrace();
        }
        return xmlDate;
    }


    private int getIdSubcategory(File file, JAXBContext context) throws JAXBException {
        Unmarshaller unmarshaller = null;
        unmarshaller = context.createUnmarshaller();
        Category category = (Category) unmarshaller.unmarshal(file);
        System.out.println(category.getSubcategory().size());
        int size = (category.getSubcategory().size())+1;
        return size;
    }



    public static AddNewsService getInstance() {
        return instance;
    }
}
