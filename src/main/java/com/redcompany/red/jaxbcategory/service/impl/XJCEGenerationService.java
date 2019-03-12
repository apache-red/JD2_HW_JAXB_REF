package com.redcompany.red.jaxbcategory.service.impl;


import com.redcompany.red.jaxbcategory.service.XmlService;
import com.redcompany.red.jaxbcategory.service.util.ServiceConstantStorage;
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

import static com.redcompany.red.jaxbcategory.service.util.ServiceConstantStorage.*;

public class XJCEGenerationService implements XmlService {

    private static final XJCEGenerationService instance = new XJCEGenerationService();

    @Override
    public boolean doService(HashMap<String, String> param) {
        try {
            generateFromSchema(new File(SCHEMA_FILE), ENTITY_PACKAGE_NAME, new File(TARGET_PATH));
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
