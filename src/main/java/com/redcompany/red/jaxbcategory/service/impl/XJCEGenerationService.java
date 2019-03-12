package com.redcompany.red.jaxbcategory.service.impl;


import com.redcompany.red.jaxbcategory.service.XmlService;
import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import org.xml.sax.InputSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class XJCEGenerationService implements XmlService {

    private static final XJCEGenerationService instance = new XJCEGenerationService();
    private static final String schemaFile = "p:\\JavaProjects\\jd2hwjaxb_category\\src\\main\\resources\\data\\category.xsd";
    private static final String entityPackageName = "com.redcompany.red.jaxbcategory.entity";
    private static final String targetPath = "p:\\JavaProjects\\jd2hwjaxb_category\\src\\main\\java\\";

    @Override
    public boolean doService(HashMap<String, String> paran) {
        try {
            generateFromSchema(new File(schemaFile), entityPackageName, new File(targetPath));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       return true;
    }

    public  JCodeModel generateFromSchema(final File schemaFile, final String packageName,
                                                final File targetPath) throws Exception {

        final SchemaCompiler sc = XJC.createSchemaCompiler();
        final FileInputStream schemaStream = new FileInputStream(schemaFile);
        final InputSource is = new InputSource(schemaStream);
        is.setSystemId(schemaFile.toURI().toString());
        sc.parseSchema(is);
        sc.forcePackageName(packageName);
        final S2JJAXBModel s2 = sc.bind();
        final JCodeModel jcm = s2.generateCode(null, null);
        try (PrintStream status = new PrintStream(new ByteArrayOutputStream())) {
            jcm.build(targetPath, status);
        }
        return jcm;
    }

    public static XJCEGenerationService getInstance() {
        return instance;
    }


}
