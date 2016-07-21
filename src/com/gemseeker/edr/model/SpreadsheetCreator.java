package com.gemseeker.edr.model;

import com.gemseeker.edr.FileLoader;
import com.gemseeker.edr.data.InmateData;
import com.gemseeker.edr.data.TimeAllowanceData;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * TODO: Download jexcelapi
 * @author msufr_000
 */
public class SpreadsheetCreator {
    
    final static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
    final FileLoader fileLoader;
    
    public SpreadsheetCreator(){
        fileLoader = new FileLoader();
    }
    
    public void generateExcelFile(){
        
    }
    
    /**
     * Print the list of all inmates estimated date of release.
     */
    public void printList(){
        try {
            ArrayList<InmateData> inmatesList = fileLoader.getInmatesDataList();
            
            // TODO: Copy saved excel file for the list of inmates and
            // enter the values.
            
            // read spreadsheet
            Workbook workbook = Workbook.getWorkbook(new File("resources/List Sheet.xlxs"));
            
            // create a copy
            WritableWorkbook copy = workbook.createWorkbook(new File("resources/list_sheet_newinstance.xls"));
            
            WritableSheet sheet = copy.getSheet(0);
            
            for(InmateData id : inmatesList){
                int row = 9;
                for(int col = 0; col < 4; col++){
                    WritableCell cell = sheet.getWritableCell(row, col);
                    Label label = (Label)cell;
                    switch(col){
                        case 0:
                            String name = id.getFirstName() + " " + id.getMiddleName() + " " + id.getLastName();
                            label.setString(name);
                            break;
                        case 1:
                            label.setString(dateFormatter.format(id.getCommitted()));
                            break;
                        case 2:
                            StringBuilder sb = new StringBuilder();
                            ArrayList<String> cases = id.getCases();
                            for(String c : cases){
                                sb.append(c);
                                sb.append("\n");
                            }
                            label.setString(sb.toString());
                            break;
                        case 3:
                            String mipStr = id.getMipYears() + " days "
                                    + id.getMipMonths() + " months "
                                    + id.getMipDays() + " days";
                            label.setString(mipStr);
                            break;
                        case 4:
                            label.setString(dateFormatter.format(id.getEdr()));
                            break;
                    }
                }
            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(SpreadsheetCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Print inmate's time allowance information.
     * @param inmateDatas 
     */
    public void printInmateTA(ArrayList<InmateData> inmateDatas){
        
    }
    
}
