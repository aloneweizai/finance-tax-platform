/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.TY11Request;

/**
 * Class T_YHXX.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class T_YHXX implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ��Ϣ���
     */
    private String _XXBH;

    /**
     * ����
     */
    private String _MSGTITLE;

    /**
     * ��ϢժҪ
     */
    private String _MSGZY;

    /**
     * ����ʱ��
     */
    private String _FSSJ;

    /**
     * ��Ϣ��Ч��
     */
    private String _MSGYSQ_Q;

    /**
     * ��Ϣ��Чֹ
     */
    private String _MSGYSQ_Z;

    /**
     * ����
     */
    private String _MSGCONTENT;

    /**
     * ��Ϣ����URL
     */
    private String _MSGGLURL;

    /**
     * ��Դϵͳ
     */
    private String _SOURCEID;

    /**
     * ��Ϣ����
     */
    private String _messageType;

    /**
     * ��˰ר��չʾ��ʽ
     */
    private String _CSZJSHOWTYPE;

    /**
     * ��˰ר��չʾ�ߴ� W:H
     */
    private String _CSZJSHOWSIZE;

    /**
     * ��˰ר��չʾʱ��
     */
    private long _CSZJSHOWTIME;

    /**
     * keeps track of state for field: _CSZJSHOWTIME
     */
    private boolean _has_CSZJSHOWTIME;

    /**
     * �Ƿ�����Ķ�
     */
    private String _ISMUSTREAD;

    /**
     * ����ID
     */
    private String _related;


      //----------------/
     //- Constructors -/
    //----------------/

    public T_YHXX() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteCSZJSHOWTIME(
    ) {
        this._has_CSZJSHOWTIME= false;
    }

    /**
     * Returns the value of field 'CSZJSHOWSIZE'. The field
     * 'CSZJSHOWSIZE' has the following description: ��˰ר��չʾ�ߴ� W:H
     *
     * @return the value of field 'CSZJSHOWSIZE'.
     */
    public String getCSZJSHOWSIZE(
    ) {
        return this._CSZJSHOWSIZE;
    }

    /**
     * Returns the value of field 'CSZJSHOWTIME'. The field
     * 'CSZJSHOWTIME' has the following description: ��˰ר��չʾʱ��
     *
     * @return the value of field 'CSZJSHOWTIME'.
     */
    public long getCSZJSHOWTIME(
    ) {
        return this._CSZJSHOWTIME;
    }

    /**
     * Returns the value of field 'CSZJSHOWTYPE'. The field
     * 'CSZJSHOWTYPE' has the following description: ��˰ר��չʾ��ʽ
     *
     * @return the value of field 'CSZJSHOWTYPE'.
     */
    public String getCSZJSHOWTYPE(
    ) {
        return this._CSZJSHOWTYPE;
    }

    /**
     * Returns the value of field 'FSSJ'. The field 'FSSJ' has the
     * following description: ����ʱ��
     *
     * @return the value of field 'FSSJ'.
     */
    public String getFSSJ(
    ) {
        return this._FSSJ;
    }

    /**
     * Returns the value of field 'ISMUSTREAD'. The field
     * 'ISMUSTREAD' has the following description: �Ƿ�����Ķ�
     *
     * @return the value of field 'ISMUSTREAD'.
     */
    public String getISMUSTREAD(
    ) {
        return this._ISMUSTREAD;
    }

    /**
     * Returns the value of field 'MSGCONTENT'. The field
     * 'MSGCONTENT' has the following description: ����
     *
     * @return the value of field 'MSGCONTENT'.
     */
    public String getMSGCONTENT(
    ) {
        return this._MSGCONTENT;
    }

    /**
     * Returns the value of field 'MSGGLURL'. The field 'MSGGLURL'
     * has the following description: ��Ϣ����URL
     *
     * @return the value of field 'MSGGLURL'.
     */
    public String getMSGGLURL(
    ) {
        return this._MSGGLURL;
    }

    /**
     * Returns the value of field 'MSGTITLE'. The field 'MSGTITLE'
     * has the following description: ����
     *
     * @return the value of field 'MSGTITLE'.
     */
    public String getMSGTITLE(
    ) {
        return this._MSGTITLE;
    }

    /**
     * Returns the value of field 'MSGYSQ_Q'. The field 'MSGYSQ_Q'
     * has the following description: ��Ϣ��Ч��
     *
     * @return the value of field 'MSGYSQ_Q'.
     */
    public String getMSGYSQ_Q(
    ) {
        return this._MSGYSQ_Q;
    }

    /**
     * Returns the value of field 'MSGYSQ_Z'. The field 'MSGYSQ_Z'
     * has the following description: ��Ϣ��Чֹ
     *
     * @return the value of field 'MSGYSQ_Z'.
     */
    public String getMSGYSQ_Z(
    ) {
        return this._MSGYSQ_Z;
    }

    /**
     * Returns the value of field 'MSGZY'. The field 'MSGZY' has
     * the following description: ��ϢժҪ
     *
     * @return the value of field 'MSGZY'.
     */
    public String getMSGZY(
    ) {
        return this._MSGZY;
    }

    /**
     * Returns the value of field 'messageType'. The field
     * 'messageType' has the following description: ��Ϣ����
     *
     * @return the value of field 'MessageType'.
     */
    public String getMessageType(
    ) {
        return this._messageType;
    }

    /**
     * Returns the value of field 'related'. The field 'related'
     * has the following description: ����ID
     *
     * @return the value of field 'Related'.
     */
    public String getRelated(
    ) {
        return this._related;
    }

    /**
     * Returns the value of field 'SOURCEID'. The field 'SOURCEID'
     * has the following description: ��Դϵͳ
     *
     * @return the value of field 'SOURCEID'.
     */
    public String getSOURCEID(
    ) {
        return this._SOURCEID;
    }

    /**
     * Returns the value of field 'XXBH'. The field 'XXBH' has the
     * following description: ��Ϣ���
     *
     * @return the value of field 'XXBH'.
     */
    public String getXXBH(
    ) {
        return this._XXBH;
    }

    /**
     * Method hasCSZJSHOWTIME.
     *
     * @return true if at least one CSZJSHOWTIME has been added
     */
    public boolean hasCSZJSHOWTIME(
    ) {
        return this._has_CSZJSHOWTIME;
    }

    /**
     * Sets the value of field 'CSZJSHOWSIZE'. The field
     * 'CSZJSHOWSIZE' has the following description: ��˰ר��չʾ�ߴ� W:H
     *
     * @param CSZJSHOWSIZE the value of field 'CSZJSHOWSIZE'.
     */
    public void setCSZJSHOWSIZE(
            final String CSZJSHOWSIZE) {
        this._CSZJSHOWSIZE = CSZJSHOWSIZE;
    }

    /**
     * Sets the value of field 'CSZJSHOWTIME'. The field
     * 'CSZJSHOWTIME' has the following description: ��˰ר��չʾʱ��
     *
     * @param CSZJSHOWTIME the value of field 'CSZJSHOWTIME'.
     */
    public void setCSZJSHOWTIME(
            final long CSZJSHOWTIME) {
        this._CSZJSHOWTIME = CSZJSHOWTIME;
        this._has_CSZJSHOWTIME = true;
    }

    /**
     * Sets the value of field 'CSZJSHOWTYPE'. The field
     * 'CSZJSHOWTYPE' has the following description: ��˰ר��չʾ��ʽ
     *
     * @param CSZJSHOWTYPE the value of field 'CSZJSHOWTYPE'.
     */
    public void setCSZJSHOWTYPE(
            final String CSZJSHOWTYPE) {
        this._CSZJSHOWTYPE = CSZJSHOWTYPE;
    }

    /**
     * Sets the value of field 'FSSJ'. The field 'FSSJ' has the
     * following description: ����ʱ��
     *
     * @param FSSJ the value of field 'FSSJ'.
     */
    public void setFSSJ(
            final String FSSJ) {
        this._FSSJ = FSSJ;
    }

    /**
     * Sets the value of field 'ISMUSTREAD'. The field 'ISMUSTREAD'
     * has the following description: �Ƿ�����Ķ�
     *
     * @param ISMUSTREAD the value of field 'ISMUSTREAD'.
     */
    public void setISMUSTREAD(
            final String ISMUSTREAD) {
        this._ISMUSTREAD = ISMUSTREAD;
    }

    /**
     * Sets the value of field 'MSGCONTENT'. The field 'MSGCONTENT'
     * has the following description: ����
     *
     * @param MSGCONTENT the value of field 'MSGCONTENT'.
     */
    public void setMSGCONTENT(
            final String MSGCONTENT) {
        this._MSGCONTENT = MSGCONTENT;
    }

    /**
     * Sets the value of field 'MSGGLURL'. The field 'MSGGLURL' has
     * the following description: ��Ϣ����URL
     *
     * @param MSGGLURL the value of field 'MSGGLURL'.
     */
    public void setMSGGLURL(
            final String MSGGLURL) {
        this._MSGGLURL = MSGGLURL;
    }

    /**
     * Sets the value of field 'MSGTITLE'. The field 'MSGTITLE' has
     * the following description: ����
     *
     * @param MSGTITLE the value of field 'MSGTITLE'.
     */
    public void setMSGTITLE(
            final String MSGTITLE) {
        this._MSGTITLE = MSGTITLE;
    }

    /**
     * Sets the value of field 'MSGYSQ_Q'. The field 'MSGYSQ_Q' has
     * the following description: ��Ϣ��Ч��
     *
     * @param MSGYSQ_Q the value of field 'MSGYSQ_Q'.
     */
    public void setMSGYSQ_Q(
            final String MSGYSQ_Q) {
        this._MSGYSQ_Q = MSGYSQ_Q;
    }

    /**
     * Sets the value of field 'MSGYSQ_Z'. The field 'MSGYSQ_Z' has
     * the following description: ��Ϣ��Чֹ
     *
     * @param MSGYSQ_Z the value of field 'MSGYSQ_Z'.
     */
    public void setMSGYSQ_Z(
            final String MSGYSQ_Z) {
        this._MSGYSQ_Z = MSGYSQ_Z;
    }

    /**
     * Sets the value of field 'MSGZY'. The field 'MSGZY' has the
     * following description: ��ϢժҪ
     *
     * @param MSGZY the value of field 'MSGZY'.
     */
    public void setMSGZY(
            final String MSGZY) {
        this._MSGZY = MSGZY;
    }

    /**
     * Sets the value of field 'messageType'. The field
     * 'messageType' has the following description: ��Ϣ����
     *
     * @param messageType the value of field 'messageType'.
     */
    public void setMessageType(
            final String messageType) {
        this._messageType = messageType;
    }

    /**
     * Sets the value of field 'related'. The field 'related' has
     * the following description: ����ID
     *
     * @param related the value of field 'related'.
     */
    public void setRelated(
            final String related) {
        this._related = related;
    }

    /**
     * Sets the value of field 'SOURCEID'. The field 'SOURCEID' has
     * the following description: ��Դϵͳ
     *
     * @param SOURCEID the value of field 'SOURCEID'.
     */
    public void setSOURCEID(
            final String SOURCEID) {
        this._SOURCEID = SOURCEID;
    }

    /**
     * Sets the value of field 'XXBH'. The field 'XXBH' has the
     * following description: ��Ϣ���
     *
     * @param XXBH the value of field 'XXBH'.
     */
    public void setXXBH(
            final String XXBH) {
        this._XXBH = XXBH;
    }

}
