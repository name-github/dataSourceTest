package com.unistrong.geotsd.datasource.utils;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;


public class ExcelData implements Iterator<Object[]> {
    private Workbook book = null;
    private Sheet sheet = null;
    private int rowNum = 0;//行数
    private int curRowNo = 0;//当前行数
    private int columnNum = 0;//列数
    private String[] columnnName;//列名

    /*在TestNG中，由@DataProvider（dataProvider="name"）修饰的方法读取Exel时，调用此类的构造方法（此方法会得到列名并将当前行移到下一行）执行完后，
    *转到TestNG自己的方法中去，然后由他们调用此类实现的hasNext()、next() 方法；
    *得到一行数据，然后返回给由@Test（dataProvider="name"）修饰的方法，如此反复到数据读完为止。
    * @param filepath Excel文件名
    * @param casename用例名
     */
    public ExcelData(String filepath, String casename) {
        try {
            File directory = new File(".");
            String ss = "DemoController."; //项目resource文件下的文件路径地址
            book = Workbook.getWorkbook(new File(directory.getCanonicalPath()
                    + "\\resources\\"
                    + ss.replaceAll("\\.", Matcher.quoteReplacement("\\"))
                    + filepath + ".xls"));
            this.sheet = book.getSheet(casename);
            this.rowNum = sheet.getRows();

            Cell[] c = sheet.getRow(0);  //获取第一行每一个单位的值存储在数组c中

            this.columnNum = c.length;   //获取sheet中的列数

            columnnName = new String[c.length];
            for (int i = 0; i < c.length; i++) {
                columnnName[i] = c[i].getContents().toString();
            }
            this.curRowNo++;     //当前行向下移动一行
            /*System.out.println(Arrays.toString(columnnName));*/  //可以把columnnName数组中的列名称 正确转换

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
/**
 *方法功能：是否有下一条数据
 *如果行数为0即空sheet或者 当前行数大于总行数
 *就关闭对excel的操作返回false，否则返回true
 */
        if (this.rowNum == 0 || this.curRowNo >= this.rowNum) {
            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else
            return true;
    }

    @Override
    public Object[] next() {
/* 方法功能：得到并返回下一行数据
* 使用for将一行的数据放入TreeMap中（TreeMap默认按照Key值升序排列，HashMap没有排序）
*然后将Map装入Object[]并返回，且将curRowNo当前行下移
*/
        Cell[] c = sheet.getRow(this.curRowNo);   //把每一行的值存储在数组c中
/*     String[] mm = new String[c.length] ;
     for (int i = 0; i < c.length; i++) {
         mm[i] = c[i].getContents().toString();
     }
     System.out.println(Arrays.toString(mm));*/
        Map<String, String> s = new TreeMap<String, String>();
        for (int i = 0; i < this.columnNum; i++) {      //columnNum 列数，columnnName 列名称
            String temp = "";
            try {
                temp = c[i].getContents().toString();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }
            s.put(this.columnnName[i], temp);  //this.columnnName[i] 为map集合的键，temp为对应的值
        }

        Object r[] = new Object[1];
        r[0] = s;
      /*  System.out.println(Arrays.toString(r));*/
        this.curRowNo++;
        return r;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }
}
