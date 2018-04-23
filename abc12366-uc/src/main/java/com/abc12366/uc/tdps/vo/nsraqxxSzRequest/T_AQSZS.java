/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.tdps.vo.nsraqxxSzRequest;

/**
 * Class T_AQSZS.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class T_AQSZS implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ��˰�˰�ȫ��Ϣ����
     */
    private java.util.List<AQSZ> _AQSZList;


      //----------------/
     //- Constructors -/
    //----------------/

    public T_AQSZS() {
        super();
        this._AQSZList = new java.util.ArrayList<AQSZ>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAQSZ
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAQSZ(
            final AQSZ vAQSZ)
    throws IndexOutOfBoundsException {
        this._AQSZList.add(vAQSZ);
    }

    /**
     * 
     * 
     * @param index
     * @param vAQSZ
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAQSZ(
            final int index,
            final AQSZ vAQSZ)
    throws IndexOutOfBoundsException {
        this._AQSZList.add(index, vAQSZ);
    }

    /**
     * Method enumerateAQSZ.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends AQSZ> enumerateAQSZ(
    ) {
        return java.util.Collections.enumeration(this._AQSZList);
    }

    /**
     * Method getAQSZ.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.tdps.vo.nsraqxxSzRequest.AQSZ at the given
     * index
     */
    public AQSZ getAQSZ(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._AQSZList.size()) {
            throw new IndexOutOfBoundsException("getAQSZ: Index value '" + index + "' not in range [0.." + (this._AQSZList.size() - 1) + "]");
        }

        return (AQSZ) _AQSZList.get(index);
    }

    /**
     * Method getAQSZ.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public AQSZ[] getAQSZ(
    ) {
        AQSZ[] array = new AQSZ[0];
        return (AQSZ[]) this._AQSZList.toArray(array);
    }

    /**
     * Method getAQSZCount.
     * 
     * @return the size of this collection
     */
    public int getAQSZCount(
    ) {
        return this._AQSZList.size();
    }

    /**
     * Method iterateAQSZ.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends AQSZ> iterateAQSZ(
    ) {
        return this._AQSZList.iterator();
    }

    /**
     * Method removeAQSZ.
     * 
     * @param vAQSZ
     * @return true if the object was removed from the collection.
     */
    public boolean removeAQSZ(
            final AQSZ vAQSZ) {
        boolean removed = _AQSZList.remove(vAQSZ);
        return removed;
    }

    /**
     * Method removeAQSZAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public AQSZ removeAQSZAt(
            final int index) {
        Object obj = this._AQSZList.remove(index);
        return (AQSZ) obj;
    }

    /**
     */
    public void removeAllAQSZ(
    ) {
        this._AQSZList.clear();
    }

    /**
     * 
     * 
     * @param index
     * @param vAQSZ
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAQSZ(
            final int index,
            final AQSZ vAQSZ)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._AQSZList.size()) {
            throw new IndexOutOfBoundsException("setAQSZ: Index value '" + index + "' not in range [0.." + (this._AQSZList.size() - 1) + "]");
        }

        this._AQSZList.set(index, vAQSZ);
    }

    /**
     * 
     * 
     * @param vAQSZArray
     */
    public void setAQSZ(
            final AQSZ[] vAQSZArray) {
        //-- copy array
        _AQSZList.clear();

        for (int i = 0; i < vAQSZArray.length; i++) {
                this._AQSZList.add(vAQSZArray[i]);
        }
    }

}
