package barqsoft.footballscores;

/**
 * Created by Jenny on 12/19/2015.
 */
public class WidgetItem {
    private String text;

    public WidgetItem(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return "WidgetItem{" +
                "text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

}
