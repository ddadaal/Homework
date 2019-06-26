package viccrubs.appautotesting.models;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import lombok.var;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import viccrubs.appautotesting.config.Config;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.utils.XmlUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.*;

public class Ui implements Logger {

    private @Getter
    final String activityName;

    private @Getter
    String xmlSource;

    private @Getter
    String currentPackage;

    // xml source may change automatically
    // use more stable classname hierarchy as signature
    private @Getter
    String signature = "";

    private @Getter
    Document xmlDocument;

    private @Getter
    List<UiElement> leafElements;

    public List<UiElement> getElementsOfInterest() {
        return leafElements;
    }

    public boolean completed() {
        return leafElements.stream().allMatch(UiElement::isAccessed);
    }

    public static Ui create(AppiumDriver driver) {
        long startTime = System.currentTimeMillis();
        val ui = new Ui(driver.currentActivity(), driver.getPageSource());
//        ui.verbose("UI created in " + (System.currentTimeMillis() - startTime));
        return ui;
    }

    // 亲测初始化一个UI只需要2-4ms, 加上driver.currentActivity和pageSource就得花100-300ms的样子，长的界面可能会上秒
    public Ui(String activityName, String xmlSource) {
//        long startTime = System.currentTimeMillis();

        this.activityName = activityName;
        this.xmlSource = xmlSource;

        // initialize
        initialize();

        // hack: if the first element is a navigate up, push it on the last one
        if (!leafElements.isEmpty() && leafElements.get(0).matchConfigElement(Config.NAVIGATE_UP_ELEMENTS)) {
            val element = leafElements.get(0);
            leafElements.remove(0);
            leafElements.add(element);
        }


        // hack: if the first element is a navigate up, swap it with the last one
//        if (!leafElements.isEmpty() && leafElements.get(0).matchConfigElement(Config.NAVIGATE_UP_ELEMENTS)) {
//            Collections.swap(leafElements, 0, leafElements.size() - 1);
//        }

//        verbose("UI Created in %d", System.currentTimeMillis() - startTime);
    }

    public Optional<UiElement> findConfigElement(Config.Element configElement) {
        return leafElements.stream().filter(x -> x.matchConfigElement(configElement)).findFirst();
    }

    @Override
    public String toString() {
        return String.format("code: %d, activity: %s", hashCode(), activityName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ui)) return false;
        Ui ui = (Ui) o;
        return hashCode == ui.hashCode;
    }

    private int hashCode;

    @Override
    public int hashCode() {
        return hashCode;
    }

    // fill signature and leaf elements
    @SneakyThrows
    private void initialize() {

        // https://stackoverflow.com/a/11264294/2725415
        // https://stackoverflow.com/questions/4237625/removing-invalid-xml-characters-from-a-string-in-java
//        xmlSource = xmlSource
//            .replaceFirst("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>", "<?xml version=\"1.1\" encoding=\"UTF-8\"?>")
//            .replaceAll("[^"
//                + "\u0001-\uD7FF"
//                + "\uE000-\uFFFD"
//                + "\ud800\udc00-\udbff\udfff"
//                + "]+", "");

        xmlSource = XmlUtils.getCleanedXml(xmlSource);

        leafElements = new ArrayList<>();

        val dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();

        xmlDocument = builder.parse(new InputSource(new StringReader(xmlSource)));

        Node root = xmlDocument.getFirstChild().getFirstChild(); // hierarchy is the first child

        var rootElement = new UiElement(new UiHierarchy(), XmlUtils.getNodeTagName(root), 1, this, root);

        // set package
        this.currentPackage = rootElement.getPackage();

        // dfs scan all leaf elements
        initializeRec(root, rootElement, leafElements);

        // calculate hashcode
        hashCode = Objects.hash(getSignature());

    }

    private void initializeRec(Node root, UiElement rootElement, List<UiElement> result) {

        // ignore specified elements
        if (Config.IGNORED_ELEMENTS.stream().anyMatch(rootElement::matchConfigElement)) {
            return;
        }

        // append signature
        signature += rootElement.getTagName() + ";";

        // get all children of element
        NodeList children = root.getChildNodes();



        if (children.getLength() == 0) {
            // is a leaf elements, add to list
            result.add(rootElement);
        } else {
            // is not, continue recurse

            // generate all UIElement
            val childrenUiElements = new ArrayList<UiElement>();
            for (int i = 0; i < children.getLength(); i++) {

                Node child = children.item(i);

                val tagName = XmlUtils.getNodeTagName(child);

                UiElement lastSameElement = null;
                for (int j = childrenUiElements.size() - 1; j >= 0; j--) {
                    if (childrenUiElements.get(j).getTagName().equals(tagName)) {
                        lastSameElement = childrenUiElements.get(j);
                        break;
                    }
                }

                childrenUiElements.add(
                    new UiElement(rootElement.getHierarchy(), tagName,
                        lastSameElement == null ? 1 : lastSameElement.getIndex() + 1,
                        rootElement.getUi(),
                        child
                    ));
            }

            for (int i = 0; i < children.getLength(); i++) {
                initializeRec(children.item(i), childrenUiElements.get(i), result);
            }
        }
    }


}
