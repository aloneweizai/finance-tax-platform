/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse;

/**
 * ������Ϣ��Ԥ����
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class QTXXS implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _QTXXList.
     */
    private java.util.List<QTXX> _QTXXList;


      //----------------/
     //- Constructors -/
    //----------------/

    public QTXXS() {
        super();
        this._QTXXList = new java.util.ArrayList<QTXX>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vQTXX
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addQTXX(
            final QTXX vQTXX)
    throws IndexOutOfBoundsException {
        this._QTXXList.add(vQTXX);
    }

    /**
     * 
     * 
     * @param index
     * @param vQTXX
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addQTXX(
            final int index,
            final QTXX vQTXX)
    throws IndexOutOfBoundsException {
        this._QTXXList.add(index, vQTXX);
    }

    /**
     * Method enumerateQTXX.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends QTXX> enumerateQTXX(
    ) {
        return java.util.Collections.enumeration(this._QTXXList);
    }

    /**
     * Method getQTXX.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.tdps.vo.CrmnsrmmGxResponse.QTXX at the given
     * index
     */
    public QTXX getQTXX(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._QTXXList.size()) {
            throw new IndexOutOfBoundsException("getQTXX: Index value '" + index + "' not in range [0.." + (this._QTXXList.size() - 1) + "]");
        }

        return (QTXX) _QTXXList.get(index);
    }

    /**
     * Method getQTXX.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public QTXX[] getQTXX(
    ) {
        QTXX[] array = new QTXX[0];
        return (QTXX[]) this._QTXXList.toArray(array);
    }

    /**
     * Method getQTXXCount.
     * 
     * @return the size of this collection
     */
    public int getQTXXCount(
    ) {
        return this._QTXXList.size();
    }

    /**
     * Method iterateQTXX.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends QTXX> iterateQTXX(
    ) {
        return this._QTXXList.iterator();
    }

    /**
     */
    public void removeAllQTXX(
    ) {
        this._QTXXList.clear();
    }

    /**
     * Method removeQTXX.
     * 
     * @param vQTXX
     * @return true if the object was removed from the collection.
     */
    public boolean removeQTXX(
            final QTXX vQTXX) {
        boolean removed = _QTXXList.remove(vQTXX);
        return removed;
    }

    /**
     * Method removeQTXXAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public QTXX removeQTXXAt(
            final int index) {
        Object obj = this._QTXXList.remove(index);
        return (QTXX) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vQTXX
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setQTXX(
            final int index,
            final QTXX vQTXX)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._QTXXList.size()) {
            throw new IndexOutOfBoundsException("setQTXX: Index value '" + index + "' not in range [0.." + (this._QTXXList.size() - 1) + "]");
        }

        this._QTXXList.set(index, vQTXX);
    }

    /**
     * 
     * 
     * @param vQTXXArray
     */
    public void setQTXX(
            final QTXX[] vQTXXArray) {
        //-- copy array
        _QTXXList.clear();

        for (int i = 0; i < vQTXXArray.length; i++) {
                this._QTXXList.add(vQTXXArray[i]);
        }
    }

}
