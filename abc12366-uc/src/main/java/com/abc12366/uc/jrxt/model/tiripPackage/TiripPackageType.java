/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.tiripPackage;

/**
 * Class TiripPackageType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class TiripPackageType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _version.
     */
    private String _version;

    /**
     * ?��??????????????????��???
     */
    private com.abc12366.uc.jrxt.model.tiripPackage.Identity _identity;

    /**
     * ?��??????????��????????????????????????????????
     */
    private com.abc12366.uc.jrxt.model.tiripPackage.ContentControl _contentControl;

    /**
     * ?��??????��??????????��??????????????????????�՛�
     */
    private com.abc12366.uc.jrxt.model.tiripPackage.RouterSession _routerSession;

    /**
     * ?��??????��????????????????????????
     */
    private com.abc12366.uc.jrxt.model.tiripPackage.BusinessContent _businessContent;

    /**
     * ?��??????????????????,????????????????
     */
    private com.abc12366.uc.jrxt.model.tiripPackage.ReturnState _returnState;


      //----------------/
     //- Constructors -/
    //----------------/

    public TiripPackageType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'businessContent'. The field
     * 'businessContent' has the following description:
     * ?��??????��????????????????????????
     *
     * @return the value of field 'BusinessContent'.
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.BusinessContent getBusinessContent(
    ) {
        return this._businessContent;
    }

    /**
     * Returns the value of field 'contentControl'. The field
     * 'contentControl' has the following description:
     * ?��??????????��????????????????????????????????
     *
     * @return the value of field 'ContentControl'.
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.ContentControl getContentControl(
    ) {
        return this._contentControl;
    }

    /**
     * Returns the value of field 'identity'. The field 'identity'
     * has the following description: ?��??????????????????��???
     *
     * @return the value of field 'Identity'.
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.Identity getIdentity(
    ) {
        return this._identity;
    }

    /**
     * Returns the value of field 'returnState'. The field
     * 'returnState' has the following description:
     * ?��??????????????????,????????????????
     *
     * @return the value of field 'ReturnState'.
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.ReturnState getReturnState(
    ) {
        return this._returnState;
    }

    /**
     * Returns the value of field 'routerSession'. The field
     * 'routerSession' has the following description:
     * ?��??????��??????????��??????????????????????�՛�
     *
     * @return the value of field 'RouterSession'.
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.RouterSession getRouterSession(
    ) {
        return this._routerSession;
    }

    /**
     * Returns the value of field 'version'.
     *
     * @return the value of field 'Version'.
     */
    public String getVersion(
    ) {
        return this._version;
    }

    /**
     * Sets the value of field 'businessContent'. The field
     * 'businessContent' has the following description:
     * ?��??????��????????????????????????
     *
     * @param businessContent the value of field 'businessContent'.
     */
    public void setBusinessContent(
            final com.abc12366.uc.jrxt.model.tiripPackage.BusinessContent businessContent) {
        this._businessContent = businessContent;
    }

    /**
     * Sets the value of field 'contentControl'. The field
     * 'contentControl' has the following description:
     * ?��??????????��????????????????????????????????
     *
     * @param contentControl the value of field 'contentControl'.
     */
    public void setContentControl(
            final com.abc12366.uc.jrxt.model.tiripPackage.ContentControl contentControl) {
        this._contentControl = contentControl;
    }

    /**
     * Sets the value of field 'identity'. The field 'identity' has
     * the following description: ?��??????????????????��???
     *
     * @param identity the value of field 'identity'.
     */
    public void setIdentity(
            final com.abc12366.uc.jrxt.model.tiripPackage.Identity identity) {
        this._identity = identity;
    }

    /**
     * Sets the value of field 'returnState'. The field
     * 'returnState' has the following description:
     * ?��??????????????????,????????????????
     *
     * @param returnState the value of field 'returnState'.
     */
    public void setReturnState(
            final com.abc12366.uc.jrxt.model.tiripPackage.ReturnState returnState) {
        this._returnState = returnState;
    }

    /**
     * Sets the value of field 'routerSession'. The field
     * 'routerSession' has the following description:
     * ?��??????��??????????��??????????????????????�՛�
     *
     * @param routerSession the value of field 'routerSession'.
     */
    public void setRouterSession(
            final com.abc12366.uc.jrxt.model.tiripPackage.RouterSession routerSession) {
        this._routerSession = routerSession;
    }

    /**
     * Sets the value of field 'version'.
     *
     * @param version the value of field 'version'.
     */
    public void setVersion(
            final String version) {
        this._version = version;
    }

}
