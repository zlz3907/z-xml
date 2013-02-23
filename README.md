# Z-XML - xml parse engine

Easy-to-use XML and JavaBean conversion tool.

## Contents

- [Features](#a1)
- [Requirements](#a2)
- [Installation](#a3)
- [Example](#a5)
- [License](#a6)

<a name='a1' />
## Features
- Based on SAX ways parse
- Do not rely on any other package
- No need to pre-defined data format
- Support the standard JavaBean
- Support any Java object set and get methods
- Support Java basic type conversion
- Support object circular references

<a name='a2' />
## Requirements
- JRE 1.6 or later

<a name='a3' />
## Installation

Via source build:

```bash
$ git clone git://github.com/zlz3907/z-xml.git
$ cd z-xml
$ ant
```

Direct download：

[click here](https://github.com/zlz3907/z-xml/blob/master/dist/z-xml/lib/z-xml-1.0.jar "Download z-xml!") to download or run:

```bash
$ wget https://github.com/zlz3907/z-xml/blob/master/dist/z-xml/lib/z-xml-1.0.jar
```

copy dist/z-xml/lib/z-xml-[version].jar to your lib folder and add z-xml-[version].jar to your classpath.

<a name='a5' />
## Example
Write java object to XML file:

```java
Person person = new Person("Celia", "3", "m");
// TODO: set more properties
List<Object> list = new ArrayList<Object>();
list.add(1); // int
list.add(true); // boolean
// ... support java base type and map set ...
person.setObjlist(list);
XMLWrite.writeObjectToXmlFile(person, "person.xml");
```

Read XML file to java object:

```java
try {
  Object obj = XMLReader.xmlStreamToObject(new FileInputStream("persion.xml"), new ZHandler());
  if (obj instanceof Person) {
    Person person = (Person) obj;
    // TODO: do something
    
  }
} catch (Exception e) {
  e.printStackTrace();
}
```

Java object convert to XML string and XML string convert to java object:

```java
// object to XML string
Object objA = new Persion("Celia", "3", "m");
String xmlA = XMLWriter.objectToXmlString(objA);
System.out.println(xmlA);
// XML string to object
Object objB = XMLReader.xmlStringToObject(xmlA);
```

<a name='a6' />
## License
