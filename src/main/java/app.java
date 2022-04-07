import com.aspose.pdf.*;
import com.aspose.pdf.operators.*;

public class app {
    public static void main(String [] args) {
        License license = new License();
        try {
            license.setLicense("C:/users/pero/desktop/aspose/Conholdate.Total.Product.Family.lic");
        } catch (Exception e) {
            e.printStackTrace();
        }
        var document = new Document("testExtraction (1).pdf");
        var page = document.getPages().get_Item(1);
        var rectangle = new Rectangle(72.024, 381.17000000953675, 181.5959996213913, 393.31399996757506);
        var absorber = new TextFragmentAbsorber();
        var searchValue = "Canada";

        searchValue = java.util.regex.Pattern.quote(searchValue);
        absorber.setPhrase(searchValue);
        absorber.setTextSearchOptions(new TextSearchOptions(rectangle, true));
        page.accept(absorber);

        var textFragments = absorber.getTextFragments();
        for (var textFragment : textFragments) {
            drawRectangleOnPage(page, textFragment.getRectangle(), new SetRGBColorStroke(1, 0, 0), new SetLineWidth(1));
            System.out.println(textFragment.getText());

        }
        document.save("22.3.pdf");
    }


        private static void drawRectangleOnPage (Page page, Rectangle rectangle, SetRGBColorStroke
        colorStroke, SetLineWidth width){
            page.getContents().add(new GSave());
            page.getContents().add(new ConcatenateMatrix(1, 0, 0, 1, 0, 0));
            page.getContents().add(colorStroke);
            page.getContents().add(width);
            page.getContents().add(new Re(rectangle.getLLX(), rectangle.getLLY(), rectangle.getWidth(), rectangle.getHeight()));
            page.getContents().add(new ClosePathStroke());
            page.getContents().add(new GRestore());
        }
    }

