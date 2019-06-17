package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import viccrubs.appautotesting.App;
import viccrubs.appautotesting.utils.AppiumUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ui {
    private @Getter final String activityName;
    private @Getter final String xmlSource;
    private List<UiElement> leafElements;

    public static Ui create(AndroidDriver<AndroidElement> driver) {
        return new Ui(driver.currentActivity(), driver.getPageSource());
    }

    public Ui(String activityName, String xmlSource) {
        this.activityName = activityName;
        this.xmlSource = xmlSource;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ui)) return false;
        Ui ui = (Ui) o;
        return getActivityName().equals(ui.getActivityName()) &&
            getXmlSource().equals(ui.getXmlSource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActivityName(), getXmlSource());
    }

    @SneakyThrows
    public List<UiElement> getLeafElements() {

        if (leafElements != null) {
            return leafElements;
        }

        leafElements = new ArrayList<>();

        DocumentBuilderFactory dbFactory =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));

        Node root = doc.getFirstChild().getFirstChild(); // hierarchy is the first child

        UiElement rootElement = UiElement.create(new ArrayList<>(), this, root.getNodeName(), 1);

        // dfs scan all leaf elements
        findAllLeafElementsRec(root, rootElement, leafElements);

        return leafElements;
    }

    private void findAllLeafElementsRec(Node root, UiElement rootElement, List<UiElement> result) {
        // get all children of element
        NodeList children = root.getChildNodes();

        if (children.getLength() == 0) {
            // is a leaf elements, add to list
            result.add(rootElement);
        } else {
            // is not, continue recurse

            // generate all uielements
            val childrenUiElements = new ArrayList<UiElement>();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                UiElement lastSameElement = null;
                for (int j = childrenUiElements.size() - 1; j >= 0; j--) {
                    if (childrenUiElements.get(j).getTagName().equals(child.getNodeName())) {
                        lastSameElement = childrenUiElements.get(j);
                        break;
                    }
                }

                childrenUiElements.add(
                    UiElement.create(rootElement.getHierarchy(), rootElement.getUi(), child.getNodeName(),
                        lastSameElement == null ? 1 : lastSameElement.getSelfLevel().getIndex() + 1));
            }

            for (int i = 0; i < children.getLength(); i++) {
                findAllLeafElementsRec(children.item(i), childrenUiElements.get(i), result);

            }
        }
    }


}
