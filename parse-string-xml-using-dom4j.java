private static void readXML() {
    try {
        String xmlResult = "<soap-env:Body>
<bam:process xmlns:bam="http://xmlns.oracle.com/bam">
<bam:MessageID>{$messageID}</bam:MessageID>
<bam:Name>CORE</bam:Name>
<bam:Operation>导入</bam:Operation>
<bam:StartTime>{$input/time/text()}</bam:StartTime>
<bam:EndTime>{fn:current-dateTime()}</bam:EndTime>
<bam:Size>{$output/size/text()}</bam:Size>
<bam:State>true</bam:State>
<bam:Describe>{$operation}</bam:Describe>
<bam:InstanceId></bam:InstanceId>
</bam:process>
</soap-env:Body>";
        /* 将xml格式字符串转化为DOM对象 */
        org.dom4j.Document document = DocumentHelper.parseText(xmlResult);
        /* 获取根结点对象 */
        Element rootElement = document.getRootElement();
        /* 循环根节点，获取其子节点 */
        for (Iterator iter = rootElement.elementIterator(); iter.hasNext();) {
            Element element = (Element) iter.next();
            /* 获取标签对象 */
            /* 获取该标签对象的属性 */
            Attribute attr = element.attribute("id");
            if (null != attr) {
                String attrVal = attr.getValue();
                String attrName = attr.getName();
                System.out.println(attrName + ": " + attrVal);
            }
            /* 循环第一层节点，获取其子节点 */
            for (Iterator iterInner = element.elementIterator(); iterInner.hasNext();) {
                /* 获取标签对象 */
                Element elementOption = (Element) iterInner.next();
                /* 获取该标签对象的名称 */
                String tagName = elementOption.getName();
                /* 获取该标签对象的内容 */
                String tagContent = elementOption.getTextTrim();
                /* 输出内容 */
                System.out.print(tagName + ": " + tagContent + "  ");
            }
            System.out.println();
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
}
