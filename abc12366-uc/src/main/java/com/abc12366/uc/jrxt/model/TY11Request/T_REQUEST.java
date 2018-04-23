/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.TY11Request;

/**
 * ???????????????
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class T_REQUEST implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _NSRSBH.
     */
    private String _NSRSBH;

    /**
     * ??????????? ????1?????????:??????? ????ดย ????? ???????? ????????
     */
    private com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX _NSRJBXX;


      //----------------/
     //- Constructors -/
    //----------------/

    public T_REQUEST() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'NSRJBXX'. The field 'NSRJBXX'
     * has the following description: ??????????? ????1?????????:??????? ????ดย
     * ????? ???????? ????????
     *
     * @return the value of field 'NSRJBXX'.
     */
    public com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX getNSRJBXX(
    ) {
        return this._NSRJBXX;
    }

    /**
     * Returns the value of field 'NSRSBH'.
     *
     * @return the value of field 'NSRSBH'.
     */
    public String getNSRSBH(
    ) {
        return this._NSRSBH;
    }

    /**
     * Sets the value of field 'NSRJBXX'. The field 'NSRJBXX' has
     * the following description: ??????????? ????1?????????:??????? ????ดย ?????
     * ???????? ????????
     *
     * @param NSRJBXX the value of field 'NSRJBXX'.
     */
    public void setNSRJBXX(
            final com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX NSRJBXX) {
        this._NSRJBXX = NSRJBXX;
    }

    /**
     * Sets the value of field 'NSRSBH'.
     *
     * @param NSRSBH the value of field 'NSRSBH'.
     */
    public void setNSRSBH(
            final String NSRSBH) {
        this._NSRSBH = NSRSBH;
    }

}
