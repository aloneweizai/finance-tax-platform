/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.tiripPackage;

/**
 * Class ContentControlType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ContentControlType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _controlList.
     */
    private java.util.List<com.abc12366.uc.jrxt.model.tiripPackage.Control> _controlList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ContentControlType() {
        super();
        this._controlList = new java.util.ArrayList<com.abc12366.uc.jrxt.model.tiripPackage.Control>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vControl
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addControl(
            final com.abc12366.uc.jrxt.model.tiripPackage.Control vControl)
    throws IndexOutOfBoundsException {
        this._controlList.add(vControl);
    }

    /**
     *
     *
     * @param index
     * @param vControl
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addControl(
            final int index,
            final com.abc12366.uc.jrxt.model.tiripPackage.Control vControl)
    throws IndexOutOfBoundsException {
        this._controlList.add(index, vControl);
    }

    /**
     * Method enumerateControl.
     *
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.abc12366.uc.jrxt.model.tiripPackage.Control> enumerateControl(
    ) {
        return java.util.Collections.enumeration(this._controlList);
    }

    /**
     * Method getControl.
     *
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.jrxt.model.tiripPackage.Control at the given index
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.Control getControl(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._controlList.size()) {
            throw new IndexOutOfBoundsException("getControl: Index value '" + index + "' not in range [0.." + (this._controlList.size() - 1) + "]");
        }

        return (com.abc12366.uc.jrxt.model.tiripPackage.Control) _controlList.get(index);
    }

    /**
     * Method getControl.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     *
     * @return this collection as an Array
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.Control[] getControl(
    ) {
        com.abc12366.uc.jrxt.model.tiripPackage.Control[] array = new com.abc12366.uc.jrxt.model.tiripPackage.Control[0];
        return (com.abc12366.uc.jrxt.model.tiripPackage.Control[]) this._controlList.toArray(array);
    }

    /**
     * Method getControlCount.
     *
     * @return the size of this collection
     */
    public int getControlCount(
    ) {
        return this._controlList.size();
    }

    /**
     * Method iterateControl.
     *
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.abc12366.uc.jrxt.model.tiripPackage.Control> iterateControl(
    ) {
        return this._controlList.iterator();
    }

    /**
     */
    public void removeAllControl(
    ) {
        this._controlList.clear();
    }

    /**
     * Method removeControl.
     *
     * @param vControl
     * @return true if the object was removed from the collection.
     */
    public boolean removeControl(
            final com.abc12366.uc.jrxt.model.tiripPackage.Control vControl) {
        boolean removed = _controlList.remove(vControl);
        return removed;
    }

    /**
     * Method removeControlAt.
     *
     * @param index
     * @return the element removed from the collection
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.Control removeControlAt(
            final int index) {
        Object obj = this._controlList.remove(index);
        return (com.abc12366.uc.jrxt.model.tiripPackage.Control) obj;
    }

    /**
     *
     *
     * @param index
     * @param vControl
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setControl(
            final int index,
            final com.abc12366.uc.jrxt.model.tiripPackage.Control vControl)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._controlList.size()) {
            throw new IndexOutOfBoundsException("setControl: Index value '" + index + "' not in range [0.." + (this._controlList.size() - 1) + "]");
        }

        this._controlList.set(index, vControl);
    }

    /**
     * 
     * 
     * @param vControlArray
     */
    public void setControl(
            final com.abc12366.uc.jrxt.model.tiripPackage.Control[] vControlArray) {
        //-- copy array
        _controlList.clear();

        for (int i = 0; i < vControlArray.length; i++) {
                this._controlList.add(vControlArray[i]);
        }
    }

}
