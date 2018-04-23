/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.tiripPackage;

/**
 * Class ControlType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ControlType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ?????????
     */
    private String _id;

    /**
     * ????????
     */
    private String _type;

    /**
     * ???????????????????
     */
    private String _impl;


      //----------------/
     //- Constructors -/
    //----------------/

    public ControlType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'id'. The field 'id' has the
     * following description: ?????????
     *
     * @return the value of field 'Id'.
     */
    public String getId(
    ) {
        return this._id;
    }

    /**
     * Returns the value of field 'impl'. The field 'impl' has the
     * following description: ???????????????????
     *
     * @return the value of field 'Impl'.
     */
    public String getImpl(
    ) {
        return this._impl;
    }

    /**
     * Returns the value of field 'type'. The field 'type' has the
     * following description: ????????
     *
     * @return the value of field 'Type'.
     */
    public String getType(
    ) {
        return this._type;
    }

    /**
     * Sets the value of field 'id'. The field 'id' has the
     * following description: ?????????
     *
     * @param id the value of field 'id'.
     */
    public void setId(
            final String id) {
        this._id = id;
    }

    /**
     * Sets the value of field 'impl'. The field 'impl' has the
     * following description: ???????????????????
     *
     * @param impl the value of field 'impl'.
     */
    public void setImpl(
            final String impl) {
        this._impl = impl;
    }

    /**
     * Sets the value of field 'type'. The field 'type' has the
     * following description: ????????
     *
     * @param type the value of field 'type'.
     */
    public void setType(
            final String type) {
        this._type = type;
    }

}
