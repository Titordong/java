import org.apache.poi.hssf.usermodel.*;//基础的表格操作
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;


import java.io.*;//屏幕输出和文件输出
import java.util.Calendar;//日历包

public class Main {
    public static String [] DayArray;
    public static String [] TimeArray;
    public static String [][] PerList=new String[6][];
    public static void main(String[] args)throws IOException{
        Main.ReadFile();
        Main.OutputFile();
        for(String sting:DayArray){
            System.out.println(sting);
        }
        for(String string:TimeArray){
            System.out.println(string);
        }
        for(int i=0;i<TimeArray.length;i++){
            for(int j=0;j<DayArray.length;j++){
                if(j!=0)
                    System.out.print(" ");
                System.out.print(PerList[i][j]);
            }
            System.out.println();
        }
    }
    public static void ReadFile()throws IOException{
        InputStream in=new FileInputStream("D:\\排班数据管理\\Basis.txt");
        String line;
        BufferedReader reader=new BufferedReader(new InputStreamReader(in,"GBK"));
        line=reader.readLine();
        DayArray =line.split("\\s+");
        line=reader.readLine();
        TimeArray=line.split("\\s+");
        Calendar calendar=Calendar.getInstance();
        //calendar.set(2018,11,9);
        in=new FileInputStream("D:\\排班数据txt\\"+
                (calendar.get(calendar.MONTH)+1)+"月"+
                calendar.get(calendar.DATE)+ "日数据.txt");
        reader=new BufferedReader(new InputStreamReader(in,"GBK"));
        reader.readLine();
        reader.readLine();
        for(int i=0;i<TimeArray.length+1;i++){
            line=reader.readLine();
            PerList[i]=line.split("\\s+");
        }
        reader.close();
    }
    public static void OutputFile()throws IOException{
        HSSFWorkbook wb=new HSSFWorkbook();

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFSheet sheet=wb.createSheet("值班表");
        int width=13;
        sheet.setColumnWidth(0, 256*width+184);
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell;
        int index=1;
        for(String string:DayArray){
            cell=row.createCell(index);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(string);
            index++;
        }
        for(int i=0;i<TimeArray.length+1;i++){
            row=sheet.createRow(i+1);
            cell=row.createCell(0);
            cell.setCellStyle(cellStyle);
            if(i==5)
                cell.setCellValue("第二关联");
            else cell.setCellValue(TimeArray[i]);
            for(int j=0;j<DayArray.length;j++){
                cell=row.createCell(j+1);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(PerList[i][j]);
            }
        }
        Calendar calendar=Calendar.getInstance();
        FileOutputStream outputStream=new FileOutputStream("D:\\排班表格汇总\\" +
                (calendar.get(Calendar.MONTH)+1)+"月"+
                calendar.get((Calendar.DATE))+"日一周值班表.xls");
        wb.write(outputStream);
        outputStream.close();
    }
}

