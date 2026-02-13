<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE helpset PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 2.0//EN" "http://java.sun.com/products/javahelp/helpset_2_0.dtd">
<helpset version="2.0">
    <title>Sistema de Ayuda - InterfazTic</title>
    
    <maps>
        <homeID>login</homeID>
        <mapref location="mapa.jhm"/>
    </maps>
    
    <view>
        <name>TOC</name>
        <label>Tabla de Contenidos</label>
        <type>javax.help.TOCView</type>
        <data>toc.xml</data>
    </view>
    
    <view>
        <name>Index</name>
        <label>Indice</label>
        <type>javax.help.IndexView</type>
        <data>index.xml</data>
    </view>
    
    <view>
        <name>Search</name>
        <label>Buscar</label>
        <type>javax.help.SearchView</type>
        <data engine="com.sun.java.help.search.DefaultSearchEngine">JavaHelpSearch</data>
    </view>
</helpset>
