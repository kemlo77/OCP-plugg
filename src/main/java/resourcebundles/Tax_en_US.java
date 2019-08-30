package resourcebundles;

import java.util.ListResourceBundle;

public class Tax_en_US extends ListResourceBundle {

  @Override
  protected Object[][] getContents() {
    return new Object[][]{{"hello","HELLO there"},
        {"open","YES, we are OPEN"}};
  }
}
