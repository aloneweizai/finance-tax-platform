/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.tiripPackage;

/**
 * Class BusinessContentType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class BusinessContentType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _subPackageList.
     */
    private java.util.List<SubPackage> _subPackageList;


      //----------------/
     //- Constructors -/
    //----------------/

    public BusinessContentType() {
        super();
        this._subPackageList = new java.util.ArrayList<SubPackage>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     *
     *
     * @param vSubPackage
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSubPackage(
            final com.abc12366.uc.jrxt.model.tiripPackage.SubPackage vSubPackage)
    throws IndexOutOfBoundsException {
        this._subPackageList.add(vSubPackage);
    }

    /**
     *
     *
     * @param index
     * @param vSubPackage
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSubPackage(
            final int index,
            final com.abc12366.uc.jrxt.model.tiripPackage.SubPackage vSubPackage)
    throws IndexOutOfBoundsException {
        this._subPackageList.add(index, vSubPackage);
    }

    /**
     * Method enumerateSubPackage.
     *
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends SubPackage> enumerateSubPackage(
    ) {
        return java.util.Collections.enumeration(this._subPackageList);
    }

    /**
     * Method getSubPackage.
     *
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.jrxt.model.tiripPackage.SubPackage at the given index
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.SubPackage getSubPackage(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._subPackageList.size()) {
            throw new IndexOutOfBoundsException("getSubPackage: Index value '" + index + "' not in range [0.." + (this._subPackageList.size() - 1) + "]");
        }

        return (com.abc12366.uc.jrxt.model.tiripPackage.SubPackage) _subPackageList.get(index);
    }

    /**
     * Method getSubPackage.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     *
     * @return this collection as an Array
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.SubPackage[] getSubPackage(
    ) {
        com.abc12366.uc.jrxt.model.tiripPackage.SubPackage[] array = new com.abc12366.uc.jrxt.model.tiripPackage.SubPackage[0];
        return (com.abc12366.uc.jrxt.model.tiripPackage.SubPackage[]) this._subPackageList.toArray(array);
    }

    /**
     * Method getSubPackageCount.
     *
     * @return the size of this collection
     */
    public int getSubPackageCount(
    ) {
        return this._subPackageList.size();
    }

    /**
     * Method iterateSubPackage.
     *
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends SubPackage> iterateSubPackage(
    ) {
        return this._subPackageList.iterator();
    }

    /**
     */
    public void removeAllSubPackage(
    ) {
        this._subPackageList.clear();
    }

    /**
     * Method removeSubPackage.
     *
     * @param vSubPackage
     * @return true if the object was removed from the collection.
     */
    public boolean removeSubPackage(
            final com.abc12366.uc.jrxt.model.tiripPackage.SubPackage vSubPackage) {
        boolean removed = _subPackageList.remove(vSubPackage);
        return removed;
    }

    /**
     * Method removeSubPackageAt.
     *
     * @param index
     * @return the element removed from the collection
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.SubPackage removeSubPackageAt(
            final int index) {
        Object obj = this._subPackageList.remove(index);
        return (com.abc12366.uc.jrxt.model.tiripPackage.SubPackage) obj;
    }

    /**
     *
     *
     * @param index
     * @param vSubPackage
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSubPackage(
            final int index,
            final com.abc12366.uc.jrxt.model.tiripPackage.SubPackage vSubPackage)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._subPackageList.size()) {
            throw new IndexOutOfBoundsException("setSubPackage: Index value '" + index + "' not in range [0.." + (this._subPackageList.size() - 1) + "]");
        }

        this._subPackageList.set(index, vSubPackage);
    }

    /**
     *
     *
     * @param vSubPackageArray
     */
    public void setSubPackage(
            final com.abc12366.uc.jrxt.model.tiripPackage.SubPackage[] vSubPackageArray) {
        //-- copy array
        _subPackageList.clear();

        for (int i = 0; i < vSubPackageArray.length; i++) {
                this._subPackageList.add(vSubPackageArray[i]);
        }
    }

}
