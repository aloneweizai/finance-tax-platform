/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse.QTXXS;

/**
 * Class QTXXSDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class QTXXSDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private String _nsURI;

    /**
     * Field _xmlName.
     */
    private String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public QTXXSDescriptor() {
        super();
        _nsURI = "http://www.chinatax.gov.cn/dataspec/";
        _xmlName = "QTXXS";
        _elementDefinition = true;

        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors

        //-- initialize element descriptors

        //-- _QTXXList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse.QTXX.class, "_QTXXList", "QTXX", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public Object getValue( Object object )
                throws IllegalStateException
            {
                QTXXS target = (QTXXS) object;
                return target.getQTXX();
            }
            @Override
            public void setValue( Object object, Object value)
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    QTXXS target = (QTXXS) object;
                    target.addQTXX( (com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse.QTXX) value);
                } catch (Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    QTXXS target = (QTXXS) object;
                    target.removeAllQTXX();
                } catch (Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public Object newInstance(Object parent) {
                return new com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse.QTXX();
            }
        };
        desc.setSchemaType("list");
        desc.setComponentType("com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse.QTXX");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.chinatax.gov.cn/dataspec/");
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _QTXXList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    @Override()
    public org.exolab.castor.mapping.AccessMode getAccessMode(
    ) {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    @Override()
    public org.exolab.castor.mapping.FieldDescriptor getIdentity(
    ) {
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    @Override()
    public Class getJavaClass(
    ) {
        return QTXXS.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    @Override()
    public String getNameSpacePrefix(
    ) {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    @Override()
    public String getNameSpaceURI(
    ) {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    @Override()
    public org.exolab.castor.xml.TypeValidator getValidator(
    ) {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    @Override()
    public String getXMLName(
    ) {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition(
    ) {
        return _elementDefinition;
    }

}
