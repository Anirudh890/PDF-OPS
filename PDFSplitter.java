package mergesplit;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.FileInputStream;

public class merge1 extends JFrame {




	public merge1()
{
JFrame f1=new JFrame();
JLabel l1 = new JLabel("Enter first pdf:");
final JTextField t1 = new JTextField(10);
JLabel l2 = new JLabel("Enter second pdf:");
final JTextField t2 = new JTextField(10);
Container cont = f1.getContentPane();
cont.setLayout(new FlowLayout());
JButton d1 = new JButton("Browse!");
JButton d2 = new JButton("Browse!");
JPanel pnl = new JPanel();
pnl.add(l1);
pnl.add(t1);
pnl.add(d1);
pnl.add(l2);
pnl.add(t2);
pnl.add(d2);
cont.add(pnl);
JButton b1 = new JButton("Click to merge!");
pnl.add(b1);
setLayout(new BorderLayout());
f1.setVisible(true);
f1.setSize(300, 450);
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String pd1= t1.getText();
String pd2= t2.getText();
try {
List<InputStream> pdfs = new ArrayList<InputStream>();
pdfs.add(new FileInputStream(pd1));
pdfs.add(new FileInputStream(pd2));
OutputStream output = new FileOutputStream("/home/6a/Documents/merge.pdf");
merge1.concatPDFs(pdfs, output, true);
} catch (Exception e) {
e.printStackTrace();
}
} });


d1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
JFileChooser fileChooser = new JFileChooser();
fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
fileChooser.setAcceptAllFileFilterUsed(false);
int rVal = fileChooser.showOpenDialog(null);
if (rVal == JFileChooser.APPROVE_OPTION) {
t1.setText(fileChooser.getSelectedFile().toString());
}
} });

d2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
JFileChooser fileChooser = new JFileChooser();
fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
fileChooser.setAcceptAllFileFilterUsed(false);
int rVal = fileChooser.showOpenDialog(null);
if (rVal == JFileChooser.APPROVE_OPTION) {
t2.setText(fileChooser.getSelectedFile().toString());
}
} });}

protected static void concatPDFs(List<InputStream> streamOfPDFFiles,
OutputStream outputStream, boolean paginate) {
// TODO Auto-generated method stub
Document document = new Document();
try {
List<InputStream> pdfs = streamOfPDFFiles;
List<PdfReader> readers = new ArrayList<PdfReader>();
int totalPages = 0;
Iterator<InputStream> iteratorPDFs = pdfs.iterator();
// Create Readers for the pdfs.
while (iteratorPDFs.hasNext()) {
InputStream pdf = iteratorPDFs.next();
PdfReader pdfReader = new PdfReader(pdf);
readers.add(pdfReader);
totalPages += pdfReader.getNumberOfPages();
}
// Create a writer for the outputstream
PdfWriter writer = PdfWriter.getInstance(document, outputStream);
document.open();
BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
// data
PdfImportedPage page;
int currentPageNumber = 0;
int pageOfCurrentReaderPDF = 0;
Iterator<PdfReader> iteratorPDFReader = readers.iterator();
// Loop through the PDF files and add to the output.
while (iteratorPDFReader.hasNext()) {
PdfReader pdfReader = iteratorPDFReader.next();
// Create a new page in the target for each source page.
while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
document.newPage();
pageOfCurrentReaderPDF++;
currentPageNumber++;
page = writer.getImportedPage(pdfReader,
pageOfCurrentReaderPDF);
cb.addTemplate(page, 0, 0);
// Code for pagination.
if (paginate) {
cb.beginText();
cb.setFontAndSize(bf, 9);
cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
+ currentPageNumber + " of " + totalPages, 520,
5, 0);
cb.endText();
}
}
pageOfCurrentReaderPDF = 0;
}
outputStream.flush();
document.close();
outputStream.close();
} catch (Exception e) {
e.printStackTrace();
} finally {
if (document.isOpen())
document.close();
}
}



public static void main(String args[])
{
merge1 d=new merge1();
}
}







package mergesplit;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.FileInputStream;
public class split2 extends JFrame{
public split2()
{
JFrame f1=new JFrame();
JLabel background=new JLabel(new ImageIcon("/home/6a/Downloads/11.jpg"));
JLabel l1 = new JLabel("Enter pdf:");
final JTextField t1 = new JTextField(10);
JLabel l2 = new JLabel("Enter start page: ");
final JTextField t2 = new JTextField(3);
JLabel l3 = new JLabel("Enter final page: ");
final JTextField t3 = new JTextField(3);
JButton b1 = new JButton("Click to split!");
Container cont = f1.getContentPane();
cont.setLayout(new FlowLayout());
JButton d1 = new JButton("Browse!");
JPanel pnl = new JPanel();
f1.add(background);
pnl.add(l1);
pnl.add(t1);
pnl.add(d1);
pnl.add(l2);
pnl.add(t2);
pnl.add(l3);
pnl.add(t3);
pnl.add(b1);
cont.add(pnl);
setLayout(new BorderLayout());
f1.setVisible(true);
f1.setSize(300, 450);
d1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
JFileChooser fileChooser = new JFileChooser();
fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
fileChooser.setAcceptAllFileFilterUsed(false);
int rVal = fileChooser.showOpenDialog(null);
if (rVal == JFileChooser.APPROVE_OPTION) {
t2.setText(fileChooser.getSelectedFile().toString());
}
} });

b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String pd1= t1.getText();
int p1= Integer.parseInt(t2.getText());
int p2= Integer.parseInt(t3.getText());
InputStream inputStream = null;
try {
inputStream = new FileInputStream(pd1);
} catch (FileNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
OutputStream output = null;
try {
output = new FileOutputStream("/home/6a/Documents/splitted.pdf");
} catch (FileNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
split2.splitPDF(inputStream,output,p1,p2);
}});
}

public static void splitPDF(InputStream inputStream,
OutputStream outputStream, int fromPage, int toPage) {
Document document = new Document();
try {
PdfReader inputPDF = new PdfReader(inputStream);
int totalPages = inputPDF.getNumberOfPages();
//make fromPage equals to toPage if it is greater
if(fromPage > toPage ) {
fromPage = toPage;
}
if(toPage > totalPages) {
toPage = totalPages;
}
// Create a writer for the outputstream
PdfWriter writer = PdfWriter.getInstance(document, outputStream);
document.open();
PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
PdfImportedPage page;
while(fromPage <= toPage) {
document.newPage();
page = writer.getImportedPage(inputPDF, fromPage);
cb.addTemplate(page, 0, 0);
fromPage++;
}
outputStream.flush();
document.close();
outputStream.close();
} catch (Exception e) {
e.printStackTrace();
}
}
public static void main(String[] args) {
split2 d=new split2();
}
}