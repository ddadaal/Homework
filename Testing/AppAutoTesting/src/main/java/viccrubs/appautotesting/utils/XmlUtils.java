package viccrubs.appautotesting.utils;


import lombok.experimental.UtilityClass;
import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class XmlUtils {

    public String getNodeTagName(Node node) {
        return node.getNodeName();
    }

    private final Pattern XML_ENTITY_PATTERN = Pattern.compile("&#(?:x([0-9a-fA-F]+)|([0-9]+));");

    /**
     * Remove problematic xml entities from the xml string so that you can parse it with java DOM / SAX libraries.
     */
    public String getCleanedXml(String xmlString) {
        Matcher m = XML_ENTITY_PATTERN.matcher(xmlString);
        Set<String> replaceSet = new HashSet<>();
        while (m.find()) {
            String group = m.group(1);
            int val;
            if (group != null) {
                val = Integer.parseInt(group, 16);
                if (isInvalidXmlChar(val)) {
                    replaceSet.add("&#x" + group + ";");
                }
            } else if ((group = m.group(2)) != null) {
                val = Integer.parseInt(group);
                if (isInvalidXmlChar(val)) {
                    replaceSet.add("&#" + group + ";");
                }
            }
        }
        String cleanedXmlString = xmlString;
        for (String replacer : replaceSet) {
            cleanedXmlString = cleanedXmlString.replaceAll(replacer, "");
        }
        return cleanedXmlString;
    }

    private boolean isInvalidXmlChar(int val) {
        if (val == 0x9 || val == 0xA || val == 0xD ||
            val >= 0x20 && val <= 0xD7FF ||
            val >= 0x10000 && val <= 0x10FFFF) {
            return false;
        }
        return true;
    }
}
