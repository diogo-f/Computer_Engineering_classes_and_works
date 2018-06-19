/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula05.ex03_Builder;

/**
 *
 * @author diogo
 */
public interface PersonBuilder {
    public void setLastName();
    public void setFirstName();
    public void setMiddleName();
    public void setSalutation();
    public void setSuffix();
    public void setStreetAddress();
    public void setCity();
    public void setState();
    public void setIsFemale();
    public void setIsEmployed();
    public void setIsHomewOwner();
    public Person getPerson();
}
