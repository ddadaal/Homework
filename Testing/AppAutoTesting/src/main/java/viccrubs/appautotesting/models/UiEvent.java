package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidElement;
import lombok.Data;

import javax.annotation.Nullable;

@Data
public class UiEvent {

    private Type type;
    private @Nullable String inputText;
    private AndroidElement uiElement;

    public enum Type {
        Tap,

    }

}
