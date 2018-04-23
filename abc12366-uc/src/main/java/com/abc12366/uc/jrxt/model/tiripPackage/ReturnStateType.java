/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.tiripPackage;

/**
 * Class ReturnStateType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ReturnStateType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ??????��?????????
     */
    private String _returnCode;

    /**
     * ??????��??????????
     */
    private String _returnMessage;


      //----------------/
     //- Constructors -/
    //----------------/

    public ReturnStateType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'returnCode'. The field
     * 'returnCode' has the following description: ??????��?????????
     *
     * @return the value of field 'ReturnCode'.
     */
    public String getReturnCode(
    ) {
        return this._returnCode;
    }

    /**
     * Returns the value of field 'returnMessage'. The field
     * 'returnMessage' has the following description:
     * ??????��??????????
     *
     * @return the value of field 'ReturnMessage'.
     */
    public String getReturnMessage(
    ) {
        return this._returnMessage;
    }

    /**
     * Sets the value of field 'returnCode'. The field 'returnCode'
     * has the following description: ??????��?????????
     *
     * @param returnCode the value of field 'returnCode'.
     */
    public void setReturnCode(
            final String returnCode) {
        this._returnCode = returnCode;
    }

    /**
     * Sets the value of field 'returnMessage'. The field
     * 'returnMessage' has the following description:
     * ??????��??????????
     *
     * @param returnMessage the value of field 'returnMessage'.
     */
    public void setReturnMessage(
            final String returnMessage) {
        this._returnMessage = returnMessage;
    }

}
