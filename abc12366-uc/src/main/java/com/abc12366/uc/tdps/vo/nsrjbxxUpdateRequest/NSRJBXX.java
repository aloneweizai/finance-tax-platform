/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.tdps.vo.nsrjbxxUpdateRequest;

/**
 * ��˰�˻�����Ϣ ����1������಻��:�ֻ����� ��ϵ�绰 Ӫҵ��ַ �������� ��������
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class NSRJBXX implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _MXXXList.
     */
    private java.util.List<MXXX> _MXXXList;


      //----------------/
     //- Constructors -/
    //----------------/

    public NSRJBXX() {
        super();
        this._MXXXList = new java.util.ArrayList<MXXX>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vMXXX
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMXXX(
            final MXXX vMXXX)
    throws IndexOutOfBoundsException {
        this._MXXXList.add(vMXXX);
    }

    /**
     * 
     * 
     * @param index
     * @param vMXXX
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMXXX(
            final int index,
            final MXXX vMXXX)
    throws IndexOutOfBoundsException {
        this._MXXXList.add(index, vMXXX);
    }

    /**
     * Method enumerateMXXX.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends MXXX> enumerateMXXX(
    ) {
        return java.util.Collections.enumeration(this._MXXXList);
    }

    /**
     * Method getMXXX.
     * 
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.tdps.vo.nsrjbxxUpdateRequest.MXXX at the
     * given index
     */
    public MXXX getMXXX(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._MXXXList.size()) {
            throw new IndexOutOfBoundsException("getMXXX: Index value '" + index + "' not in range [0.." + (this._MXXXList.size() - 1) + "]");
        }

        return (MXXX) _MXXXList.get(index);
    }

    /**
     * Method getMXXX.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public MXXX[] getMXXX(
    ) {
        MXXX[] array = new MXXX[0];
        return (MXXX[]) this._MXXXList.toArray(array);
    }

    /**
     * Method getMXXXCount.
     * 
     * @return the size of this collection
     */
    public int getMXXXCount(
    ) {
        return this._MXXXList.size();
    }

    /**
     * Method iterateMXXX.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends MXXX> iterateMXXX(
    ) {
        return this._MXXXList.iterator();
    }

    /**
     */
    public void removeAllMXXX(
    ) {
        this._MXXXList.clear();
    }

    /**
     * Method removeMXXX.
     * 
     * @param vMXXX
     * @return true if the object was removed from the collection.
     */
    public boolean removeMXXX(
            final MXXX vMXXX) {
        boolean removed = _MXXXList.remove(vMXXX);
        return removed;
    }

    /**
     * Method removeMXXXAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public MXXX removeMXXXAt(
            final int index) {
        Object obj = this._MXXXList.remove(index);
        return (MXXX) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vMXXX
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setMXXX(
            final int index,
            final MXXX vMXXX)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._MXXXList.size()) {
            throw new IndexOutOfBoundsException("setMXXX: Index value '" + index + "' not in range [0.." + (this._MXXXList.size() - 1) + "]");
        }

        this._MXXXList.set(index, vMXXX);
    }

    /**
     * 
     * 
     * @param vMXXXArray
     */
    public void setMXXX(
            final MXXX[] vMXXXArray) {
        //-- copy array
        _MXXXList.clear();

        for (int i = 0; i < vMXXXArray.length; i++) {
                this._MXXXList.add(vMXXXArray[i]);
        }
    }

}
