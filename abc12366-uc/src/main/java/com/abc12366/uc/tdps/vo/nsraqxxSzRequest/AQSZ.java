/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.tdps.vo.nsraqxxSzRequest;

/**
 * Class AQSZ.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AQSZ implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _NSRSBH.
     */
    private String _NSRSBH;

    /**
     * ��������,01��ʾ�޸��걨������ܣ�02��ʾ�ϴ�֤��
     */
    private String _JMTZ;

    /**
     * Field _MMXX.
     */
    private com.abc12366.uc.tdps.vo.nsraqxxSzRequest.MMXX _MMXX;

    /**
     * Field _ZSXXSList.
     */
    private java.util.List<com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS> _ZSXXSList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AQSZ() {
        super();
        this._ZSXXSList = new java.util.ArrayList<com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vZSXXS
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZSXXS(
            final com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS vZSXXS)
    throws IndexOutOfBoundsException {
        this._ZSXXSList.add(vZSXXS);
    }

    /**
     * 
     * 
     * @param index
     * @param vZSXXS
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZSXXS(
            final int index,
            final com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS vZSXXS)
    throws IndexOutOfBoundsException {
        this._ZSXXSList.add(index, vZSXXS);
    }

    /**
     * Method enumerateZSXXS.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS> enumerateZSXXS(
    ) {
        return java.util.Collections.enumeration(this._ZSXXSList);
    }

    /**
     * Returns the value of field 'JMTZ'. The field 'JMTZ' has the
     * following description: ��������,01��ʾ�޸��걨������ܣ�02��ʾ�ϴ�֤��
     * 
     * @return the value of field 'JMTZ'.
     */
    public String getJMTZ(
    ) {
        return this._JMTZ;
    }

    /**
     * Returns the value of field 'MMXX'.
     * 
     * @return the value of field 'MMXX'.
     */
    public com.abc12366.uc.tdps.vo.nsraqxxSzRequest.MMXX getMMXX(
    ) {
        return this._MMXX;
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
     * Method getZSXXS.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS at the given
     * index
     */
    public com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS getZSXXS(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._ZSXXSList.size()) {
            throw new IndexOutOfBoundsException("getZSXXS: Index value '" + index + "' not in range [0.." + (this._ZSXXSList.size() - 1) + "]");
        }

        return (com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS) _ZSXXSList.get(index);
    }

    /**
     * Method getZSXXS.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS[] getZSXXS(
    ) {
        com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS[] array = new com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS[0];
        return (com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS[]) this._ZSXXSList.toArray(array);
    }

    /**
     * Method getZSXXSCount.
     * 
     * @return the size of this collection
     */
    public int getZSXXSCount(
    ) {
        return this._ZSXXSList.size();
    }

    /**
     * Method iterateZSXXS.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS> iterateZSXXS(
    ) {
        return this._ZSXXSList.iterator();
    }

    /**
     */
    public void removeAllZSXXS(
    ) {
        this._ZSXXSList.clear();
    }

    /**
     * Method removeZSXXS.
     * 
     * @param vZSXXS
     * @return true if the object was removed from the collection.
     */
    public boolean removeZSXXS(
            final com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS vZSXXS) {
        boolean removed = _ZSXXSList.remove(vZSXXS);
        return removed;
    }

    /**
     * Method removeZSXXSAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS removeZSXXSAt(
            final int index) {
        Object obj = this._ZSXXSList.remove(index);
        return (com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS) obj;
    }

    /**
     * Sets the value of field 'JMTZ'. The field 'JMTZ' has the
     * following description: ��������,01��ʾ�޸��걨������ܣ�02��ʾ�ϴ�֤��
     * 
     * @param JMTZ the value of field 'JMTZ'.
     */
    public void setJMTZ(
            final String JMTZ) {
        this._JMTZ = JMTZ;
    }

    /**
     * Sets the value of field 'MMXX'.
     * 
     * @param MMXX the value of field 'MMXX'.
     */
    public void setMMXX(
            final com.abc12366.uc.tdps.vo.nsraqxxSzRequest.MMXX MMXX) {
        this._MMXX = MMXX;
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

    /**
     * 
     * 
     * @param index
     * @param vZSXXS
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setZSXXS(
            final int index,
            final com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS vZSXXS)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._ZSXXSList.size()) {
            throw new IndexOutOfBoundsException("setZSXXS: Index value '" + index + "' not in range [0.." + (this._ZSXXSList.size() - 1) + "]");
        }

        this._ZSXXSList.set(index, vZSXXS);
    }

    /**
     * 
     * 
     * @param vZSXXSArray
     */
    public void setZSXXS(
            final com.abc12366.uc.tdps.vo.nsraqxxSzRequest.ZSXXS[] vZSXXSArray) {
        //-- copy array
        _ZSXXSList.clear();

        for (int i = 0; i < vZSXXSArray.length; i++) {
                this._ZSXXSList.add(vZSXXSArray[i]);
        }
    }

}
