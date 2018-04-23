package com.abc12366.uc.jrxt.model.util;

import org.exolab.castor.xml.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by ljun on 5/5/15.
 */
public class XmlJavaParser {
    public XmlJavaParser() {
    }

    public static Object parseXmlToObject(Class aClass, String xmlstring) throws MarshalException, ValidationException {
        StringReader sr = new StringReader(xmlstring);
        Unmarshaller um = new Unmarshaller();
        um.setValidation(false);
        return Unmarshaller.unmarshal(aClass, sr);
    }

    public static String parseObjectToXml(Object o) throws IOException, MarshalException, ValidationException {
        StringWriter sw = new StringWriter();
        Marshaller marshall = new Marshaller(sw);
        marshall.setEncoding("GBK");
        marshall.setValidation(false);
        marshall.marshal(o);
        return sw.getBuffer().toString();
    }

    public static void validateObject(Object o) throws ValidationException {
        Validator validator = new Validator();
        validator.validate(o);
    }

    public static boolean isValid(Object o) {
        try {
            validateObject(o);
            return true;
        } catch (ValidationException var2) {
            return false;
        }
    }
}
