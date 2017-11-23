package by.tr.web.domain;

import by.tr.web.controller.util.XMLParameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XMLParameter.DIRECTOR_TYPE, propOrder = {
        XMLParameter.NAME,
        XMLParameter.SURNAME
}, namespace = XMLParameter.NAMESPACE)
public class Director implements Serializable {
    @XmlElement(namespace = XMLParameter.NAMESPACE)
    private String name;
    @XmlElement(namespace = XMLParameter.NAMESPACE)
    private String surname;

    public Director() {
    }

    public Director(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Director director = (Director) o;

        if (!name.equals(director.name)) return false;
        return surname.equals(director.surname);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  name + ' ' + surname;
    }
}
