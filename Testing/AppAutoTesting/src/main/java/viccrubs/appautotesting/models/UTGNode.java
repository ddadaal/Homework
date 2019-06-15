package viccrubs.appautotesting.models;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Getter;
import lombok.var;
import viccrubs.appautotesting.utils.AppiumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UTGNode {
    private @Getter String xmlSource;
    private @Getter String activityName;
    private @Getter List<AndroidElement> leaves;
    private @Getter List<UTGEdge> outEdges = new ArrayList<>();

    public static UTGNode createCurrent(AndroidDriver<AndroidElement> driver) {
        var node = new UTGNode();
        node.xmlSource = driver.getPageSource();
        node.activityName = driver.currentActivity();
        node.leaves = AppiumUtils.findAllLeafElements(driver);
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UTGNode)) return false;
        UTGNode utgNode = (UTGNode) o;
        return getXmlSource().equals(utgNode.getXmlSource()) &&
            getActivityName().equals(utgNode.getActivityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getXmlSource(), getActivityName());
    }

    public void addOutEdge(UTGNode end, UiEvent event) {
        outEdges.add(new UTGEdge(this, end, event));
    }
}
