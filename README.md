# Z-XML

## 什么是Z-XML？

Z-XML是一个小巧灵活的XML解析工具，简单、高效、实用是本项目的宗旨。
Z-XML可以用来在XML文件和Java对象之间进行互相转换，而不需要去编写复杂
的SCHEMA文件。它支持标准的JavaBean、Java基础类型以及Set和Map的自动转
换。

## 特点

- 超简单的API，一行代码一个函数就可以把你代码里的Java对象转换成XML格；
  式的字符串或直接写到XML文件中，当然反过来操作也是一样的简单；
- 即下即用，无需任何配置（No Schema），无依赖关系；
- 支持数组和哈稀表的直接转换；
- 支持Java基础类型的直接转换；
- 支持对象的set和get方法；
- 支持对象的循环引用；
- 提供可扩展的解析句柄。

## 使用说明及示例

### 下载和安装

- 安装要求
```
  Jre1.6或以上版本
```
- [下载](https://github.com/zlz3907/z-dist/raw/master/z-xml/z-xml-1.0.jar)

### 使用示例

#### 将一个Java对象写到一个XML文件中
```java
  XMLWrite.writeObjectToXmlFile(new Person("Bliss Chung", "3", "f"), "person.xml");
```
#### 读取一个XML文件，并将其转换为Java对象
```java
    try {
      Object obj =
        XMLReader.xmlStreamToObject(new FileInputStream("persion.xml"),
                                    null); // null表示使用默认的解析器
      if (obj instanceof Person) {
        Person person = (Person) obj;
        // TODO: do something
        // ...
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
```
#### 将一个Java对象转换成XML格式的字符串
```java
    String xmlStr = XMLWriter.objectToXmlString(new String[]{ "Hello", "world" });
    System.out.println(xmlA);
```
结果如下：

```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <?xml-stylesheet type="text/xsl" href="style.xsl"?>
    <XMLBean class="[Ljava.lang.String;" >
      <StringArr hashcode="303253937" class="[Ljava.lang.String;" length="2">
        <element class="java.lang.String"><![CDATA[hello]]></element>
        <element class="java.lang.String"><![CDATA[world]]></element>
      </StringArr>
    </XMLBean>
```
#### 将一个描述Java对象的XML字符串转换为Java对象

```java
    Object objB = XMLReader.xmlStringToObject(
          "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
          "<XMLBean class="" >" +
          "<StringArr class=\"[Ljava.lang.String;\" length=\"2\">" +
          "<element class=\"java.lang.String\">" +
          "<![CDATA[hello]]>" +
          "</element>" +
          "<element class=\"java.lang.String\">"
          "<![CDATA[world]]>" +
          "</element></StringArr></XMLBean>");
```
