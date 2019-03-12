package com.redcompany.red.jaxbcategory.service.impl;

import com.redcompany.red.jaxbcategory.entity.Category;
import com.redcompany.red.jaxbcategory.service.XmlService;
import com.redcompany.red.jaxbcategory.view.ConsoleShow;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;

import static com.redcompany.red.jaxbcategory.service.util.ServiceConstantStorage.*;

public class ShowAllNewsService implements XmlService {

    private static final ShowAllNewsService instance = new ShowAllNewsService();


    @Override
    public boolean doService(HashMap<String, String> param) {
        try {
            unmarshalling();
        } catch (JAXBException e) {
            return false;
        }
        return true;
    }

    public void unmarshalling() throws JAXBException {
        File file = new File(XML_FILE_PATH);
        JAXBContext context = null;
        context = JAXBContext.newInstance(JAXB_CONTEXT_PATH);
        Unmarshaller unmarshaller = null;
        unmarshaller = context.createUnmarshaller();
        Category category = (Category) unmarshaller.unmarshal(file);
        ConsoleShow.getInstance().consoleShow(category);
    }


    public static ShowAllNewsService getInstance() {
        return instance;
    }
}
