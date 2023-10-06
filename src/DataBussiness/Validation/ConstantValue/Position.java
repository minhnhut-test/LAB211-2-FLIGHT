/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Validation.ConstantValue;

/**
 *
 * @author nhutm
 */
public enum Position {
    PILOT("PILOT"),
    FLIGHT_ATTENDANT("FLIGHT ATTENDANT"),
    GROUND_STAFF("GROUND STAFF");
    private String TYPE;

    private Position(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    
}
