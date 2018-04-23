/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.tiripPackage;

/**
 * Class ParamListType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ParamListType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ��????????
     */
    private String _name;

    /**
     * ��???????
     */
    private String _value;


      //----------------/
     //- Constructors -/
    //----------------/

    public ParamListType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'name'. The field 'name' has the
     * following description: ��????????
     *
     * @return the value of field 'Name'.
     */
    public String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'value'. The field 'value' has
     * the following description: ��???????
     *
     * @return the value of field 'Value'.
     */
    public String getValue(
    ) {
        return this._value;
    }

    /**
     * Sets the value of field 'name'. The field 'name' has the
     * following description: ��????????
     *
     * @param name the value of field 'name'.
     */
    public void setName(
            final String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'value'. The field 'value' has the
     * following description: ��???????
     *
     * @param value the value of field 'value'.
     */
    public void setValue(
            final String value) {
        this._value = value;
    }

}
