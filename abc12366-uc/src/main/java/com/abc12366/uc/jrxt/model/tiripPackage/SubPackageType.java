/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.abc12366.uc.jrxt.model.tiripPackage;

/**
 * Class SubPackageType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SubPackageType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ????
     */
    private String _id;

    /**
     * ?????????
     */
    private String _content;

    /**
     * ?????????????????,?????????
     */
    private java.util.List<com.abc12366.uc.jrxt.model.tiripPackage.ParamList> _paramListList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SubPackageType() {
        super();
        this._paramListList = new java.util.ArrayList<com.abc12366.uc.jrxt.model.tiripPackage.ParamList>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     *
     *
     * @param vParamList
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addParamList(
            final com.abc12366.uc.jrxt.model.tiripPackage.ParamList vParamList)
    throws IndexOutOfBoundsException {
        this._paramListList.add(vParamList);
    }

    /**
     *
     *
     * @param index
     * @param vParamList
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addParamList(
            final int index,
            final com.abc12366.uc.jrxt.model.tiripPackage.ParamList vParamList)
    throws IndexOutOfBoundsException {
        this._paramListList.add(index, vParamList);
    }

    /**
     * Method enumerateParamList.
     *
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.abc12366.uc.jrxt.model.tiripPackage.ParamList> enumerateParamList(
    ) {
        return java.util.Collections.enumeration(this._paramListList);
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: ?????????
     *
     * @return the value of field 'Content'.
     */
    public String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'id'. The field 'id' has the
     * following description: ????
     *
     * @return the value of field 'Id'.
     */
    public String getId(
    ) {
        return this._id;
    }

    /**
     * Method getParamList.
     *
     * @param index
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.abc12366.uc.jrxt.model.tiripPackage.ParamList at the given index
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.ParamList getParamList(
            final int index)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._paramListList.size()) {
            throw new IndexOutOfBoundsException("getParamList: Index value '" + index + "' not in range [0.." + (this._paramListList.size() - 1) + "]");
        }

        return (com.abc12366.uc.jrxt.model.tiripPackage.ParamList) _paramListList.get(index);
    }

    /**
     * Method getParamList.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     *
     * @return this collection as an Array
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.ParamList[] getParamList(
    ) {
        com.abc12366.uc.jrxt.model.tiripPackage.ParamList[] array = new com.abc12366.uc.jrxt.model.tiripPackage.ParamList[0];
        return (com.abc12366.uc.jrxt.model.tiripPackage.ParamList[]) this._paramListList.toArray(array);
    }

    /**
     * Method getParamListCount.
     *
     * @return the size of this collection
     */
    public int getParamListCount(
    ) {
        return this._paramListList.size();
    }

    /**
     * Method iterateParamList.
     *
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.abc12366.uc.jrxt.model.tiripPackage.ParamList> iterateParamList(
    ) {
        return this._paramListList.iterator();
    }

    /**
     */
    public void removeAllParamList(
    ) {
        this._paramListList.clear();
    }

    /**
     * Method removeParamList.
     *
     * @param vParamList
     * @return true if the object was removed from the collection.
     */
    public boolean removeParamList(
            final com.abc12366.uc.jrxt.model.tiripPackage.ParamList vParamList) {
        boolean removed = _paramListList.remove(vParamList);
        return removed;
    }

    /**
     * Method removeParamListAt.
     *
     * @param index
     * @return the element removed from the collection
     */
    public com.abc12366.uc.jrxt.model.tiripPackage.ParamList removeParamListAt(
            final int index) {
        Object obj = this._paramListList.remove(index);
        return (com.abc12366.uc.jrxt.model.tiripPackage.ParamList) obj;
    }

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: ?????????
     *
     * @param content the value of field 'content'.
     */
    public void setContent(
            final String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'id'. The field 'id' has the
     * following description: ????
     *
     * @param id the value of field 'id'.
     */
    public void setId(
            final String id) {
        this._id = id;
    }

    /**
     *
     *
     * @param index
     * @param vParamList
     * @throws IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setParamList(
            final int index,
            final com.abc12366.uc.jrxt.model.tiripPackage.ParamList vParamList)
    throws IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._paramListList.size()) {
            throw new IndexOutOfBoundsException("setParamList: Index value '" + index + "' not in range [0.." + (this._paramListList.size() - 1) + "]");
        }

        this._paramListList.set(index, vParamList);
    }

    /**
     * 
     * 
     * @param vParamListArray
     */
    public void setParamList(
            final com.abc12366.uc.jrxt.model.tiripPackage.ParamList[] vParamListArray) {
        //-- copy array
        _paramListList.clear();

        for (int i = 0; i < vParamListArray.length; i++) {
                this._paramListList.add(vParamListArray[i]);
        }
    }

}
