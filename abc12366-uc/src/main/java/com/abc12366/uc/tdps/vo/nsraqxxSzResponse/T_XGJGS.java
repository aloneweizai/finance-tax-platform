/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.tdps.vo.nsraqxxSzResponse;

/**
 * Class T_XGJGS.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class T_XGJGS implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _XGJGList.
     */
    private java.util.List<XGJG> _XGJGList;


      //----------------/
     //- Constructors -/
    //----------------/

    public T_XGJGS() {
        super();
        this._XGJGList = new java.util.ArrayList<XGJG>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vXGJG
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addXGJG(
            final XGJG vXGJG)
    throws IndexOutOfBoundsException {
        this._XGJGList.add(vXGJG);
    }

    /**
     * 
     * 
     * @param index
     * @param vXGJG
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addXGJG(
            final int index,
            final XGJG vXGJG)
    throws IndexOutOfBoundsException {
        this._XGJGList.add(index, vXGJG);
    }

    /**
     * Method enumerateXGJG.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends XGJG> enumerateXGJG(
    ) {
        return java.util.Collections.enumeration(this._XGJGList);
    }

    /**
     * Method getXGJG.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.tdps.vo.nsraqxxSzResponse.XGJG at the given
     * index
     */
    public XGJG getXGJG(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._XGJGList.size()) {
            throw new IndexOutOfBoundsException("getXGJG: Index value '" + index + "' not in range [0.." + (this._XGJGList.size() - 1) + "]");
        }

        return (XGJG) _XGJGList.get(index);
    }

    /**
     * Method getXGJG.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public XGJG[] getXGJG(
    ) {
        XGJG[] array = new XGJG[0];
        return (XGJG[]) this._XGJGList.toArray(array);
    }

    /**
     * Method getXGJGCount.
     * 
     * @return the size of this collection
     */
    public int getXGJGCount(
    ) {
        return this._XGJGList.size();
    }

    /**
     * Method iterateXGJG.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends XGJG> iterateXGJG(
    ) {
        return this._XGJGList.iterator();
    }

    /**
     */
    public void removeAllXGJG(
    ) {
        this._XGJGList.clear();
    }

    /**
     * Method removeXGJG.
     * 
     * @param vXGJG
     * @return true if the object was removed from the collection.
     */
    public boolean removeXGJG(
            final XGJG vXGJG) {
        boolean removed = _XGJGList.remove(vXGJG);
        return removed;
    }

    /**
     * Method removeXGJGAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public XGJG removeXGJGAt(
            final int index) {
        Object obj = this._XGJGList.remove(index);
        return (XGJG) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vXGJG
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setXGJG(
            final int index,
            final XGJG vXGJG)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._XGJGList.size()) {
            throw new IndexOutOfBoundsException("setXGJG: Index value '" + index + "' not in range [0.." + (this._XGJGList.size() - 1) + "]");
        }

        this._XGJGList.set(index, vXGJG);
    }

    /**
     * 
     * 
     * @param vXGJGArray
     */
    public void setXGJG(
            final XGJG[] vXGJGArray) {
        //-- copy array
        _XGJGList.clear();

        for (int i = 0; i < vXGJGArray.length; i++) {
                this._XGJGList.add(vXGJGArray[i]);
        }
    }

}
