package by.tr.web.domain;

import by.tr.web.controller.util.XMLParameter;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = XMLParameter.GENRE_TYPE, namespace = XMLParameter.NAMESPACE)
@XmlEnum
public enum Genre {
    comedy, drama, action, romance, crime, horror, fantasy
}
