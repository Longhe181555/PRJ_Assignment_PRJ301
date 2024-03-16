/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tagshandler;




import jakarta.servlet.jsp.*;
import jakarta.servlet.jsp.tagext.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class ConvertToVnDateTag extends SimpleTagSupport {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        try {
            DateFormat usaFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
            Date date = usaFormat.parse(value);
            DateFormat vnFormat = new SimpleDateFormat("'Ngay' dd 'Thang' MM 'Nam' yyyy", new Locale("vi", "VN"));
            getJspContext().getOut().write(vnFormat.format(date));
        } catch (ParseException e) {
            throw new JspException("Error parsing date", e);
        }
    }
   
}
