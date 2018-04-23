
/**
 * AcceptServiceCallbackHandler.java
 * <p/>
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package com.abc12366.uc.webservice;

/**
 *  AcceptServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class AcceptServiceCallbackHandler {


    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public AcceptServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public AcceptServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */

    public Object getClientData() {
        return clientData;
    }


    /**
     * auto generated Axis2 call back method for accept method
     * override this method for handling normal response from accept operation
     */
    public void receiveResultaccept(
            com.abc12366.uc.webservice.AcceptServiceStub.AcceptResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from accept operation
     */
    public void receiveErroraccept(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for status method
     * override this method for handling normal response from status operation
     */
    public void receiveResultstatus(
            com.abc12366.uc.webservice.AcceptServiceStub.AcceptResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from status operation
     */
    public void receiveErrorstatus(java.lang.Exception e) {
    }


}
    