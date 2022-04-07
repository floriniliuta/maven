import com.aspose.pdf.*;
public class app {
    public static void main(String [] args){

        for (int page =2 ; page<=2;page++) {
            com.aspose.pdf.License license = new com.aspose.pdf.License();
            try {
                license.setLicense("Conholdate.Total.Product.Family.lic");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //for(Page page : pdfDocument.getPages()) {
            TableAbsorber absorber = new TableAbsorber();
            absorber.setUseFlowEngine(true);
            Document pdfDocument = new Document("RB Monthly Invoice_1220 .pdf");
            absorber.visit(pdfDocument.getPages().get_Item(page));
            System.out.println("PageNum: "+pdfDocument.getPages().get_Item(page).getNumber());
            System.out.println("The list of tables : "+absorber.getTableList().stream().count());
            for (int idTable = 0; idTable < absorber.getTableList().stream().count(); idTable++) {
                AbsorbedTable table = absorber.getTableList().get(idTable);
                // set index of a table from which you want to extract the data
                // 0 -> Table index
                if (idTable <= 1) {
                    System.out.println("THe row list" + table.getRowList().stream().count());
                    for (int idRow = 0; idRow < table.getRowList().stream().count(); idRow++) {
                        TextFragmentCollection textFragmentCollection = null;
                        AbsorbedRow row = table.getRowList().get(idRow);
                        String txt = "";
                        // set rows from which you want to extract data, in this case first two header
                        // rows
                        // 2 - start and end row condition/Header rows
                        if (idRow >=0 ) {
                            for (int idCell = 0; idCell < row.getCellList().stream().count(); idCell++) {
                                //System.out.println("THe cell list" + row.getCellList().stream().count());
                                AbsorbedCell cell = row.getCellList().get(idCell);

                                textFragmentCollection = cell.getTextFragments();
                                if (cell.getTextFragments().size() > 0) {
                                    for (TextFragment fragment : textFragmentCollection) {

                                        txt = txt.concat(fragment.getText());
                                        txt = txt.concat(",");

                                    }

                                } else {
                                    txt = txt.concat(" ");
                                    txt = txt.concat(",");

                                }

                            }
                            System.out.println("The segmant :" + txt);
                            pdfDocument.save("out.pdf");
                        }
                    }
                }
            }
        }



    }
}
